package com.harambase.pioneer.dao;

import com.github.pagehelper.StringUtil;
import com.harambase.pioneer.database.DataServiceConnection;
import com.harambase.pioneer.database.ResultSetHelper;
import com.harambase.pioneer.pojo.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    public List<Person> getPersonBySearch(String search, String type, String status) throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        List<Person> personList = new ArrayList<>();
        try{
            connection = DataServiceConnection.openDBConnection();
            if(connection == null)
                return personList;
            
            Statement stmt = connection.createStatement();
            
            String queryString = "select * from Person where 1=1 ";
            if(StringUtils.isNotEmpty(type))
                queryString += "type LIKE '%"+type+"% '";
    
            queryString +="AND status = '"+ status +" '";
            
            if(StringUtil.isNotEmpty(search)){
                queryString += "" +
                        "AND (userid like  '%"+search+"%' or" +
                        "   username like  '%"+search+"%' or" +
                        "  firstname like  '%"+search+"%' or" +
                        "   lastname like  '%"+search+"%')";
            }
            queryString += "limit 0,4";
            
            rs = stmt.executeQuery(queryString);
            personList = ResultSetHelper.getObjectFor(rs, Person.class);
            return personList;
        } finally {
            if(rs != null)
                rs.close();
            if(connection != null)
                connection.close();
        }
    }
}
