package com.harambase.pioneer.dao;

import com.github.pagehelper.StringUtil;
import com.harambase.pioneer.database.DataServiceConnection;
import com.harambase.pioneer.database.ResultSetHelper;
import com.harambase.pioneer.pojo.dto.MessageView;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageDao {

    public int countMessageByStatus(String receiverid, String senderid,
                                    String label, String status)throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = DataServiceConnection.openDBConnection();
            if(connection == null)
                return 0;
            
            Statement stmt = connection.createStatement();
            
            String queryString = "SELECT count(*) as count FROM MessageView WHERE status = '" + status + "'";

            queryString += whereBuilderByLabel(receiverid, senderid, label);

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
                                                      String label, String search) throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = DataServiceConnection.openDBConnection();
            if(connection == null)
                return 0;

            Statement stmt = connection.createStatement();

            String queryString = "select count(*) as count from MessageView where 1 = 1";

            queryString += whereBuilderByLabel(receiverid, senderid, label);

            if(StringUtil.isNotEmpty(search)){
                queryString += "" +
                        "and(email like  '%"+search+"%' or" +
                        "   subject like '%"+search+"%' or" +
                        "   status like  '%"+search+"%' or" +
                        "   sender like  '%"+search+"%' or" +
                        "   title like   '%"+search+"%' or" +
                        "   date like    '%"+search+"%')";
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

    public List<MessageView> getMessageByMapPageSearchOrdered(String receiverid, String senderid,
                                                              String label, String search, int currentIndex,
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
            queryString += whereBuilderByLabel(receiverid, senderid, label);

            if(StringUtil.isNotEmpty(search)){
                queryString += "" +
                        "and(email like  '%"+search+"%' or" +
                        "   subject like '%"+search+"%' or" +
                        "   status like  '%"+search+"%' or" +
                        "   sender like  '%"+search+"%' or" +
                        "   title like   '%"+search+"%' or" +
                        "   date like    '%"+search+"%') ";
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

    private String whereBuilderByLabel(String receiverid, String senderid, String label){
        String queryString = "";
        if(!label.equals("trash")) {
            if(receiverid != null)
                queryString += " AND receiverid =  '" + receiverid + "'";
            if(senderid != null)
                queryString +=  " AND senderid = '" + senderid + "'";
            if("important/draft/trash".contains(label) && StringUtil.isNotEmpty(label))
                queryString += " AND labels LIKE '%" + label + "%'";
        }
        else{
            queryString+= " AND (receiverid = '" + receiverid + "'" +
                    " OR senderid = '" + senderid + "'" +
                    " )AND labels LIKE '%trash%'";
        }
        return queryString;
    }
}
