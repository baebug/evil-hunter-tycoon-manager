package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.item.*;
import com.baebug.eht.manager.domain.dto.ItemDTO;
import com.baebug.eht.manager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * itemDTO 를 바탕으로 해당 Item 객체를 업데이트 한다.
     * @param itemId        대상 장비의 id 값
     * @param itemDTO       업데이트 할 정보
     */
    @Transactional
    public void update(Long itemId, ItemDTO itemDTO) {
        Item item = itemRepository.findById(itemId);
        if (item.getClass() == Weapon.class) {
//            ((Weapon) item).setAtk_spd(itemDto.getAtk_spd());
        }
        item.changeOption(itemDTO);
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
}
