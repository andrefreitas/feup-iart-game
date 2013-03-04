import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;


public class Human extends Player{

	public Human(String name, int nMoves, int nPieces, char color, Board board)
	{
		super(name,nMoves,nPieces,color,board);
	}

	@Override
	public void play() 
	{
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			public void eventDispatched(AWTEvent event) {
				if(event instanceof MouseEvent){
					if(board.processPlayAttempt((MouseEvent)event))
					{
						finishedPlay=true;
						Toolkit.getDefaultToolkit().removeAWTEventListener(this);
					}
				}
			}			
		}, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}

}
