package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.EstacionDAO;
import dao.EstacionDAO_BD;
import dao.MantenimientoDAO;
import dao.MantenimientoDAO_BD;
import dao.RutaDAO;
import dao.RutaDAO_BD;
import dao.TrayectoDAO_BD;
import dominio.Estacion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Estaciones extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public Estaciones() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Estaciones");
		setResizable(false);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(new Color(102, 204, 255));
		panelCentral.setLayout(null);

		// INICIALIZAR COMPONENTS

		JButton btnNuevaE = new JButton("Nueva Estacion");
		btnNuevaE.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnNuevaE.setBackground(new Color(255, 255, 255));
		btnNuevaE.setBounds(114, 472, 150, 30);
		panelCentral.add(btnNuevaE);

		JButton btnEditar = new JButton("Editar ");
		btnEditar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnEditar.setBounds(415, 472, 150, 30);
		btnEditar.setBackground(new Color(255, 255, 255));
		panelCentral.add(btnEditar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setBounds(1011, 472, 150, 30);
		panelCentral.add(btnEliminar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setBounds(723, 472, 150, 30);
		panelCentral.add(btnBuscar);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(114, 600, 100, 30);
		panelCentral.add(btnAtras);
		
		JButton btnInformacion = new JButton("Informacion");
		btnInformacion.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnInformacion.setBackground(Color.WHITE);
		btnInformacion.setBounds(1011, 600, 150, 30);
		panelCentral.add(btnInformacion);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(114, 61, 1050, 362);
		scrollPane.setBackground(new Color(255, 255, 255));
		panelCentral.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Apertura", "Cierre", "Estado" }));

		scrollPane.setViewportView(table);

		// TABLA ACTIONS

		Estacion estacionSeleccionada = new Estacion();
		EstacionDAO ed = new EstacionDAO_BD();
		RutaDAO rd = new RutaDAO_BD();
		MantenimientoDAO md = new MantenimientoDAO_BD();
		TrayectoDAO_BD td = new TrayectoDAO_BD();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int seleccionar = table.rowAtPoint(e.getPoint());
				estacionSeleccionada.setId(((Integer) table.getValueAt(seleccionar, 0)));
				estacionSeleccionada.setNombre(((String) table.getValueAt(seleccionar, 1)));
				estacionSeleccionada.setApertura(((String) table.getValueAt(seleccionar, 2)));
				estacionSeleccionada.setCierre(((String) table.getValueAt(seleccionar, 3)));
				estacionSeleccionada.setEstado(((String) table.getValueAt(seleccionar, 4)));
				estacionSeleccionada.setUltimoMantenimiento(ed.getUltimoMantenimiento(estacionSeleccionada.getId()));
			}
		});

		try {
			ed.llenarTabla(table);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Princ ventanaP = new Princ();
				ventanaP.setVisible(true);
				dispose();
			}
		});

		btnNuevaE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaEstacion ventanaNE = null;
				try {
					ventanaNE = new NuevaEstacion();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ventanaNE.setVisible(true);
				dispose();
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final EditarEstacion ventanaEditar = new EditarEstacion(estacionSeleccionada);
			System.out.println(estacionSeleccionada.getUltimoMantenimiento());
				
				ventanaEditar.setVisible(true);
				dispose();
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					td.eliminarTPorDestino(estacionSeleccionada.getId());
					td.eliminarTPorOrigen(estacionSeleccionada.getId());
					rd.eliminarRutaPorDestino(estacionSeleccionada.getId());
					rd.eliminarRutaPorOrigen(estacionSeleccionada.getId());
					md.eliminarMantenimiento(estacionSeleccionada.getId());
					ed.eliminarEstacion(estacionSeleccionada);
					JOptionPane.showMessageDialog(null, "Estacion eliminada correctamente");
					ed.limpiarTabla(table);
					ed.llenarTabla(table);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarEstacion ventanaBuscar = new BuscarEstacion();

				ventanaBuscar.setVisible(true);
				dispose();
			}
		});
		
		btnInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Informacion ventanaI = new Informacion();
				ventanaI.setVisible(true);
				dispose();
			}
		});

	}
}
