package logic;

/**
 * Classe auxiliar que contém os dados constantes necessários à inicialização do tabuleiro do jogo.
 */
public class BoardInit 
{
	//TODO: compactar código de iniciação das adjacências
	
	/**
	 * Método que invoca as funções adequadas para inicializar um tabuleiro recebido como argumento.
	 * @param board Objeto Board que se pretende inicializar.
	 */
	public static void init(Board board)
	{
		initSlots(board);
		initAdjacencies(board);
		initMills(board);	
	}
	
	/**
	 * Inicialização do map de slots do tabuleiro, sem peças associadas (null).
	 * @param board Objeto Board que se pretende inicializar.
	 */
	private static void initSlots(Board board)
	{
		board.slotMap.put("0-0",null);
		board.slotMap.put("0-3",null);
		board.slotMap.put("0-6",null);
		board.slotMap.put("1-1",null);
		board.slotMap.put("1-3",null);
		board.slotMap.put("1-5",null);
		board.slotMap.put("2-2",null);
		board.slotMap.put("2-3",null);
		board.slotMap.put("2-4",null);
		board.slotMap.put("3-0",null);
		board.slotMap.put("3-1",null);
		board.slotMap.put("3-2",null);
		board.slotMap.put("3-4",null);
		board.slotMap.put("3-5",null);
		board.slotMap.put("3-6",null);
		board.slotMap.put("4-2",null);
		board.slotMap.put("4-3",null);
		board.slotMap.put("4-4",null);
		board.slotMap.put("5-1",null);
		board.slotMap.put("5-3",null);
		board.slotMap.put("5-5",null);
		board.slotMap.put("6-0",null);
		board.slotMap.put("6-3",null);
		board.slotMap.put("6-6",null);
	}
	
	/**
	 * Inicialização do map de adjacências do tabuleiro, indicando as adjacências para cada slot.
	 * @param board Objeto Board que se pretende inicializar.
	 */
	private static void initAdjacencies(Board board)
	{
		AdjacentSlots a0=new AdjacentSlots();
		a0.adj.add("3-0");
		a0.adj.add("0-3");
		board.adjacentsMap.put("0-0",a0);

		AdjacentSlots a1=new AdjacentSlots();
		a1.adj.add("0-0");
		a1.adj.add("0-6");
		a1.adj.add("1-3");
		board.adjacentsMap.put("0-3",a1);

		AdjacentSlots a2=new AdjacentSlots();
		a2.adj.add("3-6");
		a2.adj.add("0-3");
		board.adjacentsMap.put("0-6",a2);

		AdjacentSlots a3=new AdjacentSlots();
		a3.adj.add("0-0");
		a3.adj.add("3-1");
		a3.adj.add("6-0");
		board.adjacentsMap.put("3-0",a3);

		AdjacentSlots a4=new AdjacentSlots();
		a4.adj.add("3-0");
		a4.adj.add("6-3");
		board.adjacentsMap.put("6-0",a4);

		AdjacentSlots a5=new AdjacentSlots();
		a5.adj.add("6-0");
		a5.adj.add("5-3");
		a5.adj.add("6-6");
		board.adjacentsMap.put("6-3",a5);

		AdjacentSlots a6=new AdjacentSlots();
		a6.adj.add("3-6");
		a6.adj.add("6-3");
		board.adjacentsMap.put("6-6",a6);

		AdjacentSlots a7=new AdjacentSlots();
		a7.adj.add("6-6");
		a7.adj.add("3-5");
		a7.adj.add("0-6");
		board.adjacentsMap.put("3-6",a7);

		AdjacentSlots a8=new AdjacentSlots();
		a8.adj.add("1-3");
		a8.adj.add("3-1");
		board.adjacentsMap.put("1-1",a8);

		AdjacentSlots a9=new AdjacentSlots();
		a9.adj.add("1-1");
		a9.adj.add("1-5");
		a9.adj.add("0-3");
		a9.adj.add("2-3");
		board.adjacentsMap.put("1-3",a9);

		AdjacentSlots a10=new AdjacentSlots();
		a10.adj.add("1-3");
		a10.adj.add("3-5");
		board.adjacentsMap.put("1-5",a10);

		AdjacentSlots a11=new AdjacentSlots();
		a11.adj.add("1-1");
		a11.adj.add("3-0");
		a11.adj.add("3-2");
		a11.adj.add("5-1");
		board.adjacentsMap.put("3-1",a11);

		AdjacentSlots a12=new AdjacentSlots();
		a12.adj.add("3-1");
		a12.adj.add("5-3");
		board.adjacentsMap.put("5-1",a12);

		AdjacentSlots a13=new AdjacentSlots();
		a13.adj.add("5-1");
		a13.adj.add("5-5");
		a13.adj.add("4-3");
		a13.adj.add("6-3");
		board.adjacentsMap.put("5-3",a13);

		AdjacentSlots a14=new AdjacentSlots();
		a14.adj.add("5-3");
		a14.adj.add("3-5");
		board.adjacentsMap.put("5-5",a14);

		AdjacentSlots a15=new AdjacentSlots();
		a15.adj.add("5-5");
		a15.adj.add("3-4");
		a15.adj.add("3-6");
		a15.adj.add("1-5");
		board.adjacentsMap.put("3-5",a15);

		AdjacentSlots a16=new AdjacentSlots();
		a16.adj.add("2-3");
		a16.adj.add("3-2");
		board.adjacentsMap.put("2-2",a16);

		AdjacentSlots a17=new AdjacentSlots();
		a17.adj.add("2-2");
		a17.adj.add("1-3");
		a17.adj.add("2-4");
		board.adjacentsMap.put("2-3",a17);

		AdjacentSlots a18=new AdjacentSlots();
		a18.adj.add("2-3");
		a18.adj.add("3-4");
		board.adjacentsMap.put("2-4",a18);

		AdjacentSlots a19=new AdjacentSlots();
		a19.adj.add("2-2");
		a19.adj.add("3-1");
		a19.adj.add("4-2");
		board.adjacentsMap.put("3-2",a19);

		AdjacentSlots a20=new AdjacentSlots();
		a20.adj.add("3-2");
		a20.adj.add("4-3");
		board.adjacentsMap.put("4-2",a20);

		AdjacentSlots a21=new AdjacentSlots();
		a21.adj.add("4-2");
		a21.adj.add("4-4");
		a21.adj.add("5-3");
		board.adjacentsMap.put("4-3",a21);

		AdjacentSlots a22=new AdjacentSlots();
		a22.adj.add("3-4");
		a22.adj.add("4-3");
		board.adjacentsMap.put("4-4",a22);

		AdjacentSlots a23=new AdjacentSlots();
		a23.adj.add("2-4");
		a23.adj.add("3-5");
		a23.adj.add("4-4");
		board.adjacentsMap.put("3-4",a23);
	}

