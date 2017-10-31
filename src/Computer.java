/*
//////// Class_Computer Definition ////////
	//Variables and Constants
	 * WIDTH								//The width of the paddle on-screen, in pixels
	 * HEIGHT								//The height of the paddle on-screen, in pixels
	 * MOVE_SPEED							//The amount of pixels the paddle can move up or down in one step
	 * PADDLE_COLOUR						//The colour that the computer's paddle will be drawn on-screen
	 * x									//The x-coordinate of the computer's paddle, measured in pixels from the top-left corner of the screen
	 * y									//The y-coordinate of the computer's paddle, measured in pixels from the top-left corner of the screen
	 * dir									//The direction that the computer intends on moving their paddle on that step; -1 would indicate moving up, 1 would indicate moving down, and 0 would indicate no chane in position
	 * move									//The amount by which the y-coordinate will change that step, in pixels; it is calculated as MOVE_SPEED multiplied by dir
	
	//Constructors
	 * public Computer()					//Initialises computer with variables
	
	//Methods
	 * public int getX()					//Method to return x out of class
	 * public int getY()					//Method to return y out of class
	 * public int getWidth()				//Method to return WIDTH out of class
	 * public int getHeight()				//Method to return HEIGHT out of class
	 * public Color getPaddleColour()		//Method to return PADDLE_COLOUR out of class
	 * public void calcDir(int ballY, int ballHeight)		//Method to change dir based on the ball's y-coordinate
	 * public void calcY(int appWidth, int appHeight, int ballX, int ballWidth)		//Method to calculate y based on the values of other variables
	 * public void reset()					//Method to reset computer to start conditions
	
//////// End definition ////////
*/

import java.awt.*;

public class Computer
{
	//Constants
	private final int WIDTH = 32;
	private final int HEIGHT = 128;
	private final int MOVE_SPEED = 16;
	private final Color PADDLE_COLOUR = Color.white;
	
	//Variables
	private int x;
	private int y;
	private int dir;
	private int move;

	//Player Constructor
	public Computer()
	{ 
		//Variables
		x = 960;
		y = 320;
		dir = 0;
		move = dir*MOVE_SPEED;
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
	
	//Method to return PADDLE_COLOUR out of class
	public Color getPaddleColour()
	{
		return PADDLE_COLOUR;
	}
	
	//Method to change dir based on the ball's y-coordinate
	public void calcDir(int ballY, int ballHeight)
	{
		if((y + HEIGHT/4) > (ballY + ballHeight/2))
		{
			dir = -1;
		}
		else if((y + HEIGHT/4*3) < (ballY + ballHeight/2))
		{
			dir = 1;
		}
		else
		{
			dir = 0;
		}			
	}
	
	//Method to calculate y based on the values of other variables
	public void calcY(int appWidth, int appHeight, int ballX, int ballWidth)
	{
		if((ballX + ballWidth/2) >= appWidth*2/3)		//Computer will only move if the ball is in the third of the screen that is nearest to it
		{
			move = dir*MOVE_SPEED;
		}
		else
		{
			move = 0;
		}
			
		y = y + move;
		
		if(y < 0)
		{
			y = 0;
		}
		else if((y + HEIGHT) >= appHeight)
		{
			y = appHeight - HEIGHT;
		}
		
		dir = 0;
	}
	
	//Method to reset computer to start conditions
	public void reset()
	{ 
		x = 960;
		y = 320;
		dir = 0;
		move = dir*MOVE_SPEED;
	}
}
