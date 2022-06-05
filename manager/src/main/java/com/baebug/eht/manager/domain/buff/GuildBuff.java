package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 연합 버프 클래스
 */
@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuildBuff {

    /**
     * 연합 버프로 적용되는 버프 옵션
     * double type (int type 으로 해도 상관없지만, casing 의 편의를 위해 double 을 사용하였다.)
     */
    private double atk;
    private double def;
    private double hp;
    private double spd;
    private double crit;

    /**
     * 연합 버프를 입력받는 메서드
     */
    public void setGuildBuff(double atk, double def, double hp, double spd, double crit) {
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.spd = spd;
        this.crit = crit;
    }

    /**
     * 클래스의 필드를 순회하며 입력받은 옵션을 능력치로 합산한다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            specDTO.add(field.getName(), (double) field.get(this));
        }
    }

}
