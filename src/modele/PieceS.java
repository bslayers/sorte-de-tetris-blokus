package modele;

public class PieceS extends AbstractPiece{

    public PieceS(int posX, int posY){
        super(posX,posY);
    }

    public PieceS(int posX, int posY, String couleur){
        super(posX,posY,couleur);
    }

    public boolean[][] basePiece(){
        boolean[][] piece = {{true,true,false},
                            {false,true,true}};
        return piece;
    }
}