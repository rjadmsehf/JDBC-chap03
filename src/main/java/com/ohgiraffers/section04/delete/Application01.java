package com.ohgiraffers.section04.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pmts = null;

        int reult = 0 ;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            Scanner sc = new Scanner(System.in);
            System.out.println("삭제할 코드 입력");
            int del = sc.nextInt();

            String d = prop.getProperty("delete");

            pmts = con.prepareStatement(d);

           pmts.setInt(1,del);

           reult = pmts.executeUpdate();



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pmts);
        }
        if (reult == 0) {
            System.out.println("등록 실패");
        } else{
            System.out.println("등록성");
        }

    }
}
