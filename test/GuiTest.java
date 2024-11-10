package test;

import junit.framework.*;
import modele.*;
import modele.PlateauPuzzle;
import vue.GUI;

public class GuiTest extends TestCase{

    public GuiTest(String name) {
        super(name);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new GuiTest("testConstructeur"));
        suite.addTest(new GuiTest("testAjouterPiece"));
        return suite;
    }

    /**
     *  Vérifie que certaines variables sont initialisées correctement
    */
    public void testConstructeur() {
        PlateauPuzzle plateau = new PlateauPuzzle(10);
        plateau.genererPiecesAleatoire(3);
        GUI gui = new GUI(plateau);
        assertNotNull(gui.getPlateau());
        assertNotNull(gui.getLabelScore());
    }

    /**
     *  Vérifie que la fonction AjouterPiece() fonctionne correctement
    */
    public void testAjouterPiece() {
        PlateauPuzzle plateau = new PlateauPuzzle(10);
        PieceL piece = new PieceL(1, 1);
        plateau.ajouterPiece(piece);
        assertTrue(plateau.getListePiece().get(0).equals(piece));
        assertEquals(1, plateau.getListePiece().get(0).getPosX());
        assertEquals(1, plateau.getListePiece().get(0).getPosY());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}