package com.example.demo.src.servers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.src.servers.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ServersDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetServerRes> getServers() {
        System.out.println("getServers 접근");
        String getUserQuery = "select * from Servers";

        return this.jdbcTemplate.query(getUserQuery,
                (rs, rowNum) -> new GetServerRes(
                        rs.getInt("serverId"),
                        rs.getInt("hostId"),
                        rs.getInt("userId"),
                        rs.getString("serverType"),
                        rs.getString("serverName"),
                        rs.getString("profileImageUrl"),
                        rs.getString("inviteUrl"),
                        rs.getString("availableDM"))
        );
    }
//    public GetServerRes getServers() {
//        System.out.println("getServer 접근");
//        String getUserQuery = "select * from Servers where serverId = ?";
//        int getUserParams = serverId;
//        return this.jdbcTemplate.queryForObject(getUserQuery,
//                (rs, rowNum) -> new GetServerRes(
//                        rs.getInt("serverId"),
//                        rs.getInt("hostId"),
//                        rs.getInt("userId"),
//                        rs.getString("serverType"),
//                        rs.getString("serverName"),
//                        rs.getString("profileImageUrl"),
//                        rs.getString("inviteUrl"),
//                        rs.getString("availableDM")),
//                getUserParams);
//    }

}
