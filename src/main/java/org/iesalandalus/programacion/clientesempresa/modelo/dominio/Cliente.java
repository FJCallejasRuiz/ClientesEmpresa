package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {

	private final String ER_CORREO;
	private final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private final String ER_TELEFONO = "([0-9]{9})";
	private String nombre, dni, correo, telefono; // Ponemos por separado los ER ya que entendemos que vienen de
													// "Expresión Regular" y para verlo mas claro

	public String FORMATO_FECHA;

	private EDate fechaNacimiento;

	private String formateaNombre(String nombre) {

		String nombreNoEsp = nombre.trim();// Le quitamos los espacios al nombre

		String nombreMin = nombreNoEsp.toLowerCase();// Hacemos el nombre minuscula entera.

		char[] arrayTemp = nombreMin.toCharArray();

		arrayTemp[0] = Character.toUpperCase(arrayTemp[0]); // Hacemos que ya de primeras la primera letra sea
															// mayúscula.

		for (int i = 0; i < nombreMin.length(); i++) {

			if (arrayTemp[i] == ' ' || arrayTemp[i] == '.' || arrayTemp[i] == ',') {
				arrayTemp[i + 1] = Character.toUpperCase(arrayTemp[i + 1]); // De esta forma va mirando cada letra y
																			// cuando encuentra una , un . o un espacio.
																			// convierte el siguiente en mayusculas, ya
																			// que se da por sentado que sería el
																			// apellido o tal.
			}
			return new String(arrayTemp);
		}

	}

	private boolean comprobarLetraDni(String dni) {

		Pattern patron;
		Matcher compara;

		patron = Pattern.compile(ER_DNI);
		compara = patron.matcher(dni);

		char[] LETRA_DNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' }; // en este orden, cada letra tiene ya asignada la posición que le
											// correspondería, T=1, R=2...

		int dniNumero = Integer.parseInt(compara.group(1));
		char letraTempDni = LETRA_DNI[dniNumero % 23];

		String letraStringDni = String.valueOf(letraTempDni); // lo convierte a string

		if (letraStringDni.equals(compara.group(2))) { // Si el string que nos averigua la letra, haciendo el resto de
														// 23 del numero del dni, es igual que el grupo 2, es decir
														// ([A-Za-z]), nos devuelve true, si no devuelve false.
			return true;
		} else
			return false;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo."); //Solo confirmamos que el nombre no sea null
		} else
			this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		} else if (dni.length() != 9) {
			throw new IllegalArgumentException("ERROR: El DNI debe ser de 8 números y una letra.");
		} else
			this.dni = dni; //confirmamos que el DNI no sea nulo ni que tenga menos de 9 caracteres.
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {

		if (correo == null) {
			throw new NullPointerException("ERROR: El correo no puede ser nulo."); //Solo confirmamos que el correo no sea null
		} else
			this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		} else if (dni.length() != 9) {
			throw new IllegalArgumentException("ERROR: El teléfono debe ser de 9 números."); 
		} else
			this.telefono = telefono; //confirmamos que el telefono no sea nulo y que sea de 9 digitos.
	}

	public EDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(EDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
