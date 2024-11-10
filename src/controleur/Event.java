package controleur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; // Bibliotheque graphique

import modele.Piece;
import vue.GUI;
import vue.VueAide;
import vue.VueApropos;
import vue.VueJeu;

public class Event implements ActionListener, MouseListener {
    private GUI d;
    private VueJeu vj;
    private int nbPieces;
    private Piece pieceEnDeplacement;
    private Point lastPressed;

    public Event(GUI d, VueJeu vj){
        this.d = d;
        this.vj = vj;
        this.nbPieces = 3;
        this.lastPressed = null;
    }

    /**
    * Définit un comportement à faire lorsque utilisateur effectue une action
    * @param e représente un événement généré lorsqu'un utilisateur interagit avec un composant graphique
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source instanceof JMenuItem){
            JMenuItem itemClique = (JMenuItem) source;
            if (itemClique.getText().equals("À propos")) {
                new VueApropos();
            }
            else if(itemClique.getText().equals("Aide")){
                new VueAide();
            }
        }
        else if(source instanceof JButton){
            JButton boutonClique = (JButton) source;
            if (boutonClique.getText().equals("Terminer")) {
                d.getLabelScore().setText(" Score : " + d.getPlateau().calculerScore());
            }
            else if (boutonClique.getText().equals("Nouvelle configuration")) {
                d.getBoutonTerminer().setEnabled(true);
                d.getBoutonResoudre().setEnabled(true);
                d.getPlateau().getListePiece().clear();
                d.getLabelScore().setText(" Score : " + 0);
                d.getPlateau().genererPiecesAleatoire(nbPieces);
                vj.repaint();
            }
            else if(boutonClique.getText().equals("Résoudre")){
                d.getBoutonTerminer().setEnabled(false);
                d.getBoutonResoudre().setEnabled(false);
                d.getPlateau().resoudre();
                d.getLabelScore().setText(" Score : " + d.getPlateau().calculerScore());
                vj.repaint();
            }
            else if (boutonClique.getText().equals("QUITTER")) {
                int resultat = JOptionPane.showConfirmDialog(this.d, "Êtes-vous sûr de vouloir quitter ?","Confirmer", JOptionPane.YES_NO_OPTION);
                if(resultat == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
            else if (boutonClique.getText().equals("Fermer")){
                Window window = SwingUtilities.getWindowAncestor(boutonClique); // La fenêtre qui contient le bouton cliqué
                window.dispose();
            }
        }
        else if(source instanceof JComboBox<?>){
            JComboBox<?> comboBox = (JComboBox<?>) source;
            String selectedDifficulty = (String) comboBox.getSelectedItem();
            switch (selectedDifficulty) {
                case "Facile":
                    nbPieces = 3;
                    break;
                case "Moyen":
                    nbPieces = 4;
                    break;
                case "Difficile":
                    nbPieces = 5;
                    break;
                default:
                    nbPieces = 3;
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
        Point clickPoint = e.getPoint();
        pieceEnDeplacement = d.getPlateau().getPieceAtPoint(clickPoint.x,clickPoint.y);
        lastPressed = clickPoint;
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if (pieceEnDeplacement != null) {
            Point releasePoint = e.getPoint();
            if(lastPressed.x == releasePoint.x && lastPressed.y == releasePoint.y){
                int[] pos = pieceEnDeplacement.getPosition();
                d.getPlateau().deplacerPiece(pieceEnDeplacement, pos[0], pos[1], 90);
            }
            else{
                d.getPlateau().deplacerPiece(pieceEnDeplacement, releasePoint.x/40, releasePoint.y/40, 0);

            }
            vj.repaint();
        }
        pieceEnDeplacement = null;
    }

    /*Déclaration de ses méthodes car on implémente MouseListener*/
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseClicked(MouseEvent e){}
}