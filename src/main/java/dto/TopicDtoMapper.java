package dto;


import entity.User;
import entity.Visibility;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TopicDtoMapper implements RowMapper{
    @Override
    public TopicDto mapRow(ResultSet rs, int rownumber) throws SQLException {
        TopicDto t=new TopicDto();
        t.setId(rs.getInt("id"));
        t.setName(rs.getString("name"));
        t.setCreatedBy((User) rs.getObject("createdBy"));
        t.setDateCreated(rs.getDate("dateCreated"));
        t.setLastUpdated(rs.getDate("lastUpdated"));
        t.setVisibility(Visibility.values()[rs.getInt("visibility")]);
        t.setSubscriptionCount(rs.getInt("subscriptionCount"));
        t.setPostCount(rs.getInt("postCount"));
        return t;
    }

}
