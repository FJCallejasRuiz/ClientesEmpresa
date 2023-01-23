package org.iesalandalus.programacion.clientesempresa;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

public class MainApp {

	private static Clientes clientes;
	private static final int NUM_MAX_CLIENTES = 5;

	public static void main(String[] args) {

		clientes = new Clientes(NUM_MAX_CLIENTES);
		int bucle = 0;
		do {
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} while (bucle == 0);

	}

	private static void insertarCliente() {
		try {
			clientes.insertar(Consola.leerCliente());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void buscarCliente() {

		Cliente clienteTest = Consola.leerClienteDni();
		Cliente clienteEncontrado = clientes.buscar(clienteTest);
		if (clienteEncontrado == null) {
			System.out.println("El cliente no existe.");
		} else
			System.out.println(clienteEncontrado);

	}

	private static void borrarCliente() {
		try {
			clientes.borrar(Consola.leerClienteDni());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void mostrarCliente() {

		Cliente[] clienteAMostrar = clientes.get();

		for (Cliente cliente : clienteAMostrar) {
			if (cliente != null) {
				System.out.println(cliente.toString());
			}

		}

	}

	private static void mostrarClienteFecha() {
		LocalDate fechaBuscada = Consola.leerFechaNacimiento();
		Cliente[] clienteAMostrar = clientes.get();
		boolean noHayNada = false;

		for (Cliente cliente : clienteAMostrar) {
			if (cliente != null && cliente.getFechaNacimiento().equals(fechaBuscada)) {
				System.out.println(cliente.toString());
				noHayNada = true;
			}

		}
		if (noHayNada == false) {
			System.out.println("No hay ningún cliente con esa fecha.");
		}

	}

	private static void ejecutarOpcion(Opcion opcion) {

		switch (opcion) {

		case BUSCAR_CLIENTE:
			buscarCliente();
			break;

		case INSERTAR_CLIENTE:
			insertarCliente();
			break;

		case BORRAR_CLIENTE:
			borrarCliente();
			break;

		case MOSTRAR_CLIENTES_FECHA:
			mostrarClienteFecha();
			break;

		case MOSTRAR_CLIENTES:
			mostrarCliente();
			break;

		default:
			System.exit(0);

		}

	}

}