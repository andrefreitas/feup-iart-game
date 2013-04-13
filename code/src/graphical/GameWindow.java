package graphical;

import game.Board;
import game.Move;
import game.Piece;
import init.NineMansMorris;

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

public class GameWindow extends JFrame{

	public static JFrame self;
	
	
		
	private JLabel background = new JLabel(new ImageIcon(
			ClassLoader.getSystemClassLoader().getResource("images/fundo2.png")));
	private URL urlWhite = ClassLoader.getSystemClassLoader().getResource("images/pecabranca.png");
	private URL urlBlack = ClassLoader.getSystemClassLoader().getResource("images/pecapreta.png");	
	
	private HashMap<String,PieceImage> board=new HashMap<String,PieceImage>();
	
	public JLabel blackPiece = new JLabel(new ImageIcon(urlBlack));
	public JLabel whitePiece = new JLabel(new ImageIcon(urlWhite));
	
	private JLabel blackPieceOUT = new JLabel(new ImageIcon(urlBlack));
	private JLabel whitePieceOUT = new JLabel(new ImageIcon(urlWhite));
	
	private JLabel selectedPiece=null;
	private PieceImage initPiece=null;
	private String finalPiece=null;
	private Boolean failDrag=false;
	private Boolean waitRemove=false;
	
	private Vector<Move> possibleMoves=NineMansMorris.board.getPossibleMoves(NineMansMorris.board.turn);
	private Boolean gameRunning=true;
	
