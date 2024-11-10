package modele;

public abstract class AbstractPiece implements Piece{
    protected int posX;
    protected int posY;
    protected int hauteur;
    protected int largeur;
    protected int orientation;
    protected boolean[][] tab;
    protected String couleur;

    public AbstractPiece(int posX, int posY, String couleur) {
        this.posX = posX;
        this.posY = posY;
        this.orientation = 0;
        this.couleur = couleur;
        this.tab = basePiece();
        this.hauteur = tab.length;
        this.largeur = tab[0].length;
    }

    public AbstractPiece(int posX, int posY){
        this(posX,posY,"X");
    }
    /**
     * Génère un nouveau tableau en fonction de l'orientation actuelle de la pièce.
     *
     * @throws IllegalArgumentException Si l'orientation de la pièce est incorrecte.
     *
     * @pre L'orientation de la pièce doit être 90, 180 ou 270.
     * @post Un nouveau tableau est généré en fonction de l'orientation actuelle de la pièce.
     * @post Les dimensions du tableau sont mises à jour en fonction de la nouvelle orientation.
     * @post Aucune modification n'est apportée à l'orientation initiale de la pièce.
     * @post Si l'orientation est incorrecte, une exception IllegalArgumentException est levée.
     */
    public void genererTableau() { 
        if (this.orientation==90){
            boolean[][] newTab = new boolean[getLargeur()][getHauteur()];
            int tabY = 0; int tabX = 0;
            for(int y = 0; getLargeur() > y; y++){
                tabX = 0;
                for(int x = getHauteur()-1; x >= 0; x--){ //90
                    newTab[tabY][tabX] = tab[x][y];
                    tabX += 1;
                }
                tabY += 1;
            }
            int tmp = getHauteur();
            setHauteur(getLargeur());
            setLargeur(tmp);
            tab = newTab;
        } 
        else if (this.orientation==180){
            boolean[][] newTab = new boolean[getHauteur()][getLargeur()];
            int tabY = 0; int tabX = 0;
            for(int x = tab.length-1; x >= 0; x--){ //180
                tabX = 0;
                for(int y = tab[0].length-1; y >= 0; y--){
                    newTab[tabY][tabX] = tab[x][y];
                    tabX += 1;
                }
                tabY += 1;
            }
            tab = newTab;
        } 
        else if(this.orientation==270){ 
            boolean[][] newTab = new boolean[getLargeur()][getHauteur()];
            int tabY = 0; int tabX = 0;
            for(int y = tab[0].length-1; y >= 0; y--){
                tabX = 0;
                for(int x = 0; x < tab.length; x++){ //270
                    newTab[tabY][tabX] = tab[x][y];
                    tabX += 1;
                }
                tabY += 1;
            }
            int tmp = getHauteur();
            setHauteur(getLargeur());
            setLargeur(tmp);
            tab = newTab;
        }
        else if (this.orientation!=0){
            throw new IllegalArgumentException("L'orientation de la piece est incorrecte ! Elle est de "+getOrientation());
        }
        orientation = 0;
    }

    public abstract boolean[][] basePiece();

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    @Override
    public int getHauteur(){
        return this.hauteur;
    }

    public void setHauteur(int newHauteur){
        this.hauteur = newHauteur;
    }

    @Override
    public int getLargeur(){
        return this.largeur;
    }

    @Override
    public void setLargeur(int newLargeur){
        this.largeur = newLargeur;
    }

    @Override
    public int getOrientation(){
        return this.orientation;
    }

    @Override
    public boolean[][] getTab(){
        return this.tab;
    }

    @Override
    public int[] getPosition(){
        int[] res = {posX,posY};
        return res;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    @Override
    public void setOrientation(int newOrientation){
        while(newOrientation < 0){
            newOrientation+=360;
        }
        this.orientation = newOrientation;
        genererTableau();
    }

    @Override
    public Boolean absoluteOcupation(int x, int y){
        int[] pos = getPosition();
        int relativeX = x-pos[0];
        int relativeY = y-pos[1];
        return getValue(relativeX,relativeY);
        
    }

    public Boolean getValue(int x,int y){
        try{
            boolean val = this.tab[y][x];
            return val;
        }
        catch(Exception e){
            return null;
        }
    }

    public String getCouleur(){
        return this.couleur;
    }

    public void setCouleur(String color){
        this.couleur = color;
    }

    public String toString(){
        String str = "";
        for (boolean[] ligne : this.tab) {
            for(boolean value : ligne){
                if(value){
                    str += " V ";
                }
                else{
                    str += " X ";
                }
            }
            str += "\n";
        }
        return str;
    } 
}