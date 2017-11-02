package GameObjects;

import Enums.*;

public abstract class PaddleObject extends GameObject
{
    int moveSpeed, score;
    BoardSide boardSide;
    PaddleDirection direction;

    @Override
    public void ResetPosition() {
        super.ResetPosition();
        direction = PaddleDirection.STATIONARY;
    }

    public int GetScore()
    {
        return score;
    }

    public void AddScore()
    {
        score++;
    }

    public abstract void ManageInput(int keyCode);
}