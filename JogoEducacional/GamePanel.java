package JogoEducacional;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {
    private Player player;
    private NPC[] npcs;
    private String currentMessage; // Armazena a mensagem atual a ser exibida
    private NPC currentNpc; // Armazena o NPC atual que está interagindo
    private Image backgroundImage; // Imagem de fundo do mapa
    private String dialogMessage; // Mensagem atual do diálogo
    private boolean isDialogActive; // Indica se o diálogo está ativo
    public int playerScore; // Pontuação do jogador

    // Lista de blocos de colisão
    private List<CollisionBlock> collisionBlocks;

    // Referência para o JFrame principal
    private JFrame parentFrame;

    public GamePanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK); // Fundo preto (será coberto pela imagem)
        this.setFocusable(true);
        this.addKeyListener(this);

        player = new Player(400, 310);
        playerScore = GameState.getPlayerScore(); // Inicializa a pontuação do jogador

        // Criando NPCs
        npcs = new NPC[]{
            new Placa1(160, 380),
            new IfElseNPC(200, 200),
            new Professor(520, 390),
           
        };


        // Inicializa a lista de blocos de colisão
        collisionBlocks = new ArrayList<>();

        collisionBlocks.add(new CollisionBlock(0, 0, 390, 40));
        collisionBlocks.add(new CollisionBlock(460, 0, 280, 40));
        collisionBlocks.add(new CollisionBlock(0, 550, 800, 20));
        collisionBlocks.add(new CollisionBlock(10, 0, 50, 600));
        collisionBlocks.add(new CollisionBlock(720, 0, 50, 620));
        collisionBlocks.add(new CollisionBlock(230, 480, 120, 60));
        collisionBlocks.add(new CollisionBlock(170, 310, 150, 20));
        collisionBlocks.add(new CollisionBlock(170, 390, 20, 20));
        collisionBlocks.add(new CollisionBlock(430, 450, 190, 10));

        collisionBlocks.add(new CollisionBlock(170, 100, 155, 110));
        collisionBlocks.add(new CollisionBlock(140, 180, 30, 30));
        collisionBlocks.add(new CollisionBlock(460, 100, 155, 110));
        collisionBlocks.add(new CollisionBlock(430, 180, 30, 25));
        collisionBlocks.add(new CollisionBlock(430, 270, 220, 110));

        // Adiciona um espaço de saída na borda superior
        for (int x = 390; x <= 420; x += 10) {
            collisionBlocks.add(new CollisionBlock(x, 0, 10, 20)); // Blocos na borda superior
        }

        // Carregar a imagem de fundo
        try {
            backgroundImage = ImageIO.read(new File("Imagens\\TownMap!.png")); // Substitua pelo caminho da sua imagem
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a imagem de fundo.");
        }
    }   

    // Método para verificar colisões
    private boolean isColliding(Rectangle bounds) {
        for (CollisionBlock block : collisionBlocks) {
            if (block.getBounds().intersects(bounds)) {
                return true; // Há colisão
            }
        }
        return false; // Não há colisão
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha a imagem de fundo redimensionada para ocupar toda a tela
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
       /* 
        // Desenha a pontuação no canto superior esquerdo
        g.setColor(Color.WHITE); // Define a cor do texto como branca
        g.setFont(new Font("Arial", Font.BOLD, 18)); // Define a fonte e tamanho do texto
        g.drawString("Pontos: " + playerScore, 20, 30); // Exibe a pontuação na tela
        
        // Desenha os blocos de colisão
        for (CollisionBlock block : collisionBlocks)
            block.draw(g);
        */
        // Desenha o jogador
        player.draw(g);

        // Desenha os NPCs
        for (NPC npc : npcs) {
            npc.draw(g);

            // Verifica se o jogador está perto do NPC
            if (npc.isPlayerNear(player.getX(), player.getY())) {
                currentMessage = npc.getMessage(); // Atualiza a mensagem atual
                currentNpc = npc; // Armazena o NPC atual
            }
        }

        // Exibe a mensagem atual na tela, acima do NPC
        if (currentMessage != null && currentNpc != null) {
            g.setColor(Color.black); // Cor do texto
            g.drawString(currentMessage, currentNpc.getX(), currentNpc.getY() - 10); // Texto acima do NPC
        }

        // Desenha a caixa de diálogo se estiver ativa
        if (isDialogActive) {
            drawDialogBox(g);
        }
    }

    // Método para desenhar a caixa de diálogo
    private void drawDialogBox(Graphics g) {
        int boxWidth = getWidth() - 40; // Largura da caixa de diálogo
        int boxHeight = 100; // Altura da caixa de diálogo
        int boxX = 20; // Posição X da caixa de diálogo
        int boxY = getHeight() - boxHeight - 20; // Posição Y da caixa de diálogo

        // Desenha o fundo da caixa de diálogo
        g.setColor(new Color(0, 0, 0, 200)); // Preto semi-transparente
        g.fillRect(boxX, boxY, boxWidth, boxHeight);

        // Desenha a borda da caixa de diálogo
        g.setColor(Color.WHITE);
        g.drawRect(boxX, boxY, boxWidth, boxHeight);

        // Desenha a mensagem do diálogo
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);

        // Quebra a mensagem em várias linhas se necessário
        String[] lines = dialogMessage.split("\n");
        int lineHeight = g.getFontMetrics().getHeight();
        int textY = boxY + 30;

        for (String line : lines) {
            g.drawString(line, boxX + 20, textY);
            textY += lineHeight;
        }
    }

    //setar a posição e direção do jogador
    public void setPlayerPosition(int x, int y) {
        player.move(x - player.getX(), y - player.getY()); // Move o jogador para as coordenadas especificadas
    }
    public void setPlayerDirection(int direction) {
        player.setDirection(direction); // Define a direção do jogador
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
    
        int newX = player.getX();
        int newY = player.getY();
    
        if (key == KeyEvent.VK_UP) {
            newY -= 10;
        } else if (key == KeyEvent.VK_DOWN) {
            newY += 10;
        } else if (key == KeyEvent.VK_LEFT) {
            newX -= 10;
        } else if (key == KeyEvent.VK_RIGHT) {
            newX += 10;
        } else if (key == KeyEvent.VK_E) { // Tecla "E" para interagir
            if (currentNpc != null) {
                dialogMessage = currentNpc.interact(); // Executa a interação do NPC
                isDialogActive = true; // Ativa a caixa de diálogo
            }
        } else if (key == KeyEvent.VK_ESCAPE) { // Tecla "ESC" para fechar o diálogo
            isDialogActive = false; // Desativa a caixa de diálogo
        }
    
        // Verifica se a nova posição colide com algum bloco
        Rectangle newBounds = new Rectangle(newX, newY, player.getBounds().width, player.getBounds().height);
        if (!isColliding(newBounds)) {
            player.move(newX - player.getX(), newY - player.getY());
        }
    
    // Verifica se o jogador atingiu as coordenadas para mudar de tela
        if (player.getX() >= 390 && player.getX() <= 420 && player.getY() <= 20) {
         GameState.setPlayerScore(playerScore); // Salva a pontuação globalmente
         parentFrame.getContentPane().removeAll();
         parentFrame.add(new SecondScreen(parentFrame));
         parentFrame.revalidate();
         parentFrame.repaint();
         parentFrame.transferFocus();
        }
    
        // Limpa a mensagem e o NPC atual quando o jogador se move
        currentMessage = null;
        currentNpc = null;
    
        repaint();
         }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}