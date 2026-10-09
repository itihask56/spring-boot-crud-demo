package com.itihas.crudSpringBootDemo.service;

import com.itihas.crudSpringBootDemo.controller.StudentController;
import com.itihas.crudSpringBootDemo.dto.CreateStudentRequestDTO;
import com.itihas.crudSpringBootDemo.dto.CreateStudentResponseDTO;
import com.itihas.crudSpringBootDemo.dto.UpdateStudentRequestDTO;
import com.itihas.crudSpringBootDemo.dto.UpdateStudentResponseDTO;
import com.itihas.crudSpringBootDemo.entity.Student;
import com.itihas.crudSpringBootDemo.exception.ResourceNotFoundException;
import com.itihas.crudSpringBootDemo.repository.StudentRepository;
import com.sun.jdi.request.DuplicateRequestException;
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

    private boolean numberExists(Student student){
        return studentRepository.existsByMobileNumber(student.getMobileNumber());

    }
    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentReq) {
        Student student = mapToEntity(studentReq);

        if(numberExists(student)){
            throw new DuplicateRequestException("Student with number "+student.getMobileNumber()+" already exist");
        }

        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);
    }

    public CreateStudentResponseDTO getStudent(Long id){
        Student studentResp = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id "+id+" not found"));
        return mapToDto(studentResp);

    }

    public List<CreateStudentResponseDTO> getAllStudent(){
        // List<Student> studentList = studentRepository.findAll();//select * from student where deleted=false
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream().map(this::mapToDto).toList();


    }



    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO studentReq){
        Student isStudentPresent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Student with "+id+" not found"));

        isStudentPresent.setName(studentReq.getName());
        isStudentPresent.setAge(studentReq.getAge());
        isStudentPresent.setEmail(studentReq.getEmail());
        isStudentPresent.setRollNo(studentReq.getRollNo());
        isStudentPresent.setSubject(studentReq.getSubject());
        isStudentPresent.setDeleted(false);
        isStudentPresent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(isStudentPresent);

        return mapToUpdateDTO(savedStudent);

    }

    public void deleteStudent(Long id){
         Student studentToBeDeleted = studentRepository
                 .findById(id)
                 .orElseThrow(()->new ResourceNotFoundException("Student with "+id+" not exists"));

         studentRepository.delete(studentToBeDeleted);

    }

    public void deleteStudentSoftly(Long id){
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student with "+id+" not exists"));

        studentToBeDeleted.setDeleted(true);
        studentRepository.save(studentToBeDeleted);

    }


}
