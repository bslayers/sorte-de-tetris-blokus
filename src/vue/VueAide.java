package vue;

import java.awt.*;
import javax.swing.*;

import controleur.Event;

public class VueAide extends JFrame {

    public VueAide(){

        /*-----------------INITIALISATION-----------------*/

        JLabel label_aide = new JLabel("<html><body><H2>Règles</H2>Le but est d'assembler des formes de sorte qu'elles occupent le moins de place possible, 3 niveaux de difficulté sont disponible (facile, moyen et difficile). À chaque partie, il est possible d'obtenir une nouvelle configuration de départ. Lorsque vous pensez avoir terminé, vous devez cliquer sur le bouton Terminer et votre score sera calculé, plus vous êtes proche de 0, meilleur est votre score. Le score est calculé en fonction de la superficie du rectangle englobant les pièces. Pour déplacer une pièce il faut rester cliqué dessus puis relâcher à l'emplacement souhaité et pour la faire tourner il suffit de cliquer dessus.</H2></body></html>");
        JPanel panel_aide = new JPanel();
        panel_aide.setLayout(new BoxLayout(panel_aide, BoxLayout.Y_AXIS));
        panel_aide.add(label_aide);
        JPanel panel_bouton = new JPanel();
        JButton bouton_fermer = new JButton("Fermer");
        panel_bouton.add(bouton_fermer);
        JPanel marge = new JPanel();
        marge.setPreferredSize(new Dimension(8,0));

        /*-----------------EVENEMENTS-----------------*/

        Event event = new Event(null, null);
        bouton_fermer.addActionListener(event);

        /*-----------------FENETRE-----------------*/

        this.add(panel_aide);
        this.add(marge, BorderLayout.WEST);
        this.add(panel_bouton,BorderLayout.SOUTH);
        this.setTitle("Aide");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null); 
        this.setVisible(true);
    }
}