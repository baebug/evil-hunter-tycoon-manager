package com.baebug.eht.manager.repository;

import com.baebug.eht.manager.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /**
     * Item 저장, 삭제
     */
    public void save(Item item) {
        em.persist(item);
    }

    public void remove(Item item) {
        em.remove(item);
    }

    /**
     * Item 조회
     * 단일, 전체, Hunter_id 기준
     */
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    /* 양방향 연관관계가 필요. but, 지금 구조가 Hunter 랑 Item 이 직접적인 관계 X
    public List<Item> findByHunter(Long hunterId) {
        return em.createQuery("select i from Item i where i.hunter_id = :hunterId", Item.class)
                .setParameter("hunterId", hunterId)
                .getResultList();
    }
    */
}
