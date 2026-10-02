package com.itihas.crudSpringBootDemo.repository;

import com.itihas.crudSpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

//@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByIdAndDeletedIsFalse(Long id);
    List<Student> findByDeletedIsFalse();
    Optional<Student> findByMobileNumberAndDeletedFalse(String mobileNumber);

}
