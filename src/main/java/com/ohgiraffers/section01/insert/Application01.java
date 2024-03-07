package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("insertMenu");


            System.out.println("query = " + query);
            // 필기 요기에 scaner 넣어서 입력가능함
            try {
                pstmt =con.prepareStatement(query);

                pstmt.setString(1,"봉골레청국장");
                pstmt.setInt(2,50000);
                pstmt.setInt(3,1);
                pstmt.setString(4,"Y");

                result = pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);}
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

        System.out.println("result = " + result);

    }
}
