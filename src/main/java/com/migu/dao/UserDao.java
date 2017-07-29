package com.migu.dao;

import com.migu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL = "SELECT count(*) FROM t_user WHERE user_name =? and password=?";
    private final static String FIND_USER_SQL = "SELECT user_id,user_name,credits FROM t_user WHERE user_name=?";
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_id=?";
    private final static String REGISTER_USER_SQL = "INSERT INTO t_user (user_name,password) VALUES(?,?)";
    private final static String EXIST_USER_SQL = "SELECT count(*) FROM t_user WHERE user_name =?";

    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    /**
     * 是否存在当前用户
     *
     * @param userName
     * @return
     */
    public int isExistUser(String userName) {
        return jdbcTemplate.queryForObject(EXIST_USER_SQL, new Object[]{userName}, Integer.class);
    }

    public User findUserByUserName(final String userName) {
        final User user = new User();
        jdbcTemplate.query(FIND_USER_SQL, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void registerUser(User user) {
        jdbcTemplate.update(REGISTER_USER_SQL, new Object[]{user.getUserName(), user.getPassword()});
    }
}
