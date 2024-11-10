package modele;

public interface Piece {
    public int getPosX();
    public int getPosY();
    public int getLargeur();
    public int getHauteur();
    public int getOrientation();
    public boolean[][] getTab();
    public int[] getPosition();
    public String getCouleur();
    public Boolean getValue(int x,int y);
    public void setPosX(int x);
    public void setPosY(int y);
    public void setOrientation(int orientation);
    public void setLargeur(int newLargeur);
    public void setHauteur(int newHauter);
    public void setCouleur(String newColor);
    public Boolean absoluteOcupation(int i, int j);
    public void genererTableau();
}