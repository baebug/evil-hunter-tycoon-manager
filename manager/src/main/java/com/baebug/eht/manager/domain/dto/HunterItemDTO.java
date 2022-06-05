package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.item.*;
import lombok.Data;

@Data
public class HunterItemDTO {
    private Weapon weapon;
    private Helmet helmet;
    private Armor armor;
    private Glove glove;
    private Shoes shoes;
    private Necklace necklace;
    private Ring ring;
    private Belt belt;

    /**
     * 헌터의 Equipment 클래스를 받아 Item 의 id 값을 넘겨주는 메서드
     */
    public void setEquipment(Equipment equipment) {
        this.weapon = equipment.getWeapon();
        this.helmet = equipment.getHelmet();
        this.armor = equipment.getArmor();
        this.glove = equipment.getGlove();
        this.shoes = equipment.getShoes();
        this.necklace = equipment.getNecklace();
        this.ring = equipment.getRing();
        this.belt = equipment.getBelt();
    }
}
