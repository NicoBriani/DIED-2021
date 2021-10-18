package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.LineaDAO;
import dao.LineaDAO_BD;
import dao.RutaDAO;
import dao.RutaDAO_BD;
import dao.TrayectoDAO_BD;
import dominio.Linea;

@SuppressWarnings("serial")
public class Lineas extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public Lineas() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Lineas");
		setResizable(false);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		panelCentral.setBackground(new Color(102, 204, 255));

		JButton btnNuevaL = new JButton("Nueva Linea");
		btnNuevaL.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnNuevaL.setBounds(114, 472, 150, 30);
		btnNuevaL.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnNuevaL);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(114, 600, 100, 30);
		panelCentral.add(btnAtras);

		JButton btnEditar = new JButton("Editar ");
		btnEditar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnEditar.setBounds(345, 472, 150, 30);
		btnEditar.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnEditar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnEliminar.setBounds(1011, 472, 150, 30);
		btnEliminar.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnEliminar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBuscar.setBounds(783, 472, 150, 30);
		panelCentral.add(btnBuscar);
		btnBuscar.setBackground(new Color(255, 255, 255));

		JButton btnDefinirTrayecto = new JButton("Definir Trayecto");
		btnDefinirTrayecto.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnDefinirTrayecto.setBounds(566, 472, 150, 30);
		btnDefinirTrayecto.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnDefinirTrayecto);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(114, 61, 1050, 362);
		scrollPane.setBackground(new Color(255, 255, 255));
		panelCentral.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Color", "Estado" }));
		scrollPane.setViewportView(table);

		// TABLA ACTIONS
		Linea lineaSeleccionada = new Linea();
		LineaDAO ld = new LineaDAO_BD();
		RutaDAO rdao = new RutaDAO_BD();
		TrayectoDAO_BD tdao = new TrayectoDAO_BD();
		

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int seleccionar = table.rowAtPoint(e.getPoint());
				lineaSeleccionada.setNombre(((String) table.getValueAt(seleccionar, 0)));
				lineaSeleccionada.setColor(((String) table.getValueAt(seleccionar, 1)));
				lineaSeleccionada.setEstado(((String) table.getValueAt(seleccionar, 2)));
			}
		});

		try {
			ld.llenarTabla(table);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//BTN ACTIONS

		btnNuevaL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaLinea ventanaNL = null;
				ventanaNL = new NuevaLinea();
				ventanaNL.setVisible(true);
				dispose();
			}
		});

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Princ ventanaP = null;
				ventanaP = new Princ();
				ventanaP.setVisible(true);
				dispose();
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final EditarLinea ventanaEditar = new EditarLinea(lineaSeleccionada);
				ventanaEditar.setVisible(true);
				dispose();
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tdao.eliminarTPorLinea(lineaSeleccionada.getNombre());
					rdao.eliminarRutaPorLinea(lineaSeleccionada.getNombre());
					ld.eliminarLinea(lineaSeleccionada);
					JOptionPane.showMessageDialog(null, "Estacion eliminada correctamente");
					Lineas lins = new Lineas();
					lins.setVisible(true);
					dispose();
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");

				}
			}

		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarLinea ventanaBuscar = new BuscarLinea();
				ventanaBuscar.setVisible(true);
				dispose();
			}
		});

		btnDefinirTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefinirTrayecto ventanaDT = new DefinirTrayecto(lineaSeleccionada.getNombre());
				ventanaDT.setVisible(true);
				dispose();
			}
		});

	}
}
