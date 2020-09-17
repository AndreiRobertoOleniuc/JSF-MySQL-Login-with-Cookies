/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loginmysql;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrei Oleniuc
 */
@Named(value = "model")
@SessionScoped
public class Model implements Serializable {

    String emailEingabe;
    String passwordEingabe;
    String check = "";
    Cookie cEmail;
    Cookie cPassword;
    String cookieC = "";

    /**
     * Creates a new instance of Model
     */
    public Model() throws ClassNotFoundException, SQLException {

    }

    public String checkCookie() throws SQLException, ClassNotFoundException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            String name = "";
            String password = " ";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    name = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            return check(name, password);
        }
        return "";
    }

    public String check(String name, String password) throws SQLException, SQLException, ClassNotFoundException {
        Connection connect = null;

        String url = "jdbc:mysql://localhost:3306/loginjsf";

        String username = "root";
        String pass = "";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection(url, username, pass);
            // System.out.println("Connection established"+connect);

        } catch (SQLException ex) {
            System.out.println("in exec");
            System.out.println(ex.getMessage());
        }

        PreparedStatement pstmt = connect.prepareStatement("select * from user");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String lname = rs.getString(2);
            String lpass = rs.getString(3);
            if (name.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
                // close resources
                return "/Suc.xhtml";
            } else {

            }
        }
        // close resources
        rs.close();
        pstmt.close();
        connect.close();
        return "";
    }

    public String login() throws SQLException, ClassNotFoundException {
        Connection connect = null;

        String url = "jdbc:mysql://localhost:3306/loginjsf";

        String username = "root";
        String password = "";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection(url, username, password);
            // System.out.println("Connection established"+connect);

        } catch (SQLException ex) {
            System.out.println("in exec");
            System.out.println(ex.getMessage());
        }

        PreparedStatement pstmt = connect.prepareStatement("select * from user");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            if (emailEingabe.equals(rs.getString(2)) && passwordEingabe.equals(rs.getString(3))) {
                check = "Login correct";
                cEmail = new Cookie("email", emailEingabe);
                cPassword = new Cookie("password", passwordEingabe);
                cEmail.setMaxAge(60 * 60 * 24 * 7);
                cPassword.setMaxAge(60 * 60 * 24 * 7);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addCookie(cEmail);
                response.addCookie(cPassword);
                return "/Suc.xhtml";
            } else {
                check = "Login wrong";
            }
        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();
        return "";
    }

    public String getEmailEingabe() {
        return emailEingabe;
    }

    public void setEmailEingabe(String emailEingabe) {
        this.emailEingabe = emailEingabe;
    }

    public String getPasswordEingabe() {
        return passwordEingabe;
    }

    public void setPasswordEingabe(String passwordEingabe) {
        this.passwordEingabe = passwordEingabe;
    }

    public String getCheck() {
        return check;
    }

    public String getCookieC() {
        return cookieC;
    }

    public void setCookieC(String cookieC) {
        this.cookieC = cookieC;
    }

}
