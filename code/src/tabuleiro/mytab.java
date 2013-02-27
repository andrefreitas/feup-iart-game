package tabuleiro;

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

public class mytab {

	public URL url1 = 
			ClassLoader.getSystemClassLoader().
			getResource("imagens/tabuleiro.gif");
	public ImageIcon icontabuleiro = new ImageIcon(url1);
	
	public JFrame fundo = new JFrame();
	public JLabel tab_label;
	
	public int[][] tab; //ind peças
	public mypeca[] pecas;
	
	public int[][][] tab_pos;//coord (x,y) da imagem das peças
	
	public int peca_select=-1;
	
	public mytab()
	{
		
	}
	
	public void init() {
		tab=new int[7][7];
		pecas=new mypeca[24];
		tab_pos = new int[7][7][2];
		tab_label=new JLabel(icontabuleiro);
		tab_label.setBounds(0, 0, 700, 700);
		
		initpecas(pecas);
		initpos(tab_pos);
		peca_select=-1;
		
		fundo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fundo.setPreferredSize(new Dimension(900,750));
		fundo.setBounds(200, 2, 900, 750);
		fundo.setLayout(null);
		
		Container a = new Container();
		
		for(int i=0;i<pecas.length;i++)
		{
			
			a.add(pecas[i].imagem);
		}
		a.add(tab_label);
		fundo.setContentPane(a);
		
		mouseInit();
		
		fundo.repaint();
		fundo.pack();
		fundo.setVisible(true);
		
	}

	private void initpos(int[][][] tp) {
		
		for(int i=0;i<tp.length;i++)
		{
			for(int e=0;e<tp[i].length;e++)
			{
				tp[i][e][0]=-1;
				tp[i][e][1]=-1;
			}
			
		}
		
		tp[0][0][0]=8;
		tp[0][0][1]=8;
		
		tp[3][0][0]=326;
		tp[3][0][1]=8;
		
		tp[6][0][0]=644;
		tp[6][0][1]=8;
		
		tp[1][1][0]=115;
		tp[1][1][1]=115;
		
		tp[3][1][0]=326;
		tp[3][1][1]=115;
		
		tp[5][1][0]=540;
		tp[5][1][1]=115;

		tp[2][2][0]=222;
		tp[2][2][1]=222;
		
		tp[3][2][0]=326;
		tp[3][2][1]=222;
		
		tp[4][2][0]=434;
		tp[4][2][1]=222;

		tp[0][3][0]=8;
		tp[0][3][1]=327;
		
		tp[1][3][0]=115;
		tp[1][3][1]=327;
		
		tp[2][3][0]=222;
		tp[2][3][1]=327;

		tp[4][3][0]=434;
		tp[4][3][1]=327;
		
		tp[5][3][0]=540;
		tp[5][3][1]=327;
		
		tp[6][3][0]=644;
		tp[6][3][1]=327;
		
		tp[2][4][0]=222;
		tp[2][4][1]=434;
		
		tp[3][4][0]=326;
		tp[3][4][1]=434;
		
		tp[4][4][0]=434;
		tp[4][4][1]=434;

		tp[1][5][0]=115;
		tp[1][5][1]=540;
		
		tp[3][5][0]=326;
		tp[3][5][1]=540;
		
		tp[5][5][0]=540;
		tp[5][5][1]=540;

		tp[0][6][0]=8;
		tp[0][6][1]=644;
		
		tp[3][6][0]=326;
		tp[3][6][1]=644;
		
		tp[6][6][0]=644;
		tp[6][6][1]=644;

		
	}

	private void mouseInit() {
		
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
								if(pecas[i].xcoord <= mouseX && (pecas[i].xcoord+pecas[i].tamanho) >= mouseX &&
								   pecas[i].ycoord <= mouseY && (pecas[i].ycoord+pecas[i].tamanho) >= mouseY)
								{
									peca_select=i;
									pecas[i].deltx=mouseX-pecas[i].xcoord;
									pecas[i].delty=mouseY-pecas[i].ycoord;
									//System.out.println("peca: "+i);
									break;
								}
								
							}
						}else{
							
							int[] pos_oux=peca_tab(mouseX,mouseY,pecas[peca_select].tamanho);
							if(pos_oux[0]==-1)
							{
								pecas[peca_select].xcoord=mouseX-pecas[peca_select].deltx;
								pecas[peca_select].ycoord=mouseY-pecas[peca_select].delty;
							}else{
								pecas[peca_select].xcoord=tab_pos[pos_oux[0]][pos_oux[1]][0];
								pecas[peca_select].ycoord=tab_pos[pos_oux[0]][pos_oux[1]][1];
							}
													
							pecas[peca_select].imagem.setBounds(pecas[peca_select].xcoord, 
																pecas[peca_select].ycoord, 
									pecas[peca_select].tamanho, pecas[peca_select].tamanho);
							
							System.out.println("pecaX: "+pecas[peca_select].xcoord+
											", pecaY: "+pecas[peca_select].ycoord);
							
						}
						
					}

					fundo.repaint();
				}
			}
		}, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		
	}

	private void initpecas(mypeca[] p) {
		
		for(int i=0;i<p.length;i++)
		{
			p[i]=new mypeca();

			if(i >= (p.length/2))
			{
				p[i].cor='b';
				p[i].imagem=p[i].branca;
				p[i].ycoord=327;
				p[i].ycoord_old=327;
			}
			
			p[i].xcoord=715;
			p[i].xcoord_old=715;
			p[i].imagem.setBounds(p[i].xcoord, p[i].ycoord, p[i].tamanho, p[i].tamanho);
			
		}
		
	}
	
	private int[] peca_tab(int x, int y, int t) //retorna ind x e y da peca no tabuleiro
	{
		int[] ret=new int[2];
		ret[0]=-1;
		ret[1]=-1;
		
		for(int i=0;i<tab_pos.length;i++)
		{
			for(int e=0;e<tab_pos[i].length;e++)
			{
				if(tab_pos[i][e][0]!=-1)
				{
					if(tab_pos[i][e][0]<=x && (tab_pos[i][e][0]+t)>=x &&
					   tab_pos[i][e][1]<=y && (tab_pos[i][e][1]+t)>=y)
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
	

}
