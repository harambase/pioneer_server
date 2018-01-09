package com.harambase.pioneer.server.core.dao.base;

import com.harambase.pioneer.server.core.pojo.base.TempCourse;
import com.harambase.pioneer.support.database.DataServiceConnection;
import com.harambase.pioneer.support.database.ResultSetHelper;
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
public class TempCourseDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public long getCountByMapPageSearchOrdered(String search, String status, String facultyId) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = 0L;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM tempcourse WHERE 1=1 ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(facultyId))
                queryString += "AND facultyId = '" + facultyId + "' ";
            if (StringUtils.isNotEmpty(search))
                queryString += "AND (crn LIKE  '%" + search + "%' OR facultyId LIKE '%" + search + "%')";

            logger.info(queryString);
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

    public List<TempCourse> getByMapPageSearchOrdered(int currentIndex, int pageSize, String search,
                                                      String order, String orderColumn, String status, String facultyId) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<TempCourse> tempCourses = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return tempCourses;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM tempcourse WHERE 1=1 ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(facultyId))
                queryString += "AND facultyId = '" + facultyId + "' ";
            if (StringUtils.isNotEmpty(search))
                queryString += "AND (crn LIKE  '%" + search + "%' OR facultyId LIKE '%" + search + "%')";

            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            tempCourses = ResultSetHelper.getObjectFor(rs, TempCourse.class);
            return tempCourses;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }
}