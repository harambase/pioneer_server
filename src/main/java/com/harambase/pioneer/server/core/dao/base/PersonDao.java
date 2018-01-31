package com.harambase.pioneer.server.core.dao.base;

import com.harambase.pioneer.server.core.pojo.base.Person;
import com.harambase.pioneer.server.core.dao.connection.DataServiceConnection;
import com.harambase.pioneer.server.core.dao.connection.ResultSetHelper;
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
public class PersonDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Person> getPersonBySearch(String search, String type, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Statement stmt = null;
        List<Person> personList = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return personList;

            stmt = connection.createStatement();

            String queryString = "select * from person where 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "%' ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";

            if (StringUtils.isNotEmpty(search)) {
                queryString += "" +
                        "AND (user_id    LIKE '%" + search + "%' OR" +
                        "     username   LIKE '%" + search + "%' OR" +
                        "     first_name LIKE '%" + search + "%' OR" +
                        "     last_name  LIKE '%" + search + "%')";
            }
            queryString += "limit 0,6";
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            personList = ResultSetHelper.getObjectFor(rs, Person.class);
            return personList;
        } finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public Long getCountByMapPageSearchOrdered(String search, String type, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = 0L;
        Statement stmt = null;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM person WHERE 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "%' ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "user_id     LIKE  '%" + search + "%' OR " +
                        "username    LIKE  '%" + search + "%' OR " +
                        "type        LIKE  '%" + search + "%' OR " +
                        "first_name  LIKE  '%" + search + "%' OR " +
                        "last_name   LIKE  '%" + search + "%' OR " +
                        "status      LIKE  '%" + search + "%' OR " +
                        "update_time LIKE  '%" + search + "%')";
            }
            logger.info(queryString);
            rs = stmt.executeQuery(queryString);

            if (rs.next()) {
                count = rs.getLong("count");
            }
            return count;

        } finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public List<Person> getByMapPageSearchOrdered(int currentIndex, int pageSize, String search,
                                                  String order, String orderColumn, String type, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Statement stmt = null;
        List<Person> personList = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return personList;

            stmt = connection.createStatement();

            String queryString = "SELECT * FROM person WHERE 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "%' ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "user_id     LIKE  '%" + search + "%' OR " +
                        "username    LIKE  '%" + search + "%' OR " +
                        "type        LIKE  '%" + search + "%' OR " +
                        "first_name  LIKE  '%" + search + "%' OR " +
                        "last_name   LIKE  '%" + search + "%' OR " +
                        "status      LIKE  '%" + search + "%' OR " +
                        "update_time LIKE  '%" + search + "%')";
            }

            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            personList = ResultSetHelper.getObjectFor(rs, Person.class);
            return personList;

        } finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

}
