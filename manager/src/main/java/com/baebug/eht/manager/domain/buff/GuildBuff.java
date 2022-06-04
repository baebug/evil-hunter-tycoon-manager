package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuildBuff {

    private int atk;
    private int def;
    private int hp;
    private int spd;
    private int crit;

    public void setGuildBuff(int atk, int def, int hp, int spd, int crit) {
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.spd = spd;
        this.crit = crit;
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Double d = Double.valueOf(String.valueOf(field.get(this)));
            specDto.add(field.getName(), d);
        }
    }

}
