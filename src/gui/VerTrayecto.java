package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.RutaDAO;
import dao.RutaDAO_BD;
import dominio.Trayecto;

@SuppressWarnings("serial")
public class VerTrayecto extends JFrame {

	private JPanel contentPane;
	private JTextField textDistancia;
	private JTextField textCosto;
	private JTextField textDuracion;
	private JTextField textDestino;
	private JTextField textOrigen;
	private JTextField textRutas;

	String estaciones = "";

	public VerTrayecto(Trayecto t, String lineaSeleccionada) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Ver trayecto");
		setResizable(false);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(102, 204, 255));
		panel.setLayout(null);

		textDistancia = new JTextField();
		textDistancia.setEditable(false);
		textDistancia.setFont(new Font("Dialog", Font.PLAIN, 15));
		textDistancia.setBounds(745, 349, 100, 20);
		panel.add(textDistancia);
		textDistancia.setColumns(10);

		textCosto = new JTextField();
		textCosto.setEditable(false);
		textCosto.setFont(new Font("Dialog", Font.PLAIN, 15));
		textCosto.setBounds(745, 449, 100, 20);
		panel.add(textCosto);
		textCosto.setColumns(10);

		textDuracion = new JTextField();
		textDuracion.setEditable(false);
		textDuracion.setFont(new Font("Dialog", Font.PLAIN, 15));
		textDuracion.setColumns(10);
		textDuracion.setBounds(745, 399, 100, 20);
		panel.add(textDuracion);

		textDestino = new JTextField();
		textDestino.setEditable(false);
		textDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
		textDestino.setColumns(10);
		textDestino.setBounds(745, 301, 100, 20);
		panel.add(textDestino);

		textOrigen = new JTextField();
		textOrigen.setEditable(false);
		textOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
		textOrigen.setColumns(10);
		textOrigen.setBounds(745, 251, 100, 20);
		panel.add(textOrigen);

		textOrigen.setText(t.getOrigen().getId().toString());
		textDestino.setText(t.getDestino().getId().toString());
		textDuracion.setText(t.getDuracionT().toString());
		textCosto.setText(t.getCostoT().toString());
		textDistancia.setText(t.getDistanciaT().toString());

		textRutas = new JTextField();
		textRutas.setEditable(false);
		textRutas.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		textRutas.setBounds(437, 160, 408, 30);
		panel.add(textRutas);
		textRutas.setColumns(10);

		JLabel lblDestino = new JLabel("Estacion Destino");
		lblDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDestino.setBounds(437, 299, 128, 20);
		panel.add(lblDestino);

		JLabel lblDistancia = new JLabel("Distancia (Km)");
		lblDistancia.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDistancia.setBounds(437, 349, 150, 20);
		panel.add(lblDistancia);

		JLabel lblCosto = new JLabel("Costo ($)");
		lblCosto.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCosto.setBounds(437, 449, 150, 20);
		panel.add(lblCosto);

		JLabel lblOrigen = new JLabel("Estacion Origen");
		lblOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblOrigen.setBounds(437, 249, 128, 20);
		panel.add(lblOrigen);

		JLabel lblDuracion = new JLabel("Duracion (Min)");
		lblDuracion.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDuracion.setBounds(437, 399, 150, 20);
		panel.add(lblDuracion);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(437, 499, 100, 30);
		panel.add(btnAtras);

		RutaDAO rdao = new RutaDAO_BD();

		List<Integer> listaEstaciones = rdao.getIdEstaciones(lineaSeleccionada);
		listaEstaciones.add(t.getDestino().getId());

		for (int i = 0; i < listaEstaciones.size() - 1; i++)
			estaciones += listaEstaciones.get(i).toString() + "-->";
		estaciones += listaEstaciones.get(listaEstaciones.size() - 1);

		textRutas.setText(estaciones);

		// BTN ACTIONS

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefinirTrayecto ventanaDT = new DefinirTrayecto(lineaSeleccionada);
				ventanaDT.setVisible(true);
				dispose();
			}
		});

	}
}
