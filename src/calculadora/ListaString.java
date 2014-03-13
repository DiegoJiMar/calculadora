package calculadora;

public class ListaString {
	private NodoString inicio;
	
	public ListaString(){
		this.setInicio(null);
	}
	
	public void push(String d){
		NodoString nuevo = new NodoString(d);
		if (isVacia())
			inicio = nuevo;
		else{
			nuevo.setSiguiente(inicio);
			inicio = nuevo;
		}
	}
	
	public String pop() throws ListaVaciaException{
		String D = "";
		if(isVacia())
			throw new ListaVaciaException();
		else{
			NodoString aux = inicio;
			D = inicio.getDato();
			aux = aux.getSiguiente();
			inicio.setSiguiente(null);
			inicio = null;
			inicio = aux;
		}
		return D;
	}
	
	public void insertar(String d){
		NodoString nuevo = new NodoString(d);
		if (inicio == null){
			inicio = nuevo;
		}
		else{
			NodoString aux = inicio;
			while (aux.getSiguiente()!=null){
				aux = aux.getSiguiente();
			}
			aux.setSiguiente(nuevo);
		}
	}
	
	public void recorrer(){
		NodoString aux = this.getInicio();
		while(aux!=null){
			System.out.print(aux.getDato()+" ");
			aux = aux.getSiguiente();
		}
	}
	
	public boolean isVacia(){
		return this.getInicio() == null;
	}
	
	public int length(){
		int t=0;
		NodoString aux = this.getInicio();
		while(aux!=null){
			aux = aux.getSiguiente();
			t++;
		}
		return t;
	}
	
	public NodoString getInicio() {
		return inicio;
	}
	public void setInicio(NodoString inicio) {
		this.inicio = inicio;
	}
}
