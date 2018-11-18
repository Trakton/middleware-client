package game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame  {

    public Game() {
        add(new GameLoop());
        setResizable(false);
        pack();
        setTitle("Middleware Game Client");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }
}
