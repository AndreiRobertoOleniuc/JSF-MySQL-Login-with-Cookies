/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loginmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Andrei Oleniuc
 */
public class DataBaseCon {

    private static DataBaseCon instance = null;
    private final String name = "root";
    private final String pwd = "";
    private final String DB_Connection_String = "jdbc:mysql://localhost:3306/loginjsf";
    private Connection cn = null;

    private DataBaseCon() {
    }

    public static DataBaseCon getInstance() {
        if (instance == null) {
            instance = new DataBaseCon();
        }

        return instance;
    }

    public Connection createConnection() {
        if (cn == null) {
            try {
                cn = DriverManager.getConnection(DB_Connection_String, name, pwd);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        return cn;
    }

    public void closeConnection() {
        try {
            cn.close();
            cn = null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
