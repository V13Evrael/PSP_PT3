package es.studium.Servidor;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class ServidorVista extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final Color greenSession = new Color(34, 139, 34);
	public static final Color yellowSession = new Color(255, 204, 0);
	public static final Color redSession = new Color(204, 0, 0);
	
	private JPanel contentPane;
	private JTextField textField;
	public final String fraseSes = "Número de sesiones activas: ";
	private int numSes = 0;
	private JButton btnSalir;
	private JTextArea textArea;
	
	public ServidorVista() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlRight = new JPanel();
		contentPane.add(pnlRight, BorderLayout.EAST);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBtn = new JPanel();
		pnlRight.add(pnlBtn, BorderLayout.NORTH);
		
		btnSalir = new JButton("Salir");
		pnlBtn.add(btnSalir);
		
		JPanel pnlPpl = new JPanel();
		contentPane.add(pnlPpl, BorderLayout.CENTER);
		pnlPpl.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlPpl.add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setForeground(redSession);
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setText(fraseSes + numSes);
		textField.setEditable(false);
		pnlTop.add(textField);
		textField.setColumns(10);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlPpl.add(pnlCenter);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		pnlCenter.add(scrollPane);	
		
	}

}
