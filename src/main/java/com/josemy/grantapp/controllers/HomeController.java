package com.josemy.grantapp.controllers;

import com.josemy.grantapp.models.Course;
import com.josemy.grantapp.models.Student;
import com.josemy.grantapp.repositories.CourseRepository;
import com.josemy.grantapp.repositories.StudentRepository;
import com.josemy.grantapp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {


  @Autowired
  CourseRepository courseRepository;


  @Autowired
  StudentRepository studentRepository;

  @Autowired
  EmailService emailService;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("courses", courseRepository.findAll());
    model.addAttribute("students", studentRepository.findAll());
    return "index";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }


  // ========================= Course ==================================

  @GetMapping("/add")
  public String courseForm(Model model) {
    model.addAttribute("course", new Course());
    return "courseform";
  }

  @PostMapping("/process")
  public String processForm(@Valid Course course, BindingResult result) {
    if (result.hasErrors()) {
      return "courseform";
    }
    courseRepository.save(course);
    return "redirect:/";
  }

  @RequestMapping("/detail/{id}")
  public String showCourse(@PathVariable("id") long id, Model model) {
    model.addAttribute("course", courseRepository.findById(id).get());
    return "showcourse";
  }

  @RequestMapping("/update/{id}")
  public String updateCourse(@PathVariable("id") long id, Model model) {
    model.addAttribute("course", courseRepository.findById(id).get());
    return "courseform";
  }

  @RequestMapping("/delete/{id}")
  public String delCourse(@PathVariable("id") long id) {
    courseRepository.deleteById(id);
    return "redirect:/";
  }


  // =========================STUDENT==================================

  @RequestMapping("/applications")
  public String application(Model model) {
    model.addAttribute("students", studentRepository.findAll());
    return "applications";
  }

  @GetMapping("/studentForm")
  public String getStudentForm(Model model) {
    model.addAttribute("student", new Student());
    model.addAttribute("courses", courseRepository.findAll());
    return "studentform";
  }

  @PostMapping("/studentForm")
  public String processStudentForm(@Valid @ModelAttribute Student student,
                                   BindingResult result) {
    if (result.hasErrors()) {
      return "studentform";
    }
    studentRepository.save(student);
    return "redirect:/applications";
  }

  @RequestMapping("/studentdetail/{id}")
  public String showApplication(@PathVariable("id") long id, Model model) {
    model.addAttribute("student", studentRepository.findById(id).get());
    return "viewapplication";
  }


  @RequestMapping("/email/{id}")
  public String GetIndex(@PathVariable("id") long id, Model model, String
          student){
    model.addAttribute("student", studentRepository.findById(id).get());
    return "sendemail";
  }

  @PostMapping("/sendSimpleEmail")
  public String SendSimpleEmail(Student student) {
    String email = student.getEmail();
    System.out.println("Email " + email);
    emailService.SendSimpleEmail(email);
    return "success";
  }



}
