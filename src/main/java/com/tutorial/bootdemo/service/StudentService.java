package com.tutorial.bootdemo.service;

import com.tutorial.bootdemo.dao.Student;
import com.tutorial.bootdemo.dto.StudentDTO;

public interface StudentService {

   StudentDTO getStudentBYId(Long id);

   Long addNewStudent(StudentDTO studentDTO);

   void deleteStudentById(Long id);

   StudentDTO updateStudentById(Long id, String name, String email);

}