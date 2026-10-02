package com.itihas.crudSpringBootDemo.service;

import com.itihas.crudSpringBootDemo.entity.Student;
import com.itihas.crudSpringBootDemo.exception.StudentAlreadyExistException;
import com.itihas.crudSpringBootDemo.repository.StudentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.management.remote.SubjectDelegationPermission;
import java.util.List;
import java.util.Optional;



@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {

        Optional<Student> existingStudent =
                studentRepository.findByMobileNumberAndDeletedFalse(
                        studentReq.getMobileNumber()
                );

        if (existingStudent.isPresent()) {
            throw new StudentAlreadyExistException("Student already exists with this mobile number");
        }

        studentReq.setDeleted(false);

        return studentRepository.save(studentReq);
    }
    public Student getStudent(Long id){

        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);
       if(studentResp.isPresent()){
           return studentResp.get();

       }
       return null;
    }

    public List<Student> getAllStudent(){
        // List<Student> studentList = studentRepository.findAll();//select * from student where deleted=false
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList;

    }

    public Student updateStudent(Long id,Student studentReq){
        Optional<Student> isStudentPresent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(isStudentPresent.isEmpty()){
            return null;
        }
        Student studentToSave = isStudentPresent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setRoll_no(studentReq.getRoll_no());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);

       return studentRepository.save(studentToSave);

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
