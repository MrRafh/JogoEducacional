package JogoEducacional;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public abstract class NPC {
    private int x;
    private int y;
    private int size = 40; // Tamanho do sprite
    private Image[] sprites; // Array de sprites
    private int currentSpriteIndex; // Índice do sprite atual
    private String message;

    public NPC(int x, int y, String message, String[] spritePaths) {
        this.x = x;
        this.y = y;
        this.message = message;
        this.currentSpriteIndex = 1; // Sprite inicial (para baixo)
        this.sprites = new Image[4]; // 4 sprites

        // Carregar os sprites
        try {
            for (int i = 0; i < spritePaths.length; i++) {
                sprites[i] = ImageIO.read(new File(spritePaths[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os sprites do NPC.");
        }
    }

    public void draw(Graphics g) {
        if (sprites[currentSpriteIndex] != null) {
            g.drawImage(sprites[currentSpriteIndex], x, y, size, size, null);
        }
    }

    public boolean isPlayerNear(int playerX, int playerY) {
        return Math.abs(playerX - x) < 50 && Math.abs(playerY - y) < 50;
    }

    public String getMessage() {
        return message;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Método abstrato para interações específicas de cada NPC
    public abstract String interact();
}