package GameObjects;

/*
//////// Class_Ball Definition ////////
	//Variables and Constants
	 * WIDTH								//The width of the ball on-screen, in pixels
	 * HEIGHT								//The height of the ball on-screen, in pixels
	 * MOVE_SPEED							//The amount of pixels the ball can move in any direction in one step
	 * BALL_COLOUR							//The colour that the ball will be drawn on-screen
	 * x									//The x-coordinate of the ball, measured in pixels from the top-left corner of the screen
	 * y									//The y-coordinate of the ball, measured in pixels from the top-left corner of the screen
	 * dir									//The direction that the ball is moving on that step, in radians; ranges from 0 to 2Pi, 0 radians is left and increasing values move clockwise
	
	//Constructors
	 * public Ball()					//Initialises ball with variables
	
	//Methods
	 * public int getX()					//Method to return x out of class
	 * public int getY()					//Method to return y out of class
	 * public int getWidth()				//Method to return WIDTH out of class
	 * public int getHeight()				//Method to return HEIGHT out of class
	 * public Color getBallColour()			//Method to return BALL_COLOUR out of class
	 * public void calcMovement(int appHeight, int playerX, int playerY, int playerWidth, int playerHeight, int computerX, int computerY, int computerWidth, int computerHeight)		//Method to calculate what the ball's new x and y values are, whether it's collided with something, and what it's new direction is if it has collided with something
	 * public void reset()					//Method to reset ball to start conditions
	
//////// End definition ////////
*/

import GameObjects.GameObject;

import java.awt.*;

public class Ball
{
	//Constants
	private final int WIDTH = 32;
	private final int HEIGHT = 32;
	private final int MOVE_SPEED = 16;
	private final Color BALL_COLOUR = Color.white;
	
	//Variables
	private int x;
	private int y;
	private double dir;

	//Ball Constuctor
	public Ball()
	{ 
		//Variables
		x = 496;
		y = 368;
		dir = 0;
	}
	
	//Method to return x out of class
	public int getX()
	{
		return x;
	}
	
	//Method to return y out of class
	public int getY()
	{
		return y;
	}
	
	//Method to return WIDTH out of class
	public int getWidth()
	{
		return WIDTH;
	}
	
	//Method to return HEIGHT out of class
	public int getHeight()
	{
		return HEIGHT;
	}
	
	//Method to return BALL_COLOUR out of class
	public Color getBallColour()
	{
		return BALL_COLOUR;
	}
	
	//Method to calculate what the ball's new x and y values are, whether it's collided with something, and what it's new direction is if it has collided with something
	public void calcMovement(int appHeight, GameObject leftPaddle, GameObject rightPaddle)
	{
		x = (int)(x + Math.cos(dir) * MOVE_SPEED);
		y = (int)(y + Math.sin(dir) * MOVE_SPEED);
		
		//Calculate collisions with top and bottom of window
		if(y < 0)
		{
			y = 0;
			dir = (2 * Math.PI) - dir;
		}
		else if((y + HEIGHT) >= appHeight)
		{
			y = appHeight - HEIGHT;
			dir = (2 * Math.PI) - dir;
		}
		
		//Calculate collisions with player
		if(((y + HEIGHT) >= leftPaddle.GetY()) && (y < (leftPaddle.GetY()+ leftPaddle.GetHeight())) && (x < (leftPaddle.GetX() + leftPaddle.GetWidth())) && (x >= leftPaddle.GetX()))
		{
			x = leftPaddle.GetX() + leftPaddle.GetWidth();
			dir = 0 + (((((y + HEIGHT/2) - (double)leftPaddle.GetY())/(double)leftPaddle.GetHeight())-0.5)*2*(Math.PI*3/8));
		}
		
		//Calculate collisions with computer
		if(((y + HEIGHT) >= rightPaddle.GetY()) && (y < (rightPaddle.GetY() + rightPaddle.GetHeight())) && ((x + WIDTH) >= rightPaddle.GetX()) && ((x + WIDTH) < (rightPaddle.GetX() + rightPaddle.GetWidth())))
		{
			x = rightPaddle.GetX() - WIDTH;
			dir = Math.PI - (((((y + HEIGHT/2) - (double)rightPaddle.GetY())/(double)rightPaddle.GetHeight())-0.5)*2*(Math.PI*3/8));
		}
		
		//Keeps the dir within the range of 0 to 2Pi
		if(dir >= Math.PI*2)
		{
			dir = dir - Math.PI*2;
		}
		else if(dir < 0)
		{
			dir = dir + Math.PI*2;
		}
		
		//Move ball out of angles to prevent it from going completely horizontal or vertical
		if((dir > Math.PI*3/8) && (dir < Math.PI/2))
		{
			dir = Math.PI*3/8;
		}
		else if((dir > Math.PI/2) && (dir < Math.PI*5/8))
		{
			dir = Math.PI*5/8;
		}
		else if((dir > Math.PI*11/8) && (dir < Math.PI*3/2))
		{
			dir = Math.PI*11/8;
		}
		else if((dir > Math.PI*3/2) && (dir < Math.PI*13/8))
		{
			dir = Math.PI*13/8;
		}
	}
	
	//Method to reset ball to start conditions
	public void reset()
	{
		x = 496;
		y = 368;
		dir = 0;
	}

}
