package com.itihas.crudSpringBootDemo.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {

    @NotBlank(message = "Name can't be blank")
    @Size(min=3,max = 50, message = "Name should be at least 3 words long")
    private String name;

    @NotNull(message = "Age is required filed")
    @Min(value = 18)
    private int age;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email can't be invalied")
    private String email;

    @NotNull(message = "Roll no. Required")
    private int rollNo;

    @NotEmpty(message = "Subject is required")
    private String subject;

    @NotNull(message = "Number can't be null")
    private String mobileNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
