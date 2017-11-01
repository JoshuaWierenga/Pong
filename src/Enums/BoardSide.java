package Enums;

public enum BoardSide {
    LEFT(32),
    RIGHT(960);

    private int side;

    BoardSide(int side)
    {
        this.side = side;
    }

    public int getPosition()
    {
        return side;
    }
}