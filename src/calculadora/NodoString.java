
package calculadora;

public class NodoString {
	private String dato;
	private NodoString siguiente;
	
	public NodoString(String d){
		this.setDato(d);
		this.setSiguiente(null);
	}
	
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	public NodoString getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoString siguiente) {
		this.siguiente = siguiente;
	}
}
