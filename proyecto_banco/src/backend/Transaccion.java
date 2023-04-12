package backend;

import java.sql.Date;

public class Transaccion {
	
	private String id_transaccion = "";
	private float monto = 0;
	private String tipo = "";
	private Date fecha = null;
	private float cargo = 0;
	private int num_cuenta = 0;
	private String id_cajero = "";
	
	
	public Transaccion(String id_transaccion, float monto, String tipo, Date fecha, float cargo, int num_cuenta,
			String id_cajero) {
		super();
		this.id_transaccion = id_transaccion;
		this.monto = monto;
		this.tipo = tipo;
		this.fecha = fecha;
		this.cargo = cargo;
		this.num_cuenta = num_cuenta;
		this.id_cajero = id_cajero;
	}


	public String getId_transaccion() {
		return id_transaccion;
	}


	public void setId_transaccion(String id_transaccion) {
		this.id_transaccion = id_transaccion;
	}


	public float getMonto() {
		return monto;
	}


	public void setMonto(int monto) {
		this.monto = monto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public float getCargo() {
		return cargo;
	}


	public void setCargo(float cargo) {
		this.cargo = cargo;
	}


	public int getNum_cuenta() {
		return num_cuenta;
	}


	public void setNum_cuenta(int num_cuenta) {
		this.num_cuenta = num_cuenta;
	}


	public String getId_cajero() {
		return id_cajero;
	}


	public void setId_cajero(String id_cajero) {
		this.id_cajero = id_cajero;
	}
	
	
	
	

}
