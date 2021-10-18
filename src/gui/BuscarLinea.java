package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.LineaDAO;
import dao.LineaDAO_BD;

@SuppressWarnings("serial")
public class BuscarLinea extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textNombre;
	private JTextField textColor;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BuscarLinea() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Buscar linea");
		setResizable(false);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		panelCentral.setBackground(new Color(102, 204, 255));

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
		panelCentral.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Color", "Estado" }));
		scrollPane.setViewportView(table);

		// INICIALIZAR COMPONENTS

		JCheckBox chckbxNombre = new JCheckBox("Nombre");
		chckbxNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		chckbxNombre.setBounds(114, 24, 97, 23);
		chckbxNombre.setBackground(new Color(102, 204, 255));
		panelCentral.add(chckbxNombre);

		JCheckBox chckbxColor = new JCheckBox("Color");
		chckbxColor.setFont(new Font("Dialog", Font.PLAIN, 15));
		chckbxColor.setBackground(new Color(102, 204, 255));
		chckbxColor.setBounds(492, 24, 89, 23);
		panelCentral.add(chckbxColor);

		JCheckBox chckbxEstado = new JCheckBox("Estado");
		chckbxEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		chckbxEstado.setBackground(new Color(102, 204, 255));
		chckbxEstado.setBounds(911, 23, 93, 23);
		panelCentral.add(chckbxEstado);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setEnabled(false);
		textNombre.setBounds(217, 24, 150, 20);
		panelCentral.add(textNombre);
		textNombre.setColumns(10);

		textColor = new JTextField();
		textColor.setEditable(false);
		textColor.setEnabled(false);
		textColor.setColumns(10);
		textColor.setBounds(615, 24, 150, 20);
		panelCentral.add(textColor);

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setEnabled(false);
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Activa", "No activa" }));
		comboBoxEstado.setBackground(new Color(255, 255, 255));
		comboBoxEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBoxEstado.setBounds(1011, 22, 150, 24);
		panelCentral.add(comboBoxEstado);

		// CHCKBX ACTIONS

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

		chckbxColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxColor.isSelected()) {
					textColor.setEditable(true);
					textColor.setEnabled(true);
				} else {
					textColor.setEditable(false);
					textColor.setEnabled(false);
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
				final Lineas ventanaL = new Lineas();
				ventanaL.setVisible(true);
				dispose();
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LineaDAO ld = new LineaDAO_BD();
				ld.limpiarTabla(table);

				try {
					if (chckbxNombre.isSelected())
						ld.llenarTablaParaBusqueda(table, "nombre_linea", textNombre.getText().toString());
					if (chckbxColor.isSelected())
						ld.llenarTablaParaBusqueda(table, "color", textColor.getText().toString());
					if (chckbxEstado.isSelected())
						ld.llenarTablaParaBusqueda(table, "estado", comboBoxEstado.getSelectedItem().toString());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
	}

}
