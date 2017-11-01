import Enums.BoardSide;
import GameObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class GameUI extends JPanel implements KeyListener
{
    private final int APP_WIDTH = 1024;					//The intended width of the applet, measured in pixels
    private final int APP_HEIGHT = 768;					//The intended height of the applet, measured in pixels
    private final Color BACKGROUND_COLOUR = Color.black;//The colour of the game background
    private final Color SCORE_COLOUR = Color.white;		//The colour of the score to be drawn on-screen
    private final Color TEXT_COLOUR = Color.white;      //The colour of the game over text to be drawn on-screen
    private final int TEXT_HEIGHT = 72;					//The height of the game over text to be drawn on-screen, measured in pixels
    private final Font TEXT_FONT = new Font("Arial", Font.BOLD, TEXT_HEIGHT);		//The font used for the game over text
    private final int WIN_SCORE = 10;					//The amount of points either player needs to win


    private boolean gameOver = false;
    private int leftScore = 0;			//The current score of the left paddle
    private int rightScore = 0;			//The current score of the right paddle
    GameObject leftPaddle;
    GameObject rightPaddle;
    Ball ball;

    public GameUI()
    {
        setSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));

        addKeyListener(this);
        ball = new Ball();
        leftPaddle = new ComputerPaddle(BoardSide.LEFT, getSize(), ball);
        //rightPaddle = new ComputerPaddle(Color.white, BoardSide.RIGHT, getSize(), ball, 8, 32, 128) ;
        rightPaddle = new ComputerPaddle(BoardSide.RIGHT, getSize(), ball);

        Timer timer = new Timer(13, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (ball.getX() >= APP_WIDTH) {
                    leftPaddle.ResetPosition();
                    rightPaddle.ResetPosition();
                    ball.reset();
                    leftScore++;
                } else if ((ball.getX() + ball.getWidth()) <= 0) {
                    leftPaddle.ResetPosition();
                    rightPaddle.ResetPosition();
                    ball.reset();
                    rightScore++;
                }

                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        leftPaddle.ManageInput(e.getKeyCode());
        rightPaddle.ManageInput(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        leftPaddle.ManageInput(0);
        rightPaddle.ManageInput(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(BACKGROUND_COLOUR);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (gameOver)
        {
            g.setColor(TEXT_COLOUR);
            g.setFont(TEXT_FONT);

            String gameOverText;

            if(leftScore == WIN_SCORE)
            {

                gameOverText  = leftPaddle.GetWinMessage();
            }
            else
            {
                gameOverText = rightPaddle.GetWinMessage();
            }

            FontMetrics fontMetrics = getFontMetrics(TEXT_FONT);
            Rectangle2D textSize = fontMetrics.getStringBounds(gameOverText, g);
            int x = (int)((getWidth()) - textSize.getWidth())/2;
            int y = (int)(getHeight() - textSize.getHeight())/2 + fontMetrics.getAscent();
            g.drawString(gameOverText, x, y);
        }
        else {
            leftPaddle.Move();
            g.setColor(leftPaddle.GetColour());
            g.fillRect(leftPaddle.GetX(), leftPaddle.GetY(), leftPaddle.GetWidth(), leftPaddle.GetHeight());

            rightPaddle.Move();
            g.setColor(rightPaddle.GetColour());
            g.fillRect(rightPaddle.GetX(), rightPaddle.GetY(), rightPaddle.GetWidth(), rightPaddle.GetHeight());

            ball.calcMovement(APP_HEIGHT, leftPaddle, rightPaddle);
            g.setColor(ball.getBallColour());
            g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

            g.setColor(SCORE_COLOUR);
            for (int i = 0; i < leftScore; i++) {
                g.fillRect(96 + i * 12, 32, 4, 32);
            }

            for (int i = 0; i < rightScore; i++) {
                g.fillRect(804 + i * 12, 32, 4, 32);
            }
        }
    }
}