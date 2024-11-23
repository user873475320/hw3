package ru.tbank.jdbc.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface RowMapper<T> {

    T mapRow(ResultSet resultSet) throws SQLException;

}
