package dto;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sk on 20-Jul-17.
 */
public class UserDtoMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet rs, int rownumber) throws SQLException {
        UserDto u = new UserDto();
        u.setId(rs.getInt(rs.findColumn("id")));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setFirstname(rs.getString("firstname"));
        u.setLastname(rs.getString("lastname"));
        u.setPhoto(rs.getBytes("photo"));
        u.setAdmin(rs.getBoolean("admin"));
        u.setActive(rs.getBoolean("active"));
        u.setDatecreated(rs.getDate("datecreated"));
        u.setLastupdated(rs.getDate("lastupdated"));
        u.setTopicCount(rs.getInt("topicCount"));
        u.setSubscriptionCount(rs.getInt("subscriptionCount"));
        return u;
    }
}
