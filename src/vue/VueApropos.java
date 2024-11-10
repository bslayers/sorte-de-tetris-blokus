package vue;

import java.awt.*;
import javax.swing.*;

import controleur.Event;

public class VueApropos extends JFrame {

    public VueApropos(){

        /*-----------------INITIALISATION-----------------*/

        JLabel label_apropos = new JLabel("<html><body><H1>Jeu</H1>Version : 0.0.1<br><br>Contributeurs :<ul><li>? ?</li><li>? ?</li><li>? ?</li><li>? ?</li></ul></body></html>");
        JPanel panel_apropos = new JPanel();
        panel_apropos.setLayout(new BoxLayout(panel_apropos, BoxLayout.Y_AXIS));
        panel_apropos.add(label_apropos);
        JPanel panel_bouton = new JPanel();
        JButton bouton_fermer = new JButton("Fermer");
        panel_bouton.add(bouton_fermer);
        JPanel marge = new JPanel();
        marge.setPreferredSize(new Dimension(8,0));

        /*-----------------EVENEMENTS-----------------*/

        Event event = new Event(null, null);
        bouton_fermer.addActionListener(event);

        /*-----------------FENETRE-----------------*/

        this.add(panel_apropos);
        this.add(marge,BorderLayout.WEST);
        this.add(panel_bouton,BorderLayout.SOUTH);
        this.setTitle("À propos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,275);
        this.setResizable(false);
        this.setLocationRelativeTo(null); 
        this.setVisible(true);
    }
}