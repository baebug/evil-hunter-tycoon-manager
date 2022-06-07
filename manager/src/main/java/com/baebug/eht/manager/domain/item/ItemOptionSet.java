package com.baebug.eht.manager.domain.item;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ItemOptionSet {

    private ItemOption option1 = new ItemOption();
    private ItemOption option2 = new ItemOption();
    private ItemOption option3 = new ItemOption();
    private ItemOption option4 = new ItemOption();
    private ItemOption option5 = new ItemOption();
    private ItemOption option6 = new ItemOption();
    private ItemOption option7 = new ItemOption();
    private ItemOption option8 = new ItemOption();


}
