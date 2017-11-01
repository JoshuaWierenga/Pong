import javax.swing.*;

public class Game
{
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new GameUI());

        frame.pack();
        frame.setVisible(true);
    }
}