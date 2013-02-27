package tabuleiro;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class mypeca {

	static URL url11 = 
			ClassLoader.getSystemClassLoader().
			getResource("imagens/pecaazul.png");
	private static ImageIcon iconbranca = new ImageIcon(url11);
	static URL url12 = 
			ClassLoader.getSystemClassLoader().
			getResource("imagens/pecaverde.png");
	private static ImageIcon iconpreta = new ImageIcon(url12);
	
	public JLabel preta;
	public JLabel branca;
	public JLabel imagem;
	
	public char cor;
	public int xcoord;
	public int ycoord;
	public int xcoord_old;
	public int ycoord_old;
	
	public int deltx;
	public int delty;
	
	public int tamanho;
	
	public mypeca()
	{
		tamanho=47;
		
		cor='p';
		xcoord=5;
		ycoord=5;
		
		xcoord_old=5;
		ycoord_old=5;
		
		preta=new JLabel(iconpreta);
		preta.setBounds(xcoord,ycoord,tamanho,tamanho);
		branca=new JLabel(iconbranca);
		branca.setBounds(xcoord,ycoord,tamanho,tamanho);
		imagem=preta;
		
	}
	
	
}
