package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EstacionDAO;
import dao.EstacionDAO_BD;
import dominio.Estacion;
import estructura.Monticulo;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class ProxMantenimiento extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textNombre;
	private JTextField textUltimoMant;
	
	public ProxMantenimiento() {
		
		getContentPane().setBackground(new Color(102, 204, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(102, 204, 255));
		setTitle("Proximo mantenimiento");
		setResizable(false);

		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setHorizontalAlignment(SwingConstants.CENTER);
		textID.setBounds(758, 282, 100, 20);
		getContentPane().add(textID);
		textID.setColumns(10);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(758, 332, 100, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textUltimoMant = new JTextField();
		textUltimoMant.setEditable(false);
		textUltimoMant.setBounds(758, 382, 100, 20);
		getContentPane().add(textUltimoMant);
		textUltimoMant.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblId.setBounds(450, 283, 46, 14);
		getContentPane().add(lblId);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNombre.setBounds(450, 330, 150, 20);
		getContentPane().add(lblNombre);

		JLabel lblUltimoMantenimiento = new JLabel("Ultimo mantenimiento");
		lblUltimoMantenimiento.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblUltimoMantenimiento.setBounds(450, 380, 150, 20);
		getContentPane().add(lblUltimoMantenimiento);
		
		
		JLabel lblNewLabel = new JLabel("Proximo mantenimiento:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setBounds(549, 207, 207, 30);
		getContentPane().add(lblNewLabel);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAtras.setBounds(50, 576, 100, 30);
		btnAtras.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnAtras);
		
		
		EstacionDAO edao = new EstacionDAO_BD();
		
		ArrayList<Estacion> estaciones = new ArrayList<Estacion>(edao.getEstaciones()) ;
		
		Monticulo proxMan = new Monticulo();
	
		Estacion estacion = proxMan.ultMantenimiento(estaciones);
		
		textID.setText(estacion.getId().toString());
		textNombre.setText(estacion.getNombre());
		textUltimoMant.setText(estacion.getUltimoMantenimiento().toString());
		
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Informacion ventanaI = new Informacion();
				ventanaI.setVisible(true);
				dispose();
			}

		});
	}
}
