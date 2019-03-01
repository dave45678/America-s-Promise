package com.josemy.grantapp.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String workAuth;
  private String veteranStatus;
  private String employmentStatus;
  private String schoolName;
  private String schoolLocation;
  private String highestEdu;
  private String major;
  private String graduationYear;
  private String description;
  private String email;


//@ManyToOne
//  private Course course;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_id")
  private Course course;



  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getHighestEdu() {
    return highestEdu;
  }

  public void setHighestEdu(String highestEdu) {
    this.highestEdu = highestEdu;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String getVeteranStatus() {
    return veteranStatus;
  }

  public void setVeteranStatus(String veteranStatus) {
    this.veteranStatus = veteranStatus;
  }

  public String getWorkAuth() {
    return workAuth;
  }

  public void setWorkAuth(String workAuth) {
    this.workAuth = workAuth;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmploymentStatus() {
    return employmentStatus;
  }

  public void setEmploymentStatus(String employmentStatus) {
    this.employmentStatus = employmentStatus;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public String getSchoolLocation() {
    return schoolLocation;
  }

  public void setSchoolLocation(String schoolLocation) {
    this.schoolLocation = schoolLocation;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGraduationYear() {
    return graduationYear;
  }

  public void setGraduationYear(String graduationYear) {
    this.graduationYear = graduationYear;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
