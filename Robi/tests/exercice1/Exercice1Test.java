package exercice1;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import graphicLayer.GRect;
import graphicLayer.GSpace;

/**
 * Tests unitaires pour l'Exercice 1.
 * Vérifie la configuration et les capacités de l'élément Robi.
 * * @author Kevin
 */
class Exercice1Test {
    private GRect robi;
    private GSpace space;

    @BeforeEach
    void setUp() {
        // Configuration initiale selon l'énoncé (200x150)
        space = new GSpace("Test Space", new Dimension(200, 150));
        robi = new GRect();
        robi.setDimension(new Dimension(40, 20));
        robi.setColor(Color.BLUE);
        space.addElement(robi);
    }

    /**
     * Accès à l'attribut protégé 'color' de GElement par réflexion.
     * Nécessaire car la couche graphique ne fournit pas de getter public.
     */
    private Color getColor(graphicLayer.GElement element) {
        try {
            Field field = graphicLayer.GElement.class.getDeclaredField("color");
            field.setAccessible(true);
            return (Color) field.get(element);
        } catch (Exception e) {
            fail("Erreur réflexion couleur : " + e.getMessage());
            return null;
        }
    }

    @Test
    void testSpaceDimensions() {
        assertEquals(200, space.getPreferredSize().width);
        assertEquals(150, space.getPreferredSize().height);
    }

    @Test
    void testRobiDimensions() {
        assertEquals(40, robi.getWidth());
        assertEquals(20, robi.getHeight());
    }

    @Test
    void testInitialColor() {
        assertEquals(Color.BLUE, getColor(robi));
    }

    // Vérifie que la méthode setPosition déplace réellement l'objet
    @Test
    void testPositionUpdate() {
        Point p = new Point(50, 30);
        robi.setPosition(p);
        assertEquals(p, robi.getPosition());
    }

    // Vérifie que le changement de couleur est effectif dans le modèle
    @Test
    void testColorModification() {
        robi.setColor(Color.GREEN);
        assertEquals(Color.GREEN, getColor(robi));
    }
}