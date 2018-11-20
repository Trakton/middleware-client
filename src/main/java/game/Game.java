package game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame  {

    public Game() {
        add(new GameLoop(123, 321));
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
