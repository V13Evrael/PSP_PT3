package es.studium.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClienteControlador implements ActionListener, WindowListener {

	Socket socket;
	DataInputStream fentrada;
	DataOutputStream fsalida;
	boolean repetir = true;
	ClienteVista cv;

	public ClienteControlador(Socket socket) {

		// Al crear nuestro Frame se nos pide el nombre, que se guardará en una variable
		// para mandarla al servidor
		// Luego creamos los flujos de entrada y salida.
		// Si tiene éxito el HiloServidor recibirá el mensaje con el nombre del cliente
		cv = new ClienteVista();
		cv.addWindowListener(this);
		this.socket = socket;

		// Se crean los flujos de entrada y salida.
		// En el flujo de salida se escribe un mensaje
		// indicando que el cliente se ha unido al Chat.
		// El HiloServidor recibe este mensaje y
		// lo reenvía a todos los clientes conectados
		try {
			fentrada = new DataInputStream(socket.getInputStream());
			fsalida = new DataOutputStream(socket.getOutputStream());
			String texto = "> Entra en el chat... " + cv.getNombreCliente();
			fsalida.writeUTF(texto);
		} catch (IOException ex) {
			System.out.println("Error de E/S");
			ex.printStackTrace();
			System.exit(0);
		} catch (NullPointerException npe) {
			System.out.println("Mensaje de error o pedida de datos");
		}

		// El método main es el que lanza el cliente,
		// para ello en primer lugar se solicita el nombre o nick del
		// cliente, una vez especificado el nombre
		// se crea la conexión al servidor y se crear la pantalla del Chat(ClientChat)
		// lanzando su ejecución (ejecutar()).

		cv.setVisible(true);

	}

	public static void main(String[] args) throws Exception {
		int puerto = 44444;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", puerto);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor \n" + ex.getMessage(),
					"<<Mensaje de Error:1>>", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		ClienteControlador cliente = new ClienteControlador(socket);
		cliente.ejecutar();

	}

	// Cuando se pulsa el botón Enviar,
	// el mensaje introducido se envía al servidor por el flujo de salida
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cv.getBtnEnviar()) {
			String texto = cv.getNombreCliente() + "> " + cv.getTxtFTextoAEnviar().getText();
			try {
				cv.getTxtFTextoAEnviar().setText("");
				fsalida.writeUTF(texto);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	// Dentro del método ejecutar(), el cliente lee lo que el
	// hilo le manda (mensajes del Chat) y lo muestra en el textarea.
	// Esto se ejecuta en un bucle del que solo se sale
	// en el momento que el cliente pulse el botón Salir
	// y se modifique la variable repetir
	public void ejecutar() {
		String texto = "";
		while (repetir) {
			try {
				texto = fentrada.readUTF();
				cv.getTxtATextoRecibido().setText(texto);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor \n" + ex.getMessage(),
						"<<Mensaje de Error:2>>", JOptionPane.ERROR_MESSAGE);
				repetir = false;
			}
		}
		try {
			socket.close();
			System.exit(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		String texto = "SERVIDOR> Abandona el juego... " + cv.getNombreCliente();
		try {
			fsalida.writeUTF(texto);
			fsalida.writeUTF("*");
			repetir = false;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
