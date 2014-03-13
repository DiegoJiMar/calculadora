
package calculadora;

public class Lista {
	private Nodo inicio;
	
	public Lista(){
		this.setInicio(null);
	}
	
	public void push(Double d){
		Nodo nuevo = new Nodo(d);
		if (isVacia())
			inicio = nuevo;
		else{
			nuevo.setSigueinte(inicio);
			inicio = nuevo;
		}
	}
	
	public Double pop() throws ListaVaciaException{
		Double D = 0.0;
		if (isVacia())
			throw new ListaVaciaException();
		else{
			Nodo aux = inicio;
			D = inicio.getDato();
			aux = aux.getSigueinte();
			inicio.setSigueinte(null);
			inicio = null;
			inicio = aux;
		}
		return D;
	}
	
	public boolean isVacia(){
		return this.getInicio() == null;
	}
	
	public Nodo getInicio() {
		return inicio;
	}
	public void setInicio(Nodo inicio) {
		this.inicio = inicio;
	}
}

