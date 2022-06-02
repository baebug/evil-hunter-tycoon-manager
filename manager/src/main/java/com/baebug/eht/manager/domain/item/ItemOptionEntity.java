package com.baebug.eht.manager.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item_option")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOptionEntity {

    @Id @GeneratedValue
    @Column(name = "item_option_id")
    private Long id;

    @Embedded
    private ItemOption itemOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemOptionEntity(ItemOption itemOption) {
        this.itemOption = itemOption;
    }

    //== 생성 메서드 ==//
    public static ItemOptionEntity createItemOption(ItemOption itemOption) {
        return new ItemOptionEntity(itemOption);
    }

}
