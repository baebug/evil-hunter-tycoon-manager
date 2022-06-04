package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.SpecDto;
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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)  // 양방향 걸었음
    private List<ItemOption> itemOptions = new ArrayList<>();

    //== 연관관계 편의 메서드 ==//
    public void addItemOption(ItemOption itemOption) {
        itemOptions.add(itemOption);
        itemOption.setItem(this);
    }

    //== 생성 메서드 ==//


    //== 비즈니스 로직 ==//
    public void changeOption(List<ItemOption> itemOptions) {
        getItemOptions().clear();
        for (ItemOption itemOption : itemOptions) {
            this.addItemOption(itemOption);
        }
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        for (ItemOption itemOption : getItemOptions()) {
            specDto.add(itemOption.getOption().getOption(), (double) itemOption.getValue());
        }
    }
}
