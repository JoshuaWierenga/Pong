package GameObjects;

import Enums.*;
import java.awt.*;

public class ComputerPaddle extends GameObject
{
    Ball ball;

    public ComputerPaddle(BoardSide side, Dimension boardSize, Ball ball)
    {
        this.ball = ball;
        moveSpeed = 16;
        y = 320;
        this.boardSize = boardSize;
        this.paddleSize = new Dimension(32, 128);
        color = Color.white;
        this.side = side;
        direction = PaddleDirection.STATIONARY;
        winMessage = "Computer has won";
    }

    public ComputerPaddle(Color paddleColor, BoardSide side, Dimension boardSize, Ball ball, int paddleSpeed, int paddleWidth, int paddleHeight)
    {
        this.ball = ball;
        moveSpeed = paddleSpeed;
        y = 320;
        this.boardSize = boardSize;
        this.paddleSize = new Dimension(paddleWidth, paddleHeight);
        color = paddleColor;
        this.side = side;
        direction = PaddleDirection.STATIONARY;
        winMessage = "Computer has won";
    }

    public int GetX()
    {
        return side.getPosition();
    }

    public int GetY()
    {
        return y;
    }

    public int GetWidth()
    {
        return paddleSize.width;
    }

    public int GetHeight()
    {
        return paddleSize.height;
    }

    public Color GetColour()
    {
        return color;
    }

    public String GetWinMessage() {
        return winMessage;
    }

    public void Move()
    {
        if ((y + paddleSize.height/4) > (ball.getY() + ball.getHeight()/2))
        {
            direction = PaddleDirection.UP;
        }
        else if ((y + paddleSize.height/4*3) < (ball.getY() + ball.getHeight()/2)) {
            direction = PaddleDirection.DOWN;
        }

        if (side == BoardSide.LEFT && (ball.getX() + ball.getWidth()/2) <= boardSize.width*1/3)
        {
            y += direction.getValue() * moveSpeed;
        }
        else if (side == BoardSide.RIGHT && (ball.getX() + ball.getWidth()/2) >= boardSize.width*2/3)
        {
            y += direction.getValue() * moveSpeed;
        }

        if(y < 0)
        {
            y = 0;
        }
        else if((y + paddleSize.height) >= boardSize.height)
        {
            y = boardSize.height - paddleSize.height;
        }

        direction = PaddleDirection.STATIONARY;
    }

    public void ResetPosition()
    {
        y = 320;
        direction = PaddleDirection.STATIONARY;
    }

    public void ManageInput(int keyCode) { }
}