	public GameWindow()
	{
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				//self.dispose();
				MainMenu.self.setVisible(true);
			}
		});
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 100, 800, 525);
		this.setResizable(false);
		
		this.setContentPane(initBoard());
		
		
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			public void eventDispatched(AWTEvent event) {
				if(event instanceof MouseEvent && gameRunning){
					if(event.getID() == MouseEvent.MOUSE_CLICKED && waitRemove){
						int mouseX=((MouseEvent) event).getX()-10;
						int mouseY=((MouseEvent) event).getY()-30;
						String strPiece=getMouseToPiece(mouseX, mouseY);
						
						if(strPiece.startsWith("out") || strPiece.startsWith("black") ||
								strPiece.startsWith("white") )
						{
							
						}else
						{
							makeRemove(strPiece);
							if(!waitRemove)
							{
								possibleMoves=NineMansMorris.board.getPossibleMoves(NineMansMorris.board.turn);
								if(possibleMoves==null || possibleMoves.size()==0 || NineMansMorris.board.gameOver()!='X')
								{
									String v="";
									if(NineMansMorris.board.turn=='B')
										v="brancos";
									else
										v="pretos";
									System.out.println("Vitoria: "+v);
									gameRunning=false;
								}
							}
						}
						
						
					}else if(event.getID() == MouseEvent.MOUSE_RELEASED && waitRemove==false){
						
						int mouseX=((MouseEvent) event).getX()-10;
						int mouseY=((MouseEvent) event).getY()-30;
						String strPiece=getMouseToPiece(mouseX, mouseY);
						failDrag=false;
						if(selectedPiece!=null)
						{
							selectedPiece.setVisible(false);
							selectedPiece=null;
							
							if(strPiece.startsWith("out") || strPiece.startsWith("black") ||
									strPiece.startsWith("white") ||!validRelease(strPiece))
							{
								if(initPiece!=null)
								{
								
									initPiece.showLast();
									initPiece=null;
								}
							}else 
							{
								
								Boolean remove=makeMove(strPiece);
								if(remove)
								{
									finalPiece=strPiece;
									waitRemove=true;
									System.out.println("Wait Remove");
								}else
								{
									initPiece=null;
									possibleMoves=NineMansMorris.board.getPossibleMoves(NineMansMorris.board.turn);
									if(possibleMoves==null || possibleMoves.size()==0 || NineMansMorris.board.gameOver()!='X')
									{
										String v="";
										if(NineMansMorris.board.turn=='B')
											v="brancos";
										else
											v="pretos";
										System.out.println("Vitoria: "+v);
										gameRunning=false;
									}
								}
							}
							
							
						}
						
					}else if(event.getID() == MouseEvent.MOUSE_DRAGGED && failDrag==false && waitRemove==false){
						
						
						int mouseX=((MouseEvent) event).getX()-10;
						int mouseY=((MouseEvent) event).getY()-30;
						String strPiece=getMouseToPiece(mouseX, mouseY);
						
						if(selectedPiece==null)
						{
							if(strPiece.startsWith("out"))
							{
								failDrag=true;
								selectedPiece=null;
							}else if(strPiece.startsWith("black") && getStage('B')==0)
							{
								selectedPiece=blackPiece;
								
							}else if(strPiece.startsWith("white") && getStage('W')==0){
								selectedPiece=whitePiece;
							}else if(validPieceToPick(strPiece))
							{
								initPiece=board.get(strPiece);
								char v=initPiece.getVisible();
								if(v=='X')
								{
									System.out.println("Erro estranho");
									failDrag=true;
									selectedPiece=null;
								}else if(v=='B')
								{
									initPiece.hidde();
									selectedPiece=blackPiece;
								}else if(v=='W')
								{
									initPiece.hidde();
									selectedPiece=whitePiece;
								}
								
							}else
							{
								failDrag=true;
							}
						}
						if(selectedPiece!=null)
						{
							selectedPiece.setBounds(mouseX, mouseY, 47, 47);
							selectedPiece.setVisible(true);
							
							
						}
					}
					
				}
			}
		}, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		
		
		
		
		this.setVisible(true);
		this.repaint();
		self=this;
	}

	protected void makeRemove(String strPiece) {
		
		System.out.println("Trying to remove: "+strPiece);
		
		for(Move m: possibleMoves)
		{
			if(initPiece==null)
			{
				if(m.stage==0 && finalPiece.startsWith(m.finalPos[0]+"-"+m.finalPos[1]) &&
						m.removedPiece!=null && m.removedPiece.keyPos.startsWith(strPiece))
				{
				
					m.showMove();
					NineMansMorris.board.makeMove(m);
					NineMansMorris.board.getMatrix();
					
					System.out.println("Turn: "+NineMansMorris.board.turn);
					this.waitRemove=false;
					if(m.value=='B')
					{
						if(NineMansMorris.board.blackStage!=0)
						{
							blackPieceOUT.setVisible(false);
						}
						board.get(strPiece).hidde();
					}else{
						if(NineMansMorris.board.whiteStage!=0)
						{
							whitePieceOUT.setVisible(false);
						}
						board.get(strPiece).hidde();
					}
					
					break;
				}
			}else
			if(m.stage!=0 && initPiece.pos.startsWith(m.initPos[0]+"-"+m.initPos[1]) && 
				finalPiece.startsWith(m.finalPos[0]+"-"+m.finalPos[1]) &&
				m.removedPiece!=null && m.removedPiece.keyPos.startsWith(strPiece))
			{
				m.showMove();
				NineMansMorris.board.makeMove(m);
				NineMansMorris.board.getMatrix();
				System.out.println("Turn: "+NineMansMorris.board.turn);
				this.waitRemove=false;
				if(m.value=='B')
				{
					if(NineMansMorris.board.blackStage!=0)
					{
						blackPieceOUT.setVisible(false);
					}
					board.get(strPiece).hidde();
					//board.get(finalPiece).showBlack();
				}else{
					if(NineMansMorris.board.whiteStage!=0)
					{
						whitePieceOUT.setVisible(false);
					}
					board.get(strPiece).hidde();
					//board.get(finalPiece).showWhite();
				}
				
				break;
			}
			
		}
	}

	protected Boolean makeMove(String strPiece) {
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
						m.showMove();
						NineMansMorris.board.makeMove(m);
						NineMansMorris.board.getMatrix();
						System.out.println("Turn: "+NineMansMorris.board.turn);
						
						
					}else
					if(m.removedPiece!=null)
					{
						ret=true;
					}
					if(m.value=='B')
					{
						if(NineMansMorris.board.blackStage!=0)
						{
							blackPieceOUT.setVisible(false);
						}
						board.get(strPiece).showBlack();
					}else{
						if(NineMansMorris.board.whiteStage!=0)
						{
							whitePieceOUT.setVisible(false);
						}
						board.get(strPiece).showWhite();
					}
					break;
				}
			}else
			if(m.stage!=0 && initPiece.pos.startsWith(m.initPos[0]+"-"+m.initPos[1]) && 
				strPiece.startsWith(m.finalPos[0]+"-"+m.finalPos[1]))
			{
				if(m.removedPiece==null)
				{
					m.showMove();
					NineMansMorris.board.makeMove(m);
					NineMansMorris.board.getMatrix();
					System.out.println("Turn: "+NineMansMorris.board.turn);
					
				}else
				if(m.removedPiece!=null)
				{
					ret=true;
				}
				if(m.value=='B')
				{
					if(NineMansMorris.board.blackStage!=0)
					{
						blackPieceOUT.setVisible(false);
					}
					board.get(strPiece).showBlack();
				}else{
					if(NineMansMorris.board.whiteStage!=0)
					{
						whitePieceOUT.setVisible(false);
					}
					board.get(strPiece).showWhite();
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

	protected boolean validRelease(String strPiece) {
		char turn = NineMansMorris.board.turn;
		int stage=-1;
		if(turn=='B')
		{
			stage=NineMansMorris.board.blackStage;
		}else if(turn=='W')
		{
			stage=NineMansMorris.board.whiteStage;
			
		}
		Piece p=NineMansMorris.board.board.get(strPiece).piece;
		if(stage==0 && initPiece==null && p==null)
			return true;
		if(stage!=0 && initPiece!=null && p==null)
			return true;
		
		return false;
	}

	protected int getStage(char c) {
		if(NineMansMorris.board.turn==c)
		{
			if(c=='B')
				return NineMansMorris.board.blackStage;
			if(c=='W')
				return NineMansMorris.board.whiteStage;
		}
		return -1;
	}

	protected boolean validPieceToPick(String strPiece) {
		
		for(Move m : possibleMoves)
		{
			if(m.stage!=0 && strPiece.startsWith(m.initPos[0]+"-"+m.initPos[1]))
			{
				return true;
			}
		}
		return false;
	}

	private Container initBoard() {
		Container ret = new Container();
		board=new HashMap<String,PieceImage>();
		this.blackPiece.setVisible(false);
		this.whitePiece.setVisible(false);
		ret.add(blackPiece);
		ret.add(whitePiece);
		for(String str : NineMansMorris.board.board.keySet())
		{
			JLabel black=new JLabel(new ImageIcon(urlBlack));
			JLabel white=new JLabel(new ImageIcon(urlWhite));
			String[] oux=str.split("-");
			int x=Integer.parseInt(oux[0]);
			int y=Integer.parseInt(oux[1]);
			x=30+x*71;
			y=20+y*68;
			black.setBounds(x, y, 47, 47);
			white.setBounds(x, y, 47, 47);
			PieceImage pI = new PieceImage(black,white);
			pI.pos=str;
			board.put(str, pI);
			pI.hidde();
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
	
	public String getMouseToPiece(int x,int y)
	{
		String ret="out";
		
		if(x>=600 && x<=647 && y>=50 && y<=97)
		{
			ret="black";
		}else if(x>=600 && x<=647 && y>=327 && y<=374)
		{
			ret="white";
		}else{
			
			for(String str : NineMansMorris.board.board.keySet())
			{
				String[] oux=str.split("-");
				int xstr=Integer.parseInt(oux[0]);
				int ystr=Integer.parseInt(oux[1]);
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
