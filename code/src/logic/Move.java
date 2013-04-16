package logic;

/**
 * Classe que representa uma jogada efetuada por um humano/CPU ou calculada no decurso do processo minimax.
 * Armazena informação sobre o stage em que o jogo se encontrava aquando da jogada, o jogador que jogou a peça, as posições
 * inicial e final e a peça removida se aplicável.
 */
public class Move 
{
	public final int stage;
	public final char value;
	public final int[] initPos;
	public final int[] finalPos;
	public final Piece removedPiece;
		
	/**
	 * Construtor da classe Move.
	 * @param st Stage em que o jogador se encontrava quando fez a jogada.
	 * @param v Identificador do jogador (B ou W)
	 * @param ix Posição inicial da peça jogada em x (linha).
	 * @param iy Posição inicial da peça jogada em y (coluna).
	 * @param fx Posição final da peça jogada em x (linha).
	 * @param fy Posição final da peça jogada em y (coluna).
	 * @param p Peça removida pelo jogador ao adversário (ou null se não foi removida nenhuma peça).
	 */
	public Move(int st, char v, int ix, int iy, int fx, int fy, Piece p)
	{
		stage=st;
		value=v;
		if(stage==0)
		{
			initPos=null;			
		}
		else
		{
			initPos = new int[2];
			initPos[0]=ix;
			initPos[1]=iy;			
		}
		
		finalPos = new int[2];
		finalPos[0]=fx;
		finalPos[1]=fy;		
		removedPiece=p;
	}
	
	/**
	 * Mostra a jogada na consola.
	 */
	public void showMove()
	{
		System.out.println("ShowMove - stage: "+stage+" value: "+value);
		if(stage!=0)
			System.out.println("Initpos: "+initPos[0]+"-"+initPos[1]);
		System.out.println("FinalPos: "+finalPos[0]+"-"+finalPos[1]);
		if(removedPiece!=null)
			System.out.println("Removed Piece: "+removedPiece.keyPos+" value: "+removedPiece.getValue());
	}
	
	/**
	 * Método que gera um identificador única para a jogada.
	 * @return String que representa o identificador da jogada.
	 */
	public String getHashKey() 
	{
		String ret=""+stage+value+finalPos[0]+finalPos[1];
		if(initPos!=null)
			ret+=""+initPos[0]+initPos[1];
		if(removedPiece!=null)
			ret+=""+removedPiece.keyPos+removedPiece.getValue();
		return ret;
	}
}