package ru.tbank.servlet.example.service;

import lombok.AllArgsConstructor;
import ru.tbank.servlet.example.entity.Student;
import ru.tbank.servlet.example.repository.StudentRepository;

import java.util.List;


@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }

}
