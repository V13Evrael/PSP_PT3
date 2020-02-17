package es.studium.Cliente;

public class ClienteMain {

	public static void main(String[] args) throws Exception {
		
		ClienteControlador cliente = ClienteModelo.iniciar();
		ClienteModelo.ejecutar(cliente);

	}
	
}
