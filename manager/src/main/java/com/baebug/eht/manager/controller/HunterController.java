package com.baebug.eht.manager.controller;

import com.baebug.eht.manager.domain.dto.HunterClassDTO;
import com.baebug.eht.manager.domain.dto.HunterDTO;
import com.baebug.eht.manager.domain.dto.StatDTO;
import com.baebug.eht.manager.domain.hunter.*;
import com.baebug.eht.manager.service.HunterService;
import com.baebug.eht.manager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
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

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("hunter", new HunterDTO());

        model.addAttribute("characteristics", Characteristic.values());
        model.addAttribute("classLists1", HunterClassList1.values());
        model.addAttribute("classLists2", HunterClassList2.values());
        model.addAttribute("classLists3", HunterClassList3.values());
        model.addAttribute("statGrades", StatGrade.values());
        return "hunters/addForm";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute HunterDTO hunterDTO,
                      BindingResult bindingResult) throws IllegalAccessException {

        if (bindingResult.hasErrors()) {
            return "hunters/addForm";
        }
        Long savedId = hunterService.join(hunterDTO);
        return "redirect:/hunters";
    }

    @PostConstruct
    public void init() throws IllegalAccessException {
        HunterDTO hunterDTO = new HunterDTO();
        hunterDTO.setName("테스트");
        hunterDTO.setCharacteristic(Characteristic.STRONG);
        hunterDTO.setDesc("test desc");

        StatDTO statDTO = new StatDTO();
        statDTO.setAtk(3);
        statDTO.setDef(3);
        statDTO.setCrit(3);
        statDTO.setSpd(3);
        statDTO.setEvasion(3);
        statDTO.setHp(3);
        statDTO.setSatiety(3);
        statDTO.setMood(3);
        statDTO.setStamina(3);

        HunterClassDTO hunterClassDTO = new HunterClassDTO();
        hunterClassDTO.setFirst(HunterClassList1.BERSERKER);
        hunterClassDTO.setSecond(HunterClassList2.SLAYER);
        hunterClassDTO.setThird(HunterClassList3.BARBARIAN);

        hunterDTO.setStat(statDTO);
        hunterDTO.setHunterClass(hunterClassDTO);

        Long hunter = hunterService.join(hunterDTO);
    }

}
