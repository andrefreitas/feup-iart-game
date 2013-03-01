import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;


public class Board {

	public Slot[][] tabuleiro;//tabuleiro[x][y].... x-> \\  y (para baixo)
	public Player jogador1;
	public Player jogador2;
	
	public int peca_select=-1;
	public Piece[] pecas;
	
	public Board()
	{
		init_slots();
		init_pieces();
		init_mouse();
	}
	
	private void init_pieces() {
		
		pecas=new Piece[18];
		for(int i=0;i<18;i++)
		{
			pecas[i]=new Piece();
			pecas[i].active=true;
			pecas[i].color='b';
			if(i<9)
				pecas[i].color='p';
		}
		
	}

	private void init_mouse() {
		
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			public void eventDispatched(AWTEvent event) {
				if(event instanceof MouseEvent){
					MouseEvent evt = (MouseEvent)event;
					if(evt.getID() == MouseEvent.MOUSE_CLICKED ){
						
						
					}else if(evt.getID() == MouseEvent.MOUSE_RELEASED )
					{
						peca_select=-1;
					}else if(evt.getID() == MouseEvent.MOUSE_DRAGGED )
					{
						int mouseX=evt.getX()-10;
						int mouseY=evt.getY()-30;
						
						
						
						//System.out.println("mouseX: "+mouseX+", mouseY: "+mouseY);
						
						if(peca_select == -1){
							for(int i=0;i<pecas.length;i++)
							{
								if(pecas[i].coordx <= mouseX && (pecas[i].coordx+pecas[i].tamanho) >= mouseX &&
								   pecas[i].coordy <= mouseY && (pecas[i].coordy+pecas[i].tamanho) >= mouseY)
								{
									peca_select=i;
									pecas[i].deltx=mouseX-pecas[i].coordx;
									pecas[i].delty=mouseY-pecas[i].coordy;
									//System.out.println("peca: "+i);
									break;
								}
								
							}
						}else{
							
							int[] pos_oux=peca_tab(mouseX,mouseY,pecas[peca_select].tamanho);
							if(pos_oux[0]==-1)
							{
								pecas[peca_select].coordx=mouseX-pecas[peca_select].deltx;
								pecas[peca_select].coordy=mouseY-pecas[peca_select].delty;
							}else{
								pecas[peca_select].coordx=tab_pos[pos_oux[0]][pos_oux[1]][0];
								pecas[peca_select].coordy=tab_pos[pos_oux[0]][pos_oux[1]][1];
							}
													
							pecas[peca_select].imagem.setBounds(pecas[peca_select].coordx, 
																pecas[peca_select].coordy, 
									pecas[peca_select].tamanho, pecas[peca_select].tamanho);
							
							System.out.println("pecaX: "+pecas[peca_select].coordx+
											", pecaY: "+pecas[peca_select].coordy);
							
						}
						
					}

					fundo.repaint();
				}
			}
		}, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		
	}

	public void init_slots()
	{
		
		tabuleiro= new Slot[7][7];
		tabuleiro[0][0]=new Slot();
		tabuleiro[0][0].occupied=false;
		tabuleiro[0][0].x=0;
		tabuleiro[0][0].y=0;
		
		tabuleiro[0][3]=new Slot();
		tabuleiro[0][3].occupied=false;
		tabuleiro[0][3].x=0;
		tabuleiro[0][3].y=3;
		
		tabuleiro[0][6]=new Slot();
		tabuleiro[0][6].occupied=false;
		tabuleiro[0][6].x=0;
		tabuleiro[0][6].y=6;
		
		tabuleiro[1][1]=new Slot();
		tabuleiro[1][1].occupied=false;
		tabuleiro[1][1].x=1;
		tabuleiro[1][1].y=1;
		
		tabuleiro[1][3]=new Slot();
		tabuleiro[1][3].occupied=false;
		tabuleiro[1][3].x=1;
		tabuleiro[1][3].y=3;
		
		tabuleiro[2][2]=new Slot();
		tabuleiro[2][2].occupied=false;
		tabuleiro[2][2].x=2;
		tabuleiro[2][2].y=2;
		
		tabuleiro[2][3]=new Slot();
		tabuleiro[2][3].occupied=false;
		tabuleiro[2][3].x=2;
		tabuleiro[2][3].y=3;
		
		tabuleiro[2][4]=new Slot();
		tabuleiro[2][4].occupied=false;
		tabuleiro[2][4].x=2;
		tabuleiro[2][4].y=4;
		
		tabuleiro[3][0]=new Slot();
		tabuleiro[3][0].occupied=false;
		tabuleiro[3][0].x=3;
		tabuleiro[3][0].y=0;
		
		tabuleiro[3][1]=new Slot();
		tabuleiro[3][1].occupied=false;
		tabuleiro[3][1].x=3;
		tabuleiro[3][1].y=1;
		
		tabuleiro[3][2]=new Slot();
		tabuleiro[3][2].occupied=false;
		tabuleiro[3][2].x=3;
		tabuleiro[3][2].y=2;
		
		tabuleiro[3][4]=new Slot();
		tabuleiro[3][4].occupied=false;
		tabuleiro[3][4].x=3;
		tabuleiro[3][4].y=4;
		
		tabuleiro[3][5]=new Slot();
		tabuleiro[3][5].occupied=false;
		tabuleiro[3][5].x=3;
		tabuleiro[3][5].y=5;
		
		tabuleiro[3][6]=new Slot();
		tabuleiro[3][6].occupied=false;
		tabuleiro[3][6].x=3;
		tabuleiro[3][6].y=6;
		
		tabuleiro[4][2]=new Slot();
		tabuleiro[4][2].occupied=false;
		tabuleiro[4][2].x=4;
		tabuleiro[4][2].y=2;
		
		tabuleiro[4][3]=new Slot();
		tabuleiro[4][3].occupied=false;
		tabuleiro[4][3].x=4;
		tabuleiro[4][3].y=3;
		
		tabuleiro[4][4]=new Slot();
		tabuleiro[4][4].occupied=false;
		tabuleiro[4][4].x=4;
		tabuleiro[4][4].y=4;
		
		tabuleiro[5][1]=new Slot();
		tabuleiro[5][1].occupied=false;
		tabuleiro[5][1].x=5;
		tabuleiro[5][1].y=1;
		
		tabuleiro[5][3]=new Slot();
		tabuleiro[5][3].occupied=false;
		tabuleiro[5][3].x=5;
		tabuleiro[5][3].y=3;
		
		tabuleiro[5][5]=new Slot();
		tabuleiro[5][5].occupied=false;
		tabuleiro[5][5].x=5;
		tabuleiro[5][5].y=5;
		
		tabuleiro[6][0]=new Slot();
		tabuleiro[6][0].occupied=false;
		tabuleiro[6][0].x=6;
		tabuleiro[6][0].y=0;
		
		tabuleiro[6][3]=new Slot();
		tabuleiro[6][3].occupied=false;
		tabuleiro[6][3].x=6;
		tabuleiro[6][3].y=3;
		
		tabuleiro[6][6]=new Slot();
		tabuleiro[6][6].occupied=false;
		tabuleiro[6][6].x=6;
		tabuleiro[6][6].y=6;
		
		
		tabuleiro[0][0].adjacents=new Slot[2];
		tabuleiro[0][0].adjacents[0]=tabuleiro[0][3];
		tabuleiro[0][0].adjacents[1]=tabuleiro[3][0];
		tabuleiro[0][0].mill1[0]=tabuleiro[0][3];
		tabuleiro[0][0].mill1[1]=tabuleiro[0][6];
		tabuleiro[0][0].mill2[0]=tabuleiro[3][0];
		tabuleiro[0][0].mill2[1]=tabuleiro[6][0];
		
		tabuleiro[3][0].adjacents=new Slot[3];
		tabuleiro[3][0].adjacents[0]=tabuleiro[0][0];
		tabuleiro[3][0].adjacents[1]=tabuleiro[6][0];
		tabuleiro[3][0].adjacents[2]=tabuleiro[3][1];
		tabuleiro[3][0].mill1[0]=tabuleiro[0][0];
		tabuleiro[3][0].mill1[1]=tabuleiro[6][0];
		tabuleiro[3][0].mill2[0]=tabuleiro[3][1];
		tabuleiro[3][0].mill2[1]=tabuleiro[3][2];
		
		tabuleiro[6][0].adjacents=new Slot[2];
		tabuleiro[6][0].adjacents[0]=tabuleiro[3][0];
		tabuleiro[6][0].adjacents[1]=tabuleiro[6][3];
		tabuleiro[6][0].mill1[0]=tabuleiro[0][0];
		tabuleiro[6][0].mill1[1]=tabuleiro[3][0];
		tabuleiro[6][0].mill2[0]=tabuleiro[6][3];
		tabuleiro[6][0].mill2[1]=tabuleiro[6][6];
		
		tabuleiro[1][1].adjacents=new Slot[2];
		tabuleiro[1][1].adjacents[0]=tabuleiro[3][1];
		tabuleiro[1][1].adjacents[1]=tabuleiro[1][3];
		tabuleiro[1][1].mill1[0]=tabuleiro[3][1];
		tabuleiro[1][1].mill1[1]=tabuleiro[5][1];
		tabuleiro[1][1].mill2[0]=tabuleiro[1][3];
		tabuleiro[1][1].mill2[1]=tabuleiro[1][5];
		
		tabuleiro[3][1].adjacents=new Slot[4];
		tabuleiro[3][1].adjacents[0]=tabuleiro[1][1];
		tabuleiro[3][1].adjacents[1]=tabuleiro[5][1];
		tabuleiro[3][1].adjacents[2]=tabuleiro[3][2];
		tabuleiro[3][1].adjacents[3]=tabuleiro[3][0];
		tabuleiro[3][1].mill1[0]=tabuleiro[1][1];
		tabuleiro[3][1].mill1[1]=tabuleiro[5][1];
		tabuleiro[3][1].mill2[0]=tabuleiro[3][2];
		tabuleiro[3][1].mill2[1]=tabuleiro[3][0];
		
		tabuleiro[5][1].adjacents=new Slot[2];
		tabuleiro[5][1].adjacents[0]=tabuleiro[3][1];
		tabuleiro[5][1].adjacents[1]=tabuleiro[5][3];
		tabuleiro[5][1].mill1[0]=tabuleiro[3][1];
		tabuleiro[5][1].mill1[1]=tabuleiro[1][1];
		tabuleiro[5][1].mill2[0]=tabuleiro[5][3];
		tabuleiro[5][1].mill2[1]=tabuleiro[5][5];
		
		tabuleiro[2][2].adjacents=new Slot[2];
		tabuleiro[2][2].adjacents[0]=tabuleiro[3][2];
		tabuleiro[2][2].adjacents[1]=tabuleiro[2][3];
		tabuleiro[2][2].mill1[0]=tabuleiro[3][2];
		tabuleiro[2][2].mill1[1]=tabuleiro[4][2];
		tabuleiro[2][2].mill2[0]=tabuleiro[2][3];
		tabuleiro[2][2].mill2[1]=tabuleiro[2][4];
		
		tabuleiro[3][2].adjacents=new Slot[3];
		tabuleiro[3][2].adjacents[0]=tabuleiro[2][2];
		tabuleiro[3][2].adjacents[1]=tabuleiro[4][2];
		tabuleiro[3][2].adjacents[2]=tabuleiro[3][1];
		tabuleiro[3][2].mill1[0]=tabuleiro[2][2];
		tabuleiro[3][2].mill1[1]=tabuleiro[4][2];
		tabuleiro[3][2].mill2[0]=tabuleiro[3][1];
		tabuleiro[3][2].mill2[1]=tabuleiro[3][0];
		
		tabuleiro[4][2].adjacents=new Slot[2];
		tabuleiro[4][2].adjacents[0]=tabuleiro[3][2];
		tabuleiro[4][2].adjacents[1]=tabuleiro[4][3];
		tabuleiro[4][2].mill1[0]=tabuleiro[3][2];
		tabuleiro[4][2].mill1[1]=tabuleiro[2][2];
		tabuleiro[4][2].mill2[0]=tabuleiro[4][3];
		tabuleiro[4][2].mill2[1]=tabuleiro[4][4];
		
		tabuleiro[0][3].adjacents=new Slot[3];
		tabuleiro[0][3].adjacents[0]=tabuleiro[0][0];
		tabuleiro[0][3].adjacents[1]=tabuleiro[0][6];
		tabuleiro[0][3].adjacents[2]=tabuleiro[1][3];
		tabuleiro[0][3].mill1[0]=tabuleiro[0][0];
		tabuleiro[0][3].mill1[1]=tabuleiro[0][6];
		tabuleiro[0][3].mill2[0]=tabuleiro[1][3];
		tabuleiro[0][3].mill2[1]=tabuleiro[2][3];
		
		tabuleiro[1][3].adjacents=new Slot[4];
		tabuleiro[1][3].adjacents[0]=tabuleiro[0][3];
		tabuleiro[1][3].adjacents[1]=tabuleiro[2][3];
		tabuleiro[1][3].adjacents[2]=tabuleiro[1][1];
		tabuleiro[1][3].adjacents[3]=tabuleiro[1][5];
		tabuleiro[1][3].mill1[0]=tabuleiro[0][3];
		tabuleiro[1][3].mill1[1]=tabuleiro[2][3];
		tabuleiro[1][3].mill2[0]=tabuleiro[1][1];
		tabuleiro[1][3].mill2[1]=tabuleiro[1][5];
		
		tabuleiro[2][3].adjacents=new Slot[3];
		tabuleiro[2][3].adjacents[0]=tabuleiro[1][3];
		tabuleiro[2][3].adjacents[1]=tabuleiro[2][2];
		tabuleiro[2][3].adjacents[2]=tabuleiro[2][4];
		tabuleiro[2][3].mill1[0]=tabuleiro[1][3];
		tabuleiro[2][3].mill1[1]=tabuleiro[0][3];
		tabuleiro[2][3].mill2[0]=tabuleiro[2][2];
		tabuleiro[2][3].mill2[1]=tabuleiro[2][4];
		
		tabuleiro[4][3].adjacents=new Slot[3];
		tabuleiro[4][3].adjacents[0]=tabuleiro[4][2];
		tabuleiro[4][3].adjacents[1]=tabuleiro[4][4];
		tabuleiro[4][3].adjacents[2]=tabuleiro[5][3];
		tabuleiro[4][3].mill1[0]=tabuleiro[4][2];
		tabuleiro[4][3].mill1[1]=tabuleiro[4][4];
		tabuleiro[4][3].mill2[0]=tabuleiro[5][3];
		tabuleiro[4][3].mill2[1]=tabuleiro[6][3];
		
		tabuleiro[5][3].adjacents=new Slot[4];
		tabuleiro[5][3].adjacents[0]=tabuleiro[4][3];
		tabuleiro[5][3].adjacents[1]=tabuleiro[6][3];
		tabuleiro[5][3].adjacents[2]=tabuleiro[5][1];
		tabuleiro[5][3].adjacents[3]=tabuleiro[5][5];
		tabuleiro[5][3].mill1[0]=tabuleiro[4][3];
		tabuleiro[5][3].mill1[1]=tabuleiro[6][3];
		tabuleiro[5][3].mill2[0]=tabuleiro[5][1];
		tabuleiro[5][3].mill2[1]=tabuleiro[5][5];
		
		tabuleiro[6][3].adjacents=new Slot[3];
		tabuleiro[6][3].adjacents[0]=tabuleiro[6][0];
		tabuleiro[6][3].adjacents[1]=tabuleiro[6][6];
		tabuleiro[6][3].adjacents[2]=tabuleiro[5][3];
		tabuleiro[6][3].mill1[0]=tabuleiro[6][0];
		tabuleiro[6][3].mill1[1]=tabuleiro[6][6];
		tabuleiro[6][3].mill2[0]=tabuleiro[5][3];
		tabuleiro[6][3].mill2[1]=tabuleiro[4][3];
		
		tabuleiro[2][4].adjacents=new Slot[2];
		tabuleiro[2][4].adjacents[0]=tabuleiro[2][3];
		tabuleiro[2][4].adjacents[1]=tabuleiro[3][4];
		tabuleiro[2][4].mill1[0]=tabuleiro[2][3];
		tabuleiro[2][4].mill1[1]=tabuleiro[2][2];
		tabuleiro[2][4].mill2[0]=tabuleiro[3][4];
		tabuleiro[2][4].mill2[1]=tabuleiro[4][4];
		
		tabuleiro[3][4].adjacents=new Slot[3];
		tabuleiro[3][4].adjacents[0]=tabuleiro[2][4];
		tabuleiro[3][4].adjacents[1]=tabuleiro[4][4];
		tabuleiro[3][4].adjacents[2]=tabuleiro[3][5];
		tabuleiro[3][4].mill1[0]=tabuleiro[2][4];
		tabuleiro[3][4].mill1[1]=tabuleiro[4][4];
		tabuleiro[3][4].mill2[0]=tabuleiro[3][5];
		tabuleiro[3][4].mill2[1]=tabuleiro[3][6];
		
		tabuleiro[4][4].adjacents=new Slot[2];
		tabuleiro[4][4].adjacents[0]=tabuleiro[4][3];
		tabuleiro[4][4].adjacents[1]=tabuleiro[3][4];
		tabuleiro[4][4].mill1[0]=tabuleiro[4][3];
		tabuleiro[4][4].mill1[1]=tabuleiro[4][2];
		tabuleiro[4][4].mill2[0]=tabuleiro[3][4];
		tabuleiro[4][4].mill2[1]=tabuleiro[2][4];
		
		tabuleiro[1][5].adjacents=new Slot[2];
		tabuleiro[1][5].adjacents[0]=tabuleiro[1][3];
		tabuleiro[1][5].adjacents[1]=tabuleiro[3][5];
		tabuleiro[1][5].mill1[0]=tabuleiro[1][3];
		tabuleiro[1][5].mill1[1]=tabuleiro[1][1];
		tabuleiro[1][5].mill2[0]=tabuleiro[3][5];
		tabuleiro[1][5].mill2[1]=tabuleiro[5][5];
		
		tabuleiro[3][5].adjacents=new Slot[4];
		tabuleiro[3][5].adjacents[0]=tabuleiro[1][5];
		tabuleiro[3][5].adjacents[1]=tabuleiro[5][5];
		tabuleiro[3][5].adjacents[2]=tabuleiro[3][4];
		tabuleiro[3][5].adjacents[3]=tabuleiro[3][6];
		tabuleiro[3][5].mill1[0]=tabuleiro[1][5];
		tabuleiro[3][5].mill1[1]=tabuleiro[5][5];
		tabuleiro[3][5].mill2[0]=tabuleiro[3][4];
		tabuleiro[3][5].mill2[1]=tabuleiro[3][6];
		
		tabuleiro[5][5].adjacents=new Slot[2];
		tabuleiro[5][5].adjacents[0]=tabuleiro[5][3];
		tabuleiro[5][5].adjacents[1]=tabuleiro[3][5];
		tabuleiro[5][5].mill1[0]=tabuleiro[5][3];
		tabuleiro[5][5].mill1[1]=tabuleiro[5][1];
		tabuleiro[5][5].mill2[0]=tabuleiro[3][5];
		tabuleiro[5][5].mill2[1]=tabuleiro[1][5];
		
		tabuleiro[0][6].adjacents=new Slot[2];
		tabuleiro[0][6].adjacents[0]=tabuleiro[0][3];
		tabuleiro[0][6].adjacents[1]=tabuleiro[3][6];
		tabuleiro[0][6].mill1[0]=tabuleiro[0][3];
		tabuleiro[0][6].mill1[1]=tabuleiro[0][0];
		tabuleiro[0][6].mill2[0]=tabuleiro[3][6];
		tabuleiro[0][6].mill2[1]=tabuleiro[6][6];
		
		tabuleiro[3][6].adjacents=new Slot[3];
		tabuleiro[3][6].adjacents[0]=tabuleiro[0][6];
		tabuleiro[3][6].adjacents[1]=tabuleiro[6][6];
		tabuleiro[3][6].adjacents[2]=tabuleiro[3][5];
		tabuleiro[3][6].mill1[0]=tabuleiro[0][6];
		tabuleiro[3][6].mill1[1]=tabuleiro[6][6];
		tabuleiro[3][6].mill2[0]=tabuleiro[3][5];
		tabuleiro[3][6].mill2[1]=tabuleiro[3][4];
		
		tabuleiro[6][6].adjacents=new Slot[2];
		tabuleiro[6][6].adjacents[0]=tabuleiro[6][3];
		tabuleiro[6][6].adjacents[1]=tabuleiro[3][6];
		tabuleiro[6][6].mill1[0]=tabuleiro[6][3];
		tabuleiro[6][6].mill1[1]=tabuleiro[6][0];
		tabuleiro[6][6].mill2[0]=tabuleiro[3][6];
		tabuleiro[6][6].mill2[1]=tabuleiro[0][6];
		
		
	}
	
	public void run()
	{
		
	}
	
	public void end()
	{
		
	}
}
