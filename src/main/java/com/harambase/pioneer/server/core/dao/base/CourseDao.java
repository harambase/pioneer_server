package com.harambase.pioneer.server.core.dao.base;

import com.harambase.pioneer.server.core.pojo.view.CourseView;
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
public class CourseDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<CourseView> findTop5ByStatusAndSearch(String search, String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<CourseView> courseViews = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return courseViews;

            Statement stmt = connection.createStatement();

            String queryString = "select * from courseview where 1=1 ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";

            if (StringUtils.isNotEmpty(search)) {
                queryString += "" +
                        "AND (crn      LIKE '%" + search + "%' OR" +
                        "     name     LIKE '%" + search + "%' OR" +
                        "     comment  LIKE '%" + search + "%')";
            }
            queryString += "order by id desc limit 0,4";
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            courseViews = ResultSetHelper.getObjectFor(rs, CourseView.class);
            return courseViews;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public Long getCountByMapPageSearchOrdered(String facultyid, String info, String search) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = 0L;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM courseview WHERE 1=1 ";
            if (StringUtils.isNotEmpty(facultyid))
                queryString += "AND faculty_id = '" + facultyid + "' ";
            if (StringUtils.isNotEmpty(info))
                queryString += "AND info = '" + info + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        " c.crn    LIKE '%" + search + "%' or c.name     LIKE '%" + search + "%' or c.credits LIKE '%" + search + "%' or" +
                        " c.level  LIKE '%" + search + "%' or c.section  LIKE '%" + search + "%' or c.day     LIKE '%" + search + "%' or" +
                        " c.time   LIKE '%" + search + "%' or c.capacity LIKE '%" + search + "%' or c.remain  LIKE '%" + search + "%' or" +
                        " c.status LIKE '%" + search + "%' or c.faculty  LIKE '%" + search + "%' or c.date    LIKE '%" + search + "%' or c.updateTime LIKE '%" + search + "%')";
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

    public List<CourseView> getByMapPageSearchOrdered(String facultyid, String info, String search, int currentIndex, int pageSize,
                                                      String order, String orderColumn) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<CourseView> courseViews = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return courseViews;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM courseview WHERE 1=1 ";
            if (StringUtils.isNotEmpty(facultyid))
                queryString += "AND faculty_id = '" + facultyid + "' ";
            if (StringUtils.isNotEmpty(info))
                queryString += "AND info = '" + info + "' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        " c.crn    LIKE '%" + search + "%' or c.name     LIKE '%" + search + "%' or c.credits LIKE '%" + search + "%' or" +
                        " c.level  LIKE '%" + search + "%' or c.section  LIKE '%" + search + "%' or c.day     LIKE '%" + search + "%' or" +
                        " c.time   LIKE '%" + search + "%' or c.capacity LIKE '%" + search + "%' or c.remain  LIKE '%" + search + "%' or" +
                        " c.status LIKE '%" + search + "%' or c.faculty  LIKE '%" + search + "%' or c.date    LIKE '%" + search + "%' or c.updateTime LIKE '%" + search + "%')";
            }
            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            courseViews = ResultSetHelper.getObjectFor(rs, CourseView.class);
            return courseViews;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public List<CourseView> findAllCoursesViewByInfo(String info) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<CourseView> courseViews = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return courseViews;

            Statement stmt = connection.createStatement();
            String queryString = "select * from courseview where info= '" + info + "'";
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            courseViews = ResultSetHelper.getObjectFor(rs, CourseView.class);
            return courseViews;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public List<CourseView> findCourseViewByFacultyId(String facultyid) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<CourseView> courseViews = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return courseViews;

            Statement stmt = connection.createStatement();
            String queryString = "select * from courseview where faculty_id= '" + facultyid + "'";

            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            courseViews = ResultSetHelper.getObjectFor(rs, CourseView.class);
            return courseViews;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public CourseView findByCrn(String crn) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return null;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT * FROM courseview WHERE crn='" + crn + "'";
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            List<CourseView> courseViewList = ResultSetHelper.getObjectFor(rs, CourseView.class);

            if (courseViewList.isEmpty())
                return null;

            return courseViewList.get(0);

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }

    }

    public List<CourseView> findCourseViewByStudentId(String studentId) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        List<CourseView> courseViews = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return courseViews;

            Statement stmt = connection.createStatement();
            String queryString = "SELECT * FROM courseview c WHERE c.crn IN (SELECT t.crn FROM Transcript t WHERE t.student_id = '" + studentId + "'";
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            courseViews = ResultSetHelper.getObjectFor(rs, CourseView.class);
            return courseViews;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public int countAllByStatus(String status) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return 0;

            Statement stmt = connection.createStatement();
            String queryString = "SELECT count(*) AS count FROM courseview WHERE status='" + status + "'";
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            if (rs.next()) {
                return rs.getInt("count");
            }

            return 0;

        } finally {
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }
}
