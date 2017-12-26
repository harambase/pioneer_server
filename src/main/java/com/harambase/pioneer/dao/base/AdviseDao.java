package com.harambase.pioneer.dao.base;

import com.harambase.pioneer.pojo.view.AdviseView;
import com.harambase.pioneer.pojo.view.CourseView;
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
public class AdviseDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public Long getCountByMapPageSearchOrdered(String facultyId, String studentId, String search) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = 0L;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM adviseview WHERE 1=1 ";
            if (StringUtils.isNotEmpty(facultyId))
                queryString += "AND faculty_id = '" + facultyId + "' ";
            if (StringUtils.isNotEmpty(studentId))
                queryString += "AND student_id = '" + studentId + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(student_id LIKE '%" + search + "%' OR " +
                               "    faculty_id LIKE '%" + search + "%' OR " +
                               "    sname      LIKE '%" + search + "%' OR " +
                               "    fname      LIKE '%" + search + "%')";
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

    public List<AdviseView> getByMapPageSearchOrdered(String facultyId, String studentId, String search, int currentIndex, int pageSize,
                                                      String order, String orderColumn) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<AdviseView> adviseViews = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return adviseViews;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM adviseview WHERE 1=1 ";
            if (StringUtils.isNotEmpty(facultyId))
                queryString += "AND faculty_id = '" + facultyId + "' ";
            if (StringUtils.isNotEmpty(studentId))
                queryString += "AND student_id = '" + studentId + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(student_id LIKE '%" + search + "%' OR " +
                               "    faculty_id LIKE '%" + search + "%' OR " +
                               "    sname      LIKE '%" + search + "%' OR " +
                               "    fname      LIKE '%" + search + "%')";
            }
            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            adviseViews = ResultSetHelper.getObjectFor(rs, AdviseView.class);
            return adviseViews;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public AdviseView findOne(Integer id) throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return null;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM courseview WHERE id=" + id + "";
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            List<AdviseView> adviseViewList = ResultSetHelper.getObjectFor(rs, AdviseView.class);

            if (adviseViewList.isEmpty())
                return null;

            return adviseViewList.get(0);

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }
}
