package JogoEducacional;

public class Challenge2 extends NPC {
    private String question; // Pergunta do desafio
    private String[] options; // Opções de múltipla escolha
    private int correctOption; // Índice da opção correta
    private String rewardMessage; // Mensagem de recompensa
    private String hintMessage; // Mensagem de dica

    public Challenge2(int x, int y) {
        super(x, y, "você sabia que if e else ", new String[] {
            "Imagens\\velho.png",    // Sprite para cima
            "Imagens\\velho.png",  // Sprite para baixo
            "Imagens\\velho.png",  // Sprite para esquerda
            "Imagens\\velho.png"  // Sprite para direita
        });

        // Definir a pergunta e as opções
        this.question = "Qual das alternativas abaixo não é um tipo de laço de repetição em programação?";
        this.options = new String[] {
            "1.For",
            "2. while",
            "3. loop"
        };
        this.correctOption = 2; // A opção correta é a segunda (índice 1)
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