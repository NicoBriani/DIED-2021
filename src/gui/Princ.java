package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class Princ extends JFrame {

	private JPanel contentPane;

	public Princ() {
		getContentPane().setBackground(new Color(102, 204, 255));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(102, 204, 255));
		setTitle("Pantalla principal");
		setResizable(false);

		// INICIALIZAR COMPONENTS
		JButton btnEstaciones = new JButton("Estaciones");
		btnEstaciones.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnEstaciones.setBounds(531, 179, 250, 80);
		btnEstaciones.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnEstaciones);

		JButton btnLineas = new JButton("Lineas");
		btnLineas.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnLineas.setBounds(531, 289, 250, 80);
		btnLineas.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnLineas);

		JButton btnBoleto = new JButton("Boleto");
		btnBoleto.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBoleto.setBounds(531, 399, 250, 80);
		btnBoleto.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnBoleto);

		// BTN ACTIONS

		btnBoleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boletos ventanaB = new Boletos();
				ventanaB.setVisible(true);
				dispose();
			}
		});

		btnEstaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estaciones ventanaE = new Estaciones();
				ventanaE.setVisible(true);
				dispose();
			}
		});

		btnLineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lineas ventanaL = new Lineas();
				ventanaL.setVisible(true);
				dispose();
			}
		});

	}

}
