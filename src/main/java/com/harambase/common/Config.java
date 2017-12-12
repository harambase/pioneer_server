package com.harambase.common;

import java.util.ResourceBundle;

public class Config {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("server");

    public final static String TEMP_FILE_PATH = resourceBundle.getString("tempFile.path");
}
