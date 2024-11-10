package modele;

public class PieceL extends AbstractPiece {
    
    public PieceL(int posX, int posY){
        super(posX,posY);
    }

    public PieceL(int posX, int posY, String couleur){
        super(posX,posY,couleur);
    }

    public boolean[][] basePiece(){
        boolean[][] piece = {{true,false},
                            {true,false},
                            {true,true}};
        return piece;
    }
}