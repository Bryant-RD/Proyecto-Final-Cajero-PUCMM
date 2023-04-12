package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Querys;
import backend.Transaccion;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class TransaccionForm extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TransaccionForm dialog = new TransaccionForm(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param selected 
	 */
	public TransaccionForm(Transaccion selected) {
		
		setBounds(100, 100, 494, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cajero");
		lblNewLabel.setBounds(12, 23, 56, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Monto");
		lblNewLabel_1.setBounds(12, 68, 56, 16);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo");
		lblNewLabel_2.setBounds(12, 115, 56, 16);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha");
		lblNewLabel_3.setBounds(259, 20, 56, 16);
		contentPanel.add(lblNewLabel_3);
		
		JTextField txtId_cajero = new JTextField();
		txtId_cajero.setEnabled(false);
		txtId_cajero.setEditable(false);
		txtId_cajero.setText("C00001");
		txtId_cajero.setBounds(80, 20, 137, 22);
		contentPanel.add(txtId_cajero);
		txtId_cajero.setColumns(10);
		
		JTextField txtMonto = new JTextField();
		txtMonto.setColumns(10);
		txtMonto.setBounds(80, 65, 137, 22);
		contentPanel.add(txtMonto);
		
		JTextField txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setEditable(false);
	     
		
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fechaActual.format(formato);
		Date fecha = Date.valueOf(fechaFormateada);
	     
       
		txtFecha.setText(fechaFormateada);
		txtFecha.setColumns(10);
		txtFecha.setBounds(327, 17, 137, 22);
		contentPanel.add(txtFecha);
		
		JComboBox cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<< Seleccionar >>", "Deposito", "Retiro"}));
		cbxTipo.setBounds(80, 112, 137, 22);
		contentPanel.add(cbxTipo);
		
		JLabel lblNewLabel_4 = new JLabel("Cuenta");
		lblNewLabel_4.setBounds(259, 65, 56, 16);
		contentPanel.add(lblNewLabel_4);
		
		JTextField txtCuenta = new JTextField();
		txtCuenta.setColumns(10);
		txtCuenta.setBounds(327, 62, 137, 22);
		contentPanel.add(txtCuenta);
		
		JLabel lblCargo = new JLabel("cargo");
		lblCargo.setBounds(259, 112, 56, 16);
		contentPanel.add(lblCargo);
		
		JTextField txtCargo = new JTextField();
		txtCargo.setColumns(10);
		txtCargo.setBounds(327, 109, 137, 22);
		contentPanel.add(txtCargo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Completar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (selected != null) {
							
							Transaccion trans = new Transaccion(selected.getId_transaccion(), Float.parseFloat(txtMonto.getText()),cbxTipo.getSelectedItem().toString() ,selected.getFecha(), Float.parseFloat(txtCargo.getText()), Integer.parseInt(txtCuenta.getText()),selected.getId_cajero());
							Querys.updateTransaccion(trans);
							dispose();
							
						} else {
							
							int rows = Querys.getTransacciones().size();
							String idTransaccion = "T0" + rows;
							Transaccion trans = new Transaccion(idTransaccion, Integer.parseInt(txtMonto.getText()),cbxTipo.getSelectedItem().toString() ,fecha, Float.parseFloat(txtCargo.getText()), Integer.parseInt(txtCuenta.getText()),txtId_cajero.getText());
							Querys.NewTransaccion(trans);
							
							txtMonto.setText("");
							cbxTipo.setSelectedIndex(0);
							txtCargo.setText("");
							txtCuenta.setText("");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		if(selected != null) {
			txtMonto.setText(Float.toString(selected.getMonto()));
			cbxTipo.setSelectedItem(selected.getTipo());
			txtCuenta.setText(Integer.toString(selected.getNum_cuenta()));
			txtCargo.setText(Float.toString(selected.getCargo()));
			
		}
		
		
	}
}
