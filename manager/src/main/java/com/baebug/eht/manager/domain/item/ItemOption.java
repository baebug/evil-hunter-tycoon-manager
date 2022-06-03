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
public class ItemOption {

    @Id @GeneratedValue
    @Column(name = "item_option_id")
    private Long id;

    private OptionList option;
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemOption(OptionList option, Integer value) {
        this.option = option;
        this.value = value;
    }

}
