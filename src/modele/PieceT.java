package modele;

public class PieceT extends AbstractPiece{
    
    public PieceT(int posX, int posY){
        super(posX,posY);
    }

    public PieceT(int posX, int posY, String couleur){
        super(posX,posY,couleur);
    }

    public boolean[][] basePiece(){
        boolean[][] tab = { {false,true,false},
                            {false,true,false},
                            {true,true,true}};
        return tab;
    } 
}