package com.tutorial.bootdemo.controller;

import com.tutorial.bootdemo.Response;
import com.tutorial.bootdemo.dao.Student;
import com.tutorial.bootdemo.dto.StudentDTO;
import com.tutorial.bootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/student/{id}")
  public Response<StudentDTO> getStudentById(@PathVariable Long id) {
     return Response.newSuccess(studentService.getStudentBYId(id));
  }

  @PostMapping("/student")
  public Response<Long> addNewStudent(@RequestBody StudentDTO studentDTO) {
    return Response.newSuccess(studentService.addNewStudent(studentDTO));
  }

  @DeleteMapping("/student/{id}")
  public void deleteStudentById(@PathVariable Long id) {
    studentService.deleteStudentById(id);
  }

  @PutMapping("/student/{id}")
  public Response<StudentDTO> updateStudentById(@PathVariable Long id,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String email) {
    return Response.newSuccess(studentService.updateStudentById(id, name, email));
  }


}