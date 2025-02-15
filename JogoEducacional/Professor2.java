package JogoEducacional;

public class Professor2 extends NPC {
    private int interactionStep; // Contador para controlar a parte da mensagem
    public Professor2(int x, int y) {
        super(x, y, "você quer aprender sobre laços de repetição?", new String[] {
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
        interactionStep++;
        if (interactionStep == 1) {
            message = "Professor diz: \n vou te ensinar alguns meios de laços de repetição.";
            interactionStep++;
        }
        if (interactionStep == 2) {
            message = "Professor diz: \n 'for': Você usa quando sabe quantas vezes quer repetir algo. Como 'pegar 5 pedras'. Então, o laço vai repetir 5 vezes.\n";
            interactionStep++; // Avança para a próxima parte
        } if (interactionStep == 3) {
            message = "Professor diz: \n 'while': Esse vai repetir enquanto algo for verdade. Tipo, 'andar enquanto não encontrar uma árvore'.";
            interactionStep++;
        }
            
        else{
            message = "Professor diz: \n'for': Você usa quando sabe quantas vezes quer repetir algo.\n Como 'pegar 5 pedras'. Então, o laço vai repetir 5 vezes.";
            interactionStep = 0; // Reinicia o contador
        }
        return message;
    }
}