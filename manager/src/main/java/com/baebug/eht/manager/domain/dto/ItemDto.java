package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.item.ItemOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDto {

    private Double atk_spd;
    private List<ItemOption> itemOptions = new ArrayList<>();

}
