
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
	 * Método que permite carregar até quatro slots passadas por argumento para se tornarem adjacentes da slot atual, carregando-as para o array "adjacents".
	 * Se uma dessas slots for null não irá constar do array "adjacents" (ou seja, "adjacents" só poderá conter slots existentes, dispostas contiguamente).
	 * @param s0 Possível slot adjacente. Pode ser null se não interessar.
	 * @param s1 Possível slot adjacente. Pode ser null se não interessar.
	 * @param s2 Possível slot adjacente. Pode ser null se não interessar.
	 * @param s3 Possível slot adjacente. Pode ser null se não interessar.
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
	 * Método que permite definir explicitamente as slots que fazem mill com a slot atual. Note-se que é possível gerar automaticamente essa informação através do método generateMills.
	 * @param vertical1 Uma das slots na mill vertical com a peça atual.
	 * @param vertical2 A outra slot na mill vertical com a peça atual.
	 * @param horizontal1 Uma das slots na mill horizontal com a peça atual.
	 * @param horizontal2 A outra slot na mill horizontal com a peça atual.
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
	 * Método que permite obter a slot contígua acima da atual. Retorna null se não existir nenhuma.
	 * @return Slot contígua acima da atual. Null se não existir.
	 */
	public Slot up()
	{
		for(int i=0; i<adjacents.length; i++)
		{
			if(adjacents[i].line<line) //Linhas de número inferior encontram-se acima na grelha de slots
				return adjacents[i];
		}		
		return null;
	}
	
	/**
	 * Método que permite obter a slot contígua abaixo da atual. Retorna null se não existir nenhuma.
	 * @return Slot contígua abaixo da atual. Null se não existir.
	 */
	public Slot down()
	{
		for(int i=0; i<adjacents.length; i++)
		{
			if(adjacents[i].line>line) //Linhas de número superior encontram-se abaixo na grelha de slots
				return adjacents[i];
		}		
		return null;
	}
	
	/**
	 * Método que permite obter a slot contígua à esquerda da atual. Retorna null se não existir nenhuma.
	 * @return Slot contígua à esquerda da atual. Null se não existir.
	 */
	public Slot left()
	{
		for(int i=0; i<adjacents.length; i++)
		{
			if(adjacents[i].column<column) //Colunas de número inferior encontram-se à esquerda na grelha de slots
				return adjacents[i];
		}		
		return null;
	}	
	
	/**
	 * Método que permite obter a slot contígua à direita da atual. Retorna null se não existir nenhuma.
	 * @return Slot contígua à direita da atual. Null se não existir.
	 */
	public Slot right()
	{
		for(int i=0; i<adjacents.length; i++)
		{
			if(adjacents[i].column>column) //Colunas de número superior encontram-se à direita na grelha de slots
				return adjacents[i];
		}		
		return null;
	}	
	
	/**
	 * Método que obtém automaticamente as slots que fazem mill com a atual, preenchendo os arrays horizontalMill e verticalMill.
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
			//Slot mais à esquerda na linha
			horizontalMill[0]=right;
			horizontalMill[1]=right.right();
		}
		else
		{
			//Slot mais à direita na linha
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
	 * Método que permite saber se a slot atual está ocupada.
	 * @return True se a slot estiver ocupada, false em caso contrário.
	 */
	public boolean occupied()
	{
		if(occupyingPiece==null)
			return false;
		else
			return true;
	}
	
}
