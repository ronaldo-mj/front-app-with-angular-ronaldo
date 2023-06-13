package br.com.garage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {
    public static Connection getConnection() throws Exception{
        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/garagem","postgres","123456");
        } catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
        public static void close(Connection conn,Statement stmt,ResultSet rs) throws Exception{
            try{
                if(rs!=null)rs.close();
                if(rs!=null)stmt.close();
                if(rs!=null)conn.close();
            } catch(Exception ex){
                throw new Exception(ex.getMessage());
            }
        }
        public static void closeConnection(Connection conn,Statement stmt,ResultSet rs) throws Exception{
            close(conn,stmt,rs);
        }
        public static void closeConnection(Connection conn,Statement stmt) throws Exception{
            close(conn,stmt,null);
        }
        public static void closeConnection(Connection conn) throws Exception{
            close(conn,null,null);
        }
}
