package com.harambase.pioneer.dao.base;

import com.harambase.support.database.DataServiceConnection;
import com.harambase.support.database.ResultSetHelper;
import com.harambase.pioneer.pojo.base.Person;
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
        List<Person> personList = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return personList;

            Statement stmt = connection.createStatement();

            String queryString = "select * from person where 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "%' ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";

            if (StringUtils.isNotEmpty(search)) {
                queryString += "" +
                        "AND (userid    LIKE '%" + search + "%' OR" +
                        "     username  LIKE '%" + search + "%' OR" +
                        "     firstname LIKE '%" + search + "%' OR" +
                        "     lastname  LIKE '%" + search + "%')";
            }
            queryString += "limit 0,4";

            rs = stmt.executeQuery(queryString);
            logger.info(queryString);
            personList = ResultSetHelper.getObjectFor(rs, Person.class);
            return personList;
        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public Long getCountByMapPageSearchOrdered(String search, String type, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = Long.parseLong("0");
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM person WHERE 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "% '";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "userid     LIKE  '%" + search + "%' OR " +
                        "username   LIKE  '%" + search + "%' OR " +
                        "type       LIKE  '%" + search + "%' OR " +
                        "password   LIKE  '%" + search + "%' OR " +
                        "qq         LIKE  '%" + search + "%' OR " +
                        "weChat     LIKE  '%" + search + "%' OR " +
                        "dorm       LIKE  '%" + search + "%' OR " +
                        "firstname  LIKE  '%" + search + "%' OR " +
                        "lastname   LIKE  '%" + search + "%' OR " +
                        "birthday   LIKE  '%" + search + "%' OR " +
                        "status     LIKE  '%" + search + "%' OR " +
                        "createTime LIKE  '%" + search + "%' OR " +
                        "updatetime LIKE  '%" + search + "%')";
            }
            rs = stmt.executeQuery(queryString);

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

    public List<Person> getByMapPageSearchOrdered(int currentIndex, int pageSize, String search,
                                                  String order, String orderColumn, String type, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<Person> personList = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return personList;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM person WHERE 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "% '";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "userid     LIKE  '%" + search + "%' OR " +
                        "username   LIKE  '%" + search + "%' OR " +
                        "type       LIKE  '%" + search + "%' OR " +
                        "password   LIKE  '%" + search + "%' OR " +
                        "qq         LIKE  '%" + search + "%' OR " +
                        "weChat     LIKE  '%" + search + "%' OR " +
                        "dorm       LIKE  '%" + search + "%' OR " +
                        "firstname  LIKE  '%" + search + "%' OR " +
                        "lastname   LIKE  '%" + search + "%' OR " +
                        "birthday   LIKE  '%" + search + "%' OR " +
                        "status     LIKE  '%" + search + "%' OR " +
                        "createTime LIKE  '%" + search + "%' OR " +
                        "updatetime LIKE  '%" + search + "%')";
            }

            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;

            rs = stmt.executeQuery(queryString);
            personList = ResultSetHelper.getObjectFor(rs, Person.class);
            return personList;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }
}
