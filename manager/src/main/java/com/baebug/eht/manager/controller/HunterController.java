package com.baebug.eht.manager.controller;

import com.baebug.eht.manager.domain.dto.HunterDTO;
import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.StatEntity;
import com.baebug.eht.manager.service.HunterService;
import com.baebug.eht.manager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hunters")
public class HunterController {

    private final HunterService hunterService;
    private final ItemService itemService;

    @GetMapping
    public String hunters(Model model) {
        List<Hunter> hunters = hunterService.findHunters();
        model.addAttribute("hunters", hunters);
        return "hunters/hunterList";
    }

    @PostConstruct
    public void init() throws IllegalAccessException {
        HunterDTO hunter1 = new HunterDTO("test1", Characteristic.CHARISMATIC, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0), "test");
        HunterDTO hunter2 = new HunterDTO("test2", Characteristic.SWIFT, HunterClass.SORCERER, new StatEntity(2, 2, 2, 2, 2, 2, 2, 2, 2), "테스트2");
        HunterDTO hunter3 = new HunterDTO("test3", Characteristic.OTHERS, HunterClass.RANGER, new StatEntity(1, 1, 1, 1, 1, 1, 1, 1, 1), "메모메모");

        hunterService.join(hunter1);
        hunterService.join(hunter2);
        hunterService.join(hunter3);
    }

}
