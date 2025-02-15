package JogoEducacional;

public class IfElseNPC extends NPC {
    public IfElseNPC(int x, int y) {
        super(x, y, "Use 'if' e 'else' para tomar decisões no código!", new String[] {
            "Imagens\\velho.png",    // Sprite para cima
            "Imagens\\velho.png",  // Sprite para baixo
            "Imagens\\velho.png",  // Sprite para esquerda
            "Imagens\\velho.png"  // Sprite para direita
        });
    }

    @Override
    public String interact() {
        return "Senhora diz:\n Você sabia que pode usar a logica da programação no dia a dia?";
    }
}