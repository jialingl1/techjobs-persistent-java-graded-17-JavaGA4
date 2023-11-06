package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.launchcode.techjobs.persistent.models.dto.JobSkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        //model.addAttribute("skills", new JobSkillDTO());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam (required = false) int employerId,
                                    @RequestParam (required = false) List<Integer> skills) {

        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        } else {
            Optional<Employer> result = employerRepository.findById(employerId); //"optional" is used as a required container to store results of findbyid.
            if (!(result.isEmpty())) {
                newJob.setEmployer(result.get()); // used setter from job class to link employer id results search to the job class
                List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
                if (!(skillObjs.isEmpty())) {
                    newJob.setSkills(skillObjs);
                }
            }
        }

//        Optional<Employer> result = employerRepository.findById(employerId); //"optional" is used as a required container to store results of findbyid.
//        if (result.isEmpty()) {
//            //model.addAttribute("title", "Invalid Employer ID: " + employerId);
//            return "add";
//        } else {
//            newJob.setEmployer(result.get()); // used setter from job class to link employer id results search to the job class
//
//        }
//
//        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//        if (skillObjs.isEmpty()) {
//            return "add";
//        } else {
//            newJob.setSkills(skillObjs);
//        }



        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
