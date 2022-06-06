package com.baebug.eht.manager.controller;

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
        model.addAttribute("classes", HunterClass.values());
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
        hunterDTO.setHunterClass(HunterClass.BERSERKER);
        hunterDTO.setDesc("test desc");

        Long hunter = hunterService.join(hunterDTO);
    }

}
