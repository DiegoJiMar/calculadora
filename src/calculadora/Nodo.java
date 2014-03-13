
package calculadora;

public class Nodo {
	private Double dato;
	private Nodo sigueinte;
	
	public Nodo(Double d){
		this.setDato(d);
		this.setSigueinte(null);
	}

	public Double getDato() {
		return dato;
	}
	public void setDato(Double dato) {
		this.dato = dato;
	}
	public Nodo getSigueinte() {
		return sigueinte;
	}
	public void setSigueinte(Nodo sigueinte) {
		this.sigueinte = sigueinte;
	}
}
