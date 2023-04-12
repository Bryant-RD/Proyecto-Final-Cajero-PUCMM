package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

public class Querys {
	
	public static ArrayList<Transaccion> getTransacciones() {
		
		ArrayList<Transaccion> cuentas = new ArrayList<Transaccion>();
		
		String selectAll = "SELECT * FROM Transaccion";
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conexion = Conexion.getConexion();
			ps = conexion.prepareStatement(selectAll);
			rs = ps.executeQuery();
			
			while (rs.next()) {
			
				String id_transaccion = rs.getString("id_transaccion");
				float monto = rs.getFloat("monto");
				String tipo = rs.getString("tipo");
				Date fecha = rs.getDate("fecha");
				float cargo = rs.getFloat("cargo");
				int num_cuenta = rs.getInt("num_cuenta");
				String id_cajero = rs.getString("id_cajero");
			
				Transaccion aux = new Transaccion(id_transaccion, monto, tipo, fecha, cargo, num_cuenta, id_cajero);
				
				cuentas.add(aux);
				
				
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} finally {
			try {
				Conexion.close(rs);
				Conexion.close(ps);
				Conexion.close(conexion);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			}
		}
		
		return cuentas;
	}
	
	public static void NewTransaccion(Transaccion transaccion) {
		
		String insert = "INSERT INTO Transaccion(id_transaccion, monto, tipo, fecha, cargo, num_cuenta, id_cajero) VALUES(?,?,?,?,?,?,?)";
		
		Connection conexion = null;
		PreparedStatement ps = null;
		int registros = 0;
		
		try {
			conexion = Conexion.getConexion();
			ps = conexion.prepareStatement(insert);
			ps.setString(1, transaccion.getId_transaccion());
			ps.setFloat(2, transaccion.getMonto());
			ps.setString(3, transaccion.getTipo());
			ps.setDate(4, transaccion.getFecha());
			ps.setFloat(5, transaccion.getCargo());
			ps.setInt(6, transaccion.getNum_cuenta());
			ps.setString(7, transaccion.getId_cajero());
			
			registros = ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Transaccion procesada correctamente");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} finally {
			try {
				Conexion.close(ps);
				Conexion.close(conexion);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			}
		}
	}
	
	public static Transaccion getTransaccion(String code) {
	    Transaccion transaccion = null;
	    String select = "SELECT * FROM Transaccion where id_transaccion = ? ";
	    Connection conexion = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conexion = Conexion.getConexion();
	        ps = conexion.prepareStatement(select);
	        ps.setString(1, code);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String id_transaccion = rs.getString("id_transaccion");
	            float monto = rs.getFloat("monto");
	            String tipo = rs.getString("tipo");
	            Date fecha = rs.getDate("fecha");
	            float cargo = rs.getFloat("cargo");
	            int num_cuenta = rs.getInt("num_cuenta");
	            String id_cajero = rs.getString("id_cajero");

	            transaccion = new Transaccion(id_transaccion, monto, tipo, fecha, cargo, num_cuenta, id_cajero);

	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, e.toString());
	    } finally {
	        try {
	            Conexion.close(rs);
	            Conexion.close(ps);
	            Conexion.close(conexion);
	        } catch (SQLException e) {
	            e.printStackTrace(System.out);
	        }
	    }

	    return transaccion;
	}
	
	
	public static void updateTransaccion(Transaccion trans) {
	    
	    Connection conexion = null;
	    PreparedStatement ps = null;
	    String update = "UPDATE Transaccion SET monto=?, tipo=?, fecha=?, cargo=?, num_cuenta=?, id_cajero=? WHERE id_transaccion=?";
	    
	    try {
	        conexion = Conexion.getConexion();
	        ps = conexion.prepareStatement(update);
	        ps.setFloat(1, trans.getMonto());
	        ps.setString(2, trans.getTipo());
	        ps.setDate(3, trans.getFecha());
	        ps.setFloat(4, trans.getCargo());
	        ps.setInt(5, trans.getNum_cuenta());
	        ps.setString(6, trans.getId_cajero());
	        ps.setString(7, trans.getId_transaccion());
	        ps.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Transaccion actualizada correctamente");
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al actualizar la transaccion: " + e.getMessage());
	        
	    } finally {
	        try {
	            Conexion.close(ps);
	            Conexion.close(conexion);
	        } catch (SQLException e) {
	            e.printStackTrace(System.out);
	        }
	    }
	}
	
	public static void  DeleteTransaccion (String code) {
		
	    String delete = "DELETE FROM Transaccion where id_transaccion = ? ";
	    Connection conexion = null;
	    PreparedStatement ps = null;
	    int registros = 0;

	    try {
	        conexion = Conexion.getConexion();
	        ps = conexion.prepareStatement(delete);
	        ps.setString(1, code);
	        registros = ps.executeUpdate();


	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, e.toString());
	    } finally {
	        try {
	            Conexion.close(ps);
	            Conexion.close(conexion);
	        } catch (SQLException e) {
	            e.printStackTrace(System.out);
	        }
	    }
		
	}

	

}
