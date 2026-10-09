package com.itihas.crudSpringBootDemo.controller;

import com.itihas.crudSpringBootDemo.dto.CreateStudentRequestDTO;
import com.itihas.crudSpringBootDemo.dto.CreateStudentResponseDTO;
import com.itihas.crudSpringBootDemo.dto.UpdateStudentRequestDTO;
import com.itihas.crudSpringBootDemo.dto.UpdateStudentResponseDTO;
import com.itihas.crudSpringBootDemo.entity.Student;
import com.itihas.crudSpringBootDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;

     public StudentController(StudentService studentService){
         this.studentService = studentService;
     }

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@Valid @RequestBody CreateStudentRequestDTO studentRequestDTO){

        CreateStudentResponseDTO createdStudent =  studentService.createStudent(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);


    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CreateStudentResponseDTO>getStudent(@PathVariable Long id ){
         CreateStudentResponseDTO studentResp = studentService.getStudent(id);
         return ResponseEntity.ok(studentResp);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent(){
         List<CreateStudentResponseDTO> studentList = studentService.getAllStudent();
         return ResponseEntity.ok(studentList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDTO studentReq){
         UpdateStudentResponseDTO studentResp = studentService.updateStudent(id,studentReq);
         return ResponseEntity.ok(studentResp);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteStudent(@PathVariable Long id){
         studentService.deleteStudent(id);
         return ResponseEntity.noContent().build();

    }

    @PatchMapping("/delete-softly/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
         studentService.deleteStudentSoftly(id);
         return ResponseEntity.noContent().build();
    }




}