	/**
	 * Inicialização do map de mills do tabuleiro, indicando os mills nos quais cada slot está contida.
	 * @param board Objeto Board que se pretende inicializar.
	 */
	private static void initMills(Board board) 
	{
		putMill(board,"0-0","3-0","6-0","0-3","0-6");
		putMill(board,"0-3","0-0","0-6","1-3","2-3");
		putMill(board,"0-6","0-0","0-3","3-6","6-6");
		putMill(board,"3-0","0-0","6-0","3-1","3-2");
		putMill(board,"6-0","0-0","3-0","6-3","6-6");
		putMill(board,"6-3","6-0","6-6","5-3","4-3");
		putMill(board,"6-6","6-0","6-3","3-6","0-6");
		putMill(board,"3-6","6-6","0-6","3-4","3-5");
		putMill(board,"1-1","1-3","1-5","3-1","5-1");
		putMill(board,"1-3","1-1","1-5","0-3","2-3");
		putMill(board,"1-5","1-1","1-3","3-5","5-5");
		putMill(board,"3-1","1-1","5-1","3-0","3-2");
		putMill(board,"5-1","3-1","1-1","5-3","5-5");
		putMill(board,"5-3","4-3","6-3","5-1","5-5");
		putMill(board,"5-5","5-1","5-3","3-5","1-5");
		putMill(board,"3-5","1-5","5-5","3-4","3-6");
		putMill(board,"2-2","3-2","4-2","2-3","2-4");
		putMill(board,"2-3","2-2","2-4","0-3","1-3");
		putMill(board,"2-4","2-2","2-3","3-4","4-4");
		putMill(board,"3-2","2-2","4-2","3-1","3-0");
		putMill(board,"4-2","3-2","2-2","4-3","4-4");
		putMill(board,"4-3","4-2","4-4","5-3","6-3");
		putMill(board,"4-4","4-2","4-3","3-4","2-4");
		putMill(board,"3-4","2-4","4-4","3-5","3-6");
	}
	
	/**
	 * Método auxiliar que permite introduzir informação sobre os mills (sempre 2) nos quais uma slot está inserida
	 * @param board Objeto Board que se pretende inicializar.
	 * @param slot Identificador da slot cujos mills na qual se insere são gerados
	 * @param m1a1 Slot 1 do mill 1
	 * @param m1a2 Slot 2 do mill 1
	 * @param m2a1 Slot 1 do mill 2
	 * @param m2a2 Slot 2 do mill 2
	 */
	private static void putMill(Board board, String slot, String m1a1, String m1a2, String m2a1, String m2a2)
	{
		SlotMills a=new SlotMills();
		a.mill1.add(m1a1);
		a.mill1.add(m1a2);
		a.mill2.add(m2a1);
		a.mill2.add(m2a2);
		board.millsMap.put(slot, a);
	}
}