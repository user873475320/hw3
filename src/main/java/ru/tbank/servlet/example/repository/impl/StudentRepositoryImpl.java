package ru.tbank.servlet.example.repository.impl;

import lombok.RequiredArgsConstructor;
import ru.tbank.servlet.example.entity.Student;
import ru.tbank.servlet.example.mapper.RowMapper;
import ru.tbank.servlet.example.repository.StudentRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



// в атрибутах jdbctemplate
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final DataSource dataSource;
    private final RowMapper<Student> rowMapper;

    private final static String GET_BY_ID = "select * from students where id = ?";
    private final static String GET_ALL = "select * from students";
    private final static String GET_BY_NAME = "select * from students where first_name = ?";

    @Override
    public Optional<Student> getById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();

            resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                return Optional.of(rowMapper.mapRow(resultSet));

            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public List<Student> getAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(GET_ALL);

            preparedStatement.execute();

            resultSet = preparedStatement.getResultSet();

            List<Student> students = new ArrayList<>();

            while (resultSet.next()) {
                students.add(rowMapper.mapRow(resultSet));
            }

            return students;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public boolean update(Student type) {
        return false;
    }

    @Override
    public boolean delete(Student type) {
        return false;
    }

    @Override
    public List<Student> getAllByName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(GET_BY_NAME);
            preparedStatement.setString(1, name);

            preparedStatement.execute();

            resultSet = preparedStatement.getResultSet();

            List<Student> students = new ArrayList<>();

            while (resultSet.next()) {
                students.add(rowMapper.mapRow(resultSet));
            }

            return students;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
