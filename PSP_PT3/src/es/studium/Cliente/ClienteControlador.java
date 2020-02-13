package es.studium.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteControlador implements ActionListener {

	Socket socket;
	DataInputStream fentrada;
	DataOutputStream fsalida;
	boolean repetir = true;
	ClienteVista cv;
	
	public ClienteControlador (Socket socket) {
		
		//Al crear nuestro Frame se nos pide el nombre, que se guardar� en una variable para mandarla al servidor
		//Luego creamos los flujos de entrada y salida.
		//Si tiene �xito el HiloServidor recibir� el mensaje con el nombre del cliente 
		cv = new ClienteVista();
		this.socket = socket;
		
		// Se crean los flujos de entrada y salida.
		// En el flujo de salida se escribe un mensaje
		// indicando que el cliente se ha unido al Chat.
		// El HiloServidor recibe este mensaje y
		// lo reenv�a a todos los clientes conectados
		try {
			fentrada = new DataInputStream(socket.getInputStream());
			fsalida = new DataOutputStream(socket.getOutputStream());
			String texto = "> Entra en el chat... " + cv.getNombreCliente() ;
			fsalida.writeUTF(texto);
		} catch (IOException ex) {
			System.out.println("Error de E/S");
			ex.printStackTrace();
			System.exit(0);
		} catch (NullPointerException npe) {
			System.out.println("Mensaje de error o pedida de datos");			
		}
		
		// El m�todo main es el que lanza el cliente,
		// para ello en primer lugar se solicita el nombre o nick del
		// cliente, una vez especificado el nombre
		// se crea la conexi�n al servidor y se crear la pantalla del Chat(ClientChat)
		// lanzando su ejecuci�n (ejecutar()).
		
		cv.setVisible(true);	
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
