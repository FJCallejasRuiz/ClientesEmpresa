package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math3.exception.NullArgumentException;

public class Cliente {

	private final String ER_CORREO = "([\\w.]+[@][\\w.]+[\\w]+)";
	private final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private final String ER_TELEFONO = "([0-9]{9})";
	private String nombre, dni, correo, telefono; // Ponemos por separado los ER ya que entendemos que vienen de
													// "Expresión Regular" y para verlo mas claro

	public String FORMATO_FECHA;

	private LocalDate fechaNacimiento=LocalDate.now();

	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo."); // constructor con parametros que llama a los setters
																	// de cada cosa
		} else if (dni == null) {
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
			
		}else if (correo == null) {
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
			
		}else if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		}else if (fechaNacimiento == null) {
			throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		}
			this.setNombre(nombre);
		this.setDni(dni);
		this.setCorreo(correo);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fechaNacimiento);;
	}

	public Cliente(Cliente cliente) { // constructor copia para evitar aliasing.
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		} else
			cliente = new Cliente(cliente);
	}

	private String formateaNombre(String nombre) {

		String nombreNoEsp = nombre.trim();// Le quitamos los espacios al nombre

		String nombreMin = nombreNoEsp.toLowerCase();// Hacemos el nombre minuscula entera.

		char[] arrayTemp = nombreMin.toCharArray();

		arrayTemp[0] = Character.toUpperCase(arrayTemp[0]); // Hacemos que ya de primeras la primera letra sea
															// mayúscula.

		for (int i = 0; i < nombreMin.length() - 1; i++) {

			if (arrayTemp[i] == ' ' || arrayTemp[i] == '.' || arrayTemp[i] == ',') {
				arrayTemp[i + 1] = Character.toUpperCase(arrayTemp[i + 1]); // De esta forma va mirando cada letra y
																			// cuando encuentra una , un . o un espacio.
																			// convierte el siguiente en mayusculas, ya
																			// que se da por sentado que sería el
																			// apellido o tal.
			}

		}
		return new String(arrayTemp);
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

		if (letraStringDni.matches(compara.group(2))) { // Si el string que nos averigua la letra, haciendo el resto de
														// 23 del numero del dni, es igual que el grupo 2, es decir
														// ([A-Za-z]), nos devuelve true, si no devuelve false. (seria
														// matches)
			return true;
		} else
			return false;

	}

	public String getNombre() {
		return new String(nombre);
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo."); // Solo confirmamos que el nombre no
																					// sea null
		} else if (nombre == "") {
			throw new IllegalArgumentException("ERROR: El nombre debe ser válido.");
		}else
			this.nombre = nombre;
	}

	public String getDni() {

		return new String(dni);
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");

		} else {

			Pattern patronDni;
			Matcher comparaDni;

			patronDni = Pattern.compile(ER_DNI);
			comparaDni = patronDni.matcher(dni);
			int dniNumero = Integer.parseInt(comparaDni.group(1));
			String stringTemporalDniNumero = String.valueOf(dniNumero);
			if (stringTemporalDniNumero.matches(comparaDni.group(1))) {
				if (comprobarLetraDni(dni)) {
					this.dni = dni;
				}
				throw new IllegalArgumentException("ERROR: ADFAD");
			} else
				throw new IllegalArgumentException("ERROR: PIOWEIRSODF");// De esta forma, hacemos un IF para
																			// asegurarnos que los numeros sean iguales,
																			// para saber si la letra está bien pues ya
																			// llamamos al comprobador, entonces si todo
																			// esta bien hacer un this.

		}

	}

	public String getCorreo() {
		return new String(correo);
	}

	public void setCorreo(String correo) {

		if (correo == null) {
			throw new NullPointerException("ERROR: El correo no puede ser nulo."); // Solo confirmamos que el correo no
																					// sea null
		} else {

			Pattern patronCorreo;
			Matcher comparaCorreo;

			patronCorreo = Pattern.compile(ER_CORREO);
			comparaCorreo = patronCorreo.matcher(correo);
			if (correo.matches(comparaCorreo.group(1)))
				this.correo = correo;
		}
		throw new IllegalArgumentException("ERROR: sjfsdfjdshlfsdjk.");

	}

	public String getTelefono() {
		return new String(telefono);
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		} else {

			Pattern patronTelefono;
			Matcher comparaTelefono;

			patronTelefono = Pattern.compile(ER_TELEFONO);
			comparaTelefono = patronTelefono.matcher(telefono);

			if (telefono.matches(comparaTelefono.group(1)))
				this.telefono = telefono;
		}
		throw new IllegalArgumentException("ERROR: aaaaaaaahlfsdjk.");
		// confirmamos que el telefono no sea nulo y que sea de 9 digitos.
	}

	public LocalDate getFechaNacimiento() {
		return new LocalDate(fechaNacimiento);
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	private String getIniciales() {
		String stringTemporal = this.formateaNombre(nombre);
		char primLetra, segunLetra = 0, tercerLetra = 0;

		char[] charTemporal = stringTemporal.toCharArray();
		primLetra = charTemporal[0];
		int x;
		for (x = 0; x < charTemporal.length; x++) {
			if (charTemporal[x] == ' ' || charTemporal[x] == '.' || charTemporal[x] == ',') {
				segunLetra = Character.toUpperCase(charTemporal[x + 1]);
				break;
			}

		}

		for (x++; x < charTemporal.length; x++) {
			if (charTemporal[x] == ' ' || charTemporal[x] == '.' || charTemporal[x] == ',') {
				tercerLetra = Character.toUpperCase(charTemporal[x + 1]);
				break;
			}

		}

		return new String(primLetra + "." + segunLetra + "." + tercerLetra);
		// con formateaNombre tenemos el nombre bien. Hacemos el metodo que vaya pasando
		// por todo el nombre y cuando detecte un " " espacio, que haga i++ y lo guarde
		// en "iniciales", cuando el nombre acabase, tendriamos las iniciales.

	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + getIniciales() + " " + formateaNombre(nombre) + ", dni=" + dni + ", correo="
				+ correo + ", telefono=" + telefono + "]";
	}

}
