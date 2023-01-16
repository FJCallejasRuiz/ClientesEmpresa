package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
	private Cliente[] coleccionClientes;

	private int capacidad = coleccionClientes.length;
	private int tamano;

	private boolean capacidadSuperada(int indice) { // sin más

		if (indice > capacidad) {
			return true;
		} else
			return false;

	}

	private boolean tamanoSuperado(int indice) {// sin más

		if (indice > tamano) {
			return true;
		} else
			return false;

	}

	private int buscarIndice(Cliente cliente) {

		int indice = tamano + 1;
		boolean buscado = false;

		for (int i = 0; i < coleccionClientes.length && !buscado; i++) {
			if (coleccionClientes[i] != null && coleccionClientes[i].equals(cliente)) {
				indice = i;
				buscado = true;
			}
		}

		return indice;
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: insertarmalo.");
		}
		if (tamano == capacidad) {
			throw new OperationNotSupportedException("ERROR: Son lo mismo bobo.");
		}

		Cliente clienteCopia = new Cliente(cliente);
		boolean checkeo = false;

		for (int i = 0; i < capacidad && !checkeo; i++) {
			if (coleccionClientes[i] == null) {
				tamano = i;
				checkeo = true;
				coleccionClientes[i] = clienteCopia;
			}
		}

	}

	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: insertarmalo.");
		}

		Cliente clienteBuscado = null;
		boolean checkeo = false;

		for (int i = 0; i < capacidad && !checkeo; i++) {
			if (coleccionClientes[i].equals(cliente)) {
				clienteBuscado = new Cliente(coleccionClientes[i]);
				checkeo = true;
			}

		}
		return clienteBuscado;
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice++; i <=

				capacidad && coleccionClientes[i] != null; i++) {
			coleccionClientes[i - 1] = coleccionClientes[i];
			coleccionClientes[i] = null;
		}
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		int indiceCliente = buscarIndice(cliente);

		if (indiceCliente == tamano + 1) {
			throw new OperationNotSupportedException("ERROR: pitoflauta");
		}
		coleccionClientes[indiceCliente] = null;
		desplazarUnaPosicionHaciaIzquierda(indiceCliente);

	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public Cliente[] get() {
	Cliente[] coleccionTemp = new Cliente[capacidad];
	for (int i=0; i<capacidad; i++){
		if(coleccionClientes[i] !=null) {
			coleccionTemp[i] = new Cliente(coleccionClientes[i]);
		}
	}
			return coleccionTemp;
		
	}

}
