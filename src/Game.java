//Game
//Contains the main things needed for the 'Pong' game to run

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends Applet implements ActionListener, KeyListener
{
	//Constants
	private final char UP = 'w';						//The key the player needs to press to move their paddle up
	private final char DOWN = 's';						//The key the player needs to press to move their paddle down
	private final int APP_WIDTH = 1024;					//The intended width of the applet, measured in pixels
	private final int APP_HEIGHT = 768;					//The intended height of the applet, measured in pixels
	private final Color BACKGROUND_COLOUR = Color.black;		//The colour of the game background
	private final Color SCORE_COLOUR = Color.white;		//The colour of the score to be drawn on-screen
	private final Color TEXT_COLOUR = Color.white;		//The colour of the game over text to be drawn on-screen
	private final int TEXT_HEIGHT = 72;					//The height of the game over text to be drawn on-screen, measured in pixels
	private final Font TEXT_FONT = new Font("Arial", Font.BOLD, TEXT_HEIGHT);		//The font used for the game over text
	private final int WIN_SCORE = 10;					//The amount of points either player needs to win
	
	//Variables
	private char c;				//The key that is currently being held down on the keyboard
	private int pScore;			//The current score of the player
	private int cScore;			//The current score of the computer
	private int dirUp;			//This will be listed as 1 if c == UP
	private int dirDown;		//This will be listed as 1 if c == DOWN
	
	//Instances
	private Player player;			//Loads an instance of Player into the applet, titled player
	private Computer computer;		//Loads an instance of Computer into the applet, titled computer
	private Ball ball;				//Loads an instance of Ball into the applet, titled ball
	
	public void init()
	{
		//Variables
		c = 0;
		pScore = 0;
		cScore = 0;
		dirUp = 0;
		dirDown = 0;
		
		this.setSize(APP_WIDTH, APP_HEIGHT);		//Sets the applet to be the ideal height and width
		player = new Player();			//Runs the constructor code in the player
		computer = new Computer();		//Runs the constructor code in the computer
		ball = new Ball();				//Runs the constructor code in the ball
		addKeyListener(this);		//Adds the key listener to the applet so that it can detect what key is being pressed
	}
	
	public void actionPerformed (ActionEvent event)
	{
	}
	
	public void keyPressed(KeyEvent e)
	{
		if((UP == e.getKeyChar()) || (DOWN == e.getKeyChar()))		//Will set c to the key being pressed if either the UP or down KEY is the one being pressed
		{
			c = e.getKeyChar();
		}
    }
	
	public void keyReleased(KeyEvent e)
	{
		if((UP == e.getKeyChar()) || (DOWN == e.getKeyChar()))		//Will set c to 0 if either the UP or DOWN key is released
		{
			c = 0;
		}
	}

	public void keyTyped(KeyEvent arg0)
	{
	}
	
	public void calcDir()
	{
		//checks to see if the key pressed is 'w', and if it is it sets dirUp to 1
		if(c == UP)
		{
			dirUp = 1;
		}
		
		//checks to see if the key pressed is 's', and if it is it sets dirDown to 1
		if(c == DOWN)
		{
			dirDown = 1;
		}
		
		//set the player.dir based on the keyboard inputs
		player.setDir(dirUp, dirDown);
		
		//reset dir-values
		dirUp = 0;
		dirDown = 0;
	}
	
	//Method that causes the game to sleep for 17 milliseconds (roughly 1/60 of a second) and then recalculate everything in the paint method, which then runs this method again, causing a loop
	public void step()
	{
		try
		{
			Thread.sleep(17);
		}
		catch (InterruptedException e)
		{
		}
		repaint();
	}
	
	public void paint (Graphics g)
	{
		if(pScore >= WIN_SCORE)
		{
			//paint the background
			g.setColor(BACKGROUND_COLOUR);
			g.fillRect(0, 0, APP_WIDTH, APP_HEIGHT);
			
			//paint the win text
			g.setColor(TEXT_COLOUR);
			g.setFont(TEXT_FONT);
			g.drawString("YOU ARE A WIN!", APP_WIDTH/4 , (APP_HEIGHT/2 - TEXT_HEIGHT/2));
			
		}
		else if(cScore >= WIN_SCORE)
		{
			//paint the background
			g.setColor(BACKGROUND_COLOUR);
			g.fillRect(0, 0, APP_WIDTH, APP_HEIGHT);
			
			//paint the lose text
			g.setColor(TEXT_COLOUR);
			g.setFont(TEXT_FONT);
			g.drawString("YOU ARE A LOSE!", APP_WIDTH/4, (APP_HEIGHT/2 - TEXT_HEIGHT/2));
		}
		else
		{
			//Calculate a change in the direction from keys
			calcDir();
			
			//calculate the player's new position
			player.calcY(APP_HEIGHT);
			computer.calcDir(ball.getY(), ball.getHeight());
			computer.calcY(APP_WIDTH, APP_HEIGHT, ball.getX(), ball.getWidth());
		
			//calculate the ball's new position
			ball.calcMovement(APP_HEIGHT, player.getX(), player.getY(), player.getWidth(), player.getHeight(), computer.getX(), computer.getY(), computer.getWidth(), computer.getHeight());
			
			//paint the background
			g.setColor(BACKGROUND_COLOUR);
			g.fillRect(0, 0, APP_WIDTH, APP_HEIGHT);
		
			//paint the score
			g.setColor(SCORE_COLOUR);
			for(int i = 0; i < pScore; i++)
			{
				g.fillRect((96 + i*12), 32, 4, 32);
			}
		
			for(int i = 0; i < cScore; i++)
			{
				g.fillRect((804 + i*12), 32, 4, 32);
			}
		
		
			//paint the player
			g.setColor(player.getPaddleColour());
			g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		
			//paint the computer
			g.setColor(computer.getPaddleColour());
			g.fillRect(computer.getX(), computer.getY(), computer.getWidth(), computer.getHeight());
		
			//paint the ball
			g.setColor(ball.getBallColour());
			g.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
			
			if((ball.getX() >= APP_WIDTH) && (pScore < WIN_SCORE) && (cScore < WIN_SCORE))		//Check to see if player has scored a goal
			{
				player.reset();
				computer.reset();
				ball.reset();
				pScore++;
			}
			else if(((ball.getX() + ball.getWidth()) <= 0) && (pScore < WIN_SCORE) && (cScore < WIN_SCORE))		//Check to see if computer has scored a goal
			{
				player.reset();
				computer.reset();
				ball.reset();
				cScore++;
			}
			
			step();
		}
	}
}