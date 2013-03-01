import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;


public class Board {

	public Slot[][] board;//board[x][y].... x-> \\  y (para baixo)
	public Player player1;
	public Player player2;
	
	public int piece_select=-1;
	public Piece[] pieces;
	
	public Board()
	{
		init_slots();
		init_pieces();
		init_mouse();
	}
	
	private void init_pieces() {
		
		pieces=new Piece[18];
		for(int i=0;i<18;i++)
		{
			pieces[i]=new Piece();
			pieces[i].active=true;
			pieces[i].color='b';
			if(i<9)
				pieces[i].color='p';
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
						piece_select=-1;
					}else if(evt.getID() == MouseEvent.MOUSE_DRAGGED )
					{
						int mouseX=evt.getX()-10;
						int mouseY=evt.getY()-30;
						
						
						
						//System.out.println("mouseX: "+mouseX+", mouseY: "+mouseY);
						
						if(piece_select == -1){
							for(int i=0;i<pieces.length;i++)
							{
								if(pieces[i].coordx <= mouseX && (pieces[i].coordx+pieces[i].mySize) >= mouseX &&
									pieces[i].coordy <= mouseY && (pieces[i].coordy+pieces[i].mySize) >= mouseY)
								{
									piece_select=i;
									pieces[i].deltx=mouseX-pieces[i].coordx;
									pieces[i].delty=mouseY-pieces[i].coordy;
									//System.out.println("peca: "+i);
									break;
								}
								
							}
						}else{
							
							int[] pos_oux=peca_tab(mouseX,mouseY,pieces[piece_select].mySize);
							if(pos_oux[0]==-1)
							{
								pieces[piece_select].coordx=mouseX-pieces[piece_select].deltx;
								pieces[piece_select].coordy=mouseY-pieces[piece_select].delty;
							}else{
								pieces[piece_select].coordx=tab_pos[pos_oux[0]][pos_oux[1]][0];
								pieces[piece_select].coordy=tab_pos[pos_oux[0]][pos_oux[1]][1];
							}
													
							pieces[piece_select].imagem.setBounds(pieces[piece_select].coordx, 
									pieces[piece_select].coordy, pieces[piece_select].mySize, 
									pieces[piece_select].mySize);
							
							System.out.println("pecaX: "+pieces[piece_select].coordx+
											", pecaY: "+pieces[piece_select].coordy);
							
						}
						
					}

					fundo.repaint();
				}
			}
		}, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		
	}

	public void init_slots()
	{
		
		board= new Slot[7][7];
		board[0][0]=new Slot();
		board[0][0].occupied=false;
		board[0][0].x=0;
		board[0][0].y=0;
		
		board[0][3]=new Slot();
		board[0][3].occupied=false;
		board[0][3].x=0;
		board[0][3].y=3;
		
		board[0][6]=new Slot();
		board[0][6].occupied=false;
		board[0][6].x=0;
		board[0][6].y=6;
		
		board[1][1]=new Slot();
		board[1][1].occupied=false;
		board[1][1].x=1;
		board[1][1].y=1;
		
		board[1][3]=new Slot();
		board[1][3].occupied=false;
		board[1][3].x=1;
		board[1][3].y=3;
		
		board[2][2]=new Slot();
		board[2][2].occupied=false;
		board[2][2].x=2;
		board[2][2].y=2;
		
		board[2][3]=new Slot();
		board[2][3].occupied=false;
		board[2][3].x=2;
		board[2][3].y=3;
		
		board[2][4]=new Slot();
		board[2][4].occupied=false;
		board[2][4].x=2;
		board[2][4].y=4;
		
		board[3][0]=new Slot();
		board[3][0].occupied=false;
		board[3][0].x=3;
		board[3][0].y=0;
		
		board[3][1]=new Slot();
		board[3][1].occupied=false;
		board[3][1].x=3;
		board[3][1].y=1;
		
		board[3][2]=new Slot();
		board[3][2].occupied=false;
		board[3][2].x=3;
		board[3][2].y=2;
		
		board[3][4]=new Slot();
		board[3][4].occupied=false;
		board[3][4].x=3;
		board[3][4].y=4;
		
		board[3][5]=new Slot();
		board[3][5].occupied=false;
		board[3][5].x=3;
		board[3][5].y=5;
		
		board[3][6]=new Slot();
		board[3][6].occupied=false;
		board[3][6].x=3;
		board[3][6].y=6;
		
		board[4][2]=new Slot();
		board[4][2].occupied=false;
		board[4][2].x=4;
		board[4][2].y=2;
		
		board[4][3]=new Slot();
		board[4][3].occupied=false;
		board[4][3].x=4;
		board[4][3].y=3;
		
		board[4][4]=new Slot();
		board[4][4].occupied=false;
		board[4][4].x=4;
		board[4][4].y=4;
		
		board[5][1]=new Slot();
		board[5][1].occupied=false;
		board[5][1].x=5;
		board[5][1].y=1;
		
		board[5][3]=new Slot();
		board[5][3].occupied=false;
		board[5][3].x=5;
		board[5][3].y=3;
		
		board[5][5]=new Slot();
		board[5][5].occupied=false;
		board[5][5].x=5;
		board[5][5].y=5;
		
		board[6][0]=new Slot();
		board[6][0].occupied=false;
		board[6][0].x=6;
		board[6][0].y=0;
		
		board[6][3]=new Slot();
		board[6][3].occupied=false;
		board[6][3].x=6;
		board[6][3].y=3;
		
		board[6][6]=new Slot();
		board[6][6].occupied=false;
		board[6][6].x=6;
		board[6][6].y=6;
		
		
		board[0][0].adjacents=new Slot[2];
		board[0][0].adjacents[0]=board[0][3];
		board[0][0].adjacents[1]=board[3][0];
		board[0][0].mill1[0]=board[0][3];
		board[0][0].mill1[1]=board[0][6];
		board[0][0].mill2[0]=board[3][0];
		board[0][0].mill2[1]=board[6][0];
		
		board[3][0].adjacents=new Slot[3];
		board[3][0].adjacents[0]=board[0][0];
		board[3][0].adjacents[1]=board[6][0];
		board[3][0].adjacents[2]=board[3][1];
		board[3][0].mill1[0]=board[0][0];
		board[3][0].mill1[1]=board[6][0];
		board[3][0].mill2[0]=board[3][1];
		board[3][0].mill2[1]=board[3][2];
		
		board[6][0].adjacents=new Slot[2];
		board[6][0].adjacents[0]=board[3][0];
		board[6][0].adjacents[1]=board[6][3];
		board[6][0].mill1[0]=board[0][0];
		board[6][0].mill1[1]=board[3][0];
		board[6][0].mill2[0]=board[6][3];
		board[6][0].mill2[1]=board[6][6];
		
		board[1][1].adjacents=new Slot[2];
		board[1][1].adjacents[0]=board[3][1];
		board[1][1].adjacents[1]=board[1][3];
		board[1][1].mill1[0]=board[3][1];
		board[1][1].mill1[1]=board[5][1];
		board[1][1].mill2[0]=board[1][3];
		board[1][1].mill2[1]=board[1][5];
		
		board[3][1].adjacents=new Slot[4];
		board[3][1].adjacents[0]=board[1][1];
		board[3][1].adjacents[1]=board[5][1];
		board[3][1].adjacents[2]=board[3][2];
		board[3][1].adjacents[3]=board[3][0];
		board[3][1].mill1[0]=board[1][1];
		board[3][1].mill1[1]=board[5][1];
		board[3][1].mill2[0]=board[3][2];
		board[3][1].mill2[1]=board[3][0];
		
		board[5][1].adjacents=new Slot[2];
		board[5][1].adjacents[0]=board[3][1];
		board[5][1].adjacents[1]=board[5][3];
		board[5][1].mill1[0]=board[3][1];
		board[5][1].mill1[1]=board[1][1];
		board[5][1].mill2[0]=board[5][3];
		board[5][1].mill2[1]=board[5][5];
		
		board[2][2].adjacents=new Slot[2];
		board[2][2].adjacents[0]=board[3][2];
		board[2][2].adjacents[1]=board[2][3];
		board[2][2].mill1[0]=board[3][2];
		board[2][2].mill1[1]=board[4][2];
		board[2][2].mill2[0]=board[2][3];
		board[2][2].mill2[1]=board[2][4];
		
		board[3][2].adjacents=new Slot[3];
		board[3][2].adjacents[0]=board[2][2];
		board[3][2].adjacents[1]=board[4][2];
		board[3][2].adjacents[2]=board[3][1];
		board[3][2].mill1[0]=board[2][2];
		board[3][2].mill1[1]=board[4][2];
		board[3][2].mill2[0]=board[3][1];
		board[3][2].mill2[1]=board[3][0];
		
		board[4][2].adjacents=new Slot[2];
		board[4][2].adjacents[0]=board[3][2];
		board[4][2].adjacents[1]=board[4][3];
		board[4][2].mill1[0]=board[3][2];
		board[4][2].mill1[1]=board[2][2];
		board[4][2].mill2[0]=board[4][3];
		board[4][2].mill2[1]=board[4][4];
		
		board[0][3].adjacents=new Slot[3];
		board[0][3].adjacents[0]=board[0][0];
		board[0][3].adjacents[1]=board[0][6];
		board[0][3].adjacents[2]=board[1][3];
		board[0][3].mill1[0]=board[0][0];
		board[0][3].mill1[1]=board[0][6];
		board[0][3].mill2[0]=board[1][3];
		board[0][3].mill2[1]=board[2][3];
		
		board[1][3].adjacents=new Slot[4];
		board[1][3].adjacents[0]=board[0][3];
		board[1][3].adjacents[1]=board[2][3];
		board[1][3].adjacents[2]=board[1][1];
		board[1][3].adjacents[3]=board[1][5];
		board[1][3].mill1[0]=board[0][3];
		board[1][3].mill1[1]=board[2][3];
		board[1][3].mill2[0]=board[1][1];
		board[1][3].mill2[1]=board[1][5];
		
		board[2][3].adjacents=new Slot[3];
		board[2][3].adjacents[0]=board[1][3];
		board[2][3].adjacents[1]=board[2][2];
		board[2][3].adjacents[2]=board[2][4];
		board[2][3].mill1[0]=board[1][3];
		board[2][3].mill1[1]=board[0][3];
		board[2][3].mill2[0]=board[2][2];
		board[2][3].mill2[1]=board[2][4];
		
		board[4][3].adjacents=new Slot[3];
		board[4][3].adjacents[0]=board[4][2];
		board[4][3].adjacents[1]=board[4][4];
		board[4][3].adjacents[2]=board[5][3];
		board[4][3].mill1[0]=board[4][2];
		board[4][3].mill1[1]=board[4][4];
		board[4][3].mill2[0]=board[5][3];
		board[4][3].mill2[1]=board[6][3];
		
		board[5][3].adjacents=new Slot[4];
		board[5][3].adjacents[0]=board[4][3];
		board[5][3].adjacents[1]=board[6][3];
		board[5][3].adjacents[2]=board[5][1];
		board[5][3].adjacents[3]=board[5][5];
		board[5][3].mill1[0]=board[4][3];
		board[5][3].mill1[1]=board[6][3];
		board[5][3].mill2[0]=board[5][1];
		board[5][3].mill2[1]=board[5][5];
		
		board[6][3].adjacents=new Slot[3];
		board[6][3].adjacents[0]=board[6][0];
		board[6][3].adjacents[1]=board[6][6];
		board[6][3].adjacents[2]=board[5][3];
		board[6][3].mill1[0]=board[6][0];
		board[6][3].mill1[1]=board[6][6];
		board[6][3].mill2[0]=board[5][3];
		board[6][3].mill2[1]=board[4][3];
		
		board[2][4].adjacents=new Slot[2];
		board[2][4].adjacents[0]=board[2][3];
		board[2][4].adjacents[1]=board[3][4];
		board[2][4].mill1[0]=board[2][3];
		board[2][4].mill1[1]=board[2][2];
		board[2][4].mill2[0]=board[3][4];
		board[2][4].mill2[1]=board[4][4];
		
		board[3][4].adjacents=new Slot[3];
		board[3][4].adjacents[0]=board[2][4];
		board[3][4].adjacents[1]=board[4][4];
		board[3][4].adjacents[2]=board[3][5];
		board[3][4].mill1[0]=board[2][4];
		board[3][4].mill1[1]=board[4][4];
		board[3][4].mill2[0]=board[3][5];
		board[3][4].mill2[1]=board[3][6];
		
		board[4][4].adjacents=new Slot[2];
		board[4][4].adjacents[0]=board[4][3];
		board[4][4].adjacents[1]=board[3][4];
		board[4][4].mill1[0]=board[4][3];
		board[4][4].mill1[1]=board[4][2];
		board[4][4].mill2[0]=board[3][4];
		board[4][4].mill2[1]=board[2][4];
		
		board[1][5].adjacents=new Slot[2];
		board[1][5].adjacents[0]=board[1][3];
		board[1][5].adjacents[1]=board[3][5];
		board[1][5].mill1[0]=board[1][3];
		board[1][5].mill1[1]=board[1][1];
		board[1][5].mill2[0]=board[3][5];
		board[1][5].mill2[1]=board[5][5];
		
		board[3][5].adjacents=new Slot[4];
		board[3][5].adjacents[0]=board[1][5];
		board[3][5].adjacents[1]=board[5][5];
		board[3][5].adjacents[2]=board[3][4];
		board[3][5].adjacents[3]=board[3][6];
		board[3][5].mill1[0]=board[1][5];
		board[3][5].mill1[1]=board[5][5];
		board[3][5].mill2[0]=board[3][4];
		board[3][5].mill2[1]=board[3][6];
		
		board[5][5].adjacents=new Slot[2];
		board[5][5].adjacents[0]=board[5][3];
		board[5][5].adjacents[1]=board[3][5];
		board[5][5].mill1[0]=board[5][3];
		board[5][5].mill1[1]=board[5][1];
		board[5][5].mill2[0]=board[3][5];
		board[5][5].mill2[1]=board[1][5];
		
		board[0][6].adjacents=new Slot[2];
		board[0][6].adjacents[0]=board[0][3];
		board[0][6].adjacents[1]=board[3][6];
		board[0][6].mill1[0]=board[0][3];
		board[0][6].mill1[1]=board[0][0];
		board[0][6].mill2[0]=board[3][6];
		board[0][6].mill2[1]=board[6][6];
		
		board[3][6].adjacents=new Slot[3];
		board[3][6].adjacents[0]=board[0][6];
		board[3][6].adjacents[1]=board[6][6];
		board[3][6].adjacents[2]=board[3][5];
		board[3][6].mill1[0]=board[0][6];
		board[3][6].mill1[1]=board[6][6];
		board[3][6].mill2[0]=board[3][5];
		board[3][6].mill2[1]=board[3][4];
		
		board[6][6].adjacents=new Slot[2];
		board[6][6].adjacents[0]=board[6][3];
		board[6][6].adjacents[1]=board[3][6];
		board[6][6].mill1[0]=board[6][3];
		board[6][6].mill1[1]=board[6][0];
		board[6][6].mill2[0]=board[3][6];
		board[6][6].mill2[1]=board[0][6];
		
		
	}
	
	public void run()
	{
		
	}
	
	public void end()
	{
		
	}
}
