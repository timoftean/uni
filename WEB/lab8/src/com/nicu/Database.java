package com.nicu;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nicu on 6/2/17.
 */

public class Database {
    private String driver;
    private String connString;
    private String user;
    private String pass;
    private Connection con;


    public Database() {
//        this.driver = "org.gjt.mm.mysql.Driver";
        this.driver = "com.mysql.jdbc.Driver";
        this.connString = "jdbc:mysql://localhost:3306/lab9";
        this.user = "root";
        this.pass = "";
    }

    public void connect() {
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(connString, user, pass);
        } catch(Exception ex) {
            System.out.println("Error while connecting: "+ex.getMessage());
        }
    }

    public void setStartTime() {
        try {
            PreparedStatement pStmt = this.con.prepareStatement("UPDATE `snake` set startTime = NOW() where id = 1");
            pStmt.executeUpdate();
            System.out.println("Set start time");
        } catch (SQLException e) {
            System.out.println("Err" + e.getMessage());
            e.printStackTrace();
        }
    }


    public void setEndTime() {
        try {
            PreparedStatement pStmt = this.con.prepareStatement("UPDATE `snake` set endTime = NOW() where id = 1");
            pStmt.executeUpdate();
            System.out.println("SetEndTime");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Date getEndTime() {
        try {
            PreparedStatement pStmt = this.con.prepareStatement("Select TIME(endTime) from `snake` where id = 1");
        pStmt.executeQuery();
            ResultSet rs = pStmt.executeQuery();
            rs.next();
            return rs.getDate("endTime");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Time getStartTime() {
        try {
            PreparedStatement pStmt = this.con.prepareStatement("Select startTime from `snake` where id = 1");
            pStmt.executeQuery();
            ResultSet rs = pStmt.executeQuery();
            System.out.println("get start time");
            if(rs.next())
                return rs.getTime("startTime");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getTime() {
        try {

            PreparedStatement pStmt = this.con.prepareStatement("SELECT startTime, endTime FROM `snake` WHERE id = 1");
            ResultSet rs = pStmt.executeQuery();

            rs.next();
            Time time1 =  rs.getTime("startTime");
            System.out.println("time1: " + time1);
            Time time2 = rs.getTime("endTime");
            System.out.println("time2: "+time2);

            Timestamp ts1 = new java.sql.Timestamp(time1.getTime());
            Timestamp ts2 = new java.sql.Timestamp(time2.getTime());
            long difference = ts2.getTime() - ts1.getTime();

            System.out.println(difference);
            return TimeUnit.MILLISECONDS.toSeconds(difference);

        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return 0;
    }
}

