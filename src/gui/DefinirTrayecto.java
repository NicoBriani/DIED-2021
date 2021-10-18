package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import dao.EstacionDAO;
import dao.EstacionDAO_BD;

import dao.RutaDAO;
import dao.RutaDAO_BD;
import dao.TrayectoDAO;
import dao.TrayectoDAO_BD;
import dominio.Estacion;

import dominio.Ruta;
import dominio.Trayecto;

import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class DefinirTrayecto extends JFrame {

	private JPanel contentPane;
	private JTextField textDistancia;
	private JTextField textCantMax;
	private JTextField textCosto;
	private JTable table;
	private JTextField textDuracion;
	TrayectoDAO tdao = new TrayectoDAO();
	TrayectoDAO_BD tdaoBD = new TrayectoDAO_BD();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DefinirTrayecto(String lineaSeleccionada) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Definir trayecto");
		setResizable(false);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(new Color(102, 204, 255));

		// INICIALIZAR COMPONENT

		textDistancia = new JTextField();
		textDistancia.setBounds(523, 250, 100, 20);
		panel.add(textDistancia);
		textDistancia.setColumns(10);

		textCantMax = new JTextField();
		textCantMax.setBounds(523, 350, 100, 20);
		panel.add(textCantMax);
		textCantMax.setColumns(10);

		textCosto = new JTextField();
		textCosto.setBounds(523, 450, 100, 20);
		panel.add(textCosto);
		textCosto.setColumns(10);

		textDuracion = new JTextField();
		textDuracion.setColumns(10);
		textDuracion.setBounds(523, 300, 100, 20);
		panel.add(textDuracion);

		JLabel lblDestino = new JLabel("Estacion Destino");
		lblDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDestino.setBounds(215, 200, 128, 20);
		panel.add(lblDestino);

		JLabel lblDistancia = new JLabel("Distancia (Km)");
		lblDistancia.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDistancia.setBounds(215, 250, 150, 20);
		panel.add(lblDistancia);

		JLabel lblCantMax = new JLabel("Cantidad maxima de pasajeros");
		lblCantMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCantMax.setBounds(215, 350, 220, 20);
		panel.add(lblCantMax);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstado.setBounds(215, 400, 150, 20);
		panel.add(lblEstado);

		JLabel lblCosto = new JLabel("Costo ($)");
		lblCosto.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCosto.setBounds(215, 450, 150, 20);
		panel.add(lblCosto);

		JLabel lblDuracion = new JLabel("Duracion (Min)");
		lblDuracion.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDuracion.setBounds(215, 300, 150, 20);
		panel.add(lblDuracion);

		JLabel lblOrigen = new JLabel("Estacion Origen");
		lblOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblOrigen.setBounds(215, 150, 128, 20);
		panel.add(lblOrigen);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAceptar.setBounds(523, 500, 100, 30);
		panel.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCancelar.setBounds(215, 500, 100, 30);
		panel.add(btnCancelar);

		JButton btnVerTrayecto = new JButton("Ver trayecto");
		btnVerTrayecto.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnVerTrayecto.setBounds(917, 500, 130, 30);
		btnVerTrayecto.setBackground(new Color(255, 255, 255));
		panel.add(btnVerTrayecto);

		JComboBox comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBoxOrigen.setBackground(new Color(255, 255, 255));
		comboBoxOrigen.setBounds(473, 150, 150, 24);
		panel.add(comboBoxOrigen);

		JComboBox comboBoxDestino = new JComboBox();
		comboBoxDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBoxDestino.setBackground(new Color(255, 255, 255));
		comboBoxDestino.setBounds(473, 200, 150, 24);
		panel.add(comboBoxDestino);

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBoxEstado.setBackground(new Color(255, 255, 255));
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Activa", "Inactiva" }));
		comboBoxEstado.setBounds(473, 400, 150, 24);
		panel.add(comboBoxEstado);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(747, 150, 300, 320);
		scrollPane.setBackground(new Color(255, 255, 255));
		panel.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Dialog", Font.PLAIN, 15));

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Origen", "Destino" }));

		scrollPane.setViewportView(table);
	
		// TABLA ACTIONS
		
		EstacionDAO edao = new EstacionDAO_BD();
		RutaDAO rdao = new RutaDAO_BD();
		
		
		for (int i = 0; i < edao.getIdEstaciones().size(); i++)
			comboBoxOrigen.addItem(edao.getIdEstaciones().get(i));
		for (int i = 0; i < edao.getIdEstaciones().size(); i++)
			comboBoxDestino.addItem(edao.getIdEstaciones().get(i));

		try {
			rdao.llenarTabla(table, lineaSeleccionada);
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		

		
		// BTN ACTIONS
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lineas ventanaL = new Lineas();
				ventanaL.setVisible(true);
				dispose();
			}
		});

		Trayecto t = new Trayecto();
		t.setIdLinea(lineaSeleccionada);

		btnVerTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerTrayecto ventanaVT = new VerTrayecto(tdaoBD.getTrayecto(t), lineaSeleccionada);
				ventanaVT.setVisible(true);
				dispose();
			}
		});

		btnAceptar.addActionListener(e -> {
			if (!textDuracion.getText().isEmpty() && !textCantMax.getText().isEmpty() && !textCosto.getText().isEmpty()
					&& !textDistancia.getText().isEmpty()) {

				Estacion origen = new Estacion();
				Estacion destino = new Estacion();

				origen.setId(Integer.parseInt(comboBoxOrigen.getSelectedItem().toString()));
				destino.setId(Integer.parseInt(comboBoxDestino.getSelectedItem().toString()));
				Ruta ruta = new Ruta();

				try {
					ruta.setId(rdao.generarIdRuta());
					ruta.setOrigen(edao.getEstacion(origen));
					ruta.setDestino(edao.getEstacion(destino));
					ruta.setCantMaxPasajeros(Integer.parseInt(textCantMax.getText()));
					ruta.setDistancia(Double.parseDouble(textDistancia.getText()));
					ruta.setCosto(Double.parseDouble(textCosto.getText()));
					ruta.setDuracionViaje(Integer.parseInt(textDuracion.getText()));
					ruta.setEstado(comboBoxEstado.getSelectedItem().toString());
					ruta.setLineaId(lineaSeleccionada);
					rdao.nuevaRuta(ruta);
					JOptionPane.showMessageDialog(null, "Estacion cargada correctamente");

					tdao.gestionarTrayecto(ruta);

					this.revalidate();
					this.repaint();
					rdao.limpiarTabla(table);
					rdao.llenarTabla(table, lineaSeleccionada);

				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

			else
				JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");

		});

	}
}
