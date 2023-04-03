package com.itemservice.study3.repository.item;

import com.itemservice.study3.domain.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void init() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);

        Item save = itemRepository.save(item);

        Item findItem = itemRepository.findById(save.getId());

        assertThat(findItem).isEqualTo(save);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> all = itemRepository.findAll();

        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1, item2);
    }

    @Test
    void update() {
        Item item = new Item("item1", 10000, 10);

        Item save = itemRepository.save(item);
        Long id = save.getId();

        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(id, updateParam);

        Item find = itemRepository.findById(id);

        assertThat(find.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(find.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(find.getQuantity()).isEqualTo(updateParam.getQuantity());


    }

}