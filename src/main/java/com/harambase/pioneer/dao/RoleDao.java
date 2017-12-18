package com.harambase.pioneer.dao;

import com.harambase.support.database.DataServiceConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Deprecated
@Component
public class RoleDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Deprecated
    public String getSingleRoleName(int roleId) {

        ResultSet rs = null;
        Connection connection = null;
        List<String> roleNameList = new ArrayList<>();

        connection = DataServiceConnection.openDBConnection();
        if(connection == null)
            return null;

        try {
            Statement stmt = connection.createStatement();

            String queryString = "SELECT role_name as roleName FROM Role role where role.roleid = "+ roleId;
            rs = stmt.executeQuery(queryString);
            while(rs.next()){
                roleNameList.add(rs.getString("roleName"));
            }
            rs.close();
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return roleNameList.get(0);
    }

    @Deprecated
    public String getSingleRoleCode(int roleId) {

        ResultSet rs = null;
        Connection connection = null;
        List<String> roleNameList = new ArrayList<>();

        connection = DataServiceConnection.openDBConnection();
        if(connection == null)
            return null;

        try {
            Statement stmt = connection.createStatement();

            String queryString = "SELECT role_code as roleCode FROM Role role where role.roleid = "+ roleId;
            rs = stmt.executeQuery(queryString);
            while(rs.next()){
                roleNameList.add(rs.getString("roleCode"));
            }
            rs.close();
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return roleNameList.get(0);
    }

}
