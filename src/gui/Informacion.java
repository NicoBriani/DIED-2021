package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class Informacion extends JFrame {

	private JPanel contentPane;

	public Informacion() {
		
		getContentPane().setBackground(new Color(102, 204, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(102, 204, 255));
		setTitle("Informacion");
		setResizable(false);

		// INICIALIZAR COMPONENTS
		JButton btnFlujoMax = new JButton("Flujo maximo");
		btnFlujoMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnFlujoMax.setBounds(531, 179, 250, 80);
		btnFlujoMax.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnFlujoMax);

		JButton btnPageRank = new JButton("Page rank");
		btnPageRank.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnPageRank.setBounds(531, 289, 250, 80);
		btnPageRank.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnPageRank);

		JButton btnProximoMantenimiento = new JButton("Proximo mantenimiento");
		btnProximoMantenimiento.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnProximoMantenimiento.setBounds(531, 399, 250, 80);
		btnProximoMantenimiento.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnProximoMantenimiento);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAtras.setBounds(50, 576, 100, 30);
		btnAtras.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnAtras);
		
		// BTN ACTIONS

		btnProximoMantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 ProxMantenimiento ventanaPM = new ProxMantenimiento();
				ventanaPM.setVisible(true);
				dispose();
			}
		});

		btnFlujoMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlujoMax ventanaFM = new FlujoMax();
				ventanaFM.setVisible(true);
				dispose();
			}
		});

		btnPageRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JPanel(), "Modalidad aún no implementada", " ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Estaciones ventanaE = new Estaciones();
				ventanaE.setVisible(true);
				dispose();
			}

		});

	}

}
