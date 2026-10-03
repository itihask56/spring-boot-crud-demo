package com.itihas.crudSpringBootDemo.service;

import com.itihas.crudSpringBootDemo.controller.StudentController;
import com.itihas.crudSpringBootDemo.dto.CreateStudentRequestDTO;
import com.itihas.crudSpringBootDemo.dto.CreateStudentResponseDTO;
import com.itihas.crudSpringBootDemo.dto.UpdateStudentRequestDTO;
import com.itihas.crudSpringBootDemo.dto.UpdateStudentResponseDTO;
import com.itihas.crudSpringBootDemo.entity.Student;
import com.itihas.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    private Student mapToEntity(CreateStudentRequestDTO studentRequestDTO){
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setEmail(studentRequestDTO.getEmail());
        student.setRollNo(studentRequestDTO.getRollNo());
        student.setMobileNumber(studentRequestDTO.getMobileNumber());
        student.setSubject(studentRequestDTO.getSubject());
        student.setDeleted(false);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        return student;

    }

    private CreateStudentResponseDTO mapToDto(Student student){
        CreateStudentResponseDTO studentResponseDTO = new CreateStudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setAge(student.getAge());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setRollNo(student.getRollNo());
        studentResponseDTO.setMobileNumber(student.getMobileNumber());
        studentResponseDTO.setSubject(student.getSubject());
        studentResponseDTO.setMessage("Student Saved Successfully");
        studentResponseDTO.setCreatedAt(student.getCreatedAt());
        studentResponseDTO.setUpdatedAt(student.getUpdatedAt());
        return studentResponseDTO;
    }

    private UpdateStudentResponseDTO mapToUpdateDTO(Student student){
        UpdateStudentResponseDTO studentResponseDTO = new UpdateStudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setAge(student.getAge());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setRollNo(student.getRollNo());
        studentResponseDTO.setMobileNumber(student.getMobileNumber());
        studentResponseDTO.setSubject(student.getSubject());
        studentResponseDTO.setMessage("Student updated Successfully");
        studentResponseDTO.setUpdatedAt(student.getUpdatedAt());
        return studentResponseDTO ;

    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentReq) {
        Student student = mapToEntity(studentReq);
        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);
    }
    public CreateStudentResponseDTO getStudent(Long id){

        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);
       if(studentResp.isPresent()){
           return mapToDto(studentResp.get());

       }
       return null;
    }

    public List<CreateStudentResponseDTO> getAllStudent(){
        // List<Student> studentList = studentRepository.findAll();//select * from student where deleted=false
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream().map(this::mapToDto).toList();


    }



    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO studentReq){
        Optional<Student> isStudentPresent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(isStudentPresent.isEmpty()){
            return null;
        }
        Student studentToSave = isStudentPresent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
        studentToSave.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(studentToSave);

       return mapToUpdateDTO(savedStudent);

    }

    public Boolean deleteStudent(Long id){
        Boolean isStudentExist = studentRepository.existsById(id);
        if(!isStudentExist){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public Boolean deleteStudentSoftly(Long id){
        Optional<Student> isStudentExist = studentRepository.findByIdAndDeletedIsFalse(id);
        if(isStudentExist.isEmpty()){
            return false;
        }
        Student studentToSave = isStudentExist.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);

        return true;


    }


}
