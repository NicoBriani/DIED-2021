package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class PantallaInicio extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	JPanel panelCentral;
	JButton btnIngresar;

	public PantallaInicio() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Pantalla inicio");
		setResizable(false);

		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(102, 204, 255));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		

		// INICIALIZAR COMPONENTS
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBackground(new Color(255, 255, 255));
		btnIngresar.setFont(new Font("Dialog", Font.PLAIN, 15));

		btnIngresar.setBounds(597, 377, 100, 30);
		panelCentral.add(btnIngresar);

		textField = new JTextField();
		textField.setBounds(597, 256, 200, 20);
		panelCentral.add(textField);
		textField.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblUsuario.setBounds(488, 259, 70, 20);
		panelCentral.add(lblUsuario);

		JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblContraseña.setBounds(486, 304, 100, 20);
		panelCentral.add(lblContraseña);

		passwordField = new JPasswordField();
		passwordField.setBounds(597, 306, 200, 20);
		panelCentral.add(passwordField);

		// BTN ACTIONS

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Princ ventanaP = new Princ();
				ventanaP.setVisible(true);
				dispose();
			}
		});

	}

}
