package ru.tbank.jdbc.example.repository.impl;

import lombok.RequiredArgsConstructor;
import ru.tbank.jdbc.example.JdbcTemplate;
import ru.tbank.jdbc.example.entity.Student;
import ru.tbank.jdbc.example.mapper.RowMapper;
import ru.tbank.jdbc.example.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> rowMapper;

    private static final String GET_BY_ID = "SELECT * FROM students WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM students";
    private static final String UPDATE = "UPDATE students SET first_name = ?, last_name = ?, age = ?, teacher_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM students WHERE id = ?";
    private static final String GET_ALL_BY_NAME = "SELECT * FROM students WHERE first_name = ? OR last_name = ?";

    @Override
    public Optional<Student> getById(Long id) {
        List<Student> students = jdbcTemplate.execute(GET_BY_ID, rowMapper, id);
        return students.isEmpty() ? Optional.empty() : Optional.of(students.get(0));
    }

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.execute(GET_ALL, rowMapper);
    }

    @Override
    public boolean update(Student student) {
        try {
            jdbcTemplate.execute(UPDATE, rowMapper,
                    student.getFirstName(),
                    student.getLastName(),
                    student.getTeacherIds().isEmpty() ? null : student.getTeacherIds().get(0),
                    student.getId()
            );
            return true;
        } catch (Exception e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        try {
            jdbcTemplate.execute(DELETE, rowMapper, student.getId());
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Student> getAllByName(String name) {
        return jdbcTemplate.execute(GET_ALL_BY_NAME, rowMapper, name, name);
    }
}
