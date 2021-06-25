package com.example.demo.src.channel;

import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.src.channel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ChannelDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetChannelRes> getChannel() {
        String getChannelQuery = "select * from Channel ";
        return this.jdbcTemplate.query(getChannelQuery,
                (rs, rowNum) -> new GetChannelRes(
                        rs.getInt("chId"),
                        rs.getString("chName"),
                        rs.getString("chSubject"),
                        rs.getString("chType;"),
                        rs.getString("status;"),
                        rs.getDate("alertMute"))
        );
    }



}
