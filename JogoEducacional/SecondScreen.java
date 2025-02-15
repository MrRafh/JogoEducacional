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

public class SecondScreen extends JPanel implements KeyListener {
    private Player player;
    private NPC[] npcs;
    private String currentMessage; // Armazena a mensagem atual a ser exibida
    private NPC currentNpc; // Armazena o NPC atual que está interagindo
    private Image backgroundImage; // Imagem de fundo do mapa
    private String dialogMessage; // Mensagem atual do diálogo
    private boolean isDialogActive; // Indica se o diálogo está ativo
    private boolean isChallengeActive; // Indica se um desafio está ativo
    private ChallengeNPC challengeNPC; // Referência ao NPC de desafio ativo
    public int playerScore; // Pontuação do jogador

    // Lista de blocos de colisão
    private List<CollisionBlock> collisionBlocks;

    // Referência para o JFrame principal
    private JFrame parentFrame;

    public SecondScreen(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK); // Fundo preto (será coberto pela imagem)
        this.setFocusable(true);
        this.addKeyListener(this);

        // Posiciona o jogador na parte inferior da tela
        player = new Player(400, 500);
        playerScore = GameState.getPlayerScore();

        // Criando NPCs para a segunda tela
        npcs = new NPC[]{
            new ChallengeNPC(200, 300), // NPC com desafio
            new Placa2(290, 275),
            new Professor2(390,120),
            // Adicione mais NPCs aqui
        };

        // Inicializa a lista de blocos de colisão
        collisionBlocks = new ArrayList<>();

        // Adiciona blocos de colisão para as bordas do mapa
        collisionBlocks.add(new CollisionBlock(0, 0, 800, 20)); // Borda superior
        collisionBlocks.add(new CollisionBlock(0, 580, 800, 20)); // Borda inferior
        collisionBlocks.add(new CollisionBlock(0, 0, 60, 600)); // Borda esquerda
        collisionBlocks.add(new CollisionBlock(730, 0, 20, 600)); // Borda direita

        // Cercas
        collisionBlocks.add(new CollisionBlock(50, 440, 335, 130));
        collisionBlocks.add(new CollisionBlock(470, 440, 335, 130));

        // Pedras
        collisionBlocks.add(new CollisionBlock(330, 290, 600, 10));
        collisionBlocks.add(new CollisionBlock(60, 290, 130, 10));
        collisionBlocks.add(new CollisionBlock(600, 140, 400, 10));

        //arvores
        collisionBlocks.add(new CollisionBlock(60, 70, 330, 80));

        // Carregar a imagem de fundo da segunda tela
        try {
            backgroundImage = ImageIO.read(new File("Imagens\\TELA 2.png")); // Substitua pelo caminho da sua imagem
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a imagem de fundo da segunda tela.");
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
        // Desenha os blocos de colisão
        for (CollisionBlock block : collisionBlocks) {
            block.draw(g);
        }
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
            Font font = new Font("Arial", Font.BOLD, 10);
            g.setFont(font);
        }
       /*  // Desenha a pontuação no canto superior esquerdo
        g.setColor(Color.WHITE); // Define a cor do texto como branca
        g.setFont(new Font("Arial", Font.BOLD, 18)); // Define a fonte e tamanho do texto
        g.drawString("Pontos: " + GameState.getPlayerScore() , 20, 30); // Exibe a pontuação na tela
         */

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

        if (isChallengeActive) {
            // Lida com a seleção de opções durante o desafio
            if (key >= KeyEvent.VK_1 && key <= KeyEvent.VK_3) { // Teclas 1, 2, 3
                int selectedOption = key - KeyEvent.VK_1; // Converte para índice (0, 1, 2)
                if (challengeNPC.checkAnswer(selectedOption)) {
                    dialogMessage = challengeNPC.getRewardMessage(); // Resposta correta
           
                    GameState.addPoints(10);
              
                    repaint();
                } else {
                    dialogMessage = challengeNPC.getHintMessage(); // Resposta incorreta
                }
                isChallengeActive = false; // Encerra o desafio
            }
        } else {
            // Movimentação normal do jogador
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
                dialogMessage = currentNpc.interact(); // Interage com o NPC, seja ele qual for
                if (currentNpc != null && currentNpc instanceof ChallengeNPC) {
                    challengeNPC = (ChallengeNPC) currentNpc; // Armazena o NPC de desafio
                    dialogMessage = challengeNPC.interact(); // Exibe a pergunta e as opções
                    isDialogActive = true; // Ativa a caixa de diálogo
                    isChallengeActive = true; // Inicia o desafio
                }
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
        }

        // Verifica se o jogador atingiu as coordenadas para voltar à primeira tela
        if (player.getX() >= 390 && player.getX() <= 420 && player.getY() >= 520) {    
            GameState.setPlayerScore(playerScore); // Salva a pontuação
            System.out.println("Jogador atingiu as coordenadas de retorno. Voltando para a primeira tela...");
            

            // Cria a primeira tela (GamePanel) e define a posição e direção do jogador
            GamePanel firstScreen = new GamePanel(parentFrame);
            GameState.setPlayerScore(playerScore);
            firstScreen.setPlayerPosition(420, 40); // Define a posição do jogador
            firstScreen.setPlayerDirection(1); // Define a direção do jogador como "para baixo"

            // Transporta o jogador de volta para a primeira tela
            parentFrame.getContentPane().removeAll();
            parentFrame.add(firstScreen);
            parentFrame.revalidate();
            parentFrame.repaint();
            parentFrame.transferFocus();
            
        }

        if (player.getX() >= 60 && player.getX() <= 690 && player.getY() <= 20) {
            GameState.setPlayerScore(playerScore);
            // Transporta o jogador para a segunda tela
            parentFrame.getContentPane().removeAll();
            parentFrame.add(new ThirdScreen(parentFrame)); // Passa o parentFrame como argumento
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