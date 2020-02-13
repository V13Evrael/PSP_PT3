package es.studium.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ClienteVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFTextoAEnviar;
	private JTextArea txtATextoRecibido;
	
	public JTextField getTxtFTextoAEnviar() {
		return txtFTextoAEnviar;
	}


	public void setTxtFTextoAEnviar(JTextField txtFTextoAEnviar) {
		this.txtFTextoAEnviar = txtFTextoAEnviar;
	}


	public JTextArea getTxtATextoRecibido() {
		return txtATextoRecibido;
	}


	public void setTxtATextoRecibido(JTextArea txtATextoRecibido) {
		this.txtATextoRecibido = txtATextoRecibido;
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public JLabel getLblNombreCliente() {
		return lblNombreCliente;
	}


	public void setLblNombreCliente(JLabel lblNombreCliente) {
		this.lblNombreCliente = lblNombreCliente;
	}


	public JButton getBtnEnviar() {
		return btnEnviar;
	}


	public void setBtnEnviar(JButton btnEnviar) {
		this.btnEnviar = btnEnviar;
	}


	private JScrollPane scrollPane;
	
	private String nombreCliente="";
	private JLabel lblNombreCliente;
	private JButton btnEnviar;


	public ClienteVista() {
		
		do {
			nombreCliente = JOptionPane.showInputDialog(this, "Escribe tu nombre para conectar con la base de datos (No puede ser vacío).", "Elegir nombre", JOptionPane.INFORMATION_MESSAGE);
		} while (nombreCliente.equals(""));
		
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(new CompoundBorder(new LineBorder(new Color(240, 240, 240), 2), new LineBorder(new Color(0, 0, 0), 1, true)), new CompoundBorder(new LineBorder(new Color(240, 240, 240), 4, true), new LineBorder(new Color(0, 0, 0), 1, true))));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
			
		txtATextoRecibido = new JTextArea();
		txtATextoRecibido.setEditable(false);
		scrollPane = new JScrollPane(txtATextoRecibido);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		panel_1.add(scrollPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNombreCliente = new JLabel(nombreCliente + ": ");
		panel.add(lblNombreCliente, BorderLayout.WEST);
		
		txtFTextoAEnviar = new JTextField();
		panel.add(txtFTextoAEnviar, BorderLayout.CENTER);
		txtFTextoAEnviar.setColumns(10);
		
		btnEnviar = new JButton("Enviar predicci\u00F3n");
		panel.add(btnEnviar, BorderLayout.EAST);
		
		this.setLocationRelativeTo(null);
	}

}
