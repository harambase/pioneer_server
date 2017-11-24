package com.harambase.pioneer.dao;

import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.StringUtil;
import com.harambase.pioneer.database.DataServiceConnection;
import com.harambase.pioneer.database.ResultSetHelper;
import com.harambase.pioneer.pojo.Menu;
import com.harambase.pioneer.pojo.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuDao {
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
