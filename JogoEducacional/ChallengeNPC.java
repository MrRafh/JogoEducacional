package JogoEducacional;

public class ChallengeNPC extends NPC {
    private String question; // Pergunta do desafio
    private String[] options; // Opções de múltipla escolha
    private int correctOption; // Índice da opção correta
    private String rewardMessage; // Mensagem de recompensa
    private String hintMessage; // Mensagem de dica

    public ChallengeNPC(int x, int y) {
        super(x, y, "Olá! Vamos testar seus conhecimentos sobre IF e ELSE?", new String[] {
            "Imagens\\velho.png",    // Sprite para cima
            "Imagens\\velho.png",  // Sprite para baixo
            "Imagens\\velho.png",  // Sprite para esquerda
            "Imagens\\velho.png"  // Sprite para direita
        });

        // Definir a pergunta e as opções
        this.question = "O que o comando 'else' faz em um bloco 'if'?";
        this.options = new String[] {
            "1. Executa se a condição do 'if' for verdadeira",
            "2. Executa se a condição do 'if' for falsa",
            "3. Executa independentemente da condição do 'if'"
        };
        this.correctOption = 1; // A opção correta é a segunda (índice 1)
        this.rewardMessage = "Parabéns! Você acertou!";
        this.hintMessage = "Nah, parece que você não prestou atenção na explicação";
    }

    @Override
    public String interact() {
        // Exibe a pergunta e as opções na caixa de diálogo
        StringBuilder dialog = new StringBuilder();
        dialog.append(question).append("\n");
        for (String option : options) {
            dialog.append(option).append("\n");
        }
        return dialog.toString();
    }

    // Método para verificar a resposta do jogador
    public boolean checkAnswer(int selectedOption) {
        return selectedOption == correctOption;
    }

    public String getRewardMessage() {
        return rewardMessage;
    }

    public String getHintMessage() {
        return hintMessage;
    }
}