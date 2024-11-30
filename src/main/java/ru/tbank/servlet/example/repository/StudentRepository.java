package ru.tbank.servlet.example.repository;

import ru.tbank.servlet.example.entity.Student;

import java.util.List;


public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> getAllByName(String name);

}
