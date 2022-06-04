package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Item 객체
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    /**
     * itemOption 과 일대다의 연관관계를 맺고 있다.
     */
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)  // 양방향 걸었음
    private List<ItemOption> itemOptions = new ArrayList<>();

    /**
     * 연관관계 편의 메서드
     */
    public void addItemOption(ItemOption itemOption) {
        itemOptions.add(itemOption);
        itemOption.setItem(this);
    }

    /**
     * 가지고 있는 itemOptions 를 비우고 새로 입력받은 리스트를 넣는다.
     * @param itemOptions   itemOption 리스트
     */
    public void changeOption(List<ItemOption> itemOptions) {
        getItemOptions().clear();
        for (ItemOption itemOption : itemOptions) {
            this.addItemOption(itemOption);
        }
    }

    /**
     * itemOption 리스트를 순회하며 능력치를 합산한다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        for (ItemOption itemOption : getItemOptions()) {
            specDTO.add(itemOption.getOption().getOption(), (double) itemOption.getValue());
        }
    }
}
