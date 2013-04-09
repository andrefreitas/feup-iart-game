
public class Slot {

	public int column; //x value in the board matrix
	public int line; //y value in the board matrix
	
	public int pixelX;
	public int pixelY;
	
	public Slot[] adjacents;
	public Slot[] verticalMill;
	public Slot[] horizontalMill;
	
	public Piece occupyingPiece;
	
	public Slot(int column, int line, int pixelX, int pixelY)
	{
		this.column=column;
		this.line=line;
		this.pixelX=pixelX;
		this.pixelY=pixelY;
		
		occupyingPiece=null;
		verticalMill = new Slot[2];
		horizontalMill = new Slot[2];
	}
	
	/**
	 * M�todo que permite carregar at� quatro slots passadas por argumento para se tornarem adjacentes da slot atual, carregando-as para o array "adjacents".
	 * Se uma dessas slots for null n�o ir� constar do array "adjacents" (ou seja, "adjacents" s� poder� conter slots existentes, dispostas contiguamente).
	 * @param s0 Poss�vel slot adjacente. Pode ser null se n�o interessar.
	 * @param s1 Poss�vel slot adjacente. Pode ser null se n�o interessar.
	 * @param s2 Poss�vel slot adjacente. Pode ser null se n�o interessar.
	 * @param s3 Poss�vel slot adjacente. Pode ser null se n�o interessar.
	 */
	public void setAdjacents(Slot s0, Slot s1, Slot s2, Slot s3)
	{				
		int numberOfAdjacents = 0;
		Slot[] newAdjacents = new Slot[4];
		newAdjacents[0] = s0; newAdjacents[1] = s1; newAdjacents[2] = s2; 	newAdjacents[3] = s3;

		for(int i = 0; i < 4; i++)
		{
			if(newAdjacents[i]!=null)
				numberOfAdjacents++;
		}
		
		adjacents = new Slot[numberOfAdjacents];

		for(int i = 0, j=0; i < 4; i++)
		{
			if(newAdjacents[i]!=null)
			{
				adjacents[j]=newAdjacents[i];
				j++;
			}
		}
	}
	
	/**
	 * M�todo que permite definir explicitamente as slots que fazem mill com a slot atual. Note-se que � poss�vel gerar automaticamente essa informa��o atrav�s do m�todo generateMills.
	 * @param vertical1 Uma das slots na mill vertical com a pe�a atual.
	 * @param vertical2 A outra slot na mill vertical com a pe�a atual.
	 * @param horizontal1 Uma das slots na mill horizontal com a pe�a atual.
	 * @param horizontal2 A outra slot na mill horizontal com a pe�a atual.
	 * @see generateMills
	 */
	public void setMills(Slot vertical1, Slot vertical2, Slot horizontal1, Slot horizontal2)
	{
		verticalMill[0]=vertical1;
		verticalMill[1]=vertical1;
		horizontalMill[0]=horizontal1;
		horizontalMill[1]=horizontal2;
	}
	
	/**
	 * M�todo que permite obter a slot cont�gua acima da atual. Retorna null se n�o existir nenhuma.
	 * @return Slot cont�gua acima da atual. Null se n�o existir.
	 */
	public Slot up()
	{
		for(int i=0; i<adjacents.length; i++)
		{
			if(adjacents[i].line<line) //Linhas de n�mero inferior encontram-se acima na grelha de slots
				return adjacents[i];
		}		
		return null;
	}
	
	/**
	 * M�todo que permite obter a slot cont�gua abaixo da atual. Retorna null se n�o existir nenhuma.
	 * @return Slot cont�gua abaixo da atual. Null se n�o existir.
	 */
	public Slot down()
	{
		for(int i=0; i<adjacents.length; i++)
		{
			if(adjacents[i].line>line) //Linhas de n�mero superior encontram-se abaixo na grelha de slots
				return adjacents[i];
		}		
		return null;
	}
	
	/**
	 * M�todo que permite obter a slot cont�gua � esquerda da atual. Retorna null se n�o existir nenhuma.
	 * @return Slot cont�gua � esquerda da atual. Null se n�o existir.
	 */
	public Slot left()
	{
		for(int i=0; i<adjacents.length; i++)
		{
			if(adjacents[i].column<column) //Colunas de n�mero inferior encontram-se � esquerda na grelha de slots
				return adjacents[i];
		}		
		return null;
	}	
	
	/**
	 * M�todo que permite obter a slot cont�gua � direita da atual. Retorna null se n�o existir nenhuma.
	 * @return Slot cont�gua � direita da atual. Null se n�o existir.
	 */
	public Slot right()
	{
		for(int i=0; i<adjacents.length; i++)
		{
			if(adjacents[i].column>column) //Colunas de n�mero superior encontram-se � direita na grelha de slots
				return adjacents[i];
		}		
		return null;
	}	
	
	/**
	 * M�todo que obt�m automaticamente as slots que fazem mill com a atual, preenchendo os arrays horizontalMill e verticalMill.
	 */
	public void generateMills()
	{
		Slot left = left();
		Slot right = right();
		Slot up = up();
		Slot down = down();
		
		//Mill horizontal (linha)
		if(left!=null && right!=null)
		{
			//Slot no centro da linha
			horizontalMill[0]=left;
			horizontalMill[1]=right;
		}
		else if(left==null)
		{
			//Slot mais � esquerda na linha
			horizontalMill[0]=right;
			horizontalMill[1]=right.right();
		}
		else
		{
			//Slot mais � direita na linha
			horizontalMill[0]=left;
			horizontalMill[1]=left.left();
		}
		
		//Mill vertical (coluna)
		if(up!=null && down!=null)
		{
			//Slot no centro da coluna
			verticalMill[0]=up;
			verticalMill[1]=down;
		}
		else if(up==null)
		{
			//Slot mais acima na coluna
			verticalMill[0]=down;
			verticalMill[1]=down.down();
		}
		else
		{
			//Slot mais abaixo na coluna
			verticalMill[0]=up;
			verticalMill[1]=up.up();
		}		
	
	}
	
	/**
	 * M�todo que permite saber se a slot atual est� ocupada.
	 * @return True se a slot estiver ocupada, false em caso contr�rio.
	 */
	public boolean occupied()
	{
		if(occupyingPiece==null)
			return false;
		else
			return true;
	}
	
}
