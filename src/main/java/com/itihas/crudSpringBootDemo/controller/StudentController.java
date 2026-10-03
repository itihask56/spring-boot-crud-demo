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
         if(studentResp==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
         return ResponseEntity.ok(studentResp);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent(){
         List<CreateStudentResponseDTO> studentList = studentService.getAllStudent();
         if(studentList == null){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
         return ResponseEntity.ok(studentList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDTO studentReq){
         UpdateStudentResponseDTO studentResp = studentService.updateStudent(id,studentReq) ;
         if(studentResp == null){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
         return ResponseEntity.ok(studentResp);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteStudent(@PathVariable Long id){
         Boolean isDeleted = studentService.deleteStudent(id);
         if(!isDeleted){
             return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok("Student deleted successfully");



    }
    @PatchMapping("/delete-softly/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){

      Boolean isDeleted = studentService.deleteStudentSoftly(id);
      if(!isDeleted){
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok("Record Deleted");
    }




}
