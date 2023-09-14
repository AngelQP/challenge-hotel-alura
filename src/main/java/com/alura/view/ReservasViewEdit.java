package com.alura.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.JTextField;

import com.alura.controller.ReservaController;
import com.alura.model.Reserva;
import com.alura.util.ChangeDate;
import com.alura.util.ObservadorDatos;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import static java.time.temporal.ChronoUnit.DAYS;


@SuppressWarnings("serial")
public class ReservasViewEdit extends JFrame {
	
	private List<ObservadorDatos> observadores = new ArrayList<>();

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	int xMouse, yMouse;
	private JLabel labelExit;
	
	// Controlador
	private static ReservaController reservaController;
	
	// Inicio de valor unitario
	int valorUnitario = 50;
	
	// Inicio del objeto Reserva
	private static Reserva reserva;
	


	/**
	 * Create the frame.
	 */
	public ReservasViewEdit(Reserva reserva, ObservadorDatos observador) {
		super("Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasViewEdit.class.getResource("../images/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		// Inicio de Reserva
		ReservasViewEdit.reserva = reserva;

		// Inicio de UI
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Código que crea los elementos de la interfáz gráfica
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);
		
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 218, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);
		
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 218, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);
		
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(68, 60, 289, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasViewEdit.class.getResource("../images/Ha-100px.png")));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasViewEdit.class.getResource("../images/reservas-img-3.png")));
		
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 214, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);
												
		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);
		
		
		//Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasViewEdit.class.getResource("../images/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 161, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaEntrada);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasViewEdit.class.getResource("../images/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 246, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(78, 328, 43, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);


		txtFormaPago = new JComboBox<String>();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel<String>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);

		/* Boton Guardar */
		
		JPanel btnGuardar = new JPanel();
		
		
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.setBounds(70, 493, 122, 35);
		panel.add(btnGuardar);
		btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblGuardar = new JLabel("GUARDAR");
		lblGuardar.setBounds(0, 0, 122, 44);
		btnGuardar.add(lblGuardar);
		lblGuardar.setForeground(SystemColor.controlLtHighlight);
		lblGuardar.setVerticalAlignment(SwingConstants.CENTER);
		lblGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		/* Boton Cancelar */
		
		JPanel btnCancelar = new JPanel();	
		btnCancelar.setBackground(SystemColor.textHighlight);
		btnCancelar.setBounds(238, 493, 122, 35);
		panel.add(btnCancelar);
		btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblCancelar = new JLabel("CANCELAR");
		lblCancelar.setBounds(0, 0, 122, 44);
		btnCancelar.add(lblCancelar);
		lblCancelar.setForeground(SystemColor.controlLtHighlight);
		lblCancelar.setVerticalAlignment(SwingConstants.CENTER);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		// Restaurar valores de Backup
		recuperarBackup();

		// Agregar observadores
		agregarObservador(observador);
		
		
		/* Manejo de eventos de buttons */

		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?","Salir",JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {					
					dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(new Color(12, 138, 199));
			     labelExit.setForeground(Color.white);
			}
		});
		
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasViewEdit.txtFechaEntrada.getDate() != null && ReservasViewEdit.txtFechaSalida.getDate() != null) {	
					
					if(!confirmDate(txtFechaEntrada.getDate(), txtFechaSalida.getDate())) {
						return;
					}
					
					LocalDate fechaIn = ChangeDate.formatDateToLocalDate(txtFechaEntrada.getDate());
					LocalDate fechaOut = ChangeDate.formatDateToLocalDate(txtFechaSalida.getDate());
					Integer valor = Integer.parseInt(txtValor.getText());
					String formaPago = txtFormaPago.getSelectedItem().toString();
					
					// actualizar datos
					ReservasViewEdit.reserva.setFechaEntrada(fechaIn);
					ReservasViewEdit.reserva.setFechaSalida(fechaOut);
					ReservasViewEdit.reserva.setValor(valor);
					ReservasViewEdit.reserva.setFormaPago(formaPago);
					
					// TODO: Actualizar en BD
					ReservasViewEdit.reservaController = new ReservaController();
					
					boolean state = ReservasViewEdit.reservaController.update(ReservasViewEdit.reserva);
					
					if(state) {
						// TODO: Notificar a observadores luego de la BD
						notificarObservadores(reserva);
						JOptionPane.showMessageDialog(null, "Datos actualizados");						
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido actualizar los datos.");
					}
					

				} 
				
				else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}						
		});
		
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?","Salir",JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {					
					dispose();
				}
			}						
		});
		
		
		/* Manejo de eventos de JDateChooser */
		txtFechaSalida.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//Activa el evento, después del usuario seleccionar las fechas se debe calcular el valor de la reserva
				
				
				if(txtFechaSalida.getDate() != null && txtFechaEntrada.getDate() != null) {		
					
					LocalDate fechaIn = ChangeDate.formatDateToLocalDate(txtFechaEntrada.getDate());
					LocalDate fechaOut = ChangeDate.formatDateToLocalDate(txtFechaSalida.getDate());
					
					if(!confirmDate(txtFechaEntrada.getDate(), txtFechaSalida.getDate())) {
						return;
					}
					
					
					int dias = (int) DAYS.between(fechaIn, fechaOut);
					int valorReserva = dias * valorUnitario;
					
					txtValor.setText(Integer.toString(valorReserva));
					
					System.out.println(dias);
				}
			}
		});
		
	
	
	
	}
	
	
		
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	 }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
    
    private boolean confirmDate(Date fechaIn, Date fechaOut) {
    	boolean confirmReserve = false;
    	
    	LocalDate fechaEntrada = ChangeDate.formatDateToLocalDate(fechaIn);
    	LocalDate fechaSalida = ChangeDate.formatDateToLocalDate(fechaOut);
    	
    	if(fechaSalida.compareTo(fechaEntrada) <= 0) {    		
    		JOptionPane.showMessageDialog(null, "La fecha de Check Out tiene que ser mayor que la de Check In");
    	}
    	else {
    		confirmReserve = true;
    	}
    	
    	return confirmReserve;
    }
        
    private void recuperarBackup() {
    	
    	// Restauración de fechas
    	Date dateIn = ChangeDate.formatLocalDateToDate(reserva.getFechaEntrada());
    	Date dateOut = ChangeDate.formatLocalDateToDate(reserva.getFechaSalida());
    	
    	txtFechaEntrada.setDate(dateIn);
    	txtFechaSalida.setDate(dateOut);
    	
    	// Restauración de valor
    	int dias = (int) DAYS.between(reserva.getFechaEntrada(), reserva.getFechaSalida());
		int valorReserva = dias * valorUnitario;
		
		txtValor.setText(Integer.toString(valorReserva));
		
		// Restauración de combobox
		String valor = reserva.getFormaPago();
		
		for (int i = 0; i < txtFormaPago.getItemCount() ; i++) {		
			
			if(valor.equals(txtFormaPago.getItemAt(i))) {
				System.out.println(txtFormaPago.getItemAt(i));
				txtFormaPago.setSelectedIndex(i);
				break;
			}
			
		}
    }
    
    /* Metodos del observador */
    public void agregarObservador(ObservadorDatos observador) {
    	observadores.add(observador);
    }
    
    public void notificarObservadores(Reserva reserva) {
    	for (ObservadorDatos observador : observadores) {
			observador.actualizarDatos(reserva);
		}
    }

    public void actualizarDatos(Reserva reserva) {
    	// Actualizar datos del primerJframe
    	
    	//Notificar a los observadores con los nuevos datos
    	notificarObservadores(reserva);
    }
    
    
}
