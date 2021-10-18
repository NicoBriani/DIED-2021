package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EstacionDAO;
import dao.EstacionDAO_BD;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class Boletos extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Boletos() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Boletos");
		setResizable(false);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		panelCentral.setBackground(new Color(102, 204, 255));

		// INICIALIZAR COMPONENTS

		JLabel lblEstacionO = new JLabel("Estacion origen");
		lblEstacionO.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstacionO.setBounds(459, 250, 150, 20);
		panelCentral.add(lblEstacionO);

		JLabel lblEstacionD = new JLabel("Esacion destino");
		lblEstacionD.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstacionD.setBounds(459, 310, 150, 20);
		panelCentral.add(lblEstacionD);

		JComboBox comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setBounds(749, 250, 60, 24);
		comboBoxOrigen.setBackground(new Color(255, 255, 255));
		panelCentral.add(comboBoxOrigen);

		JComboBox comboBoxDestino = new JComboBox();
		comboBoxDestino.setBounds(749, 310, 60, 24);
		comboBoxDestino.setBackground(new Color(255, 255, 255));
		panelCentral.add(comboBoxDestino);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAtras.setBounds(459, 419, 100, 30);
		btnAtras.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnAtras);

		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnSiguiente.setBounds(709, 419, 100, 30);
		btnSiguiente.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnSiguiente);

		EstacionDAO edao = new EstacionDAO_BD();

		for (int i = 0; i < edao.getIdEstaciones().size(); i++)
			comboBoxOrigen.addItem(edao.getIdEstaciones().get(i));

		for (int i = 0; i < edao.getIdEstaciones().size(); i++)
			comboBoxDestino.addItem(edao.getIdEstaciones().get(i));

		// BTN ACTIONS

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Princ ventanaP = new Princ();
				ventanaP.setVisible(true);
				dispose();
			}

		});

		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final NuevoBoleto ventanaNB = new NuevoBoleto(comboBoxOrigen.getSelectedItem().toString(),
						comboBoxDestino.getSelectedItem().toString());
				ventanaNB.setVisible(true);
				dispose();
			}

		});

	}
}
