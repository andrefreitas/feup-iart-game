package graphical;


import java.awt.AWTEvent;
import java.awt.Container;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

import logic.Game;
import logic.Move;
import logic.Piece;

/**
 * Classe que implementa os gráficos do jogo e a interação com o utilizador, invocando os métodos da lógica apropriados.
 */
public class GameWindow extends JFrame
{
	//TODO: Acabar (grande) refactoring e documentação dos métodos
	
	private static final long serialVersionUID = 6675690679297219233L;

	public static JFrame self;

	//Recursos gráficos
	private JLabel background = new JLabel(new ImageIcon(
			ClassLoader.getSystemClassLoader().getResource("images/fundo_jogo.png")));
	private URL urlWhite = ClassLoader.getSystemClassLoader().getResource("images/pecabranca.png");
	private URL urlBlack = ClassLoader.getSystemClassLoader().getResource("images/pecapreta.png");	
	public JLabel blackPiece = new JLabel(new ImageIcon(urlBlack));
	public JLabel whitePiece = new JLabel(new ImageIcon(urlWhite));
	private JLabel blackPieceOUT = new JLabel(new ImageIcon(urlBlack));
	private JLabel whitePieceOUT = new JLabel(new ImageIcon(urlWhite));	
	
	private HashMap<String,PieceImage> visualBoard=new HashMap<String,PieceImage>(); //Mapa de imagens de peças atribuídas às slots do tabuleiro (trata-se do tabuleiro como é representado visualmente e não logicamente) TODO: esta separação é desnecessária, tratar disto no refactoring

	
	private JLabel selectedPiece=null; //Peça selecionada/arrastada pelo jogador
	private PieceImage initPiece=null; // TODO: ???
	private String finalPiece=null; // TODO: ???
	private Boolean failDrag=false; //Se verdadeiro, jogador não arrastou corretamente a peça para nenhuma slot
	private Boolean waitRemove=false; //Se verdadeiro, jogador formou uma mill mas ainda não removeu a peça do adversário

	private Vector<Move> possibleMoves=Game.board.getPossibleMoves(Game.board.turn); //Vector de jogadas possíveis para o jogador atual, só é gerado uma vez para cada turno
	private Boolean gameRunning=true;

	private JLabel turnLabel; //Label com o texto que identifica o turno do jogador

	private AWTEventListener mouseListener; //Objeto que escuta as ações do utilizador

