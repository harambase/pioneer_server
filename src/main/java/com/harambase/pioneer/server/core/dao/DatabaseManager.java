package com.harambase.pioneer.server.core.dao;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.support.database.DataServiceConnection;
import com.harambase.pioneer.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@Component
public class DatabaseManager {

    private final static Logger logger = LoggerFactory.getLogger(DataServiceConnection.class);

    public HaramMessage restart() {
        return null;
    }

    public HaramMessage reset(String username, String password) {

        try {
            Process process = Runtime.getRuntime().exec("mysql db_name < /home/liova/download/tpch/queries/Q1.sql");
            OutputStream outputStream = process.getOutputStream();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
        return ReturnMsgUtil.success(null);
    }

    public HaramMessage backup(String username, String password) {
        return null;
    }


}
