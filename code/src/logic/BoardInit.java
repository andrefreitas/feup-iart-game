package logic;

/**
 * Classe auxiliar que contém os dados constantes necessários à inicialização do tabuleiro do jogo.
 */
public class BoardInit 
{
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
		SlotMills a0=new SlotMills();
		a0.mill1.add("3-0");
		a0.mill1.add("6-0");
		a0.mill2.add("0-3");
		a0.mill2.add("0-6");
		board.millsMap.put("0-0",a0);

		SlotMills a1=new SlotMills();
		a1.mill1.add("0-0");
		a1.mill1.add("0-6");
		a1.mill2.add("1-3");
		a1.mill2.add("2-3");
		board.millsMap.put("0-3",a1);

		SlotMills a2=new SlotMills();
		a2.mill1.add("0-0");
		a2.mill1.add("0-3");
		a2.mill2.add("3-6");
		a2.mill2.add("6-6");
		board.millsMap.put("0-6",a2);

		SlotMills a3=new SlotMills();
		a3.mill1.add("0-0");
		a3.mill1.add("6-0");
		a3.mill2.add("3-1");
		a3.mill2.add("3-2");
		board.millsMap.put("3-0",a3);

		SlotMills a4=new SlotMills();
		a4.mill1.add("0-0");
		a4.mill1.add("3-0");
		a4.mill2.add("6-3");
		a4.mill2.add("6-6");
		board.millsMap.put("6-0",a4);

		SlotMills a5=new SlotMills();
		a5.mill1.add("6-0");
		a5.mill1.add("6-6");
		a5.mill2.add("5-3");
		a5.mill2.add("4-3");
		board.millsMap.put("6-3",a5);

		SlotMills a6=new SlotMills();
		a6.mill1.add("6-0");
		a6.mill1.add("6-3");
		a6.mill2.add("3-6");
		a6.mill2.add("0-6");
		board.millsMap.put("6-6",a6);

		SlotMills a7=new SlotMills();
		a7.mill1.add("6-6");
		a7.mill1.add("0-6");
		a7.mill2.add("3-4");
		a7.mill2.add("3-5");
		board.millsMap.put("3-6",a7);

		SlotMills a8=new SlotMills();
		a8.mill1.add("1-3");
		a8.mill1.add("1-5");
		a8.mill2.add("3-1");
		a8.mill2.add("5-1");
		board.millsMap.put("1-1",a8);

		SlotMills a9=new SlotMills();
		a9.mill1.add("1-1");
		a9.mill1.add("1-5");
		a9.mill2.add("0-3");
		a9.mill2.add("2-3");
		board.millsMap.put("1-3",a9);

		SlotMills a10=new SlotMills();
		a10.mill1.add("1-1");
		a10.mill1.add("1-3");
		a10.mill2.add("3-5");
		a10.mill2.add("5-5");
		board.millsMap.put("1-5",a10);

		SlotMills a11=new SlotMills();
		a11.mill1.add("1-1");
		a11.mill1.add("5-1");
		a11.mill2.add("3-0");
		a11.mill2.add("3-2");
		board.millsMap.put("3-1",a11);

		SlotMills a12=new SlotMills();
		a12.mill1.add("3-1");
		a12.mill1.add("1-1");
		a12.mill2.add("5-3");
		a12.mill2.add("5-5");
		board.millsMap.put("5-1",a12);

		SlotMills a13=new SlotMills();
		a13.mill1.add("4-3");
		a13.mill1.add("6-3");
		a13.mill2.add("5-1");
		a13.mill2.add("5-5");
		board.millsMap.put("5-3",a13);

		SlotMills a14=new SlotMills();
		a14.mill1.add("5-1");
		a14.mill1.add("5-3");
		a14.mill2.add("3-5");
		a14.mill2.add("1-5");
		board.millsMap.put("5-5",a14);

		SlotMills a15=new SlotMills();
		a15.mill1.add("1-5");
		a15.mill1.add("5-5");
		a15.mill2.add("3-4");
		a15.mill2.add("3-6");
		board.millsMap.put("3-5",a15);

		SlotMills a16=new SlotMills();
		a16.mill1.add("3-2");
		a16.mill1.add("4-2");
		a16.mill2.add("2-3");
		a16.mill2.add("2-4");
		board.millsMap.put("2-2",a16);

		SlotMills a17=new SlotMills();
		a17.mill1.add("2-2");
		a17.mill1.add("2-4");
		a17.mill2.add("0-3");
		a17.mill2.add("1-3");
		board.millsMap.put("2-3",a17);

		SlotMills a18=new SlotMills();
		a18.mill1.add("2-2");
		a18.mill1.add("2-3");
		a18.mill2.add("3-4");
		a18.mill2.add("4-4");
		board.millsMap.put("2-4",a18);

		SlotMills a19=new SlotMills();
		a19.mill1.add("2-2");
		a19.mill1.add("4-2");
		a19.mill2.add("3-1");
		a19.mill2.add("3-0");
		board.millsMap.put("3-2",a19);

		SlotMills a20=new SlotMills();
		a20.mill1.add("3-2");
		a20.mill1.add("2-2");
		a20.mill2.add("4-3");
		a20.mill2.add("4-4");
		board.millsMap.put("4-2",a20);

		SlotMills a21=new SlotMills();
		a21.mill1.add("4-2");
		a21.mill1.add("4-4");
		a21.mill2.add("5-3");
		a21.mill2.add("6-3");
		board.millsMap.put("4-3",a21);

		SlotMills a22=new SlotMills();
		a22.mill1.add("4-2");
		a22.mill1.add("4-3");
		a22.mill2.add("3-4");
		a22.mill2.add("2-4");
		board.millsMap.put("4-4",a22);

		SlotMills a23=new SlotMills();
		a23.mill1.add("2-4");
		a23.mill1.add("4-4");
		a23.mill2.add("3-5");
		a23.mill2.add("3-6");
		board.millsMap.put("3-4",a23);
	}
}