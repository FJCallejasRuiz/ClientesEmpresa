package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {

	private final String ER_CORREO = "([\\w.]+[@][\\w]+[.][\\w]+)";
	private final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private final String ER_TELEFONO = "([0-9]{9})";
	private String nombre, dni, correo, telefono; // Ponemos por separado los ER ya que entendemos que vienen de
													// "Expresión Regular" y para verlo mas claro

	public String FORMATO_FECHA;

	private LocalDate fechaNacimiento;
	DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {

		this.setNombre(nombre);
		this.setDni(dni);
		this.setCorreo(correo);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fechaNacimiento);

	}

	public Cliente(Cliente cliente) { // constructor copia para evitar aliasing.
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		} else {
			nombre = cliente.getNombre();
			dni = cliente.getDni();
			correo = cliente.getCorreo();
			telefono = cliente.getTelefono();
			fechaNacimiento = cliente.getFechaNacimiento();
		}

	}

	private String formateaNombre(String nombre) {
		String primLetra;
		String resto;
		String nombreFinal = "";
		String[] nombreNoEspMinus = nombre.trim().toLowerCase().split("\s+");// Le quitamos los espacios al nombre
		for (int i = 0; i < nombreNoEspMinus.length; i++) {
			primLetra = ("" + nombreNoEspMinus[i].charAt(0)).toUpperCase();
			resto = nombreNoEspMinus[i].substring(1);

			nombreNoEspMinus[i] = primLetra + resto;
			nombreFinal = nombreFinal + " " + primLetra + resto;
		}
		return nombreFinal.trim();

	}

	private boolean comprobarLetraDni(String dni) {

		Pattern patron;
		Matcher compara;

		patron = Pattern.compile(ER_DNI);
		compara = patron.matcher(dni);
		if (compara.matches()) {
			char[] LETRA_DNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
					'V', 'H', 'L', 'C', 'K', 'E' }; // en este orden, cada letra tiene ya asignada la posición que le
													// correspondería, T=1, R=2...

			int dniNumero = Integer.parseInt(compara.group(1));
			char letraTempDni = LETRA_DNI[dniNumero % 23];

			String letraStringDni = String.valueOf(letraTempDni); // lo convierte a string

			if (letraStringDni.matches(compara.group(2))) { // Si el string que nos averigua la letra, haciendo el resto
															// de
															// 23 del numero del dni, es igual que el grupo 2, es decir
															// ([A-Za-z]), nos devuelve true, si no devuelve false.
															// (seria
															// matches)
				return true;
			}
		} else
			return false;
		return false;

	}

	public String getNombre() {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		return new String(nombre);
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo."); // Solo confirmamos que
																									// el nombre no
			// sea null
		} else if ((nombre.trim().isEmpty())) {
			throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
		} else
			this.nombre = formateaNombre(nombre);
	}

	public String getDni() {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}

		return new String(dni);
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
		}
		Pattern patronDni;
		Matcher comparaDni;
		patronDni = Pattern.compile(ER_DNI);
		comparaDni = patronDni.matcher(dni);

		if (comparaDni.matches()) {
			if (comprobarLetraDni(dni)) {
				this.dni = dni;
			} else
				throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");

		} else {
			throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
		}
		// De esta forma, hacemos un IF para
		// asegurarnos que los numeros sean iguales,
		// para saber si la letra está bien pues ya
		// llamamos al comprobador, entonces si todo
		// esta bien hacer un this.

	}

	public String getCorreo() {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
		}
		return new String(correo);
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
		} else if ((correo.matches(ER_CORREO))) {
			this.correo = correo;
		} else
			throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
	}

	public String getTelefono() {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		return new String(telefono);
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		} else if ((telefono.matches(ER_TELEFONO))) {
			this.telefono = telefono;
		} else
			throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
	}

	public LocalDate getFechaNacimiento() {
		if (fechaNacimiento == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		} else
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
		char primLetra, segunLetra = 0, tercerLetra = 0, cuartaLetra = 0;

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

		for (x++; x < charTemporal.length; x++) {
			if (charTemporal[x] == ' ' || charTemporal[x] == '.' || charTemporal[x] == ',') {
				cuartaLetra = Character.toUpperCase(charTemporal[x + 1]);
				break;
			}

		}

		return new String("(" + primLetra + segunLetra + tercerLetra + cuartaLetra + ")");
		// con formateaNombre tenemos el nombre bien. Hacemos el metodo que vaya pasando
		// por todo el nombre y cuando detecte un " " espacio, que haga i++ y lo guarde
		// en "iniciales", cuando el nombre acabase, tendriamos las iniciales.

	}

	@Override
	public String toString() {
		return "nombre=" + formateaNombre(nombre) + " " + getIniciales() + ", DNI=" + dni + ", correo=" + correo
				+ ", teléfono=" + telefono + ", fecha nacimiento=" + fechaNacimiento.format(formatoLargo);
	}

}
