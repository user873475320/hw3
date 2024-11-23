package ru.tbank.jdbc.example;

import ru.tbank.jdbc.example.mapper.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> execute(String sql, RowMapper<T> rowMapper, Object... objects) {
        List<T> resultList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    resultList.add(rowMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + sql);
        }

        return resultList;
    }
}
