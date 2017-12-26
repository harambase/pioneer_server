package com.harambase.pioneer.dao.base;

import com.harambase.pioneer.pojo.base.Person;
import com.harambase.pioneer.pojo.view.TranscriptView;
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
public class TranscriptDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Long getCountByMapPageSearchOrdered(String search, String studentId, String crn) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = Long.parseLong("0");
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM transcriptview WHERE 1=1 ";
            if (StringUtils.isNotEmpty(studentId))
                queryString += "AND studentid LIKE '%" + studentId + "%' ";
            if (StringUtils.isNotEmpty(crn))
                queryString += "AND crn = '" + crn + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "studentid  LIKE  '%" + search + "%' OR " +
                        "crn        LIKE  '%" + search + "%' OR " +
                        "grade      LIKE  '%" + search + "%' OR " +
                        "complete   LIKE  '%" + search + "%' OR " +
                        "assigntime LIKE  '%" + search + "%' OR " +
                        "sname      LIKE  '%" + search + "%' OR " +
                        "coursename LIKE  '%" + search + "%')";
            }
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

    public List<TranscriptView> getByMapPageSearchOrdered(int currentIndex, int pageSize, String search,
                                                          String order, String orderColumn, String studentId, String crn) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<TranscriptView> transcriptViews = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return transcriptViews;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM transcriptview WHERE 1=1 ";
            if (StringUtils.isNotEmpty(studentId))
                queryString += "AND studentid LIKE '%" + studentId + "%' ";
            if (StringUtils.isNotEmpty(crn))
                queryString += "AND crn = '" + crn + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "studentid  LIKE  '%" + search + "%' OR " +
                        "crn        LIKE  '%" + search + "%' OR " +
                        "grade      LIKE  '%" + search + "%' OR " +
                        "complete   LIKE  '%" + search + "%' OR " +
                        "assigntime LIKE  '%" + search + "%' OR " +
                        "sname      LIKE  '%" + search + "%' OR " +
                        "coursename LIKE  '%" + search + "%')";
            }

            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            transcriptViews = ResultSetHelper.getObjectFor(rs, TranscriptView.class);
            return transcriptViews;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

}
