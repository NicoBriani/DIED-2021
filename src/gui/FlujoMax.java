package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EstacionDAO;
import dao.EstacionDAO_BD;
import dao.RutaDAO;
import dao.RutaDAO_BD;
import estructura.CalculoFlujoMax;
import estructura.Grafo;
import dominio.Estacion;
import dominio.Ruta;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FlujoMax extends JFrame {

	private JPanel contentPane;
	private JTextField textFlujoMax;

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FlujoMax() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Flujo maximo");
		setResizable(false);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		panelCentral.setBackground(new Color(102, 204, 255));

		// INICIALIZAR COMPONENTS

		JLabel lblEstacionO = new JLabel("Estacion origen");
		lblEstacionO.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstacionO.setBounds(459, 190, 150, 20);
		panelCentral.add(lblEstacionO);

		JLabel lblEstacionD = new JLabel("Esacion destino");
		lblEstacionD.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEstacionD.setBounds(459, 250, 150, 20);
		panelCentral.add(lblEstacionD);

		JComboBox comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setBounds(749, 190, 60, 24);
		comboBoxOrigen.setBackground(new Color(255, 255, 255));
		panelCentral.add(comboBoxOrigen);

		JComboBox comboBoxDestino = new JComboBox();
		comboBoxDestino.setBounds(749, 250, 60, 24);
		comboBoxDestino.setBackground(new Color(255, 255, 255));
		panelCentral.add(comboBoxDestino);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAtras.setBounds(459, 419, 100, 30);
		btnAtras.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnAtras);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCalcular.setBounds(709, 419, 100, 30);
		btnCalcular.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnCalcular);
		
		JLabel lblFlujomax = new JLabel("FlujoMax");
		lblFlujomax.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblFlujomax.setBounds(459, 315, 150, 20);
		panelCentral.add(lblFlujomax);
		
		textFlujoMax = new JTextField();
		textFlujoMax.setBounds(723, 317, 86, 20);
		panelCentral.add(textFlujoMax);
		textFlujoMax.setColumns(10);

		EstacionDAO edao = new EstacionDAO_BD();

		for (int i = 0; i < edao.getIdEstaciones().size(); i++)
			comboBoxOrigen.addItem(edao.getIdEstaciones().get(i));

		for (int i = 0; i < edao.getIdEstaciones().size(); i++)
			comboBoxDestino.addItem(edao.getIdEstaciones().get(i));

		
		// BTN ACTIONS

				btnAtras.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						final Informacion ventanaI = new Informacion();
						ventanaI.setVisible(true);
						dispose();
					}

				});

				btnCalcular.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						
						
						ArrayList<ArrayList<Ruta>> grafo = new Grafo().crearGrafo();
						
					
							//long maxFlow =  new CalculoFlujoMax().getMaxFlow(Integer.parseInt(comboBoxOrigen.getSelectedItem().toString()), (Integer.parseInt(comboBoxDestino.getSelectedItem().toString())), grafo);
						//if(maxFlow >= 100000000) maxFlow=0;
							//textFlujoMax.setText("" + maxFlow); 	
							
						
					
					}
				});
				}
}
