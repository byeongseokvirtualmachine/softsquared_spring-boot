package com.example.demo.src.test;


import com.example.demo.src.test.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TestDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetTestRes> getTest() {
        String getTestQuery = "select * from Test";
        return this.jdbcTemplate.query(getTestQuery,
                (rs, rowNum) -> new GetTestRes(
                        rs.getInt("userId"),
                        rs.getInt("data"))
        );
    }

    public GetTestRes getTest(int userId) {
        String getTestQuery = "select userId, data from Test where userId = ?";
        int getTestParams = userId;
        return this.jdbcTemplate.queryForObject(getTestQuery,
                (rs, rowNum) -> new GetTestRes(
                        rs.getInt("userId"),
                        rs.getInt("data")),
                getTestParams);
    }


}