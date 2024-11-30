package ru.tbank.servlet.example.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.tbank.servlet.example.config.DataSourceConfiguration;
import ru.tbank.servlet.example.mapper.StudentRowMapper;
import ru.tbank.servlet.example.repository.StudentRepository;
import ru.tbank.servlet.example.repository.impl.StudentRepositoryImpl;
import ru.tbank.servlet.example.service.StudentService;
import ru.tbank.servlet.example.service.StudentServiceImpl;

import java.io.IOException;
import java.util.Properties;


@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataSourceConfiguration configuration =
                new DataSourceConfiguration(properties);

        StudentRepository studentRepository =
                new StudentRepositoryImpl(configuration.hikariDataSource(), new StudentRowMapper());

        StudentService StudentService = new StudentServiceImpl(studentRepository);

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userService", StudentService);
    }

}
