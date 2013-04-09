import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Board 
{

	public URL url1 = ClassLoader.getSystemClassLoader().getResource("images/fundo2.png");
	public ImageIcon icontabuleiro = new ImageIcon(url1);	
	static URL url11 = ClassLoader.getSystemClassLoader().getResource("images/pecabranca.png");
	static URL url12 = ClassLoader.getSystemClassLoader().getResource("images/pecapreta.png");	
	
	public JFrame window = new JFrame();
	public JLabel background;	
	
	public Slot[][] board;//board[x][y].... x-> \\  y (para baixo)  ...(ou board[linha][coluna])
	public Player[] players;
	public int turn;
	
	public Piece[] pieces;
	
	public boolean remove_piece;	
	public boolean piece_onBoard=false;
	public Piece selectedPiece=null;
		
	public Board()
	{
		init_slots();
		init_players();
		init_pieces();		
		
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
		
				
		window.repaint();
		window.pack();
		window.setVisible(true);
		
		runGame();
	}
	
	public void runGame()
	{
		Player winner;
		
		players[turn].play();
		while(true)
		{
			if(players[turn].finishedPlay)
			{
				winner=gameOver();
				if(winner!=null)
				{
					System.out.println("O jogador das peças "+winner.color+" ganhou o jogo.");
					break;
				}
				
				players[turn].finishedPlay=false;
				turn=change_turn(turn);
				players[turn].play();
				
				
			}
		}
	}
	
	/**
	 * Método que permite saber se o jogo acabou.
	 * @return Null se o jogo ainda não acabou ou o jogador vencedor.
	 */
	public Player gameOver()
	{
		
		if(players[0].nPieces<3)
			return players[1];
		if(players[1].nPieces<3)
			return players[0];
		return null;
	}
	
	public int change_turn(int t)
	{
		
		return t*(-1)+1;
	}
	
	public void init_players() 
	{		
		turn=(int) (Math.random()*2);
		remove_piece=false;
		
		players=new Player[2];
		players[0]=new Human("Jogador 0",0,9,'p',this);
		players[1]=new Human("Jogador 1",0,9,'b',this);
	}

	private void init_pieces() 
	{
		
		pieces=new Piece[18];
		for(int i=0;i<18;i++)
		{						
			if(i<9)
				pieces[i]=new Piece(500,50,players[0],new JLabel(new ImageIcon(url12)));	
			else
				pieces[i]=new Piece(500,327,players[1],new JLabel(new ImageIcon(url11)));
		}		
	}

	public boolean valid_position()
	{
		if(selectedPiece==null)
		{
			return false;
		}
		
		if(selectedPiece.OnBoard==null)
		{
			return false;
		}
		
		if(selectedPiece.OnBoard.occupied())
		{
			return false;
		}
		
		if(players[turn].nMoves<9)
		{
			//"stage 1"
			
			if(selectedPiece.OnBoardOld!=null)
				return false;
		}
		else if(players[turn].nPieces>3)
		{
			System.out.println("AQUI");
			//"stage 2"
			boolean found=false;
			Slot[] adjacents = selectedPiece.OnBoard.adjacents;
			
			for(int i=0;i<adjacents.length;i++)
			{
				if(adjacents[i].line==selectedPiece.OnBoardOld.line && adjacents[i].column==selectedPiece.OnBoardOld.column) 
					found=true;
			}
			
			if(found==false)
				return false;
		}
		else
		{
			//"stage 3"
			
			
		}
		//falta validar "stage" de jogo: 3-voar peças
		return true;
	}
	
	public boolean check_mills()
	{
		Player owner=selectedPiece.owner;
		Slot[] oux_mill1=selectedPiece.OnBoard.verticalMill;
		Slot[] oux_mill2=selectedPiece.OnBoard.horizontalMill;
		
		if(oux_mill1[0].occupied() && oux_mill1[1].occupied())
		{
			if(oux_mill1[0].occupyingPiece.owner==owner && oux_mill1[1].occupyingPiece.owner==owner)
			{
				return true;
			}
		}
		
		if(oux_mill2[0].occupied() && oux_mill2[1].occupied())
		{
			if(oux_mill2[0].occupyingPiece.owner==owner && oux_mill2[1].occupyingPiece.owner==owner)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean valid_remove()
	{
		if(players[turn]==selectedPiece.owner && selectedPiece.OnBoard.occupied())
		{
			//falta dar prioridade a peças que n formem mills.
			return true;
		}
		
		return false;
	}
	
	/**
	 * Método que processa as jogadas dos jogadores humanos. Deve ser invocado pelo método play dos jogadores humanos quando estes detetam interacção do jogador.
	 * @param evt MouseEvent que disparou a jogada.
	 * @return True se evento resulta numa jogada e o turno deve ser passado ao próximo jogador.
	 */
	public boolean processPlayAttempt(MouseEvent evt)
	{
		boolean madePlay=false;
		
		if(evt.getID() == MouseEvent.MOUSE_CLICKED ){
			
			if(remove_piece)
			{
				int mouseX=evt.getX()-10;
				int mouseY=evt.getY()-30;
				for(int i=0;i<pieces.length;i++)
				{
					if(pieces[i].coord.x <= mouseX && (pieces[i].coord.x+pieces[i].mySize) >= mouseX &&
						pieces[i].coord.y <= mouseY && (pieces[i].coord.y+pieces[i].mySize) >= mouseY &&
						pieces[i].owner == players[turn])
					{
						selectedPiece=pieces[i];
						
						//System.out.println("peca: "+i);
						break;
					}
					
				}
				if(selectedPiece!=null)
				if(valid_remove())
				{
					
					players[turn].nPieces--;
					System.out.println("peça removida turn: "+turn);
					if(players[turn].nPieces<3)
						madePlay=true;
					selectedPiece.image.setBounds(1000, 1000, 1, 1);
					selectedPiece.OnBoard.occupyingPiece=null;
					selectedPiece.OnBoard=null;
					selectedPiece.OnBoardOld=null;
					selectedPiece.coord.setLocation(1000, 1000);
					selectedPiece.coord_old.setLocation(1000, 1000);
					
					remove_piece=false;
				}
				selectedPiece=null;
			}
			
			//System.out.println("Click");
		}else if(evt.getID() == MouseEvent.MOUSE_RELEASED && remove_piece==false)
		{
			if(selectedPiece!=null)
			{
				if(piece_onBoard && valid_position())
				{
					
					selectedPiece.coord_old.setLocation(selectedPiece.coord);
					selectedPiece.OnBoard.occupyingPiece=selectedPiece;
					if(selectedPiece.OnBoardOld!=null)
					{
						selectedPiece.OnBoardOld.occupyingPiece=null;
						
						
					}
					selectedPiece.OnBoardOld=selectedPiece.OnBoard;
					
					players[turn].nMoves++;
					System.out.println("Jogada valida turn: "+turn);
					madePlay=true;
					
					if(check_mills())
					{
						System.out.println("mill remover cor: "+turn);
						remove_piece=true;
					}else
					{
						//madePlay=true;
					}
					
				}else{
					selectedPiece.coord.setLocation(selectedPiece.coord_old);
					selectedPiece.image.setBounds(selectedPiece.coord.x, 
							selectedPiece.coord.y, selectedPiece.mySize, 
							selectedPiece.mySize);
				}
				
			}
			piece_onBoard=false;
			selectedPiece=null;
		}else if(evt.getID() == MouseEvent.MOUSE_DRAGGED && remove_piece==false)
		{
			int mouseX=evt.getX()-10;
			int mouseY=evt.getY()-30;
			
			
			
			//System.out.println("mouseX: "+mouseX+", mouseY: "+mouseY);
			
			if(selectedPiece == null){
				for(int i=0;i<pieces.length;i++)
				{
					if(pieces[i].coord.x <= mouseX && (pieces[i].coord.x+pieces[i].mySize) >= mouseX &&
						pieces[i].coord.y <= mouseY && (pieces[i].coord.y+pieces[i].mySize) >= mouseY &&
						pieces[i].owner == players[turn])
					{
						selectedPiece=pieces[i];
						pieces[i].delt.setLocation(mouseX-pieces[i].coord.x,mouseY-pieces[i].coord.y);
						//System.out.println("peca: "+i);
						break;
					}
					
				}
			}else{
				
				Slot pos_oux=getSlotAtPos(mouseX,mouseY,selectedPiece.mySize);
				if(pos_oux==null)
				{
					selectedPiece.coord.setLocation(mouseX-selectedPiece.delt.x,mouseY-selectedPiece.delt.y);
					piece_onBoard=false;
				}else{
					selectedPiece.coord.setLocation(pos_oux.pixelX,pos_oux.pixelY);
					selectedPiece.OnBoard=pos_oux;
					
					piece_onBoard=true;
				}
										
				selectedPiece.image.setBounds(selectedPiece.coord.x, 
						selectedPiece.coord.y, selectedPiece.mySize, 
						selectedPiece.mySize);
				
				/*
				System.out.println("pecaX: "+pieces[piece_select].coordx+
								", pecaY: "+pieces[piece_select].coordy);
				*/
			}
			
		}

		window.repaint();
		
		return madePlay;
	}
	
	public Slot getSlotAtPos(int mouseX, int mouseY, int mySize) 
	{
		Slot ret=null;
		
		for(int i=0;i<7;i++)
		{
			for(int e=0;e<7;e++)
			{
				if(board[i][e]!=null)
				{
					if(board[i][e].pixelX <= mouseX && (board[i][e].pixelX+mySize)>=mouseX &&
							board[i][e].pixelY<=mouseY && (board[i][e].pixelY+mySize)>=mouseY)
					{
						ret=board[i][e];
						break;
					}
				}
			}			
		}		
		
		return ret;
	}


	public void init_slots()
	{		
		board = new Slot[7][7];
		
		//Construir slots
		board[0][0]=new Slot(0,0,30,20);		
		board[0][3]=new Slot(0,3,30,222);			
		board[0][6]=new Slot(0,6,30,428);		
		
		board[1][1]=new Slot(1,1,102,85);	
		board[1][3]=new Slot(1,3,102,222);		
		board[1][5]=new Slot(1,5,102,360);
		
		board[2][2]=new Slot(2,2,174,154);		
		board[2][3]=new Slot(2,3,174,222);		
		board[2][4]=new Slot(2,4,174,290);
		
		board[3][0]=new Slot(3,0,245,20);		
		board[3][1]=new Slot(3,1,245,85);		
		board[3][2]=new Slot(3,2,245,154);	
		board[3][4]=new Slot(3,4,245,290);	
		board[3][5]=new Slot(3,5,245,360);	
		board[3][6]=new Slot(3,6,245,428);
		
		board[4][2]=new Slot(4,2,315,154);
		board[4][3]=new Slot(4,3,315,222);	
		board[4][4]=new Slot(4,4,315,290);
		
		board[5][1]=new Slot(5,1,386,85);	
		board[5][3]=new Slot(5,3,386,222);		
		board[5][5]=new Slot(5,5,386,360);
		
		board[6][0]=new Slot(6,0,456,20);	
		board[6][3]=new Slot(6,3,456,222);	
		board[6][6]=new Slot(6,6,456,428);
		
		//Definir adjacências
		board[0][0].setAdjacents(board[0][3], board[3][0], null, null);
		board[0][3].setAdjacents(board[0][0], board[0][6], board[1][3], null);
		board[0][6].setAdjacents(board[0][3], board[3][6], null, null);
		
		board[1][1].setAdjacents(board[3][1], board[1][3], null, null);
		board[1][3].setAdjacents(board[0][3], board[2][3], board[1][1], board[1][5]);
		board[1][5].setAdjacents(board[1][3], board[3][5], null, null);
		
		board[2][2].setAdjacents(board[3][2], board[2][3], null, null);
		board[2][3].setAdjacents(board[1][3], board[2][2], board[2][4], null);
		board[2][4].setAdjacents(board[2][3], board[3][4], null, null);
		
		board[3][0].setAdjacents(board[0][0], board[6][0], board[3][1], null);	
		board[3][1].setAdjacents(board[1][1], board[5][1], board[3][2], board[3][0]);
		board[3][2].setAdjacents(board[2][2], board[4][2], board[3][1], null);
		board[3][4].setAdjacents(board[2][4], board[4][4], board[3][5], null);
		board[3][5].setAdjacents(board[1][5], board[5][5], board[3][4], board[3][6]);
		board[3][6].setAdjacents(board[0][6], board[6][6], board[3][5], null);
		
		board[4][2].setAdjacents(board[3][2], board[4][3], null, null);	
		board[4][3].setAdjacents(board[4][2], board[4][4], board[5][3], null);
		board[4][4].setAdjacents(board[4][3], board[3][4], null, null);
		
		board[5][1].setAdjacents(board[3][1], board[5][3], null, null);
		board[5][3].setAdjacents(board[4][3], board[6][3], board[5][1], board[5][5]);
		board[5][5].setAdjacents(board[5][3], board[3][5], null, null);
		
		board[6][0].setAdjacents(board[3][0], board[6][3], null, null);		
		board[6][3].setAdjacents(board[6][0], board[6][6], board[5][3], null);	
		board[6][6].setAdjacents(board[6][3], board[3][6], null, null);

		//Gerar mills automaticamente
		for(int i=0;i<7;i++)
			for(int j=0;j<7;j++)
				if(board[i][j]!=null)
					board[i][j].generateMills();
	}
	
	public void run()
	{
		
	}
	
	public void end()
	{
		
	}
	
}
