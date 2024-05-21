package com.tutorial.bootdemo.converter;

import com.tutorial.bootdemo.dao.Student;
import com.tutorial.bootdemo.dto.StudentDTO;

public class StudentConverter {

  public static StudentDTO convertStudentToStudentDTO(Student student) {
     StudentDTO studentDTO = new StudentDTO();
     studentDTO.setId(student.getId());
     studentDTO.setName(student.getName());
     studentDTO.setEmail(student.getEmail());
     return studentDTO;
  }

  public static Student convertStudentDTOToStudent(StudentDTO studentDTO) {
     Student student = new Student();
     student.setId(studentDTO.getId());
     student.setName(studentDTO.getName());
     student.setEmail(studentDTO.getEmail());
     return student;
  }

}