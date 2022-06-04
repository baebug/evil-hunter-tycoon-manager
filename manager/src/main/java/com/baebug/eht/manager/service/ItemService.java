package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.item.*;
import com.baebug.eht.manager.domain.dto.ItemDto;
import com.baebug.eht.manager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final InitService initService;

    /**
     * item 추가, 삭제
     */
    @Transactional
    public Long join(ItemDto itemDto) {
        Item item = Accessory.createItem(itemDto.getItemOptions());
        itemRepository.save(item);

        return item.getId();
    }

    @Transactional
    public void update(Long itemId, ItemDto itemDto) {
        Item item = itemRepository.findById(itemId);
        item.changeOption(itemDto.getItemOptions());
    }

    @Transactional
    public void delete(Item item) {
        itemRepository.remove(item);
    }

    /**
     * Item 조회 로직
     * 1개, type (default 필요)
     */
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public void findItems() {
        // 동적 쿼리 작성? default 값 넣으면 동적 아닌데. enum 받아서 dtype 이랑 비교?
    }

    /*@PostConstruct
    public void postC() throws IllegalAccessException {
        initService.postC();
    }*/
}
