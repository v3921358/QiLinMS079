/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.custom.treasure_house;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class treasure_x {

    /*
    treasure_house
    藏宝记录表
     */
 /*
    0没有，1有
     */
    public static int 判断本地图有没有宝藏(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("判断本地图有没有宝藏_出错。");
        }
        return data;
    }

    public static int 取藏宝坐标x(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("x");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("取藏宝坐标x_出错。");
        }
        return data;
    }

    public static int 取藏宝坐标y(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("y");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("取藏宝坐标y_出错。");
        }
        return data;
    }

}
