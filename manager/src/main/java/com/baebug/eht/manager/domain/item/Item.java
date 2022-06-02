package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.hunter.Hunter;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hunter_id")
    private Hunter hunter;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)  // 양방향 걸었음
    private List<ItemOptionEntity> itemOptions = new ArrayList<>();

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    //== 연관관계 편의 메서드 ==//
    public void addItemOption(ItemOptionEntity itemOptionEntity) {
        itemOptions.add(itemOptionEntity);
        itemOptionEntity.setItem(this);
    }

    //== 생성 메서드 ==//
    public static Item createItem(List<ItemOptionEntity> itemOptions) {
        Item item = new Item();
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }

    //== 비즈니스 로직 ==//
    public void changeOption(List<ItemOptionEntity> itemOptions) {
        getItemOptions().clear();
        for (ItemOptionEntity itemOption : itemOptions) {
            this.addItemOption(itemOption);
        }
    }

}
