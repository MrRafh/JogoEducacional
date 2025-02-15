package JogoEducacional;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class CollisionBlock {
    private int x;
    private int y;
    private int width;
    private int height;

    public CollisionBlock(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Retorna um retângulo que representa a área do bloco
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Desenha o bloco de colisão
    public void draw(Graphics g) {
        g.setColor(new Color(255, 0, 0, 128)); // Vermelho semi-transparente
        g.fillRect(x, y, width, height);
        g.setColor(Color.RED); // Borda vermelha
        g.drawRect(x, y, width, height);
    }
}