package vue;

import java.awt.*;
import javax.swing.*; // Bibliotheque graphique
import modele.PlateauPuzzle;
import controleur.Event;

public class GUI extends JFrame{ // JFrame est une classe de Swing

    /*Début de l'initialisation des différents variables*/
    protected VueJeu vueJeu;
    protected int hauteur;
    protected int largeur;
    protected JButton bouton_terminer;
    protected JButton bouton_configuration;
    protected JButton bouton_resoudre;
    protected JComboBox<String> difficulte;
    protected JLabel label_score;
    protected PlateauPuzzle plateau;
    protected int score;

    public GUI(PlateauPuzzle plateau) {

        /*-----------------INITIALISATION-----------------*/
        
        this.vueJeu = new VueJeu(plateau);
        this.hauteur = vueJeu.getPreferredSize().height;
        this.largeur = vueJeu.getPreferredSize().width + 180;
        this.plateau = plateau;

        // Initialise le menu du haut
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem itemAide = new JMenuItem("Aide");
        JMenuItem itemPropos = new JMenuItem("À propos");
        menu.add(itemAide);
        menu.addSeparator();
        menu.add(itemPropos);
        menubar.add(menu);

        // Initialise le label score
        JPanel panel_score = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.score = 0;
        this.label_score = new JLabel(" Score : " + this.score);
        this.label_score.setFont(new Font("Helvetica", Font.BOLD, 20));
        panel_score.add(this.label_score);

        // Initialise et ajoute les boutons et la JComboBox
        JPanel panel_bouton = new JPanel(new FlowLayout());
        JLabel label_difficulte = new JLabel("Difficulté : ");
        String[] niveaux = {"Facile", "Moyen", "Difficile"};
        this.difficulte = new JComboBox<>(niveaux);
        this.bouton_terminer = new JButton("Terminer");
        this.bouton_configuration = new JButton("Nouvelle configuration");
        this.bouton_resoudre = new JButton("Résoudre");
        JButton bouton_quitter = new JButton("QUITTER");
        panel_bouton.add(label_difficulte);
        panel_bouton.add(this.difficulte);
        panel_bouton.add(this.bouton_terminer);
        panel_bouton.add(this.bouton_configuration);
        panel_bouton.add(this.bouton_resoudre);
        panel_bouton.add(bouton_quitter);

        // Crée le panel de gauche
        JPanel panel_gauche = new JPanel();
        panel_gauche.setLayout(new BoxLayout(panel_gauche, BoxLayout.Y_AXIS));
        panel_gauche.setPreferredSize(new Dimension(180,this.hauteur));
        panel_gauche.add(panel_score);
        panel_gauche.add(panel_bouton);

        /*-----------------EVENEMENTS-----------------*/

        Event event = new Event(this, this.vueJeu);
        this.bouton_terminer.addActionListener(event);
        this.bouton_configuration.addActionListener(event);
        this.bouton_resoudre.addActionListener(event);
        bouton_quitter.addActionListener(event);
        this.difficulte.addActionListener(event);
        this.vueJeu.addMouseListener(event);
        panel_gauche.addMouseListener(event);
        itemAide.addActionListener(event);
        itemPropos.addActionListener(event);

        /*-----------------FENETRE-----------------*/

        this.add(this.vueJeu, BorderLayout.CENTER);
        this.add(panel_gauche,BorderLayout.WEST);
        this.setJMenuBar(menubar);
        this.setTitle("Jeu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.largeur, this.hauteur);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
    * Retourne le plateau.
    * @return la plateau
    */
    public PlateauPuzzle getPlateau(){
        return this.plateau;
    }

    /**
    * Retourne le label qui contient le score.
    * @return le label score
    */
    public JLabel getLabelScore(){
        return this.label_score;
    }

    public JButton getBoutonTerminer(){
        return this.bouton_terminer;
    }

    public JButton getBoutonResoudre(){
        return this.bouton_resoudre;
    }
}