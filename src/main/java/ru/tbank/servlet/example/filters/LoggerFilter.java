package ru.tbank.servlet.example.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter("/*")
public class LoggerFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        logger.info("Request method and URI: {} {}", req.getMethod(), req.getRequestURI());

        chain.doFilter(req, res);

        logger.info("Response sent with status: {}", res.getStatus());
    }
}
