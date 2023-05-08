package com.shop.controller;

import com.shop.dto.ItemDto;
import com.shop.service.ItemServiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {

    @Autowired
    private ItemServiceForm itemService;

    @RequestMapping("/ex01")
    public String thymeleafEx01(Model model){
        model.addAttribute("data", "타임리프 예제");
        return "thymeleafEx/thymeleafEx01";
    }

    @RequestMapping("/ex02")
    public String thymeleafExam02(Model model){
        ItemDto dto = new ItemDto();
        dto.setItemDetail("상세설명");
        dto.setItemNm("더미");
        dto.setPrice(10000);
        dto.setRegTime(LocalDateTime.now());
        model.addAttribute("dto",dto);
        return"thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value="/ex03")
    public String thymeleafExam03(Model model){
        List<ItemDto> itemDtoList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm("더미"+i);
            itemDto.setItemDetail("정보"+i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());
            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList",itemDtoList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value="/ex04")
    public String thymeleafExam04(Model model){
        List<ItemDto> itemDtoList = new ArrayList<>();
        for(int i = 1 ; i <= 5 ; i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm("더미"+i);
            itemDto.setItemDetail("정보"+i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());
            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList",itemDtoList);
        return "thymeleafEx/thymeleafEx04";
    }
    @GetMapping(value="/ex05")
    public String thymeleafExam05(Model model) {
        return "thymeleafEx/thymeleafEx05";
    }

    @GetMapping(value="/ex06")
    public String thymeleafExam06(@RequestParam ("param1") String param1,
            @RequestParam ("param2") String param2,  Model  model) {
        model.addAttribute("param1",param1);
        model.addAttribute("param2",param2);
        return "thymeleafEx/thymeleafEx06";
    }

    @GetMapping(value="/ex08")
    public String thymeleafExam08(Model model){
        model.addAttribute("items", itemService.getItemAll());
        return "thymeleafEx/thymeleafEx08";
    }

    @GetMapping(value="/ex09")
    public String thymeleafExam09(Model model, ItemDto itemDto){
        return "thymeleafEx/thymeleafEx09";
    }

    @PostMapping(value="/ex09r")
    public String thymeleafExam09Result(HttpServletRequest req, Model model, ItemDto items){
        itemService.createItem(items);
        model.addAttribute("items",itemService.getItemAll());
        return "thymeleafEx/thymeleafEx09r";
    }


}
