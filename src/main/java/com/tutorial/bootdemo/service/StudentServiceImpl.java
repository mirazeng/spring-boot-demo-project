package com.tutorial.bootdemo.service;

import com.tutorial.bootdemo.converter.StudentConverter;
import com.tutorial.bootdemo.dao.Student;
import com.tutorial.bootdemo.dao.StudentRepository;
import com.tutorial.bootdemo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public StudentDTO getStudentBYId(Long id) {
    Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
    return StudentConverter.convertStudentToStudentDTO(student);
  }

  @Override
  public Long addNewStudent(StudentDTO studentDTO) throws IllegalStateException{
    List<Student> studentList = studentRepository.findByEmail(studentDTO.getEmail());
    if (!CollectionUtils.isEmpty(studentList)) {
      throw new IllegalStateException("Student email:" + studentDTO.getEmail() + " already exists");
    }
    Student student = studentRepository.save(StudentConverter.convertStudentDTOToStudent(studentDTO));
    return student.getId();
  }

  @Override
  public void deleteStudentById(Long id)throws IllegalArgumentException {
    studentRepository
        .findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("Student with id:" + id + " does not exist"));
    studentRepository.deleteById(id);
  }

  @Override
  @Transactional
  public StudentDTO updateStudentById(Long id, String name, String email) {
    Student studentInDB = studentRepository
        .findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("Student with id:" + id + " does not exist"));
    if (StringUtils.hasLength(name) && !studentInDB.getName().equals(name)) {
      studentInDB.setName(name);
    }
    if (StringUtils.hasLength(email) && !studentInDB.getEmail().equals(email)) {
      studentInDB.setEmail(email);
    }
    Student student = studentRepository.save(studentInDB);
    return StudentConverter.convertStudentToStudentDTO(student);
  }
}