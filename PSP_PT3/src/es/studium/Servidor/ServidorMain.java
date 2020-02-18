package es.studium.Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServidorMain {

	public static void main(String args[]) throws Exception {
		// Desde el main se inicia el servidor
		// y las variables y se prepara la pantalla
		ServidorControlador.servidor = new ServerSocket(ServidorControlador.PUERTO);
		System.out.println("Servidor iniciado...");
		new ServidorControlador();
		// Se usa un bucle para controlar el número de conexiones.
		// Dentro del bucle el servidor espera la conexión
		// del cliente y cuando se conecta se crea un socket
		while (true) {
			Socket socket;
			try {
				socket = ServidorControlador.servidor.accept();
			} catch (SocketException sex) {
				// Sale por aquí si pulsamos el botón salir
				break;
			}
			// El socket creado se añade a la tabla,
			// se incrementa el número de conexiones
			// y se lanza el hilo para gestionar los mensajes
			// del cliente que se acaba de conectar
			ServidorControlador.listaSockets.add(socket);
			HiloServidor hilo = new HiloServidor(socket);
			hilo.start();
		}
		// Si se alcanzan 15 conexiones o se pulsa el botón Salir,
		// el programa se sale del bucle.
		// Al pulsar Salir se cierra el ServerSocket
		// lo que provoca una excepción (SocketException)
		// en la sentencia accept(), la excepción se captura
		// y se ejecuta un break para salir del bucle
		if (!ServidorControlador.servidor.isClosed()) {
			try {
				ServidorControlador.servidor.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Servidor finalizado...");
		}
	}

}
