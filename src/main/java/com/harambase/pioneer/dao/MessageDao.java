package com.harambase.pioneer.dao;

import com.github.pagehelper.StringUtil;
import com.harambase.support.database.DataServiceConnection;
import com.harambase.support.database.ResultSetHelper;
import com.harambase.pioneer.pojo.MessageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public int countMessageByStatus(String receiverid, String senderid,
                                    String box, String status)throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = DataServiceConnection.openDBConnection();
            if(connection == null)
                return 0;
            
            Statement stmt = connection.createStatement();
            
            String queryString = "SELECT count(*) as count FROM MessageView WHERE status = '" + status + "'";
    
            if(box.equals("inbox"))
                queryString += " AND receiverid = '" + receiverid + "'";
            if(box.equals("important"))
                queryString+= "  AND receiverid = '" + receiverid + "' AND labels LIKE '%important%'";
            if(box.equals("sent"))
                queryString += " AND senderid =   '" + senderid + "'";
            if(box.equals("draft"))
                queryString += " AND senderid =   '" + senderid + "'";
            if(box.equals("trash"))
                queryString+= "  AND (receiverid = '"+ receiverid + "' OR senderid = '" + senderid + "') AND status = 'trashed'";

            rs = stmt.executeQuery(queryString);

            int ret = 0;
            if (rs.next()) {
                ret = rs.getInt("count");
            }
            return ret;
            
        } finally {
            if(rs != null)
                rs.close();
            if(connection != null)
                connection.close();
        }
        
    }

    public long getMessageCountByMapPageSearchOrdered(String receiverid, String senderid,
                                                      String box, String search) throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = DataServiceConnection.openDBConnection();
            if(connection == null)
                return 0;

            Statement stmt = connection.createStatement();

            String queryString = "select count(*) as count from MessageView where 1 = 1";

            queryString += whereBuilderByLabel(receiverid, senderid, box);

            if(StringUtil.isNotEmpty(search)){
                queryString += "" +
                        "and(email LIKE  '%"+search+"%' or" +
                        "   subject LIKE '%"+search+"%' or" +
                        "   status LIKE  '%"+search+"%' or" +
                        "   sender LIKE  '%"+search+"%' or" +
                        "   title LIKE   '%"+search+"%' or" +
                        "   date LIKE    '%"+search+"%')";
            }
            rs = stmt.executeQuery(queryString);
            int ret = 0;
            if (rs.next()) {
                ret = rs.getInt("count");
            }
            return ret;

        } finally {
            if(rs != null)
                rs.close();
            if(connection != null)
                connection.close();
        }
    }

    public List<MessageView> getMessageByMapPageSearchOrdered(String receiverid, String senderid, String box, String search, int currentIndex,
                                                              int pageSize, String order, String orderColumn) throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        List<MessageView> messageViewList = new ArrayList<>();
        try{
            connection = DataServiceConnection.openDBConnection();
            if(connection == null)
                return messageViewList;

            Statement stmt = connection.createStatement();

            String queryString = "select * from MessageView where 1 = 1";
            queryString += whereBuilderByLabel(receiverid, senderid, box);

            if(StringUtil.isNotEmpty(search)){
                queryString += "" +
                        "and(email LIKE  '%"+search+"%' or" +
                        "   subject LIKE '%"+search+"%' or" +
                        "   status LIKE  '%"+search+"%' or" +
                        "   sender LIKE  '%"+search+"%' or" +
                        "   title LIKE   '%"+search+"%' or" +
                        "   date LIKE    '%"+search+"%') ";
            }
            queryString += "" +
                    " order by " + orderColumn + " " + order + " " +
                    " limit " + currentIndex + "," + pageSize;

            rs = stmt.executeQuery(queryString);
            messageViewList = ResultSetHelper.getObjectFor(rs, MessageView.class);
            return messageViewList;
        } finally {
            if(rs != null)
                rs.close();
            if(connection != null)
                connection.close();
        }
    }

    private String whereBuilderByLabel(String receiverid, String senderid, String box){
        String queryString = "";
        
        if(box.equals("inbox")) 
            queryString += " AND receiverid LIKE '%" + receiverid + "%' AND status LIKE '%read%'";
        if(box.equals("important"))
            queryString+= "  AND receiverid LIKE '%" + receiverid + "%' AND status LIKE '%read%' AND labels LIKE '%important%'";
        if(box.equals("sent"))
            queryString += " AND senderid =   '" + senderid   + "' AND status LIKE '%read%'";
        if(box.equals("draft"))
            queryString += " AND senderid =   '" + senderid   + "' AND status = 'saved'";
        if(box.equals("trash"))
            queryString+= "  AND (receiverid LIKE '%"+ receiverid + "%' OR senderid = '" + senderid + "') AND status = 'trashed'";
        
        return queryString;
    }
}
