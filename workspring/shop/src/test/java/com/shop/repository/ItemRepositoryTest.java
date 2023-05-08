package com.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest{
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EntityManager em;
    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("메이플빵");
        item.setPrice(1500);
        item.setItemDetail("웡키스티커 모으기용");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem);

    }

    public void createItemList(){
        for(int i=1;i<=10;i++){
            Item item = new Item();
            item.setItemNm("테스트 상품"+i);
            item.setPrice(1000+i);
            item.setItemDetail("테스트 상품입니다.설명"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명,상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemNmOrItemDetail("테스트 상품1" ,"테스트 상품입니다.설명5");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
    @Test
    @DisplayName("가격 내림차순 LessThan 테스트")
    public void findByPriceLessThanOrderByPriceDescTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByPriceLessThanOrderByPriceDesc(1005);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("Query 를 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemDetail("1");
        for(Item item : itemList){
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("Query Native")
    public void findByItemDetailByNativeTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemDetailByNative("1");
        for(Item item : itemList){
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트")
    public void queryDSLTest(){
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"테스트 상품 상세 설명"+"%"));

        List<Item> itemList = query.fetch();
        for(Item item : itemList){
            System.out.println(item);
        }
    }

    public void createItemList2(){
        for(int i=1;i<=10;i++){
            Item item = new Item();
            item.setItemNm("테스트 상품"+i);
            item.setPrice(1000+i);
            item.setItemDetail("테스트 상품 상세 설명"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }for(int i=6;i<=10;i++){
            Item item = new Item();
            item.setItemNm("테스트 상품"+i);
            item.setPrice(2000+i);
            item.setItemDetail("테스트 상품 상세 설명"+i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트2")
    public void queryDslTest2(){
        this.createItemList2();
        QItem item = QItem.item;
        String itemDetail = "테스트 상품 상세 설명";
        int price=1004;
        String itemSellStatus = "SELL";
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
//        booleanBuilder.and(item.price.gt(price));
//        System.out.println(itemSellStatus);
//        if(StringUtils.equals(itemSellStatus,ItemSellStatus.SELL)){
//            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
//        }
        Pageable pageable = PageRequest.of(1,5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder,pageable);
        System.out.println("total elements:" +itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem : resultItemList){
            System.out.println(resultItem);
        }
    }

}