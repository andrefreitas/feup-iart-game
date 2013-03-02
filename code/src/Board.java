import java.awt.AWTEvent;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Board {

	static URL url11 = 
			ClassLoader.getSystemClassLoader().
			getResource("images/pecabranca.png");
	private static ImageIcon iconbranca = new ImageIcon(url11);
	static URL url12 = 
			ClassLoader.getSystemClassLoader().
			getResource("images/pecapreta.png");
	private static ImageIcon iconpreta = new ImageIcon(url12);
	
	
	public URL url1 = 
			ClassLoader.getSystemClassLoader().
			getResource("images/fundo2.png");
	public ImageIcon icontabuleiro = new ImageIcon(url1);
	
	public JFrame window = new JFrame();
	public JLabel background;
	
	
	public Slot[][] board;//board[x][y].... x-> \\  y (para baixo)
	public Player[] players;
	public int turn;
	public boolean remove_piece;
	
	public boolean piece_onBoard=false;
	public int piece_select=-1;
	public Piece[] pieces;
	
	public Board()
	{
		init_slots();
		init_pieces();
		
		init_players();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(new Dimension(800,538));
		window.setBounds(250, 100, 900, 750);
		window.setLayout(null);
		
		background = new JLabel(icontabuleiro);
		background.setBounds(0, 0, 800, 500);
		
		Container a = new Container();
		
		for(int i=0;i<pieces.length;i++)
		{
			
			a.add(pieces[i].image);
		}
		a.add(background);
		window.setContentPane(a);
		
		
		init_mouse();
		
		
		window.repaint();
		window.pack();
		window.setVisible(true);
	}
	
	public int change_turn(int t)
	{
		
		return t*(-1)+1;
	}
	
	public void init_players() {
		
		turn=0;
		remove_piece=false;
		
		players=new Player[2];
		players[0]=new Human();
		players[1]=new Human();
		
		players[0].color='p';
		players[0].nMoves=0;
		players[0].nPieces=9;
		
		players[1].color='b';
		players[1].nMoves=0;
		players[1].nPieces=9;
	}

	private void init_pieces() {
		
		pieces=new Piece[18];
		for(int i=0;i<18;i++)
		{
			pieces[i]=new Piece();
			pieces[i].mySize=47;
			
			pieces[i].black=new JLabel(iconpreta);
			//pieces[i].black.setBounds(xcoord,ycoord,tamanho,tamanho);
			pieces[i].white=new JLabel(iconbranca);
			//pieces[i].white.setBounds(xcoord,ycoord,tamanho,tamanho);
			
			
			pieces[i].active=true;
			pieces[i].color='b';
			
			pieces[i].image=pieces[i].white;
			pieces[i].coordy=327;
			pieces[i].coordy_old=327;
			if(i<9)
			{
				pieces[i].color='p';
				pieces[i].image=pieces[i].black;
				pieces[i].coordy=50;
				pieces[i].coordy_old=50;
				
			}
			pieces[i].coordx=500;
			pieces[i].coordx_old=500;
			pieces[i].xOnBoard_old=-1;
			pieces[i].yOnBoard_old=-1;
			pieces[i].xOnBoard=-1;
			pieces[i].yOnBoard=-1;
			
			pieces[i].image.setBounds(pieces[i].coordx, pieces[i].coordy, 
					pieces[i].mySize, pieces[i].mySize);
		}
		
	}

	public boolean valid_position()
	{
		if(piece_select==-1)
		{
			return false;
		}
		
		if(pieces[piece_select].xOnBoard==-1)
		{
			return false;
		}
		
		if(board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].occupied)
		{
			return false;
		}
		
		if(players[turn].nMoves<9)
		{
			//"stage 1"
			
			if(pieces[piece_select].xOnBoard_old!=-1)
				return false;
		}else if(players[turn].nPieces>3)
		{
			//"stage 2"
			boolean found=false;
			for(int i=0;i<board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].adjacents.length;i++)
			{
				if(board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].adjacents[i].x==
						pieces[piece_select].xOnBoard_old &&
				   board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].adjacents[i].y==
						pieces[piece_select].yOnBoard_old) 
						
				{
					found=true;
				}
			}
			if(found==false)
				return false;
		}else
		{
			//"stage 3"
			
			
		}
		//falta validar "stage" de jogo: 3-voar peças
		return true;
	}
	
	public boolean check_mills()
	{
		char oux_color=pieces[piece_select].color;
		Slot[] oux_mill1=board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].mill1;
		Slot[] oux_mill2=board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].mill2;
		
		if(oux_mill1[0].occupied && oux_mill1[1].occupied)
		{
			if(oux_mill1[0].piece.color==oux_color && oux_mill1[1].piece.color==oux_color)
			{
				return true;
			}
		}
		
		if(oux_mill2[0].occupied && oux_mill2[1].occupied)
		{
			if(oux_mill2[0].piece.color==oux_color && oux_mill2[1].piece.color==oux_color)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean valid_remove()
	{
		if(players[turn].color==pieces[piece_select].color && 
				board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].occupied)
		{
			//falta dar prioridade a peças que n formem mills.
			return true;
		}
		
		return false;
	}
	
	private void init_mouse() {
		
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			public void eventDispatched(AWTEvent event) {
				if(event instanceof MouseEvent){
					MouseEvent evt = (MouseEvent)event;
					if(evt.getID() == MouseEvent.MOUSE_CLICKED ){
						
						if(remove_piece)
						{
							int mouseX=evt.getX()-10;
							int mouseY=evt.getY()-30;
							for(int i=0;i<pieces.length;i++)
							{
								if(pieces[i].coordx <= mouseX && (pieces[i].coordx+pieces[i].mySize) >= mouseX &&
									pieces[i].coordy <= mouseY && (pieces[i].coordy+pieces[i].mySize) >= mouseY &&
									pieces[i].color == players[turn].color)
								{
									piece_select=i;
									
									//System.out.println("peca: "+i);
									break;
								}
								
							}
							if(piece_select!=-1)
							if(valid_remove())
							{
								
								players[turn].nPieces--;
								System.out.println("peça removida turn: "+turn);
								pieces[piece_select].image.setBounds(1000, 1000, 1, 1);
								board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].occupied=false;
								board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].piece=null;
								pieces[piece_select].xOnBoard=-1;
								pieces[piece_select].yOnBoard=-1;
								pieces[piece_select].xOnBoard_old=-1;
								pieces[piece_select].yOnBoard_old=-1;
								pieces[piece_select].coordx=1000;
								pieces[piece_select].coordy=1000;
								pieces[piece_select].coordx_old=1000;
								pieces[piece_select].coordy_old=1000;
								
								remove_piece=false;
							}
							piece_select=-1;
						}
						
						//System.out.println("Click");
					}else if(evt.getID() == MouseEvent.MOUSE_RELEASED && remove_piece==false)
					{
						if(piece_select!=-1)
						{
							if(piece_onBoard && valid_position())
							{
								
								pieces[piece_select].coordx_old=pieces[piece_select].coordx;
								pieces[piece_select].coordy_old=pieces[piece_select].coordy;
								board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].piece=pieces[piece_select];
								board[pieces[piece_select].xOnBoard][pieces[piece_select].yOnBoard].occupied=true;
								if(pieces[piece_select].xOnBoard_old!=-1)
								{
									board[pieces[piece_select].xOnBoard_old][pieces[piece_select].yOnBoard_old].piece=null;
									board[pieces[piece_select].xOnBoard_old][pieces[piece_select].yOnBoard_old].occupied=false;
									
									
								}
								pieces[piece_select].xOnBoard_old=pieces[piece_select].xOnBoard;
								pieces[piece_select].yOnBoard_old=pieces[piece_select].yOnBoard;
								
								players[turn].nMoves++;
								System.out.println("Jogada valida turn: "+turn);
								turn=change_turn(turn);
								
								if(check_mills())
								{
									System.out.println("mill remover cor: "+turn);
									remove_piece=true;
								}
								
							}else{
								
								pieces[piece_select].coordx=pieces[piece_select].coordx_old;
								pieces[piece_select].coordy=pieces[piece_select].coordy_old;
								pieces[piece_select].image.setBounds(pieces[piece_select].coordx, 
										pieces[piece_select].coordy, pieces[piece_select].mySize, 
										pieces[piece_select].mySize);
							}
							
						}
						piece_onBoard=false;
						piece_select=-1;
					}else if(evt.getID() == MouseEvent.MOUSE_DRAGGED && remove_piece==false)
					{
						int mouseX=evt.getX()-10;
						int mouseY=evt.getY()-30;
						
						
						
						//System.out.println("mouseX: "+mouseX+", mouseY: "+mouseY);
						
						if(piece_select == -1){
							for(int i=0;i<pieces.length;i++)
							{
								if(pieces[i].coordx <= mouseX && (pieces[i].coordx+pieces[i].mySize) >= mouseX &&
									pieces[i].coordy <= mouseY && (pieces[i].coordy+pieces[i].mySize) >= mouseY &&
									pieces[i].color == players[turn].color)
								{
									piece_select=i;
									pieces[i].deltx=mouseX-pieces[i].coordx;
									pieces[i].delty=mouseY-pieces[i].coordy;
									//System.out.println("peca: "+i);
									break;
								}
								
							}
						}else{
							
							int[] pos_oux=piece_pos_board(mouseX,mouseY,pieces[piece_select].mySize);
							if(pos_oux[0]==-1)
							{
								pieces[piece_select].coordx=mouseX-pieces[piece_select].deltx;
								pieces[piece_select].coordy=mouseY-pieces[piece_select].delty;
								piece_onBoard=false;
							}else{
								pieces[piece_select].coordx=board[pos_oux[0]][pos_oux[1]].pixelx;
								pieces[piece_select].coordy=board[pos_oux[0]][pos_oux[1]].pixely;
								pieces[piece_select].xOnBoard=pos_oux[0];
								pieces[piece_select].yOnBoard=pos_oux[1];
								
								piece_onBoard=true;
							}
													
							pieces[piece_select].image.setBounds(pieces[piece_select].coordx, 
									pieces[piece_select].coordy, pieces[piece_select].mySize, 
									pieces[piece_select].mySize);
							
							/*
							System.out.println("pecaX: "+pieces[piece_select].coordx+
											", pecaY: "+pieces[piece_select].coordy);
							*/
						}
						
					}

					window.repaint();
				}
			}

			
		}, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		
	}
	
	
	public int[] piece_pos_board(int mouseX, int mouseY, int mySize) {
		int[] ret=new int[2];
		ret[0]=-1;
		ret[1]=-1;
		
		for(int i=0;i<7;i++)
		{
			for(int e=0;e<7;e++)
			{
				if(board[i][e]!=null)
				{
					if(board[i][e].pixelx <= mouseX && (board[i][e].pixelx+mySize)>=mouseX &&
							board[i][e].pixely<=mouseY && (board[i][e].pixely+mySize)>=mouseY)
					{
						ret[0]=i;
						ret[1]=e;
						break;
					}
				}
			}
			
		}
		
		
		return ret;
	}


	public void init_slots()
	{
		
		board= new Slot[7][7];
		board[0][0]=new Slot();
		board[0][0].occupied=false;
		board[0][0].x=0;
		board[0][0].y=0;
		board[0][0].pixelx=30;
		board[0][0].pixely=20;
		
		board[0][3]=new Slot();
		board[0][3].occupied=false;
		board[0][3].x=0;
		board[0][3].y=3;
		board[0][3].pixelx=30;
		board[0][3].pixely=222;
		
		board[0][6]=new Slot();
		board[0][6].occupied=false;
		board[0][6].x=0;
		board[0][6].y=6;
		board[0][6].pixelx=30;
		board[0][6].pixely=428;
		
		board[1][1]=new Slot();
		board[1][1].occupied=false;
		board[1][1].x=1;
		board[1][1].y=1;
		board[1][1].pixelx=102;
		board[1][1].pixely=85;
		
		board[1][3]=new Slot();
		board[1][3].occupied=false;
		board[1][3].x=1;
		board[1][3].y=3;
		board[1][3].pixelx=102;
		board[1][3].pixely=222;
		
		board[1][5]=new Slot();
		board[1][5].occupied=false;
		board[1][5].x=1;
		board[1][5].y=5;
		board[1][5].pixelx=102;
		board[1][5].pixely=360;
		
		board[2][2]=new Slot();
		board[2][2].occupied=false;
		board[2][2].x=2;
		board[2][2].y=2;
		board[2][2].pixelx=174;
		board[2][2].pixely=154;
		
		board[2][3]=new Slot();
		board[2][3].occupied=false;
		board[2][3].x=2;
		board[2][3].y=3;
		board[2][3].pixelx=174;
		board[2][3].pixely=222;
		
		board[2][4]=new Slot();
		board[2][4].occupied=false;
		board[2][4].x=2;
		board[2][4].y=4;
		board[2][4].pixelx=174;
		board[2][4].pixely=290;
		
		board[3][0]=new Slot();
		board[3][0].occupied=false;
		board[3][0].x=3;
		board[3][0].y=0;
		board[3][0].pixelx=245;
		board[3][0].pixely=20;
		
		board[3][1]=new Slot();
		board[3][1].occupied=false;
		board[3][1].x=3;
		board[3][1].y=1;
		board[3][1].pixelx=245;
		board[3][1].pixely=85;
		
		board[3][2]=new Slot();
		board[3][2].occupied=false;
		board[3][2].x=3;
		board[3][2].y=2;
		board[3][2].pixelx=245;
		board[3][2].pixely=154;
		
		board[3][4]=new Slot();
		board[3][4].occupied=false;
		board[3][4].x=3;
		board[3][4].y=4;
		board[3][4].pixelx=245;
		board[3][4].pixely=290;
		
		board[3][5]=new Slot();
		board[3][5].occupied=false;
		board[3][5].x=3;
		board[3][5].y=5;
		board[3][5].pixelx=245;
		board[3][5].pixely=360;
		
		board[3][6]=new Slot();
		board[3][6].occupied=false;
		board[3][6].x=3;
		board[3][6].y=6;
		board[3][6].pixelx=245;
		board[3][6].pixely=428;
		
		board[4][2]=new Slot();
		board[4][2].occupied=false;
		board[4][2].x=4;
		board[4][2].y=2;
		board[4][2].pixelx=315;
		board[4][2].pixely=154;
		
		board[4][3]=new Slot();
		board[4][3].occupied=false;
		board[4][3].x=4;
		board[4][3].y=3;
		board[4][3].pixelx=315;
		board[4][3].pixely=222;
		
		board[4][4]=new Slot();
		board[4][4].occupied=false;
		board[4][4].x=4;
		board[4][4].y=4;
		board[4][4].pixelx=315;
		board[4][4].pixely=290;
		
		board[5][1]=new Slot();
		board[5][1].occupied=false;
		board[5][1].x=5;
		board[5][1].y=1;
		board[5][1].pixelx=386;
		board[5][1].pixely=85;
		
		board[5][3]=new Slot();
		board[5][3].occupied=false;
		board[5][3].x=5;
		board[5][3].y=3;
		board[5][3].pixelx=386;
		board[5][3].pixely=222;
		
		board[5][5]=new Slot();
		board[5][5].occupied=false;
		board[5][5].x=5;
		board[5][5].y=5;
		board[5][5].pixelx=386;
		board[5][5].pixely=360;
		
		board[6][0]=new Slot();
		board[6][0].occupied=false;
		board[6][0].x=6;
		board[6][0].y=0;
		board[6][0].pixelx=456;
		board[6][0].pixely=20;
		
		board[6][3]=new Slot();
		board[6][3].occupied=false;
		board[6][3].x=6;
		board[6][3].y=3;
		board[6][3].pixelx=456;
		board[6][3].pixely=222;
		
		board[6][6]=new Slot();
		board[6][6].occupied=false;
		board[6][6].x=6;
		board[6][6].y=6;
		board[6][6].pixelx=456;
		board[6][6].pixely=428;
		
		
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
