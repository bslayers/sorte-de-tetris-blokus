package vue;

import java.util.*;
import modele.*;

public class VueTerminal {
    private PlateauPuzzle plateau;

    public VueTerminal(PlateauPuzzle plateau) {
        this.plateau = plateau;
    }

    /** 
     * Gère le déroulement du jeu dans un environnement de terminal.
     * @param plateau Le plateau de jeu contenant les pièces.
    */
    public void JeuTerminal(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(this.plateau);
            int numeroPiece=this.numeroPiece();
            Piece pieceToMove = this.plateau.getListePiece().get(numeroPiece-1);
            int orientation = this.orientation(pieceToMove.getOrientation());
            int x = this.coordonnee("X");
            int y = this.coordonnee("Y");
            boolean res = this.plateau.deplacerPiece(pieceToMove, x, y, orientation);
            if(res){
                System.out.println("\033[H\033[2J");
                System.out.println(this.plateau);
                System.out.println("La piece a bien été déplacé !");
            }
            else{
                System.out.println("\033[H\033[2J");
                System.out.println(this.plateau);
                System.out.println("Impossible de déplacer la pièce");
            }
            System.out.println("Voulez vous continuer ? (oui/non) : ");
            String arret = scanner.nextLine();
            if(arret.equals("non")){
                System.out.println("\033[H\033[2J");
                System.out.println(this.plateau);
                System.out.println("Votre score est de " + this.plateau.calculerScore());
                break;
            }
            System.out.println("\033[H\033[2J");
        }
        scanner.close();
    }

    public int numeroPiece() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choisissez une pièce parmi celles ci-dessous");
            for (int i = 0; i < this.plateau.getListePiece().size(); i++) {
                System.out.println("Piece " + (i + 1) + " : Couleur = " + this.plateau.getListePiece().get(i).getCouleur());
            }
            System.out.println("Merci de choisir le numéro de la pièce : ");
            try {
                String res = scanner.nextLine();
                int inputNumber = Integer.parseInt(res);
                if (inputNumber > 0 && inputNumber <= this.plateau.getListePiece().size()) {
                    return inputNumber;
                } else {
                    System.out.println("\033[H\033[2J");
                    System.out.println(this.plateau);
                    System.out.println("Numéro de pièce invalide. Veuillez réessayer.");
                }
            } catch (Exception e) {
                System.out.println("\033[H\033[2J");
                System.out.println(this.plateau);
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }
    
    public int coordonnee(String coordonnee) {
        Scanner scanner = new Scanner(System.in);
        int nb = 0;
         while(true){
                System.out.println("Entrez la nouvelle coordonnée " + coordonnee + " de la pièce : ");
                try{
                    String res = scanner.nextLine();
                    nb = Integer.parseInt(res);
                    break;  
                }
                catch(Exception e){}
            }
        return nb;
    }

    public int orientation(int orientation) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous changer son orientation ? (oui/non) : ");
        String changer = scanner.nextLine();
    
        if (changer.equals("oui")) {
            int turn = 0;
            while (true) {
                System.out.println("Choisissez le nombre de rotation vers la droite : ");
                try {
                    String res = scanner.nextLine();
                    turn = Integer.parseInt(res);
    
                    while (turn < 0) {
                        turn += 4;
                    }
                    orientation = (orientation + (turn * 90)) % 360;
                    return orientation;
                } catch (Exception e) {
                    System.out.println("Veuillez entrer un nombre valide.");
                }
            }
        }
        return orientation; 
    }
}