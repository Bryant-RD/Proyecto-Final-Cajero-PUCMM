package backend;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	
	public static Connection getConexion() {
		 String url = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=Proyecto_Cajero";
	        String user = "test";
	        String password = "123456";

	        try {
	            Connection connection = DriverManager.getConnection(url, user, password);
	            System.out.println("Conexión exitosa a SQL Server");
	            return connection;
	        } catch (SQLException e) {
	            System.out.println("Error al conectarse a SQL Server: " + e.getMessage());
	            return null;
	        }
			
	}
	
	public static void close(ResultSet rs) throws SQLException {
		rs.close();
	}
	
	public static void close(Statement smtm) throws SQLException {
		((Connection) smtm).close();
	}
	public static void close(PreparedStatement smtm) throws SQLException {
		smtm.close();
	}

	public static void close(Connection conn) throws SQLException {
		conn.close();
	}

}
