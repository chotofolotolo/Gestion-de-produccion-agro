package com.myproyect.miproyect.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class STDRespository {
    protected Connection conn;
    protected PreparedStatement pstmt;

    public STDRespository() {

        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campo_verde_sa",
                    "root",
                    "");
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
        }

    }
}
