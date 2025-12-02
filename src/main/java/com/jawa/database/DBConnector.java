//package com.jawa.database;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnector {
//    private static final String URL = "jdbc:mysql://localhost:3306/dungeon_game?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = ""; // default XAMPP: kosong
//
//    private static Connection connection = null;
//
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//
//                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//                System.out.println("✅ Koneksi ke database berhasil!");
//            } catch (ClassNotFoundException e) {
//                System.err.println("❌ MySQL JDBC Driver tidak ditemukan!");
//                e.printStackTrace();
//            } catch (SQLException e) {
//                System.err.println("❌ Gagal terhubung ke database!");
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//
//    public static void closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//                System.out.println("🔌 Koneksi ditutup.");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}