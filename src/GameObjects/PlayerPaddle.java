package GameObjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import Enums.*;

public class PlayerPaddle extends PaddleObject {

    int upKey, downKey;

    public PlayerPaddle(BoardSide boardSide, Dimension boardDimensions)
    {
        moveSpeed = 16;
        this.boardSide = boardSide;
        boardSize = boardDimensions;
        direction = PaddleDirection.STATIONARY;
        objectSize = new Dimension(32, 128);
        X = originalX = boardSide.getPosition();
        Y = originalY = 320;
        color = Color.white;

        if (this.boardSide == BoardSide.LEFT)
        {
            upKey = KeyEvent.VK_W;
            downKey = KeyEvent.VK_S;
        }
        else
        {
            upKey = KeyEvent.VK_UP;
            downKey = KeyEvent.VK_DOWN;
        }
    }

    public PlayerPaddle(BoardSide boardSide, Dimension boardDimensions, Color paddleColor, int paddleSpeed, int upKey, int downKey)
    {
        moveSpeed = paddleSpeed;
        this.boardSide = boardSide;
        boardSize = boardDimensions;
        direction = PaddleDirection.STATIONARY;
        objectSize = new Dimension(32, 128);
        X = originalX = boardSide.getPosition();
        Y = originalY = 320;
        color = paddleColor;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    @Override
    public void Move()
    {
        Y += direction.getValue() * moveSpeed;
        super.Move();
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