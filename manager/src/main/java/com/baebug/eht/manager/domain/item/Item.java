package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.ItemDTO;
import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Item 객체
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "option1")),
            @AttributeOverride(name = "value", column = @Column(name = "value1"))
    })
    private ItemOption option1 = new ItemOption();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "option2")),
            @AttributeOverride(name = "value", column = @Column(name = "value2"))
    })
    private ItemOption option2 = new ItemOption();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "option3")),
            @AttributeOverride(name = "value", column = @Column(name = "value3"))
    })
    private ItemOption option3 = new ItemOption();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "option4")),
            @AttributeOverride(name = "value", column = @Column(name = "value4"))
    })
    private ItemOption option4 = new ItemOption();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "option5")),
            @AttributeOverride(name = "value", column = @Column(name = "value5"))
    })
    private ItemOption option5 = new ItemOption();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "option6")),
            @AttributeOverride(name = "value", column = @Column(name = "value6"))
    })
    private ItemOption option6 = new ItemOption();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "option7")),
            @AttributeOverride(name = "value", column = @Column(name = "value7"))
    })
    private ItemOption option7 = new ItemOption();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "option", column = @Column(name = "option8")),
            @AttributeOverride(name = "value", column = @Column(name = "value8"))
    })
    private ItemOption option8 = new ItemOption();

    @Transient
    private SpecDTO itemSpec = new SpecDTO();

    /**
     * 아이템 정보를 수정하는 메서드
     */
    public void changeOption(ItemDTO itemDTO) {
        this.option1.setOption(itemDTO.getOption1());
        this.option2.setOption(itemDTO.getOption2());
        this.option3.setOption(itemDTO.getOption3());
        this.option4.setOption(itemDTO.getOption4());
        this.option5.setOption(itemDTO.getOption5());
        this.option6.setOption(itemDTO.getOption6());
        this.option7.setOption(itemDTO.getOption7());
        this.option8.setOption(itemDTO.getOption8());
    }

    /**
     *
     */
    public void setItemSpec() throws IllegalAccessException {
        SpecDTO itemSpec = this.itemSpec;
        itemSpec.clear();
        calculate(itemSpec);
    }


    /**
     * itemOption 을 순회하며 specDTO 에 능력치를 합산한다.
     * @param specDTO       입력받은 DTO
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        if (getOption1().getOption() != null) specDTO.add(getOption1().getOption().getOption(), (double) getOption1().getValue());
        if (getOption2().getOption() != null) specDTO.add(getOption2().getOption().getOption(), (double) getOption2().getValue());
        if (getOption3().getOption() != null) specDTO.add(getOption3().getOption().getOption(), (double) getOption3().getValue());
        if (getOption4().getOption() != null) specDTO.add(getOption4().getOption().getOption(), (double) getOption4().getValue());
        if (getOption5().getOption() != null) specDTO.add(getOption5().getOption().getOption(), (double) getOption5().getValue());
        if (getOption6().getOption() != null) specDTO.add(getOption6().getOption().getOption(), (double) getOption6().getValue());
        if (getOption7().getOption() != null) specDTO.add(getOption7().getOption().getOption(), (double) getOption7().getValue());
        if (getOption8().getOption() != null) specDTO.add(getOption8().getOption().getOption(), (double) getOption8().getValue());
    }
}
