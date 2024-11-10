package modele;

public class CustomPiece extends AbstractPiece{

    private boolean[][] initPiece;

    public CustomPiece(int posX, int posY, boolean[][] init){
        super(posX,posY);
        this.initPiece = init;
        super.tab = basePiece();
        super.hauteur = tab.length;
        super.largeur = tab[0].length;
    }

    public CustomPiece(int posX, int posY, boolean[][] init, String couleur){
        super(posX,posY,couleur);
        this.initPiece = init;
        super.tab = basePiece();
        super.hauteur = tab.length;
        super.largeur = tab[0].length;
    }

    public boolean[][] basePiece(){
        boolean[][] res = null;
        if(this.initPiece == null){
            res = new boolean[][]{{true}};
        }
        else{
            res = initPiece;
        }
        return res;
    }  
}