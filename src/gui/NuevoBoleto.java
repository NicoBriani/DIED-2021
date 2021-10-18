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

import dao.BoletoDAO;
import dao.BoletoDAO_BD;
import dao.EstacionDAO;
import dao.EstacionDAO_BD;
import dao.TrayectoDAO_BD;
import dominio.Boleto;
import dominio.Estacion;

@SuppressWarnings("serial")
public class NuevoBoleto extends JFrame {

	private JPanel contentPane;
	private JTextField textOrigen;
	private JTextField textNombre;
	private JTextField textNumero;
	private JTextField textCorreo;
	private JTextField textDestino;
	private JTextField textFecha;
	private JTextField textCosto;
	private JTextField textRapido;
	private JTextField textBarato;
	private JTextField textDistancia;
	Integer menorDuracion;
	Double menorCosto;
	Double menorDistancia;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NuevoBoleto(String origen, String destino) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Nuevo boleto");
		setResizable(false);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(new Color(102, 204, 255));

		// INICIALIZAR COMPONENTS

		textOrigen = new JTextField();
		textOrigen.setEditable(false);
		textOrigen.setBounds(480, 86, 54, 20);
		panel.add(textOrigen);
		textOrigen.setColumns(10);
		textOrigen.setText(origen);

		textNombre = new JTextField();
		textNombre.setBounds(480, 133, 100, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);

		textNumero = new JTextField();
		textNumero.setBounds(480, 183, 100, 20);
		panel.add(textNumero);
		textNumero.setColumns(10);

		textCorreo = new JTextField();
		textCorreo.setBounds(480, 233, 411, 20);
		panel.add(textCorreo);
		textCorreo.setColumns(10);

		textDestino = new JTextField();
		textDestino.setEditable(false);
		textDestino.setColumns(10);
		textDestino.setBounds(837, 88, 54, 20);
		panel.add(textDestino);
		textDestino.setText(destino);

		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(791, 135, 100, 20);
		panel.add(textFecha);

		textCosto = new JTextField();
		textCosto.setColumns(10);
		textCosto.setBounds(791, 183, 100, 20);
		panel.add(textCosto);

		textRapido = new JTextField();
		textRapido.setEditable(false);
		textRapido.setColumns(10);
		textRapido.setBounds(567, 302, 324, 20);
		panel.add(textRapido);

		textBarato = new JTextField();
		textBarato.setEditable(false);
		textBarato.setColumns(10);
		textBarato.setBounds(567, 349, 324, 20);
		panel.add(textBarato);

		textDistancia = new JTextField();
		textDistancia.setEditable(false);
		textDistancia.setColumns(10);
		textDistancia.setBounds(567, 399, 324, 20);
		panel.add(textDistancia);

		JLabel lblEstacionO = new JLabel("Estacion origen");
		lblEstacionO.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstacionO.setBounds(345, 84, 150, 20);
		panel.add(lblEstacionO);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNombre.setBounds(345, 131, 150, 20);
		panel.add(lblNombre);

		JLabel lblNumero = new JLabel("Numero de boleto");
		lblNumero.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNumero.setBounds(345, 181, 150, 20);
		panel.add(lblNumero);

		JLabel lblCorreo = new JLabel("Correo electronico");
		lblCorreo.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCorreo.setBounds(345, 231, 150, 20);
		panel.add(lblCorreo);

		JLabel lblEstacionDestino = new JLabel("Estacion destino");
		lblEstacionDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstacionDestino.setBounds(656, 84, 150, 20);
		panel.add(lblEstacionDestino);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblFecha.setBounds(656, 133, 150, 20);
		panel.add(lblFecha);

		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCosto.setBounds(656, 181, 150, 20);
		panel.add(lblCosto);

		JLabel lblCaminoMasRapido = new JLabel("Camino mas rapido");
		lblCaminoMasRapido.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCaminoMasRapido.setBounds(345, 300, 150, 20);
		panel.add(lblCaminoMasRapido);

		JLabel lblCaminoMasBarato = new JLabel("Camino mas barato");
		lblCaminoMasBarato.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCaminoMasBarato.setBounds(345, 347, 150, 20);
		panel.add(lblCaminoMasBarato);

		JLabel lblCaminoDeMenor = new JLabel("Camino de menor distancia");
		lblCaminoDeMenor.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCaminoDeMenor.setBounds(345, 397, 200, 20);
		panel.add(lblCaminoDeMenor);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAceptar.setBounds(791, 537, 100, 30);
		btnAceptar.setBackground(new Color(255, 255, 255));
		panel.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCancelar.setBounds(345, 537, 100, 30);
		btnCancelar.setBackground(new Color(255, 255, 255));
		panel.add(btnCancelar);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Camino mas rapido", "Camino mas barato", "Camino de menor distancia" }));
		comboBox.setBounds(345, 450, 259, 30);
		panel.add(comboBox);

		TrayectoDAO_BD tdao = new TrayectoDAO_BD();

		menorDuracion = tdao.buscarTrayectoDuracion(origen, destino);
		menorCosto = tdao.buscarTrayectoCosto(origen, destino);
		menorDistancia = tdao.buscarTrayectoDistancia(origen, destino);

		textRapido.setText(menorDuracion + " minutos");
		textBarato.setText(menorCosto + " pesos");
		textDistancia.setText(menorDistancia + " kilometros");

		// BTN ACTIONS

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Boletos ventanaB = new Boletos();
				ventanaB.setVisible(true);
				dispose();
			}
		});

		btnAceptar.addActionListener(e -> {
			if (!textNombre.getText().isEmpty() && !textNumero.getText().isEmpty() && !textCorreo.getText().isEmpty()
					&& !textCosto.getText().isEmpty() && !textFecha.getText().isEmpty()) {

				Boleto boleto = new Boleto();

				Estacion estacionO = new Estacion();
				estacionO.setId(Integer.parseInt(origen));
				Estacion estacionD = new Estacion();
				estacionD.setId(Integer.parseInt(destino));

				EstacionDAO ed = new EstacionDAO_BD();

				boleto.setOrigen(ed.getEstacion(estacionO));
				boleto.setDestino(ed.getEstacion(estacionO));
				boleto.setNombre(textNombre.getText());
				boleto.setCorreo(textCorreo.getText());
				boleto.setFecha(textFecha.getText());
				boleto.setCosto(Double.parseDouble(textCosto.getText()));
				boleto.setNroBoleto(Integer.parseInt(textCosto.getText()));

				BoletoDAO bd = new BoletoDAO_BD();

				try {
					bd.nuevoBoleto(boleto);
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
