package controller;

import dto.PatientInsertDto;
import dto.PatientViewDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.PatientService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Log4j2
public class PatientController {

@Autowired
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @RequestMapping("/patient")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<PatientViewDto> patientList = patientService.getAll();
        modelAndView.addObject("patientList", patientList);
        modelAndView.setViewName("patientList");
        return modelAndView;
    }


    @RequestMapping(value = "/addPatient", method = GET)
    public ModelAndView redirectToAddPatient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patientInsertDto", new PatientInsertDto());
        modelAndView.setViewName("addPatient");
        return modelAndView;
    }

    @RequestMapping(value = "/patient/{id}", method = GET)
    public ModelAndView getPatient(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        PatientViewDto patientViewDto = patientService.getPatientByID(id);
        modelAndView.addObject("patientList", patientViewDto);
        modelAndView.setViewName("patientList");
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = POST)
    public ModelAndView addPatient(@Valid @ModelAttribute("patientInsertDto")
                                        PatientInsertDto patientInsertDto,
                                BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return redirectToAddPatient();
        } else {
            patientService.addPatients(patientInsertDto);
            modelAndView.addObject("patientList", patientService.getAll());
            modelAndView.setViewName("patientList");
            return modelAndView;
        }
    }

    @GetMapping(value = "/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        PatientViewDto detailPatient= patientService.getPatientByID(id);
        modelAndView.addObject("detailPatient", detailPatient);
//        ShowReview showReview = new ShowReview();
//        modelAndView.addObject("showReview", showReview);
        modelAndView.setViewName("detailPatient");
        return modelAndView;
    }


    @RequestMapping(value = "/edit", method = GET)
    public ModelAndView edit(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        PatientInsertDto patientInsertDto = patientService.editById(id);
        modelAndView.addObject("patientInsertDto", patientInsertDto);
        modelAndView.setViewName("addPatient");
        return modelAndView;
    }


    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        patientService.delete(id);
        modelAndView.addObject("patientList", patientService.getAll());
        modelAndView.setViewName("patientList");
        return modelAndView;
    }





}
