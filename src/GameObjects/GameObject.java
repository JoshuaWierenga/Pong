package GameObjects;

import Enums.*;
import java.awt.*;

public class GameObject
{
    int X, Y, originalX, originalY;
    Dimension boardSize, objectSize;
    Color color;

    public int GetX()
    {
        return X;
    }

    public int GetY()
    {
        return Y;
    }

    public int GetWidth()
    {
        return objectSize.width;
    }

    public int GetHeight()
    {
        return objectSize.height;
    }

    public Color GetColour()
    {
        return color;
    }

    public void ResetPosition()
    {
        Y = originalY;
    }

    public void Move()
    {
        if(Y < 0)
        {
            Y = 0;
        }
        else if((Y + objectSize.height) >= boardSize.height)
        {
            Y = boardSize.height - objectSize.height;
        }
    }

    public void AddScore() {
    }
}