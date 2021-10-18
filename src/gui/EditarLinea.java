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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.LineaDAO;
import dao.LineaDAO_BD;

import dominio.Linea;

@SuppressWarnings("serial")
public class EditarLinea extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textColor;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EditarLinea(Linea l) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Editar lineas");
		setResizable(false);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(new Color(102, 204, 255));

		// INICIALIZAR COMPONENTS

		textNombre = new JTextField();
		textNombre.setBounds(736, 250, 100, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);

		textColor = new JTextField();
		textColor.setBounds(736, 300, 100, 20);
		panel.add(textColor);
		textColor.setColumns(10);

		textNombre.setText(l.getNombre().toString());
		textColor.setText(l.getColor().toString());

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBoxEstado.setBackground(new Color(255, 255, 255));
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Activa", "No Activa" }));
		comboBoxEstado.setBounds(686, 348, 150, 24);
		panel.add(comboBoxEstado);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNombre.setBounds(428, 248, 150, 20);
		panel.add(lblNombre);

		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblColor.setBounds(428, 298, 150, 20);
		panel.add(lblColor);

		JLabel lblEstado = new JLabel("Estado de la linea");
		lblEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstado.setBounds(428, 348, 150, 20);
		panel.add(lblEstado);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAceptar.setBounds(736, 498, 100, 30);
		btnAceptar.setBackground(new Color(255, 255, 255));
		panel.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setBounds(428, 498, 100, 30);
		panel.add(btnCancelar);

		// BTN ACTIONS

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Lineas ventanaL = new Lineas();
				ventanaL.setVisible(true);
				dispose();
			}
		});

		btnAceptar.addActionListener(e -> {
			if (!textNombre.getText().isEmpty() && !textColor.getText().isEmpty()) {

				Linea lin = new Linea();

				lin.setNombre(textNombre.getText());
				lin.setColor(textColor.getText());
				lin.setEstado(comboBoxEstado.getSelectedItem().toString());

				LineaDAO ld = new LineaDAO_BD();

				try {
					ld.editarLinea(lin);
					JOptionPane.showMessageDialog(null, "Estacion cargada correctamente");
					this.revalidate();
					this.repaint();
				} catch (NumberFormatException e1) {
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");

				}
			}

			else {
				JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");

			}
		});

	}

}
