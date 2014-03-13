package calculadora;

public class notacionPostfija {
	private String op;
	private Lista lnum;
	private ListaString lop;
	private ListaString lnP;
	
	public notacionPostfija(String op){
		this.setOp(op);
		lnum = new Lista();
		lop = new ListaString();
		lnP = new ListaString();
	}
	
	public void creaNotacion(){
		String n = "";
		Double num = 0.0;
		int ban = 0;
		try{
			for (int i=0;i<op.length();i++){
				while((i<op.length()) && ((isNumero(op.charAt(i)))||(op.charAt(i))=='.')){
					n+=op.charAt(i);
					i++;
				}
				int p = n.length()-1;
				for(int j = 0; j < n.length();j++){
					num+= (n.charAt(j)-48) * (1*Math.pow(10,p));
					p--;
				}
				lnum.push(num);
				lnP.insertar(n);
				num = 0.0;
				n="";
				if((i<op.length())&&(!lop.isVacia())&&((prioridad(op.charAt(i)))<=(prioridad(lop.getInicio().getDato().charAt(0))))){
					if(prioridad(op.charAt(i))==-1){
						if(isParIni(op.charAt(i))){
							NodoString aux = lnP.getInicio();
							while(aux.getSiguiente()!=null){
								aux = aux.getSiguiente();
							}System.out.println(lnP.length());
							if ((isNumero(aux.getDato().charAt(0)))){
								lop.push("x");
							}
							lop.push(String.valueOf(op.charAt(i)));
							ban++;
						}
						else{
							while(!isParIni(lop.getInicio().getDato().charAt(0))){
								if(lop.getInicio().getDato().charAt(0)=='('){
									lop.pop();
									}
								lnP.insertar(lop.pop());
							}
							if(lop.getInicio().getDato().charAt(0)=='(')
								lop.pop();
							else
									lnP.insertar(lop.pop());
							ban--;
						}
					}
					else{
						if((lop.getInicio().getDato().charAt(0)=='^')&&(op.charAt(i)=='^'))
							lop.push(String.valueOf(op.charAt(i)));
						else{
							lnP.insertar(lop.pop());
							lop.push(String.valueOf(op.charAt(i)));
							}
					}
				}
				else if(i<op.length()){
					if(ban!=0){
						lop.push(String.valueOf(op.charAt(i)));
					}
					else{
						//while(!lop.isVacia())
							//lnP.insertar(lop.pop());
						lop.push(String.valueOf(op.charAt(i)));
						NodoString aux = lnP.getInicio();
						while(aux.getSiguiente()!=null){
							aux = aux.getSiguiente();
						}
						if(aux.getDato()!=""){
						if ((isNumero(aux.getDato().charAt(0)))&&(op.charAt(i)=='('))
							lop.push("x");
						if ((isNumero(aux.getDato().charAt(0)))&&((isParIni(op.charAt(i)))&&(op.charAt(i)!='('))){
							lop.pop();
							lop.push("x");
							lop.push(String.valueOf(op.charAt(i)));
						}
						}
					}
				}
			}
			while(!lop.isVacia())
				lnP.insertar(lop.pop());
			}catch (ListaVaciaException e){
				System.out.println("Error, lista vacia");
			}
	}
	
	public Double resuelveNotacion(){
		Lista PilaR = new Lista();
		Double s1;
		Double s2;
		try{
			NodoString aux = lnP.getInicio();
			while(aux!=null){
				if((aux.getDato()!="")&&(isNumero(aux.getDato().charAt(0))))
					PilaR.push(Double.valueOf(aux.getDato()));
				else{
					if(aux.getDato()!=""){
						switch(aux.getDato().charAt(0)){
						case '+':
							PilaR.push(PilaR.pop()+PilaR.pop());
							break;
						case '-':
							s1=PilaR.pop();
							s2=PilaR.pop();
							PilaR.push(s2-s1);
							break;
						case 'x':
							PilaR.push(PilaR.pop()*PilaR.pop());
							break;
						case '÷':
							s1=PilaR.pop();
							s2=PilaR.pop();
							PilaR.push(s2/s1);
							break;
						case '%':
							s1=PilaR.pop();
							s2=PilaR.pop();
							PilaR.push(s2%s1);
							break;
						case '^':
							s1=PilaR.pop();
							s2=PilaR.pop();
							PilaR.push(Math.pow(s2, s1));
							break;
						case 'q':
							PilaR.push(Math.sqrt(PilaR.pop()));
							break;
						case 's':
							PilaR.push(Math.sin(PilaR.pop()));
							break;
						case 'c':
							PilaR.push(Math.cos(PilaR.pop()));
							break;
						case 't':
							PilaR.push(Math.tan(PilaR.pop()));
							break;
						}
					}
				}
				aux = aux.getSiguiente();
			}
			Double r = PilaR.pop();
			return redondeo(r,5);
		}catch(ListaVaciaException e){
			System.out.println("Error, pila vacia");
		}
		return 0.0;
	}
	
	public void borrarTodo(){
		this.setOp("");
		this.setLnP(null);
		this.setLnum(null);
		this.setLop(null);
	}
	
	public Integer convierteEntero(String n){
		int num = 0;
		int p = n.length()-1;
		for(int i = 0; i < n.length();i++){
			num+= (int)(n.charAt(i)-48) * (1*Math.pow(10,p));
			p--;
		}
		return num;
	}
	
	public boolean isNumero(char s){
		return ((s>='0')&&(s<='9'));
	}
	
	public int prioridad (char s) {
		   if (s=='+' || s=='-')
		      return 0;
		   else if (s=='*' || s=='/' || s=='%')
		      return 1;
		   else if (s=='^'||s=='q')
		      return 2;

		   return -1;
	}
	
	public boolean isParIni(char s){
		return ((s == '(') || (s=='s') || (s == 'c') || (s=='t') || (s=='q'));
	}
	
	private double redondeo(double numero, int decimales){
		return ((double)Math.round(numero*Math.pow(10,decimales)))/Math.pow(10,decimales);
	}
	
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public ListaString getLnP() {
		return lnP;
	}
	public void setLnP(ListaString lnP) {
		this.lnP = lnP;
	}
	public Lista getLnum() {
		return lnum;
	}
	public void setLnum(Lista lnum) {
		this.lnum = lnum;
	}
	public ListaString getLop() {
		return lop;
	}
	public void setLop(ListaString lop) {
		this.lop = lop;
	}
}
