package JogoEducacional;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo Educacional RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        GamePanel gamePanel = new GamePanel(frame);
        frame.add(gamePanel);

        frame.setVisible(true);
    }
}