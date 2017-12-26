package com.harambase.pioneer.dao.base;

import com.harambase.pioneer.pojo.base.TempUser;
import com.harambase.pioneer.pojo.view.StudentView;
import com.harambase.support.database.DataServiceConnection;
import com.harambase.support.database.ResultSetHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class TempUserDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Long getCountByMapPageSearchOrdered(String search, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = Long.parseLong("0");
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM tempuser WHERE 1=1 ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(search))
                queryString += "AND userid   LIKE  '%" + search + "%' ";

            rs = stmt.executeQuery(queryString);
            logger.info(queryString);

            if (rs.next()) {
                count = rs.getLong("count");
            }
            return count;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public List<TempUser> getByMapPageSearchOrdered(int currentIndex, int pageSize, String search,
                                                       String order, String orderColumn, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<TempUser> tempUsers = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return tempUsers;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM tempuser WHERE 1=1 ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(search))
                queryString += "AND userid   LIKE  '%" + search + "%' ";

            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            tempUsers = ResultSetHelper.getObjectFor(rs, TempUser.class);
            return tempUsers;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

}
