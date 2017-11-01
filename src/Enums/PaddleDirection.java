package Enums;

public enum PaddleDirection {
    UP(-1),
    STATIONARY(0),
    DOWN(1);

    private int direction;

    PaddleDirection(int direction)
    {
        this.direction = direction;
    }

    public int getValue()
    {
        return direction;
    }
}