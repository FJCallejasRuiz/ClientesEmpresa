package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
	private Cliente[] coleccionClientes;

	private int capacidad;
	private int tamano;

	public Clientes(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.coleccionClientes = new Cliente[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}

	private boolean capacidadSuperada(int indice) { // sin más

		if (indice > capacidad) {
			return true;
		} else
			return false;

	}

	/*private boolean tamanoSuperado(int indice) {// sin más

		if (indice > tamano) {
			return true;
		} else
			return false;

	}*/

	private int buscarIndice(Cliente cliente) {

		int indice = this.tamano + 1;
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
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}

		if (buscarIndice(cliente) != this.tamano + 1) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");
		}

		if (this.capacidadSuperada(buscarIndice(cliente))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
		}

		Cliente clienteCopia = new Cliente(cliente);
		boolean checkeo = false;

		for (int i = 0; i < this.capacidad && !checkeo; i++) {
			if (coleccionClientes[i] == null) {
				this.tamano = i + 1;
				checkeo = true;
				coleccionClientes[i] = clienteCopia;
			}
		}

	}

	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}

		Cliente clienteBuscado = null;
		boolean checkeo = false;

		for (int i = 0; i < this.capacidad && !checkeo; i++) {
			if (coleccionClientes[i] != null && coleccionClientes[i].equals(cliente)) {
				clienteBuscado = new Cliente(coleccionClientes[i]);
				checkeo = true;
			}

		}
		return clienteBuscado;
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		this.tamano = indice;
		for (int i = indice+1; i <= capacidad && coleccionClientes[i] != null; i++) {
			coleccionClientes[i - 1] = coleccionClientes[i];
			coleccionClientes[i] = null;
			this.tamano = i;
		}
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		int indiceCliente = buscarIndice(cliente);

		if (indiceCliente == this.tamano + 1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");
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
		for (int i = 0; i < capacidad; i++) {
			if (coleccionClientes[i] != null) {
				coleccionTemp[i] = new Cliente(coleccionClientes[i]);
			}
		}
		return coleccionTemp;

	}

}
