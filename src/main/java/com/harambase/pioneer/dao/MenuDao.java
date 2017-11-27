package com.harambase.pioneer.dao;

import com.harambase.pioneer.database.DataServiceConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<String> getResUrlsByRoleId(Integer roleId) throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        List<String> menuList = new ArrayList<>();
        try{
            connection = DataServiceConnection.openDBConnection();
            if(connection == null)
                return menuList;

            Statement stmt = connection.createStatement();

            String queryString = "SELECT url as url FROM Relation rel INNER JOIN menu m ON rel.menuid = m.id where rel.roleid = "+ roleId;

            rs = stmt.executeQuery(queryString);
            while(rs.next()){
                menuList.add(rs.getString("url"));
            }

            return menuList;

        }finally {
            if(rs != null)
                rs.close();
            if(connection != null)
                connection.close();
        }
    }

}