	public GameWindow()
	{
		super();
		visualBoard.clear();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) 
			{	
				self.removeWindowListener(this);
				Toolkit.getDefaultToolkit().removeAWTEventListener(mouseListener);
				MainMenu.self.setVisible(true);
				self.removeAll();
				self.dispose();				
			}
		});
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 100, 800, 525);
		this.setResizable(false);
		this.setContentPane(initBoard());

		mouseListener = new AWTEventListener() {
			public void eventDispatched(AWTEvent event) {
				if(event instanceof MouseEvent && gameRunning)
				{
					if(event.getID() == MouseEvent.MOUSE_CLICKED && waitRemove)
					{
						int mouseX=((MouseEvent) event).getX()-10;
						int mouseY=((MouseEvent) event).getY()-30;
						String strPiece=getMouseToPiece(mouseX, mouseY);

						if(!strPiece.startsWith("out") && !strPiece.startsWith("black") && !strPiece.startsWith("white"))
						{
							makeRemove(strPiece);
							if(!waitRemove)
							{
								possibleMoves=Game.board.getPossibleMoves(Game.board.turn);
								if(possibleMoves==null || possibleMoves.size()==0 || Game.board.gameOver()!='X')
								{
									String v="";
									if(Game.board.turn=='B')
										v="whites";
									else
										v="blacks";
									//System.out.println("Vitoria: "+v);
									turnLabel.setText("Victory for the "+v);
									gameRunning=false;
								}
								else
									checkBotAndPlay();
							}
						}
					}
					else if(event.getID() == MouseEvent.MOUSE_RELEASED && waitRemove==false)
					{
						int mouseX=((MouseEvent) event).getX()-10;
						int mouseY=((MouseEvent) event).getY()-30;
						String strPiece=getMouseToPiece(mouseX, mouseY);
						failDrag=false;
						if(selectedPiece!=null)
						{
							selectedPiece.setVisible(false);
							selectedPiece=null;

							//System.out.println("released: "+strPiece);

							if(strPiece.startsWith("out") || strPiece.startsWith("black") || strPiece.startsWith("white") ||!validRelease(strPiece))
							{
								if(initPiece!=null)
								{								
									initPiece.showLast();
									initPiece=null;
								}
							}
							else 
							{
								boolean remove=makeMove(strPiece);
								if(remove)
								{
									finalPiece=strPiece;
									waitRemove=true;
									//System.out.println("Wait Remove");
								}
								else
								{
									initPiece=null;
									possibleMoves=Game.board.getPossibleMoves(Game.board.turn);
									if(possibleMoves==null || possibleMoves.size()==0 || Game.board.gameOver()!='X')
									{
										String v="";
										if(Game.board.turn=='B')
											v="whites";
										else
											v="blacks";
										//System.out.println("Vitoria: "+v);
										turnLabel.setText("Victory for the "+v);
										gameRunning=false;
									}
									else
										checkBotAndPlay();
								}
							}
						}
					}
					else if(event.getID() == MouseEvent.MOUSE_DRAGGED && failDrag==false && waitRemove==false)
					{
						int mouseX=((MouseEvent) event).getX()-10;
						int mouseY=((MouseEvent) event).getY()-30;
						String strPiece=getMouseToPiece(mouseX, mouseY);

						if(selectedPiece==null)
						{
							if(strPiece.startsWith("out"))
							{
								failDrag=true;
								selectedPiece=null;
							}
							else if(strPiece.startsWith("black") && getStage('B')==0)
								selectedPiece=blackPiece;
							else if(strPiece.startsWith("white") && getStage('W')==0)
								selectedPiece=whitePiece;
							else if(validPieceToPick(strPiece))
							{
								initPiece=visualBoard.get(strPiece);
								char v=initPiece.getVisible();
								if(v=='X')
								{
									//System.out.println("Erro estranho");
									failDrag=true;
									selectedPiece=null;
								}
								else if(v=='B')
								{
									initPiece.hide();
									selectedPiece=blackPiece;
								}
								else if(v=='W')
								{
									initPiece.hide();
									selectedPiece=whitePiece;
								}

							}
							else
								failDrag=true;
						}
						if(selectedPiece!=null)
						{
							int nextX=mouseX-20;
							int nextY=mouseY-20;
							if(!strPiece.startsWith("out") && !strPiece.startsWith("black") && 
									!strPiece.startsWith("white"))
							{
								int x=Character.getNumericValue(strPiece.charAt(0));
								int y=Character.getNumericValue(strPiece.charAt(2));
								nextX=30+x*71;
								nextY=20+y*68;
							}
							selectedPiece.setBounds(nextX, nextY, 47, 47);
							selectedPiece.setVisible(true);
						}
					}
				}
			}
		};
		Toolkit.getDefaultToolkit().addAWTEventListener(mouseListener, 
				AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);

		this.setVisible(true);
		this.repaint();
		self=this;
		checkBotAndPlay();
	}

	private void checkBotAndPlay() 
	{
		char turn = Game.board.turn;
		if(Game.gameType==1 && turn==Game.botColor || Game.gameType==2)
		{
			(new Thread(){
				@Override
				public void run() 
				{
					while(true)
					{
						gameRunning=false;
						//System.out.println("Bot tem "+possibleMoves.size()+" jogadas.");
						Game.playBot(possibleMoves);
						possibleMoves=Game.board.getPossibleMoves(Game.board.turn);
						if(possibleMoves==null || possibleMoves.size()==0 || Game.board.gameOver()!='X')
						{
							String v="";
							if(Game.board.turn=='B')
								v="whites";
							else
								v="blacks";
							//System.out.println("Vitoria: "+v);
							turnLabel.setText("Victory for the "+v);
							gameRunning=false;
							break;
						}
						else
						{
							gameRunning=true;
							if(Game.board.turn=='B')
								turnLabel.setText("Turn: Black");
							else
								turnLabel.setText("Turn: White");
						}
						if(Game.gameType!=2)
							break;
					}
				}
			}).start();
		}
	}

	public void botPlay(Move m)
	{
		botMovePlay(m);
		if(m.value=='B')
		{
			if(Game.board.blackStage!=0)
				blackPieceOUT.setVisible(false);
			visualBoard.get(m.finalPos[0]+"-"+m.finalPos[1]).showBlack();
		}
		else
		{
			if(Game.board.whiteStage!=0)
				whitePieceOUT.setVisible(false);
			visualBoard.get(m.finalPos[0]+"-"+m.finalPos[1]).showWhite();
		}
		if(m.stage!=0)
			visualBoard.get(m.initPos[0]+"-"+m.initPos[1]).hide();
		if(m.removedPiece!=null)
			visualBoard.get(m.removedPiece.keyPos).hide();
	}
	public void botMovePlay(Move m)
	{
		final Move mfinal=m;
		Thread a = new Thread(new Runnable(){

			@Override
			public void run() {
				JLabel piece=null;
				if(mfinal.value=='B')
				{
					piece=blackPiece;
				}else if(mfinal.value=='W')
				{
					piece=whitePiece;
				}
				if(mfinal.stage!=0)
				{
					double x=mfinal.initPos[0]*71+30;
					double y=mfinal.initPos[1]*68+20;
					
					double xf=mfinal.finalPos[0]*71+30;
					double yf=mfinal.finalPos[1]*68+20;
					
					double deltx=xf-x;
					double delty=yf-y;
					deltx=deltx/(double)100;
					delty=delty/(double)100;
					
					piece.setBounds((int) x,(int) y, 47, 47);
					piece.setVisible(true);
					visualBoard.get(mfinal.initPos[0]+"-"+mfinal.initPos[1]).hide();
					while(true)
					{
						//System.out.println("x:"+x+" y:"+y+" deltx:"+deltx+" delty:"+delty+" xf:"+xf+" yf:"+yf);
						double difx=xf-x;
						double dify=yf-y;
						if(difx<1 && difx>-1)
							x=xf;
						else
							x+=deltx;
						
						if(dify<1 && dify>-1)
							y=yf;
						else
							y+=delty;
						piece.setBounds((int)x,(int) y, 47, 47);
						
						if(x==xf && y==yf)
						{
							break;
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					piece.setVisible(false);
				}else{
					double x=600;
					double y=50;
					if(mfinal.value=='W')
					{
						y=327;
						
					}else
					{
						
					}
					double xf=mfinal.finalPos[0]*71+30;
					double yf=mfinal.finalPos[1]*68+20;
					
					double deltx=xf-x;
					double delty=yf-y;
					deltx=deltx/(double)100;
					delty=delty/(double)100;
					
					piece.setBounds((int) x,(int) y, 47, 47);
					piece.setVisible(true);
					/*
					 * this.blackPieceOUT.setBounds(600, 50, 47, 47);
						this.whitePieceOUT.setBounds(600, 327, 47, 47);
					 * */
					//visualBoard.get(mfinal.initPos[0]+"-"+mfinal.initPos[1]).hide();
					while(true)
					{
						//System.out.println("x:"+x+" y:"+y+" deltx:"+deltx+" delty:"+delty+" xf:"+xf+" yf:"+yf);
						double difx=xf-x;
						double dify=yf-y;
						if(difx<1 && difx>-1)
							x=xf;
						else
							x+=deltx;
						
						if(dify<1 && dify>-1)
							y=yf;
						else
							y+=delty;
						piece.setBounds((int)x,(int) y, 47, 47);
						
						if(x==xf && y==yf)
						{
							break;
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					piece.setVisible(false);
				}
				
			}
			
		});
		a.start();
		try {
			a.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void makeRemove(String strPiece) 
	{
		//System.out.println("Trying to remove: "+strPiece);

		for(Move m: possibleMoves)
		{
			if(initPiece==null)
			{
				if(m.stage==0 && finalPiece.startsWith(m.finalPos[0]+"-"+m.finalPos[1]) &&
						m.removedPiece!=null && m.removedPiece.keyPos.startsWith(strPiece))
				{

					//m.showMove();
					Game.board.makeMove(m);
					if(Game.board.turn=='B')
						turnLabel.setText("Turn: Black");
					else
						turnLabel.setText("Turn: White");
					//Game.board.getMatrix();

					//System.out.println("Turn: "+Game.board.turn);
					this.waitRemove=false;
					if(m.value=='B')
					{
						if(Game.board.blackStage!=0)
							blackPieceOUT.setVisible(false);
						visualBoard.get(strPiece).hide();
					}
					else
					{
						if(Game.board.whiteStage!=0)
							whitePieceOUT.setVisible(false);
						visualBoard.get(strPiece).hide();
					}

					break;
				}
			}
			else if(m.stage!=0 && initPiece.pos.startsWith(m.initPos[0]+"-"+m.initPos[1]) && 
					finalPiece.startsWith(m.finalPos[0]+"-"+m.finalPos[1]) &&
					m.removedPiece!=null && m.removedPiece.keyPos.startsWith(strPiece))
			{
				//m.showMove();
				Game.board.makeMove(m);
				if(Game.board.turn=='B')
					turnLabel.setText("Turn: Black");
				else
					turnLabel.setText("Turn: White");
				//Game.board.getMatrix();
				//System.out.println("Turn: "+Game.board.turn);
				this.waitRemove=false;
				if(m.value=='B')
				{
					if(Game.board.blackStage!=0)
						blackPieceOUT.setVisible(false);

					visualBoard.get(strPiece).hide();
					//board.get(finalPiece).showBlack();
				}
				else
				{
					if(Game.board.whiteStage!=0)
						whitePieceOUT.setVisible(false);

					visualBoard.get(strPiece).hide();
					//board.get(finalPiece).showWhite();
				}

				break;
			}
		}
	}

	private Boolean makeMove(String strPiece) 
	{
		Boolean ret=false;
		int i=0;
		for(Move m: possibleMoves)
		{
			if(initPiece==null)
			{
				if(m.stage==0 && strPiece.startsWith(m.finalPos[0]+"-"+m.finalPos[1]))
				{
					if(m.removedPiece==null)
					{
						//m.showMove();
						Game.board.makeMove(m);
						if(Game.board.turn=='B')
							turnLabel.setText("Turn: Black");
						else
							turnLabel.setText("Turn: White");
						//Game.board.getMatrix();
						//System.out.println("Turn: "+Game.board.turn);
					}
					else if(m.removedPiece!=null)
						ret=true;

					if(m.value=='B')
					{
						if(Game.board.blackStage!=0)
							blackPieceOUT.setVisible(false);
						visualBoard.get(strPiece).showBlack();
					}
					else
					{
						if(Game.board.whiteStage!=0)
							whitePieceOUT.setVisible(false);
						visualBoard.get(strPiece).showWhite();
					}
					break;
				}
			}
			else if(m.stage!=0 && initPiece.pos.startsWith(m.initPos[0]+"-"+m.initPos[1]) && 
					strPiece.startsWith(m.finalPos[0]+"-"+m.finalPos[1]))
			{
				if(m.removedPiece==null)
				{
					//m.showMove();
					Game.board.makeMove(m);
					if(Game.board.turn=='B')
						turnLabel.setText("Turn: Black");
					else
						turnLabel.setText("Turn: White");
					//Game.board.getMatrix();
					//System.out.println("Turn: "+Game.board.turn);

				}
				else if(m.removedPiece!=null)
					ret=true;

				if(m.value=='B')
				{
					if(Game.board.blackStage!=0)
						blackPieceOUT.setVisible(false);
					visualBoard.get(strPiece).showBlack();
				}
				else
				{
					if(Game.board.whiteStage!=0)
						whitePieceOUT.setVisible(false);
					visualBoard.get(strPiece).showWhite();
				}
				break;
			}
			i++;
		}
		if(i>=possibleMoves.size() && initPiece!=null)
		{
			initPiece.showLast();
			initPiece=null;
		}
		return ret;
	}

	private boolean validRelease(String strPiece) 
	{
		char turn = Game.board.turn;
		int stage=-1;

		if(turn=='B')
			stage=Game.board.blackStage;
		else if(turn=='W')
			stage=Game.board.whiteStage;

		Piece p=Game.board.getSlotMap().get(strPiece);
		if(stage==0 && initPiece==null && p==null)
			return true;
		if(stage!=0 && initPiece!=null && p==null)
			return true;

		return false;
	}

	private int getStage(char c) 
	{
		if(Game.board.turn==c)
		{
			if(c=='B')
				return Game.board.blackStage;
			if(c=='W')
				return Game.board.whiteStage;
		}
		return -1;
	}

	private boolean validPieceToPick(String strPiece) 
	{
		for(Move m : possibleMoves)
		{
			if(m.stage!=0 && strPiece.startsWith(m.initPos[0]+"-"+m.initPos[1]))
				return true;
		}
		return false;
	}

	private Container initBoard() 
	{
		Container ret = new Container();
		visualBoard=new HashMap<String,PieceImage>();
		this.blackPiece.setVisible(false);
		this.whitePiece.setVisible(false);

		turnLabel = new JLabel("Turn: Black");
		turnLabel.setForeground(Color.RED);
		turnLabel.setBounds(615, 472, 169, 14);
		ret.add(turnLabel);
		ret.add(blackPiece);
		ret.add(whitePiece);
		for(String str : Game.board.getSlotMap().keySet())
		{
			JLabel black=new JLabel(new ImageIcon(urlBlack));
			JLabel white=new JLabel(new ImageIcon(urlWhite));
			int x=Character.getNumericValue(str.charAt(0));
			int y=Character.getNumericValue(str.charAt(2));
			x=30+x*71;
			y=20+y*68;
			black.setBounds(x, y, 47, 47);
			white.setBounds(x, y, 47, 47);
			PieceImage pI = new PieceImage(black,white);
			pI.pos=str;
			visualBoard.put(str, pI);
			pI.hide();
			ret.add(white);
			ret.add(black);
		}
		ret.add(blackPieceOUT);
		ret.add(whitePieceOUT);
		this.blackPieceOUT.setBounds(600, 50, 47, 47);
		this.whitePieceOUT.setBounds(600, 327, 47, 47);
		this.blackPieceOUT.setVisible(true);
		this.whitePieceOUT.setVisible(true);
		background.setBounds(0, 0, 800, 500);
		ret.add(background);
		return ret;
	}

	private String getMouseToPiece(int x, int y)
	{
		String ret="out";

		if(x>=600 && x<=647 && y>=50 && y<=97)
			ret="black";
		else if(x>=600 && x<=647 && y>=327 && y<=374)
			ret="white";
		else
		{
			for(String str : Game.board.getSlotMap().keySet())
			{
				int xstr=Character.getNumericValue(str.charAt(0));
				int ystr=Character.getNumericValue(str.charAt(2));
				xstr=30+xstr*71;
				ystr=20+ystr*68;

				if(x>=xstr && x<=xstr+47 && y>=ystr && y<=ystr+47)
				{
					ret=str;
					break;
				}
			}
		}

		return ret;
	}
}