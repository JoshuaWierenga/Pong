package GameObjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import Enums.*;

public class PlayerPaddle extends GameObject {

    public PlayerPaddle(BoardSide side, Dimension boardSize)
    {
        moveSpeed = 16;
        y = 320;
        this.boardSize = boardSize;
        this.paddleSize = new Dimension(32, 128);
        color = Color.white;
        this.side = side;
        direction = PaddleDirection.STATIONARY;
        if (side == BoardSide.LEFT)
        {
            upKey = KeyEvent.VK_W;
            downKey = KeyEvent.VK_S;
        }
        else if (side == BoardSide.RIGHT)
        {
            upKey = KeyEvent.VK_UP;
            downKey = KeyEvent.VK_DOWN;
        }
        winMessage = side.toString() + " paddle has won";
    }

    public PlayerPaddle(Color paddleColor, BoardSide side,  Dimension boardSize, int paddleSpeed, int paddleHeight, int paddleWidth, char upKey, char downKey, String winMessage)
    {
        moveSpeed = paddleSpeed;
        y = 320;
        this.boardSize = boardSize;
        this.paddleSize = new Dimension(paddleWidth, paddleHeight);
        color = paddleColor;
        this.side = side;
        direction = PaddleDirection.STATIONARY;
        this.upKey = upKey;
        this.downKey = downKey;
        this.winMessage = winMessage;
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
        y += direction.getValue() * moveSpeed;

        if (y < 0)
        {
            y = 0;
        }
        else if ((y + paddleSize.height) > boardSize.height)
        {
            y = boardSize.height - paddleSize.height;
        }
    }

    public void ResetPosition()
    {
        y = 320;
        direction = PaddleDirection.STATIONARY;
    }

    public void ManageInput(int keyCode)
    {
        if (upKey == keyCode)
        {
            direction = PaddleDirection.UP;
        }
        else if (downKey == keyCode)
        {
            direction = PaddleDirection.DOWN;
        }
        else if (0 == keyCode)
        {
            direction = PaddleDirection.STATIONARY;
        }
    }
}