package ru.tbank.jdbc.example.mapper;

import ru.tbank.jdbc.example.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentRowMapper implements RowMapper<Student>{

    @Override
    public Student mapRow(ResultSet resultSet) throws SQLException {
        return Student.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                // .teacherId(resultSet.getLong("teacher_id"))
                .build();
    }

}
