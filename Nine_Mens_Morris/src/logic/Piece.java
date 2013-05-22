package logic;

/**
 * Classe que representa uma pe�a jogada no tabuleiro.
 */
public class Piece 
{
	private char value; //B - black, W - white
	public int x; //Linha da pe�a no tabuleiro
	public int y; //Coluna da pe�a no tabuleiro
	public String keyPos; //Representa��o em formato String para a posi��o da pe�a no tabuleiro (serve de identificador)
	
	public Piece(char value,int x, int y)
	{
		this.value=value;
		this.x=x;
		this.y=y;
		keyPos=x+"-"+y;
	}
	
	public char getValue()
	{
		return value;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
		
	public Piece clone()
	{
		Piece ret = new Piece(value,x,y);
		return ret;
	}
}
