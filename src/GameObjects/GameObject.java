package GameObjects;

import Enums.*;
import java.awt.*;

public abstract class GameObject {

    int moveSpeed, y;
    Dimension boardSize, paddleSize;
    Color color;
    BoardSide side;
    PaddleDirection direction;
    int upKey, downKey;

    public abstract int GetX();

    public abstract int GetY();

    public abstract int GetWidth();

    public abstract int GetHeight();

    public abstract Color GetColour();

    public abstract void Move();

    public abstract void ManageInput(int keyCode);

    public abstract void ResetPosition();
}