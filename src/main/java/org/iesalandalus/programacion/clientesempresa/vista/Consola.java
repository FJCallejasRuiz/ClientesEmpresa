package org.iesalandalus.programacion.clientesempresa.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
//import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("Las distintas opciones son:");
		System.out.println("1. Insertar un cliente.");
		System.out.println("2. Buscar un cliente.");
		System.out.println("3. Borrar un cliente.");
		System.out.println("4. Mostrar todos los clientes.");
		System.out.println("5. Mostrar los clientes en una fecha concreta.");
		System.out.println("6. -Salir-");
	}

	public static Opcion elegirOpcion() {
		System.out.println("Por favor, escriba el número de la opción que desee.");
		int numeroMenu = Entrada.entero();
		while (numeroMenu < 1 || numeroMenu > 6) {
			System.out.println("El número introducido no pertenece a una opción, inténtelo de nuevo.");
			numeroMenu = Entrada.entero();
		}
		switch (numeroMenu) {

		case 1:
			System.out.println("Has escogido la opción '1. Insertar un cliente.'");
			System.out.println("-------------------------------------------");

			return Opcion.INSERTAR_CLIENTE;
		case 2:
			System.out.println("Has escogido la opción '2. Buscar un cliente.'");
			System.out.println("-------------------------------------------");
			return Opcion.BUSCAR_CLIENTE;
		case 3:
			System.out.println("Has escogido la opción '3. Borrar un cliente.'");
			System.out.println("-------------------------------------------");
			return Opcion.BORRAR_CLIENTE;
		case 4:
			System.out.println("Has escogido la opción '4. Mostrar todos los clientes.'");
			System.out.println("-------------------------------------------");
			return Opcion.MOSTRAR_CLIENTES;
		case 5:
			System.out.println("Has escogido la opción '5. Mostrar los clientes en una fecha concreta.'");
			System.out.println("-------------------------------------------");
			return Opcion.MOSTRAR_CLIENTES_FECHA;
		default:
			System.out.println("Has escogido la opción '6. -Salir-");
			System.out.println("-------------------------------------------");
			return Opcion.SALIR;
		}

	}

	public Cliente leerCliente() {
		DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Por favor, introduzca los datos del cliente que son:");
		System.out.println("Su nombre:");
		String nombreCliente = Entrada.cadena();
		System.out.println("Su DNI:");
		String dniCliente = Entrada.cadena();
		while (dniCliente.length() != 9) {
			System.out.println("Introduzca un DNI de tamaño válido (8 dígitos y 1 número.)");
			dniCliente = Entrada.cadena();
		}
		System.out.println("Su correo:");
		String correoCliente = Entrada.cadena();

		System.out.println("Su teléfono");
		String telefonoCliente = Entrada.cadena();
		while (telefonoCliente.length() > 9 || telefonoCliente.length() < 1) {
			System.out.println("Introduzca un teléfono de tamaño válido (9 dígitos)");
			telefonoCliente = Entrada.cadena();
		}
		System.out.println("Su fecha de nacimiento en formato dd/mm/yyyy:");
		String fechaNacimientoCliente = Entrada.cadena();
		String fechaDate = fechaNacimientoCliente.formatted(formatoLargo);
		LocalDate fechaConvertida = LocalDate.parse(fechaDate);

		Cliente clienteALeer = new Cliente(nombreCliente, dniCliente, correoCliente, telefonoCliente, fechaConvertida);
		return clienteALeer;
	}

	public Cliente leerClienteDni() {
		System.out.println("Introduzca el DNI del cliente:");
		String dniCliente = Entrada.cadena();
		while (dniCliente.length() != 9) {
			System.out.println("Introduzca un DNI de tamaño válido (8 dígitos y 1 número.)");
			dniCliente = Entrada.cadena();
		}
		LocalDate fechaTest = LocalDate.now();
		Cliente test = new Cliente("Paquito", dniCliente, "fran_cr99@hotmail.com", "693310060", fechaTest);
		return test;
	}

	public LocalDate leerFechaNacimiento() {
		DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Introduzca la fecha de nacimiento en formato dd/mm/yyyy:");
		String fechaNacimientoCliente = Entrada.cadena();
		String fechaDate = fechaNacimientoCliente.formatted(formatoLargo);
		LocalDate fechaConvertida = LocalDate.parse(fechaDate);
		return fechaConvertida;

	}
	
	
	
	
}
