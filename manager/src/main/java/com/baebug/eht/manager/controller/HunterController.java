package com.baebug.eht.manager.controller;

import com.baebug.eht.manager.domain.buff.CommonBuff;
import com.baebug.eht.manager.domain.dto.*;
import com.baebug.eht.manager.domain.hunter.*;
import com.baebug.eht.manager.domain.item.Item;
import com.baebug.eht.manager.domain.item.OptionList;
import com.baebug.eht.manager.domain.item.Weapon;
import com.baebug.eht.manager.service.HunterService;
import com.baebug.eht.manager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public String addHunterForm(Model model) {
        model.addAttribute("hunter", new HunterDTO());

        model.addAttribute("characteristics", Characteristic.values());
        model.addAttribute("classList1", HunterClassList1.values());
        model.addAttribute("classList2", HunterClassList2.values());
        model.addAttribute("classList3", HunterClassList3.values());
        model.addAttribute("statGrades", StatGrade.values());

        return "hunters/addHunterForm";
    }

    @PostMapping("/add")
    public String addHunter(@Valid @ModelAttribute HunterDTO hunterDTO,
                            BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()) {
            return "hunters/addHunterForm";
        }
        hunterService.join(hunterDTO);

        return "redirect:/hunters";
    }

    @GetMapping("/{hunterId}")
    public String hunter(@PathVariable("hunterId") Long hunterId, Model model) throws IllegalAccessException {
        Hunter hunter = hunterService.findHunter(hunterId);
        Map<String, SpecDTO> specMap = hunterService.getSpecMap(hunter);
        List<Double> atkSpd = hunterService.getAtkSpd(hunter);

        model.addAttribute("hunter", hunter);
        model.addAttribute("specMap", specMap);
        model.addAttribute("atkSpd", atkSpd);

        return "hunters/hunter";
    }

    @GetMapping("/{hunterId}/edit")
    public String editHunterForm(@PathVariable long hunterId, Model model) {
        Hunter hunter = hunterService.findHunter(hunterId);
        model.addAttribute("hunter", hunter);

        model.addAttribute("characteristics", Characteristic.values());
        model.addAttribute("classList1", HunterClassList1.values());
        model.addAttribute("classList2", HunterClassList2.values());
        model.addAttribute("classList3", HunterClassList3.values());
        model.addAttribute("statGrades", StatGrade.values());

        return "hunters/editHunterForm";
    }

    @PostMapping("/{hunterId}/edit")
    public String editHunter(@PathVariable Long hunterId,
                             @Valid @ModelAttribute HunterDTO hunterDTO,
                             BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()) {
            return "hunters/editHunterForm";
        }

        hunterService.update(hunterId, hunterDTO);

        return "redirect:/hunters/{hunterId}";
    }

    @PostMapping("/{hunterId}/delete")
    public String deleteHunter(@PathVariable Long hunterId) {
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
                       BindingResult bindingResult,
                       Model model) throws IllegalAccessException {
        if (bindingResult.hasErrors()) {

            Hunter hunter = hunterService.findHunter(hunterId);
            model.addAttribute("hunter", hunter);
            model.addAttribute("tech", hunter.getTech());

            return "hunters/techForm";
        }

        hunterService.updateTech(hunterId, techDTO);

        return "redirect:/hunters/{hunterId}";
    }

    @GetMapping("/{hunterId}/equipments")
    public String equipment(@PathVariable Long hunterId, Model model) throws IllegalAccessException {
        Hunter hunter = hunterService.findHunter(hunterId);
        Map<String, SpecDTO> equipmentSpec = hunterService.getEquipmentSpec(hunter);
        List<Double> atkSpd = hunterService.getAtkSpd(hunter);

        model.addAttribute("hunter", hunter);
        model.addAttribute("equipmentSpec", equipmentSpec);
        model.addAttribute("atkSpd", atkSpd);

        return "equipments/equipment";
    }

    @GetMapping("/{hunterId}/equipments/{itemId}")
    public String editEquipmentForm(@PathVariable Long hunterId,
                                    @PathVariable Long itemId,
                                    Model model) throws IllegalAccessException {
        Hunter hunter = hunterService.findHunter(hunterId);
        Item item = itemService.findItem(itemId);

        if (item.getClass() == Weapon.class) {
            model.addAttribute("isWeapon", true);
        }

        model.addAttribute("hunter", hunter);
        model.addAttribute("item", item);
        model.addAttribute("itemOptionList", OptionList.values());

        return "equipments/editEquipmentForm";
    }

    @PostMapping("/{hunterId}/equipments/{itemId}")
    public String editEquipment(@PathVariable Long hunterId,
                                @PathVariable Long itemId,
                                @Valid @ModelAttribute ItemDTO itemDTO,
                                BindingResult bindingResult,
                                Model model) throws IllegalAccessException {

        if (bindingResult.hasErrors()) {
            Hunter hunter = hunterService.findHunter(hunterId);
            Item item = itemService.findItem(itemId);

            if (item.getClass() == Weapon.class) {
                model.addAttribute("isWeapon", true);
            }

            model.addAttribute("hunter", hunter);
            model.addAttribute("item", item);
            model.addAttribute("itemOptionList", OptionList.values());

            log.info("{}", bindingResult.getAllErrors());

            return "equipments/editEquipmentForm";
        }

        itemService.update(itemId, itemDTO);

        return "redirect:/hunters/{hunterId}/equipments";
    }

    @GetMapping("/buffs")
    public String editBuffForm(Model model) {
        CommonBuff commonBuff = hunterService.getCommonBuff();
        model.addAttribute("commonBuff", commonBuff);

        return "hunters/buffs";
    }

    @PostMapping("/buffs")
    public String editBuff(@Valid @ModelAttribute CommonBuffDTO commonBuffDTO,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("commonBuff", commonBuffDTO);
            return "hunters/buffs";
        }

        hunterService.setCommonBuff(commonBuffDTO);

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
        hunterClassDTO.setFirstClass(HunterClassList1.BERSERKER);
        hunterClassDTO.setSecondClass(HunterClassList2.SLAYER);
        hunterClassDTO.setThirdClass(HunterClassList3.BARBARIAN);

        hunterDTO.setStat(statDTO);
        hunterDTO.setHunterClass(hunterClassDTO);

        Long hunter = hunterService.join(hunterDTO);
    }

}
