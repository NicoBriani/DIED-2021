package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.EstacionDAO;
import dao.EstacionDAO_BD;
import dao.MantenimientoDAO;
import dao.MantenimientoDAO_BD;
import dominio.Estacion;
import dominio.Mantenimiento;

import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class NuevoMantenimiento extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textInicio;
	private JTextField textFin;

	public NuevoMantenimiento(Estacion estacion) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Nuevo mantenimietno");
		setResizable(false);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(new Color(102, 204, 255));
		
		// INICIALIZAR COMPONENTS

		textID = new JTextField();
		textID.setHorizontalAlignment(SwingConstants.CENTER);
		textID.setBounds(736, 200, 100, 20);
		panel.add(textID);
		textID.setColumns(10);

		textInicio = new JTextField();
		textInicio.setBounds(736, 250, 100, 20);
		panel.add(textInicio);
		textInicio.setColumns(10);

		textFin = new JTextField();
		textFin.setBounds(736, 300, 100, 20);
		panel.add(textFin);
		textFin.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblId.setBounds(428, 201, 46, 14);
		panel.add(lblId);

		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblInicio.setBounds(428, 248, 150, 20);
		panel.add(lblInicio);

		JLabel lblFin = new JLabel("Fin");
		lblFin.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblFin.setBounds(428, 298, 150, 20);
		panel.add(lblFin);

		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblObservaciones.setBounds(428, 348, 150, 20);
		panel.add(lblObservaciones);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAceptar.setBackground(new Color(255, 255, 255));
		btnAceptar.setBounds(736, 498, 100, 30);
		panel.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCancelar.setBounds(428, 498, 100, 30);
		btnCancelar.setBackground(new Color(255, 255, 255));
		panel.add(btnCancelar);

		JTextPane textObservaciones = new JTextPane();
		textObservaciones.setBounds(428, 379, 408, 93);
		panel.add(textObservaciones);

		
		// BTN ACTIONS
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Estaciones ventanaE = new Estaciones();
				ventanaE.setVisible(true);
				dispose();
			}
		});

		btnAceptar.addActionListener(e -> {
			if (!textID.getText().isEmpty() && !textInicio.getText().isEmpty() && !textFin.getText().isEmpty()) {

				Mantenimiento man = new Mantenimiento();
				
				
				try {
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date datoInicio1 = new java.util.Date();
					datoInicio1 = formato.parse(textInicio.getText());
					java.sql.Date datoInicio2 = new java.sql.Date(datoInicio1.getTime());
					java.util.Date datoFin1 = new java.util.Date();
					datoFin1 = formato.parse(textFin.getText());
					java.sql.Date datoFin2 = new java.sql.Date(datoFin1.getTime());
				
					man.setId(Integer.parseInt(textID.getText()));
					man.setInicio(datoInicio2);
					man.setFin(datoFin2);
					man.setObservaciones(textObservaciones.getText().toString());
					
					estacion.setUltimoMantenimiento(datoFin2);
					
					EstacionDAO ed = new EstacionDAO_BD();
					
					ed.editarEstacion(estacion);
					
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			

				

				MantenimientoDAO md = new MantenimientoDAO_BD();

				try {
					md.nuevoMantenimiento(man, estacion.getId());
					JOptionPane.showMessageDialog(null, "Mantenimiento cargado correctamente");
					final Estaciones ventanaE = new Estaciones();
					ventanaE.setVisible(true);
					dispose();
				} catch (NumberFormatException e1) {
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			else {
				JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");

			}
		});
	}

}
