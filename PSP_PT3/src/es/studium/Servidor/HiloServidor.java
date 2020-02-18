package es.studium.Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidor extends Thread {
	DataInputStream fentrada;
	Socket socket;

	public HiloServidor(Socket socket) {
		this.socket = socket;
		try {
			fentrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e)

		{
			System.out.println("Error de E/S");
			e.printStackTrace();
		}
	}

// En el m�todo run() lo primero que hacemos
// es enviar todos los mensajes actuales al cliente que se
// acaba de incorporar
	public void run() {
		ServidorControlador.ACTUALES++;
		switch (ServidorControlador.ACTUALES) {
		
		case 1:
			ServidorControlador.sv.getTextField().setForeground(ServidorVista.getYellowsession());
			break;
		
		case 2:
			ServidorControlador.sv.getTextField().setForeground(ServidorVista.getGreensession());
		}
		
		ServidorControlador.sv.getTextField().setText(ServidorControlador.sv.fraseSes + ServidorControlador.ACTUALES);
		String texto = ServidorControlador.sv.getTextArea().getText();
		EnviarMensajes(texto);
		
// Seguidamente, se crea un bucle en el que se recibe lo que el cliente escribe en el chat.
// Cuando un cliente finaliza con el bot�n Salir, se env�a un * al servidor del Chat,
// entonces se sale del bucle while, ya que termina el proceso del cliente,
// de esta manera se controlan las conexiones actuales
		while (true) {
			String cadena = "";
			try {
				cadena = fentrada.readUTF();
				if (cadena.trim().equals("*")) {
					ServidorControlador.ACTUALES--;
					switch (ServidorControlador.ACTUALES) {
					
					case 0:
						ServidorControlador.sv.getTextField().setForeground(ServidorVista.getRedsession());
						break;
					
					case 1:
						ServidorControlador.sv.getTextField().setForeground(ServidorVista.getYellowsession());
					}
					ServidorControlador.sv.getTextField().setText(ServidorControlador.sv.fraseSes + ServidorControlador.ACTUALES);
					break;
				}
// El texto que el cliente escribe en el chat,
// se a�ade al textarea del servidor y se reenv�a a todos los clientes
				else {
					ServidorControlador.sv.getTextArea().append(cadena + "\n");
					texto = ServidorControlador.sv.getTextArea().getText();
					EnviarMensajes(texto);
				}
			} catch (Exception ex)

			{
				ex.printStackTrace();
				break;
			}
		}
	}

	// El m�todo EnviarMensajes() env�a el texto del textarea a
	// todos los sockets que est�n en la tabla de sockets,
	// de esta forma todos ven la conversaci�n.
	// El programa abre un stream de salida para escribir el texto en el socket
	private void EnviarMensajes(String texto) {
		for (Socket sock : ServidorControlador.listaSockets) {
			try {
				DataOutputStream fsalida = new DataOutputStream(sock.getOutputStream());
				fsalida.writeUTF(texto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}