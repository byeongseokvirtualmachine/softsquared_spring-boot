package com.example.demo.src.mate;


import com.example.demo.src.mate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MateDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetMateRes> getMates() {
        String getMatesQuery = "select * from Mate";
        return this.jdbcTemplate.query(getMatesQuery,
                (rs, rowNum) -> new GetMateRes(
                        rs.getInt("mateListId"),
                        rs.getInt("userId"),
                        rs.getInt("mateId"),
                        rs.getString("status"))
        );
    }


    public GetMateRes getMate(int userId) {
        System.out.println("getMate Dao 도착 : ");

        String getMateQuery = "select * from Mate where userId = ?";
        int getMateParams = userId;
        return this.jdbcTemplate.queryForObject(getMateQuery,
                (rs, rowNum) -> new GetMateRes(
                        rs.getInt("mateListId"),
                        rs.getInt("userId"),
                        rs.getInt("mateId"),
                        rs.getString("status")),
                getMateParams);
    }

    public int checkMateId(int userId, int mateId) {
        System.out.println("친구 있는지 확인한다?");
        String checkMateQuery = "SELECT exists (SELECT mateId FROM Mate WHERE userId = ? AND mateId = ?)";
        Object[] checkMateParams = new Object[]{userId, mateId};
        System.out.println("에러가 없는지...userId : " + checkMateParams);
        return this.jdbcTemplate.queryForObject(checkMateQuery, int.class, checkMateParams);

    }

    public int getMateId(int userId, int mateId) {
        System.out.println("친구 가져오는지 확인한다?");
        String checkMateQuery = "SELECT mateListId, userId, mateId, status FROM Mate WHERE userId = ? AND mateId = ?";
        Object[] checkMateParams = new Object[]{userId, mateId};
        System.out.println("에러가 없는지...userId : " + checkMateParams);
        return this.jdbcTemplate.queryForObject(checkMateQuery,int.class, checkMateParams);

    }


    public int createMate(PostMateReq postMateReq) {
        System.out.println("친구 이제 만든다.");
        String createMateQuery = "insert into Mate (userId, mateId, status) values (?,?,?)";
        System.out.println(createMateQuery);
        Object[] createMateParams = new Object[]{postMateReq.getUserId(), postMateReq.getMateId(), postMateReq.getStatus()};
        System.out.println(createMateParams);
        this.jdbcTemplate.update(createMateQuery, createMateParams);

        String lastInsertMateListIdQuery = "select last_insert_id()";
        System.out.println("last_inser_id() 이게 없던게 아닐까?? ");
        System.out.println(lastInsertMateListIdQuery);
        return this.jdbcTemplate.queryForObject(lastInsertMateListIdQuery, int.class);
    }
}
