package JogoEducacional;

public class Placa1 extends NPC{
    public Placa1(int x, int y){
        super(x, y, "Não deixe a nintendo ver este jogo", new String[]{
            "Imagens\\Placa.png",
            "Imagens\\Placa.png",
            "Imagens\\Placa.png",
            "Imagens\\Placa.png"
        });
    }
    


@Override
    public String interact() {
        return "o elif é uma forma de (se não) condicional que você usa para verificar mais de uma condição\n verifica antes do ELSE se uma condição atinge parametros X";
}
}