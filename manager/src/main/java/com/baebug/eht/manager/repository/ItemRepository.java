package com.baebug.eht.manager.repository;

import com.baebug.eht.manager.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public void remove(Item item) {
        em.remove(item);
    }
}
