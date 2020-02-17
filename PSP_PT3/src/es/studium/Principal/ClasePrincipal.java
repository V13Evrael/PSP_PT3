package es.studium.Principal;

import es.studium.Cliente.ClienteControlador;
import es.studium.Servidor.ServidorVista;

public class ClasePrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new ClienteControlador(null);
		new ServidorVista().setVisible(true);
		
	}

}
