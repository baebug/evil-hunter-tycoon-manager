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
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)  // 양방향 걸었음
    private List<ItemOptionEntity> itemOptions = new ArrayList<>();

    /* 룬도 그냥 하나의 옵션으로 취급
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "rune_option")),
            @AttributeOverride(name = "value", column = @Column(name = "rune_value"))
    })
    private ItemOption rune;
    */

    //== 연관관계 편의 메서드 ==//
    public void addItemOption(ItemOptionEntity itemOptionEntity) {
        itemOptions.add(itemOptionEntity);
        itemOptionEntity.setItem(this);
    }

    //== 생성 메서드 ==//
    /*
    public static Item createItem(ItemOptionEntity... itemOptions) {
        Item item = new Item();
        item.setRune(rune);
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
    */

    //== 비즈니스 로직 ==//

}
