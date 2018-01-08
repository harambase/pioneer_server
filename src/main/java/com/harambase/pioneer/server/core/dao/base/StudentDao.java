package com.harambase.pioneer.server.core.dao.base;

import com.harambase.pioneer.server.core.pojo.view.StudentView;
import com.harambase.pioneer.support.database.DataServiceConnection;
import com.harambase.pioneer.support.database.ResultSetHelper;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class StudentDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Long getCountByMapPageSearchOrdered(String search, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = 0L;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM studentview WHERE 1=1 ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "student_id   LIKE  '%" + search + "%' OR " +
                        "max_credits LIKE  '%" + search + "%' OR " +
                        "status      LIKE  '%" + search + "%' OR " +
                        "sname       LIKE  '%" + search + "%' OR " +
                        "complete    LIKE  '%" + search + "%' OR " +
                        "progress    LIKE  '%" + search + "%' OR " +
                        "incomplete  LIKE  '%" + search + "%')";
            }
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

    public List<LinkedHashMap<String, Object>> getByMapPageSearchOrdered(int currentIndex, int pageSize, String search,
                                                       String order, String orderColumn, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<LinkedHashMap<String, Object>> studentList = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return studentList;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM studentview WHERE 1=1 ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "student_id  LIKE  '%" + search + "%' OR " +
                        "max_credits LIKE  '%" + search + "%' OR " +
                        "status      LIKE  '%" + search + "%' OR " +
                        "sname       LIKE  '%" + search + "%' OR " +
                        "complete    LIKE  '%" + search + "%' OR " +
                        "progress    LIKE  '%" + search + "%' OR " +
                        "incomplete  LIKE  '%" + search + "%')";
            }
            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            studentList = ResultSetHelper.getObjectAsLinkedHashMap(rs, StudentView.class);
            return studentList;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public LinkedHashMap<String, Object> findOne(String studentId) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return null;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM studentview WHERE student_id='" + studentId + "'";
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            List<LinkedHashMap<String, Object>> studentList = ResultSetHelper.getObjectAsLinkedHashMap(rs, StudentView.class);

            if (studentList.isEmpty())
                return null;

            return studentList.get(0);

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }
}
