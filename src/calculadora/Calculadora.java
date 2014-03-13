
package calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private String opCalc = "";
	private String opMaq = "";
	private JTextField texto;
	private Font fuente;
	JPanel cp = (JPanel)this.getContentPane();
	private JPanel pad1;
	private JButton B;
	
	public Calculadora(){
		super("Calculadora v1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(275, 100, 450, 300);
		this.setResizable(false);
		cp.setLayout(new BorderLayout());
		texto = new JTextField ();
		texto.setEditable(false);
		fuente=new Font("Serief",Font.BOLD|Font.ITALIC,25);
		texto.setForeground(Color.BLACK);
		texto.setFont(fuente);
		texto.setHorizontalAlignment(JTextField.RIGHT);
		texto.setText("0");
		B = new JButton("CE");
		B.setBackground(Color.RED);
		B.addActionListener(this);
		pad1=new JPanel();
		pad1.setLayout(new GridLayout(0,6));
		String nomBotones [] = {"sen","7","8","9","x","÷","cos","4","5","6","%","sqrt","tan","1","2","3","+","-","(",")","0",".","^","="};
		for(int i=0;i<nomBotones.length;i++){
			JButton button = new JButton(nomBotones[i]);
			button.addActionListener(this);
			if(nomBotones[i].charAt(0)>='0' && nomBotones[i].charAt(0)<='9')
				button.setBackground(Color.lightGray);
			pad1.add(button);
		}
		texto.setBounds(20,10,220,20);
		cp.add(texto, BorderLayout.NORTH);
		cp.add(pad1,BorderLayout.CENTER);
		cp.add(B,BorderLayout.EAST);
	}
	
	public void actionPerformed(ActionEvent e) {
		char caracter=((JButton) e.getSource()).getText().charAt(0);
		if(caracter>=48 && caracter<=57){
			opMaq=opMaq+caracter;
			opCalc=opCalc+caracter;
			texto.setText(opCalc);
		}else{
			if(caracter=='C'){
				opMaq="";
				opCalc="";
				texto.setText("0");
			
			}else if(caracter=='='){
				try {
					notacionPostfija n1 = new notacionPostfija(opMaq);
					n1.creaNotacion();
					Double resultado = n1.resuelveNotacion();
					n1.getLnP().recorrer();
					texto.setText(resultado+"");
					opMaq=resultado+"";
					opCalc=resultado+"";
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}else{
				switch(caracter){
				case '+':
				case'x':
				case'÷':
				case'%':
				case'^':
				case'.':
				case'(':
				case')':
					opMaq=opMaq+caracter;
					opCalc=opCalc+caracter;
					break;
				case'-':
					if(opMaq=="")
						opMaq=opMaq+"0-";
					else if(!(opMaq.charAt(opMaq.length()-1)>='0' && opMaq.charAt(opMaq.length()-1)<='9'))
						opMaq=opMaq+"0-";
					else
						opMaq=opMaq+caracter;
					opCalc=opCalc+caracter;
					break;
				case 'c':
					opMaq=opMaq+caracter;
					opCalc=opCalc+" cos( ";
					break;
				case 't':
					opMaq=opMaq+caracter;
					opCalc=opCalc+" tan( ";
					break;
				case 's':
					char caracter2=((JButton) e.getSource()).getText().charAt(1);
					if(caracter2=='q'){
						opMaq=opMaq+"q";
						opCalc=opCalc+" sqrt( ";
					}else{
						opMaq=opMaq+"s";
						opCalc=opCalc+" sin( ";
					}
					break;
				}
				texto.setText(opCalc);
			}
		}	
	}	
}

