package com.harambase.pioneer.dao;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class MessageDao {
    
    public int countMessageByStatus(String receiverid, String senderid,
                                           String label, String status)throws Exception{
        
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = DataServiceFactory.openDBConnection();
            if(connection == null)
                return 0;
            
            Statement stmt = connection.createStatement();
            
            String queryString = "SELECT count(*) as count FROM MessageView WHERE status = '" + status + "'";
            
            if(!label.equals("trash")) {
                if(receiverid != null)
                    queryString+= " AND receiverid =  '" + receiverid + "'" +
                                  " AND labels LIKE '%" + label + "%'";
                if(senderid != null)
                    queryString+=  " AND senderid = '" + senderid + "'" +
                            " AND labels LIKE '%" + label + "%'";
                   
            }
            else{
                queryString+= " AND (receiverid = '" + receiverid + "'" +
                        " OR senderid = '" + senderid + "'" +
                        " )AND labels LIKE '%" + label + "%'";
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
    
}
