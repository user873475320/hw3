package ru.tbank.servlet.example;

import ru.tbank.servlet.example.mapper.RowMapper;

import java.util.List;


public class JdbcTemplate {

    private CustomDataSource customDataSource;

    public <T> List<T> execute(String sql, RowMapper<T> rowMapper, Object ... objects) {
        return null;
        // нужно будет реализовать дома
    }

}
