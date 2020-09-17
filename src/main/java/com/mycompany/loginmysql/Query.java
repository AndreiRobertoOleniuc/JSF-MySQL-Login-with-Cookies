/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loginmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Andrei Oleniuc
 */
public class Query {

    String email;
    String password;
    //Connects jdbc with query
    private final DataBaseCon jdbc = DataBaseCon.getInstance();
    private final ArrayList<User> users = new ArrayList<>();

    public void update() {
        Connection con;
        ResultSet rs;
        con = jdbc.createConnection();

        try {
            PreparedStatement st = con.prepareStatement("select * from user");
            rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                email = rs.getString(2);
                password = rs.getString(3);
                System.out.println(email);
            }
        } catch (SQLException ex) {

        }

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
