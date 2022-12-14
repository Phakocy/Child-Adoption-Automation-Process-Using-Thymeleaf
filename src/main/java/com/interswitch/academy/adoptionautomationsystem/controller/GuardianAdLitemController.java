package com.interswitch.academy.adoptionautomationsystem.controller;

import com.interswitch.academy.adoptionautomationsystem.dto.ChildrenDto;
import com.interswitch.academy.adoptionautomationsystem.dto.GuardianAdLitemDto;
import com.interswitch.academy.adoptionautomationsystem.entities.Children;
import com.interswitch.academy.adoptionautomationsystem.service.ChildrenService;
import com.interswitch.academy.adoptionautomationsystem.service.GuardianAdLitemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class GuardianAdLitemController {

    private ChildrenService childrenService;
    private GuardianAdLitemService guardianService;


    public GuardianAdLitemController(ChildrenService childrenService, GuardianAdLitemService guardianService) {
        this.childrenService = childrenService;
        this.guardianService = guardianService;
    }

    @GetMapping("/admin/guardians")
    public String getAllGuardianAdLitem(Model model){

        List<GuardianAdLitemDto> guardians = guardianService.findAllGuardians();
        model.addAttribute("allGuardians", guardians);
        return "admin/guardians";
    }

    @GetMapping("admin/guardians/newguardian")
    public String newStaffForm(Model model) {

        GuardianAdLitemDto staffDto = new GuardianAdLitemDto();
        model.addAttribute("create_guardian", staffDto);
        return "admin/create-guardian";
    }

    @PostMapping("admin/guardians")
    public String createStaff(@Valid @ModelAttribute("create_guardian") GuardianAdLitemDto guardianAdLitemDto, BindingResult result, Model model) {

        log.info("Dto is:  {}", guardianAdLitemDto);
        if(result.hasErrors()){
            model.addAttribute("create_guardian", guardianAdLitemDto);
            return "admin/create-guardian";
        }
        guardianService.addStaff(guardianAdLitemDto);
        return "redirect:/admin/guardians";

    }

    // handler method to handle edit post request
    @GetMapping("/admin/guardians/{guardianId}/edit")
    public String editGuardianForm(@PathVariable("guardianId") String guardianId,
                               Model model){

        GuardianAdLitemDto guardianDto = guardianService.findGuardianById(guardianId);
        model.addAttribute("guardian", guardianDto);
        return "admin/edit_guardian";
    }

    // handler method to handle edit post form submit request
    @PostMapping("/admin/guardians/{guardianId}")
    public String updateGuardian(@PathVariable("guardianId") String guardianId,
                             @Valid @ModelAttribute("guardian") GuardianAdLitemDto guardian,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("guardian", guardian);
            return "admin/edit_guardian";
        }

        guardian.setId(guardianId);
       guardianService.updateGuardian(guardian);
        return "redirect:/admin/guardians";
    }

    // handler method to handle delete Guardian Ad Litem
    @GetMapping("/admin/guardians/{guardianId}/delete")
    public String deleteGuardian(@PathVariable("guardianId") String guardianId){
        this.guardianService.deleteGuardian(guardianId);
        return "redirect:/admin/guardians";
    }

    // handler method to handle view Guardian
    @GetMapping("/admin/guardians/{guardianId}/view")
    public String viewGuardianAdLitem(@PathVariable("guardianId") String guardianId,
                               Model model){
        GuardianAdLitemDto guardianDto = guardianService.findGuardianById(guardianId);
        model.addAttribute("guardian", guardianDto);
        return "admin/view_guardian";

    }

    // handler method to handle search guardians
    // localhost:${Port:0}/admin/guardians/search?text=java
    @GetMapping("/admin/guardians/search")
    public String searchForms(@RequestParam(value = "text") String text,
                              Model model){
        List<GuardianAdLitemDto> guardian = guardianService.searchGuardian(text);
        model.addAttribute("allGuardians", guardian);
        return "admin/guardians";
    }

    @GetMapping("/admin/guardian/{guardianId}/viewchidlren")
    public String viewChildrenByGuardianId(@PathVariable("guardianId") String guardianId,
                                Model model){
        List<ChildrenDto> children = childrenService.findChildByGuardianId(guardianId);
        model.addAttribute("allChildren", children);
        return "admin/children";
}
}
