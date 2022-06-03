package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tech {

    @Id @GeneratedValue
    @Column(name = "tech_id")
    private Long id;

    private TechList option;
    private int value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hunter_id")
    private Hunter hunter;

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    public Tech(TechList option, int value) {
        this.option = option;
        this.value = value;
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        specDto.add(option.getOption(), option.getWeight() * value);
    }

}
