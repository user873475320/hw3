package ru.tbank.servlet.example.repository;

import ru.tbank.servlet.example.entity.Student;

import java.util.List;
import java.util.Optional;


interface CrudRepository<T, ID> {

    Optional<T> getById(ID id);

    List<Student> getAll();

    boolean update(T type);

    boolean delete(T type);

}
