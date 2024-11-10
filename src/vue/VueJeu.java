package vue;

import java.awt.*;
import javax.swing.*; // Bibliotheque graphique
import modele.Piece;
import modele.PlateauPuzzle;

import java.util.ArrayList;

public class VueJeu extends JPanel {
    private PlateauPuzzle plateau;
    private ArrayList<Color> pieceColors;

    public VueJeu(PlateauPuzzle plateau){
        this.plateau = plateau;
        this.initializePieceColors();
    }

    private void initializePieceColors() {
        this.pieceColors = new ArrayList<>();
        this.pieceColors.add(Color.RED);
        this.pieceColors.add(Color.BLUE);
        this.pieceColors.add(Color.GREEN);
        this.pieceColors.add(Color.YELLOW);
        this.pieceColors.add(Color.PINK);
    }

    /**
    * Dessine la grille du jeu avec les couleurs appropriées.
    * @param g l'objet Graphics utilisé pour dessiner la grille
    */
   @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tailleCase = 40;
        int colorIndex = 0;
        for (int i = 0; i < this.plateau.getTaille(); i++) {
            for (int j = 0; j < this.plateau.getTaille(); j++) {
                int x = i * tailleCase;
                int y = j * tailleCase;
                g.setColor(Color.WHITE);
                g.fillRect(x, y, tailleCase, tailleCase);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, tailleCase, tailleCase);
                for (Piece piece : this.plateau.getListePiece()) {
                    g.setColor(this.pieceColors.get(colorIndex));
                    for (int k = 0; k < piece.getHauteur(); k++) {
                        for (int m = 0; m < piece.getLargeur(); m++) {
                            int x2 = (piece.getPosX() + m) * tailleCase;
                            int y2 = (piece.getPosY() + k) * tailleCase;
                            if (piece.getValue(m, k)) {
                                g.setColor(this.pieceColors.get(colorIndex));
                                g.fillRect(x2, y2, tailleCase, tailleCase);
                                g.setColor(Color.BLACK);
                                g.drawRect(x2, y2, tailleCase, tailleCase);
                            }
                        }
                    }
                    colorIndex = (colorIndex + 1) % this.pieceColors.size();
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int tailleCase = 40;
        int largeur = (this.plateau.getTaille()+1) * tailleCase + tailleCase;
        int hauteur = (this.plateau.getTaille()+1) * tailleCase + tailleCase;
        return new Dimension(largeur, hauteur);
    }
}