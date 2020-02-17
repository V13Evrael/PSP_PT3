package es.studium.Cliente;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClienteModelo {

	public static ClienteControlador iniciar() {
		
		int puerto = 44444;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", puerto);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor \n" + ex.getMessage(),
					"<<Mensaje de Error:1>>", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		ClienteControlador cliente = new ClienteControlador(socket);
		
		return cliente;
	}
	
	// Dentro del método ejecutar(), el cliente lee lo que el
	// hilo le manda (mensajes del Chat) y lo muestra en el textarea.
	// Esto se ejecuta en un bucle del que solo se sale
	// en el momento que el cliente pulse el botón Salir
	// y se modifique la variable repetir
	public static void ejecutar(ClienteControlador cc) {
		String texto = "";
		while (cc.repetir) {
			try {
				texto = cc.fentrada.readUTF();
				cc.cv.getTxtATextoRecibido().setText(texto);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor \n" + ex.getMessage(),
						"<<Mensaje de Error:2>>", JOptionPane.ERROR_MESSAGE);
				cc.repetir = false;
			}
		}
		try {
			cc.socket.close();
			System.exit(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
