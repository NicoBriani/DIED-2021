package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.EstacionDAO;
import dao.EstacionDAO_BD;

import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BuscarEstacion extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textApertura;
	private JTextField textCierre;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BuscarEstacion() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Buscar estacion");
		setResizable(false);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		panelCentral.setBackground(new Color(102, 204, 255));

		// INICIALIZAR COMPONENTS

		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAtras.setBounds(114, 581, 150, 30);
		btnAtras.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnAtras);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBuscar.setBounds(1011, 581, 150, 30);
		btnBuscar.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnBuscar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 191, 1050, 362);
		scrollPane.setBackground(new Color(255, 255, 255));
		panelCentral.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Apertura", "Cierre", "Estado" }));

		scrollPane.setViewportView(table);

		JCheckBox chckbxId = new JCheckBox("Id");
		chckbxId.setFont(new Font("Dialog", Font.PLAIN, 15));
		chckbxId.setBackground(new Color(102, 204, 255));
		chckbxId.setBounds(114, 24, 55, 23);
		panelCentral.add(chckbxId);

		JCheckBox chckbxNombre = new JCheckBox("Nombre");
		chckbxNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		chckbxNombre.setBounds(114, 105, 78, 23);
		chckbxNombre.setBackground(new Color(102, 204, 255));
		panelCentral.add(chckbxNombre);

		JCheckBox chckbxApertura = new JCheckBox("Apertura");
		chckbxApertura.setFont(new Font("Dialog", Font.PLAIN, 15));
		chckbxApertura.setBounds(492, 24, 89, 23);
		chckbxApertura.setBackground(new Color(102, 204, 255));
		panelCentral.add(chckbxApertura);

		JCheckBox chckbxCierre = new JCheckBox("Cierre");
		chckbxCierre.setFont(new Font("Dialog", Font.PLAIN, 15));
		chckbxCierre.setBounds(492, 105, 89, 23);
		chckbxCierre.setBackground(new Color(102, 204, 255));
		panelCentral.add(chckbxCierre);

		JCheckBox chckbxEstado = new JCheckBox("Estado");
		chckbxEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		chckbxEstado.setBounds(911, 23, 93, 23);
		chckbxEstado.setBackground(new Color(102, 204, 255));
		panelCentral.add(chckbxEstado);

		textId = new JTextField();
		textId.setEditable(false);
		textId.setEnabled(false);
		textId.setBounds(217, 24, 150, 20);
		panelCentral.add(textId);
		textId.setColumns(10);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setEnabled(false);
		textNombre.setColumns(10);
		textNombre.setBounds(217, 105, 150, 20);
		panelCentral.add(textNombre);

		textApertura = new JTextField();
		textApertura.setEditable(false);
		textApertura.setEnabled(false);
		textApertura.setColumns(10);
		textApertura.setBounds(615, 24, 150, 20);
		panelCentral.add(textApertura);

		textCierre = new JTextField();
		textCierre.setEditable(false);
		textCierre.setEnabled(false);
		textCierre.setColumns(10);
		textCierre.setBounds(615, 105, 150, 20);
		panelCentral.add(textCierre);

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setEnabled(false);
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Operativa", "Mantenimiento" }));
		comboBoxEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBoxEstado.setBackground(new Color(255, 255, 255));
		comboBoxEstado.setBounds(1011, 22, 150, 24);
		panelCentral.add(comboBoxEstado);

		// CHCKBX ACTIONS
		chckbxId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxId.isSelected()) {
					textId.setEditable(true);
					textId.setEnabled(true);
				} else {
					textId.setEditable(false);
					textId.setEnabled(false);
				}

			}
		});

		chckbxNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxNombre.isSelected()) {
					textNombre.setEditable(true);
					textNombre.setEnabled(true);
				} else {
					textNombre.setEditable(false);
					textNombre.setEnabled(false);
				}

			}
		});

		chckbxApertura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxApertura.isSelected()) {
					textApertura.setEditable(true);
					textApertura.setEnabled(true);
				} else {
					textApertura.setEditable(false);
					textApertura.setEnabled(false);
				}

			}
		});

		chckbxCierre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxCierre.isSelected()) {
					textCierre.setEditable(true);
					textCierre.setEnabled(true);
				} else {
					textCierre.setEditable(false);
					textCierre.setEnabled(false);
				}

			}
		});

		chckbxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxEstado.isSelected())
					comboBoxEstado.setEnabled(true);

				else
					comboBoxEstado.setEnabled(false);

			}
		});

		// BTN ACTIONS

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Estaciones ventanaE = new Estaciones();
				ventanaE.setVisible(true);
				dispose();
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EstacionDAO ed = new EstacionDAO_BD();
				ed.limpiarTabla(table);

				try {
					if (chckbxId.isSelected())
						ed.llenarTablaParaBusqueda(table, "id_estacion", textId.getText().toString());
					if (chckbxNombre.isSelected())
						ed.llenarTablaParaBusqueda(table, "nombre_estacion", textNombre.getText().toString());
					if (chckbxApertura.isSelected())
						ed.llenarTablaParaBusqueda(table, "apertura", textApertura.getText().toString());
					if (chckbxCierre.isSelected())
						ed.llenarTablaParaBusqueda(table, "cierre", textCierre.getText().toString());
					if (chckbxEstado.isSelected())
						ed.llenarTablaParaBusqueda(table, "estado", comboBoxEstado.getSelectedItem().toString());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});

	}
}
