package com.baebug.eht.manager.controller;

import com.baebug.eht.manager.domain.dto.*;
import com.baebug.eht.manager.domain.hunter.*;
import com.baebug.eht.manager.service.HunterService;
import com.baebug.eht.manager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("classList1", HunterClassList1.values());
        model.addAttribute("classList2", HunterClassList2.values());
        model.addAttribute("classList3", HunterClassList3.values());
        model.addAttribute("statGrades", StatGrade.values());

        return "hunters/addForm";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute HunterDTO hunterDTO,
                      BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()) {
            return "hunters/addForm";
        }
        hunterService.join(hunterDTO);

        return "redirect:/hunters";
    }

    @GetMapping("/{hunterId}")
    public String hunter(@PathVariable("hunterId") Long hunterId, Model model) throws IllegalAccessException {
        Hunter hunter = hunterService.findHunter(hunterId);
        SpecDTO totalSpec = hunterService.getTotalSpec(hunter);

        model.addAttribute("hunter", hunter);
        model.addAttribute("totalSpec", totalSpec);

        return "hunters/hunter";
    }

    @GetMapping("/{hunterId}/edit")
    public String editForm(@PathVariable long hunterId, Model model) {
        Hunter hunter = hunterService.findHunter(hunterId);
        model.addAttribute("hunter", hunter);

        model.addAttribute("characteristics", Characteristic.values());
        model.addAttribute("classList1", HunterClassList1.values());
        model.addAttribute("classList2", HunterClassList2.values());
        model.addAttribute("classList3", HunterClassList3.values());
        model.addAttribute("statGrades", StatGrade.values());

        return "hunters/editForm";
    }

    @PostMapping("/{hunterId}/edit")
    public String edit(@PathVariable Long hunterId,
                       @Valid @ModelAttribute HunterDTO hunterDTO,
                       BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()) {
            return "hunters/editForm";
        }

        hunterService.update(hunterId, hunterDTO);

        return "redirect:/hunters/{hunterId}";
    }

    @PostMapping("/{hunterId}/delete")
    public String delete(@PathVariable Long hunterId) {
        hunterService.exile(hunterId);

        return "redirect:/hunters";
    }

    @GetMapping("/{hunterId}/tech")
    public String techForm(@PathVariable Long hunterId, Model model) {
        Hunter hunter = hunterService.findHunter(hunterId);
        model.addAttribute("hunter", hunter);
        model.addAttribute("tech", hunter.getTech());

        return "hunters/techForm";
    }

    @PostMapping("/{hunterId}/tech")
    public String tech(@PathVariable Long hunterId,
                       @Valid @ModelAttribute TechDTO techDTO,
                       BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()) {
            return "hunters/techForm";
        }

        hunterService.updateTech(hunterId, techDTO);

        return "redirect:/hunters/{hunterId}";
    }

    @GetMapping("/{hunterId}/equipment")
    public String equipment(@PathVariable Long hunterId, Model model) throws IllegalAccessException {
        Hunter hunter = hunterService.findHunter(hunterId);
        SpecDTO equipmentSpec = hunterService.getEquipmentSpec(hunter);

        model.addAttribute("hunter", hunter);
        model.addAttribute("equipmentSpec", equipmentSpec);

        return "hunters/equipment";
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
        hunterClassDTO.setFirstClass(HunterClassList1.BERSERKER);
        hunterClassDTO.setSecondClass(HunterClassList2.SLAYER);
        hunterClassDTO.setThirdClass(HunterClassList3.BARBARIAN);

        hunterDTO.setStat(statDTO);
        hunterDTO.setHunterClass(hunterClassDTO);

        Long hunter = hunterService.join(hunterDTO);
    }

}
