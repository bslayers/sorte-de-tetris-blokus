package modele;

public class PieceBuilder {

    public PieceBuilder(){}

    public Piece createRandomPiece(int width, int height, String couleur){
        boolean[][] piece = new boolean[height][width];
        boolean isPieceEmpty = true;
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                double tmp = Math.round(Math.random()*2);
                piece[y][x] = (tmp == 1);
                if (piece[y][x]) {
                    isPieceEmpty = false;
                }
            }
        }
        if (isPieceEmpty) { //afin d'eviter que une piece vide soit generer
            int randomX = (int) (Math.random() * width);
            int randomY = (int) (Math.random() * height);
            piece[randomY][randomX] = true;
        }
        Piece res = new CustomPiece(0, 0, piece, couleur);
        return res;
    }  
}