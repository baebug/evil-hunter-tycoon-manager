package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.StatEntity;
import com.baebug.eht.manager.domain.item.Accessory;
import com.baebug.eht.manager.domain.item.Necklace;
import com.baebug.eht.manager.domain.item.Ring;
import com.baebug.eht.manager.domain.item.Weapon;
import com.baebug.eht.manager.repository.HunterRepository;
import com.baebug.eht.manager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class InitService {

    private final EntityManager em;
    private final ItemRepository itemRepository;
    private final HunterRepository hunterRepository;

    @Transactional
    public void postC() throws Exception {
        Hunter hunter = new Hunter("헌터A", Characteristic.CHARISMATIC, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));
        hunterRepository.save(hunter);

//        Weapon weapon = new Weapon();
//        Necklace acc1 = new Necklace();
//        Ring acc2 = new Ring();
//        itemRepository.save(weapon);
//        itemRepository.save(acc1);
//        itemRepository.save(acc2);

    }
}
