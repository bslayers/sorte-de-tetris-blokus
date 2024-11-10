import modele.*;
import vue.GUI;
import vue.VueTerminal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez vous jouer dans le terminal ou sur l'interface ? (1 ou 2) : ");
        String res = scanner.next();
        PlateauPuzzle plateau = new PlateauPuzzle(10);
        plateau.genererPiecesAleatoire(3);
        if(res.equals("1")){
            VueTerminal vueTerminal = new VueTerminal(plateau);
            vueTerminal.JeuTerminal();
        }
        else{
            new GUI(plateau);
            System.out.println("Jeu en cours...");
        }
        scanner.close();
    }
}