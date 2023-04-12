package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import backend.Transaccion;
import backend.Querys;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.management.Query;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private DefaultTableModel model;
	private static Object[] rows;
	private Transaccion selected = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 13, 701, 45);
		panel.add(panel_1);
		
		JLabel label = new JLabel("Codigo");
		label.setBounds(10, 14, 46, 14);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(66, 8, 227, 26);
		panel_1.add(textField);
		
		JButton button = new JButton("Buscar");
		button.setBounds(303, 9, 89, 25);
		panel_1.add(button);
		
		JButton btnNewTransaccion = new JButton("Transaccion");
		btnNewTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransaccionForm frm = new TransaccionForm(null);
				frm.setVisible(true);
			}
		});
		btnNewTransaccion.setBounds(569, 9, 120, 25);
		panel_1.add(btnNewTransaccion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 69, 701, 324);
		panel.add(scrollPane);
		
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Querys.DeleteTransaccion(selected.getId_transaccion());
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(47, 406, 117, 45);
		panel.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransaccionForm frm = new TransaccionForm(selected);
				frm.setVisible(true);
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(571, 406, 117, 45);
		panel.add(btnModificar);
		
		JTable table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int aux = table.getSelectedRow();
				
				if (aux != -1) {
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
					String code = (String) table.getValueAt(aux, 0); // selecciona el codigo segun la celda selecionada con el numero de fila y columna
					selected = Querys.getTransaccion(code);
					System.out.print(selected.getId_transaccion());

				}
			}
		});
		model = new DefaultTableModel();
		String[] headers = {"ID","Monto","Tipo","fecha", "cargo", "Cuenta", "Cajero"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		

		
		cargarCuentas();
	}
	
	private void cargarCuentas() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		ArrayList<Transaccion> transacciones = Querys.getTransacciones();
		
		for (int i = 0; i < transacciones.size() ; i++) {
			rows[0] = transacciones.get(i).getId_transaccion();
			rows[1] = transacciones.get(i).getMonto();
			rows[2] = transacciones.get(i).getTipo();
			rows[3] = transacciones.get(i).getFecha();
			rows[4] = transacciones.get(i).getCargo();
			rows[5] = transacciones.get(i).getNum_cuenta();
			rows[6] = transacciones.get(i).getId_cajero();
			model.addRow(rows);
		}
		
	}
	
}
