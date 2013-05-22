package logic;

/**
 * Classe que representa uma jogada efetuada por um humano/CPU ou calculada no decurso do processo minimax.
 * Armazena informa��o sobre o stage em que o jogo se encontrava aquando da jogada, o jogador que jogou a pe�a, as posi��es
 * inicial e final e a pe�a removida se aplic�vel.
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
	 * @param ix Posi��o inicial da pe�a jogada em x (linha).
	 * @param iy Posi��o inicial da pe�a jogada em y (coluna).
	 * @param fx Posi��o final da pe�a jogada em x (linha).
	 * @param fy Posi��o final da pe�a jogada em y (coluna).
	 * @param p Pe�a removida pelo jogador ao advers�rio (ou null se n�o foi removida nenhuma pe�a).
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
	 * M�todo que gera um identificador �nica para a jogada.
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