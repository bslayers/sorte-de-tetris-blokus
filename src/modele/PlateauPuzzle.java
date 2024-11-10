package modele;

import java.util.ArrayList;

public class PlateauPuzzle {
    private ArrayList<Piece> listePiece;
    private int taille;
    
    public PlateauPuzzle(int taille){
        this.listePiece = new ArrayList<>();
        this.taille = taille;
    }

    public ArrayList<Piece> getListePiece() {
        return this.listePiece;
    }

    public int getTaille(){
        return this.taille;
    }

    public boolean ajouterPiece(Piece piece) {
        if (!this.collision(piece)) {
            this.listePiece.add(piece);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Vérifie s'il y a une collision entre la pièce courante et les autres pièces sur le tableau.
     * Une collision peut se produire si la pièce sort du tableau ou si elle se chevauche avec une autre pièce.
     *
     * @param p La pièce à vérifier pour les collisions.
     * @return true s'il y a une collision, false sinon.
     * 
     * @pre La pièce p ne doit pas être null.
     * @post Le tableau ne subit aucune modification.
     * @post La position et l'orientation de la pièce p ne sont pas modifiées.
     * @post La méthode renvoie true si la pièce p entre en collision avec une autre pièce ou si elle sort du tableau, false sinon.
     */
    private boolean collision(Piece p){
        int[] pos = p.getPosition();
        if(pos[0] < 0 || pos[1] < 0 || pos[0] + p.getLargeur() > this.taille || pos[1] + p.getHauteur() > this.taille){
            return true; // La pièce sort du tableau
        }
        for(Piece pieceTab: this.listePiece){
            if(pieceTab != p){
                int[] posTab = pieceTab.getPosition();
                if(pos[0] < posTab[0] + pieceTab.getLargeur() &&
                   pos[0] + p.getLargeur() > posTab[0] &&
                   pos[1] < posTab[1] + pieceTab.getHauteur() &&
                   pos[1] + p.getHauteur() > posTab[1]){
                    // Les pièces se chevauchent, vérifier les positions individuelles
                    for(int i = pos[0]; i < pos[0] + p.getLargeur(); i++){
                        for(int j = pos[1]; j < pos[1] + p.getHauteur(); j++){
                            Boolean val1 = p.absoluteOcupation(i, j);
                            Boolean val2 = pieceTab.absoluteOcupation(i, j);
                            if(val1 != null && val2 != null){
                                if(val1 && val2){
                                    return true; // Collision détectée
                                }
                            }
                        }
                    }
                }
            }
        }
        return false; // Pas de collision
    }    
    /**
     * Déplace une pièce aux nouvelles coordonnées et avec une nouvelle orientation,
     * tout en vérifiant s'il y a une collision avec d'autres pièces.
     *
     * @param piece La pièce à déplacer.
     * @param x La nouvelle coordonnée x.
     * @param y La nouvelle coordonnée y.
     * @param orientation La nouvelle orientation de la pièce.
     * @return true si le déplacement est réussi sans collision, false sinon.
     *
     * @pre La pièce ne doit pas être null.
     * @pre x et y doivent être des coordonnées valides dans le tableau.
     * @pre L'orientation doit être un angle valide (0, 90, 180, 270).
     * @post Si le déplacement est réussi, les coordonnées et l'orientation de la pièce sont mises à jour.
     * @post Si une collision est détectée, les coordonnées et l'orientation de la pièce sont restaurées à leurs valeurs précédentes.
     * @post Aucune autre pièce sur le tableau n'est modifiée.
     */
    public boolean deplacerPiece(Piece piece, int x, int y, int orientation){
        int baseX = piece.getPosX();
        int baseY = piece.getPosY();
        int newOri = (orientation == 0)? 0 : 270;
        piece.setPosX(x);
        piece.setPosY(y);
        piece.setOrientation(orientation);
        if(this.collision(piece)){
            piece.setPosX(baseX);
            piece.setPosY(baseY);
            piece.setOrientation(newOri);
            return false;
        }
        return true;
    }

    public boolean deplacerPiece(Piece p, int x, int y){
        return this.deplacerPiece(p,x,y,p.getOrientation());
    }
    /**
     * Calcule le score actuel en fonction de la position des pièces sur le tableau.
     *
     * @return Le score calculé en fonction des positions des pièces.
     *
     * @pre Aucune pièce ne doit être null.
     * @post Aucune modification n'est apportée à la position ou à la configuration des pièces.
     * @post Le score calculé est basé sur les positions actuelles des pièces sur le tableau.
     */
    public int calculerScore(){
        int minX = this.taille; int minY = this.taille; int maxX = 0; int maxY = 0;
        for(Piece p: this.listePiece){
            int[] pos = p.getPosition();
            minX = Math.min(minX,pos[0]);
            minY = Math.min(minY,pos[1]);
            maxX = Math.max(maxX,pos[0]+p.getLargeur());
            maxY = Math.max(maxY,pos[1]+p.getHauteur());
        }
        int res = (maxX-minX)*(maxY-minY);
        return res;
    }
    
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("  ");
        for (int i = 0; i < this.taille; i++) {
            res.append(i).append("  ");
        }
        res.append("\n");
        for (int y = 0; y < this.taille; y++) {
            res.append(y).append(" ");
            for (int x = 0; x < this.taille; x++) {
                Piece p = this.getPieceAt(x, y);
                if(p == null){
                    res.append("#  ");
                }
                else{
                    Boolean val = p.absoluteOcupation(x,y);
                    if(val != null && val){
                        res.append(p.getCouleur()+"  ");
                    }
                    else{
                        res.append("#  ");
                    }
                    
                }
            }
            res.append("\n");
        }
        return res.toString();
    }
    /**
     * Renvoie la pièce qui occupe la position spécifiée sur le tableau.
     *
     * @param i La coordonnée en x.
     * @param j La coordonnée en y.
     * @return La pièce qui occupe la position spécifiée, ou null si aucune pièce n'occupe cette position.
     *
     * @pre Aucune pièce ne doit être null.
     * @pre Les coordonnées (i, j) doivent être valides dans le tableau.
     * @post Aucune modification n'est apportée à la position ou à la configuration des pièces.
     * @post La méthode renvoie la pièce qui occupe la position spécifiée ou null si aucune pièce n'occupe cette position.
     */
    public Piece getPieceAt(int i, int j) {
        for (Piece piece : this.listePiece) {
            Boolean tmp = piece.absoluteOcupation(i, j);
            if(tmp !=null && tmp){
                return piece;
            }
        }
        return null;
    }
    /**
     * Génère et ajoute aléatoirement un nombre spécifié de pièces au tableau.
     *
     * @param n Le nombre de pièces à générer et ajouter.
     *
     * @pre n doit être un entier positif.
     * @post La taille de la liste des pièces est augmentée de n.
     * @post Chaque pièce générée est placée aléatoirement sur le tableau sans collision avec les autres pièces.
     */
    public void genererPiecesAleatoire(int n){
        String[] couleur = {"A", "B", "C", "D", "E"};
        int temp = 0;
        PieceBuilder build = new PieceBuilder();
        while (this.getListePiece().size() != n) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            int randomNumber = (int) (Math.random() * 4);
            int randomOrientation = (int) (Math.random() * 4) * 90;  // 0, 90, 180, ou 270
            Piece piece = null;
            switch (randomNumber) {
                case 0:
                    piece = new PieceL(x, y, couleur[temp]);
                    piece.setOrientation(randomOrientation);
                    break;
                case 1:
                    piece = new PieceS(x, y, couleur[temp]);
                    piece.setOrientation(randomOrientation);
                    break;
                case 2:
                    piece = new PieceT(x, y, couleur[temp]);
                    piece.setOrientation(randomOrientation);
                    break;
                case 3:
                    piece = build.createRandomPiece(2, 2, couleur[temp]);
                    piece.setPosX(x);
                    piece.setPosY(y);
                    break;
            }
            if(piece != null && this.ajouterPiece(piece)){
                temp+=1;
            };
        }
    }
    /**
     * Renvoie la pièce située aux coordonnées spécifiées sur le tableau.
     *
     * @param x La coordonnée x du point.
     * @param y La coordonnée y du point.
     * @return La pièce située aux coordonnées spécifiées, ou null si aucune pièce n'est présente à cet emplacement.
     *
     * @pre Aucune pièce ne doit être null.
     * @pre Les coordonnées (x, y) doivent être valides dans le tableau.
     * @post Aucune modification n'est apportée à la position ou à la configuration des pièces.
     * @post La méthode renvoie la pièce située aux coordonnées spécifiées ou null si aucune pièce n'est présente à cet emplacement.
     */
    public Piece getPieceAtPoint(int x, int y) {
        for (Piece piece : this.listePiece) {
            int pieceX = piece.getPosX() * 40;
            int pieceY = piece.getPosY() * 40;
            int pieceWidth = piece.getLargeur() * 40;
            int pieceHeight = piece.getHauteur() * 40;
            if (x >= pieceX && x < pieceX + pieceWidth &&
                y >= pieceY && y < pieceY + pieceHeight) {
                return piece;
            }
        }
        return null;
    }
    /**
     * Tente de résoudre le puzzle en déplaçant chaque pièce sur le tableau.
     *
     * @throws RuntimeException Si aucune solution valide n'est trouvée.
     *
     * @pre Aucune pièce ne doit être null.
     * @pre Les positions initiales des pièces sont valides dans le tableau.
     * @post Les positions finales des pièces sont mises à jour après les déplacements.
     * @post Aucune modification n'est apportée aux pièces qui ne peuvent pas être déplacées sans collision.
     * @post Une exception RuntimeException est levée si aucune solution valide n'est trouvée.
     */
    public void resoudre(){
        int x = 0;
        int y = 0;
        for (Piece piece : this.listePiece){
            while(!(this.deplacerPiece(piece,x,y))){
                if(x>10){
                    x=-1;
                    y=y+1;
                }
                if(x==9 && y ==9){
                    throw new RuntimeException("Pas de solutions valide");
                }
                x=x+1;
            }
        }
    }
}