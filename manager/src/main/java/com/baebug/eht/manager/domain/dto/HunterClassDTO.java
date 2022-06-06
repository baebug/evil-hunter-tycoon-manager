package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.hunter.HunterClassList1;
import com.baebug.eht.manager.domain.hunter.HunterClassList2;
import com.baebug.eht.manager.domain.hunter.HunterClassList3;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HunterClassDTO {

    @NotNull
    private HunterClassList1 firstClass;
    private HunterClassList2 secondClass;
    private HunterClassList3 thirdClass;

}
