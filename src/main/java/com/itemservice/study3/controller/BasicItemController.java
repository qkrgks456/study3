package com.itemservice.study3.controller;

import com.itemservice.study3.domain.item.Item;
import com.itemservice.study3.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "/basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam Integer price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "/basic/item";
    }

    // @PostMapping("/add") // "item" 이라는 이름으로 모델에 담아준다
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        itemRepository.save(item);

        // model.addAttribute("item", item); // 생략가능

        return "/basic/item";
    }

    // @PostMapping("/add") // name 생략시 클래스 이름 맨앞문자 소문자로 바꿔서 ex. Item -> item
    public String addItemV3(@ModelAttribute Item item) {

        itemRepository.save(item);

        return "/basic/item";
    }

    @PostMapping("/add") // 생략 가능
    public String addItemV4(Item item) {

        itemRepository.save(item);

        return "/basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {

        return "/basic/editForm";
    }


    // 테스트용 데이터 추가
    @PostConstruct
    public void init() {
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
    }


}
