package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.EstacionDAO;
import dao.EstacionDAO_BD;
import dominio.Estacion;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class EditarEstacion extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textNombre;
	private JTextField textHApertura;
	private JTextField textHCierre;
	Boolean flag = false;
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EditarEstacion(Estacion estacion) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Editar estacion");
		setResizable(false);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(new Color(102, 204, 255));

		// INICIALIZAR COMPONENTS

		textID = new JTextField();
		textID.setHorizontalAlignment(SwingConstants.CENTER);
		textID.setEditable(false);
		textID.setBounds(736, 200, 100, 20);
		panel.add(textID);
		textID.setColumns(10);

		textNombre = new JTextField();
		textNombre.setBounds(736, 250, 100, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);

		textHApertura = new JTextField();
		textHApertura.setBounds(736, 300, 100, 20);
		panel.add(textHApertura);
		textHApertura.setColumns(10);

		textHCierre = new JTextField();
		textHCierre.setBounds(736, 350, 100, 20);
		panel.add(textHCierre);
		textHCierre.setColumns(10);

		JComboBox comboBoxEstado = new JComboBox();

		comboBoxEstado.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBoxEstado.setBackground(new Color(255, 255, 255));
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Operativa", "Mantenimiento", }));
		comboBoxEstado.setBounds(686, 400, 150, 24);
		panel.add(comboBoxEstado);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setBounds(428, 201, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNombre.setBounds(428, 248, 150, 20);
		panel.add(lblNombre);

		JLabel lblHorarioDeApertura = new JLabel("Horario de Apertura");
		lblHorarioDeApertura.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblHorarioDeApertura.setBounds(428, 298, 150, 20);
		panel.add(lblHorarioDeApertura);

		JLabel lblHorarioDeCierre = new JLabel("Horario de cierre");
		lblHorarioDeCierre.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblHorarioDeCierre.setBounds(428, 348, 150, 20);
		panel.add(lblHorarioDeCierre);

		JLabel lblEstadoDeLa = new JLabel("Estado de la estacion");
		lblEstadoDeLa.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstadoDeLa.setBounds(428, 400, 150, 20);
		panel.add(lblEstadoDeLa);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAceptar.setBackground(new Color(255, 255, 255));
		btnAceptar.setBounds(736, 498, 100, 30);
		panel.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setBounds(428, 498, 100, 30);
		panel.add(btnCancelar);

		textID.setText(estacion.getId().toString());
		textNombre.setText(estacion.getNombre().toString());
		textHApertura.setText(estacion.getApertura().toString());
		textHCierre.setText(estacion.getCierre().toString());
		Date ultimoMan = (Date) estacion.getUltimoMantenimiento();
		
		flag = false;
		if (estacion.getEstado() == "Operativa")
			flag = true;

		// BTN ACTIONS

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Estaciones ventanaE = new Estaciones();
				ventanaE.setVisible(true);
				dispose();
			}
		});

		btnAceptar.addActionListener(e -> {
			if (!textID.getText().isEmpty() && !textNombre.getText().isEmpty() && !textHApertura.getText().isEmpty()
					&& !textHCierre.getText().isEmpty()) {

				Estacion est = new Estacion();

				est.setId(Integer.parseInt(textID.getText()));
				est.setNombre(textNombre.getText());
				est.setApertura(textHApertura.getText());
				est.setCierre(textHCierre.getText());
				est.setEstado(comboBoxEstado.getSelectedItem().toString());
				est.setUltimoMantenimiento(ultimoMan);
				
				EstacionDAO ed = new EstacionDAO_BD();

				try {
					ed.editarEstacion(est);
					if (flag.booleanValue())
						if (comboBoxEstado.getSelectedItem().toString() == "Mantenimiento") {
							final NuevoMantenimiento ventanaNM = new NuevoMantenimiento(est);
							ventanaNM.setVisible(true);
							dispose();
						} else {
							final Estaciones ventanaE = new Estaciones();
							ventanaE.setVisible(true);
							dispose();
						}

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
