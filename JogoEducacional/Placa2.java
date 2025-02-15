package JogoEducacional;

public class Placa2 extends NPC{
    public Placa2(int x, int y){
        super(x, y, "velho perguntão", new String[]{
            "Imagens\\Placa.png",
            "Imagens\\Placa.png",
            "Imagens\\Placa.png",
            "Imagens\\Placa.png"
        });
    }
    


@Override
    public String interact() {
        return "Cuidado laços de repetição a frente";
}
}