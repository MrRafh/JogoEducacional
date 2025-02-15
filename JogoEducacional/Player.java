package JogoEducacional;

import java.awt.Image;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

public class Player {
    private int x;
    private int y;
    private int size = 40; // Tamanho do sprite
    private Image[] sprites; // Array de sprites
    private int currentSpriteIndex; // Índice do sprite atual

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentSpriteIndex = 1; // Sprite inicial
        this.sprites = new Image[4]; // 4 sprites:a

        // Carregar os sprites
        try {
            sprites[0] = ImageIO.read(new File("Imagens\\costas.png")); // Cima
            sprites[1] = ImageIO.read(new File("Imagens\\frente.png")); // Baixo
            sprites[2] = ImageIO.read(new File("Imagens\\esquerda.png")); // Esquerda
            sprites[3] = ImageIO.read(new File("Imagens\\direita.png")); // Direita
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os sprites do jogador.");
        }
    }

    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;

        // Atualiza o sprite
        if (deltaY < 0) {
            currentSpriteIndex = 0; //Cima
        } else if (deltaY > 0) {
            currentSpriteIndex = 1; //Baixo
        } else if (deltaX < 0) {
            currentSpriteIndex = 2; //Esquerda
        } else if (deltaX > 0) {
            currentSpriteIndex = 3; //Direita
        }
    }

    public void draw(Graphics g) {
        if (sprites[currentSpriteIndex] != null) {
            g.drawImage(sprites[currentSpriteIndex], x, y, size, size, null);
        }
    }
    public void setDirection(int direction) {
        this.currentSpriteIndex = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //a área do jogador
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}