package JogoEducacional;

public class Professor extends NPC {
    private int interactionStep; // Contador para controlar a parte da mensagem
    public Professor(int x, int y) {
        super(x, y, "Olá! Vou te explicar como funciona o IF e ELSE.", new String[] {
            "Imagens\\rene.png",    // cima
            "Imagens\\rene.png",  // baixo
            "Imagens\\rene.png",  //esquerda
            "Imagens\\rene.png"  // direita
        });
        this.interactionStep = 0; // Inicializa o contador
    }

    @Override
    public String interact() {
        String message;
        if (interactionStep == 0) {
            message = "Professor diz: \n 'O IF e ELSE são usados para tomar decisões no código.'";
            interactionStep++; // Avança para a próxima parte
        } else {
            message = "Professor\n exemplo: if (maçãs>2) { dividir com o colega } else { comer sozinho }";
            interactionStep = 0; // Reinicia o contador
        }
        return message;
    }
}