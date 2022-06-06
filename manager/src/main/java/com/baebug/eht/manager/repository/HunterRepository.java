package com.baebug.eht.manager.repository;

import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HunterRepository {

    private final EntityManager em;

    /**
     * Hunter 저장, 삭제
     */
    public void save(Hunter hunter) {
        em.persist(hunter);
    }

    public void remove(Hunter hunter) {
        em.remove(hunter);
    }

    /**
     * Hunter 조회
     * 단일, 전체, HunterClass 기준
     */
    public Hunter findById(Long hunterId) {
        return em.find(Hunter.class, hunterId);
    }

    public List<Hunter> findAll() {
        return em.createQuery("select h from Hunter h join fetch h.stat", Hunter.class)
                .getResultList();
    }

    public List<Hunter> findByClass(HunterClass hunterClass) {
        // baseClass 를 받아와서 그걸로 쿼리 작성 (HunterClass 관련 컬럼이 두개여야 하는가?)
        return em.createQuery("select h from Hunter h where h.hunterClass = :class", Hunter.class)
                .setParameter("class", hunterClass)
                .getResultList();
    }
}
