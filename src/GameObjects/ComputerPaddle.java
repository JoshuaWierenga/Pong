package GameObjects;

import Enums.*;
import java.awt.*;

public class ComputerPaddle extends PaddleObject
{
    Ball ball;

    public ComputerPaddle(BoardSide boardSide, Dimension boardDimensions, Ball ball)
    {
        moveSpeed = 16;
        this.boardSide = boardSide;
        boardSize = boardDimensions;
        direction = PaddleDirection.STATIONARY;
        objectSize = new Dimension(32, 128);
        X = originalX = boardSide.getPosition();
        Y = originalY = 320;
        color = Color.white;
        this.ball = ball;
    }

    public ComputerPaddle(BoardSide boardSide, Dimension boardDimensions, Ball ball, Color paddleColor, int paddleSpeed)
    {
        moveSpeed = paddleSpeed;
        this.boardSide = boardSide;
        boardSize = boardDimensions;
        direction = PaddleDirection.STATIONARY;
        objectSize = new Dimension(32, 128);
        X = originalX = boardSide.getPosition();
        Y = originalY = 320;
        color = paddleColor;
        this.ball = ball;
    }

    @Override
    public void Move()
    {
        if ((Y + objectSize.height/4) > (ball.getY() + ball.getHeight()/2))
        {
            direction = PaddleDirection.UP;
        }
        else if ((Y + objectSize.height/4*3) < (ball.getY() + ball.getHeight()/2)) {
            direction = PaddleDirection.DOWN;
        }
        else
        {
            direction = PaddleDirection.STATIONARY;
        }

        if ((boardSide == BoardSide.LEFT && (ball.getX() + ball.getWidth()/2) <= boardSize.width*1/3) ||
                (boardSide == BoardSide.RIGHT && (ball.getX() + ball.getWidth()/2) >= boardSize.width*2/3))
        {
            Y += direction.getValue() * moveSpeed;
        }

        super.Move();
    }

    public void ManageInput(int keyCode) { }
}