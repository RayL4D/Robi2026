package exercice1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import graphicLayer.GRect;
import graphicLayer.GSpace;

/**
 * Exercice 1 : Prise en main de la couche graphique.
 * Animation d'un rectangle bleu (Robi) parcourant les bords intérieurs du Space.
 * * @author Kevin SIDER
 * @version 1.1
 */
public class Exercice1_0 {
    private GSpace space = new GSpace("Exercice 1", new Dimension(200, 150));
    private GRect robi = new GRect();

    public Exercice1_0() {
        robi.setDimension(new Dimension(40, 20));
        robi.setColor(Color.BLUE);
        space.addElement(robi);
        space.open();
        
        this.runAnimation();
    }

    /**
     * Programme l'animation de robi suivant les bords intérieurs.
     * Le déplacement s'adapte automatiquement au redimensionnement du space.
     */
    private void runAnimation() {
        int x = 0;
        int y = 0;

        while (true) {
            // 1. Déplacement jusqu'au bord droit (s'adapte à la largeur du space)
            while (x < space.getWidth() - robi.getWidth()) {
                x++;
                this.step(x, y);
            }

            // 2. Déplacement jusqu'au bord bas (s'adapte à la hauteur du space)
            while (y < space.getHeight() - robi.getHeight()) {
                y++;
                this.step(x, y);
            }

            // 3. Déplacement jusqu'au bord gauche
            while (x > 0) {
                x--;
                this.step(x, y);
            }

            // 4. Déplacement jusqu'au bord haut
            while (y > 0) {
                y--;
                this.step(x, y);
            }

            // 5. Changement de couleur aléatoire à la fin du cycle
            robi.setColor(new Color((int) (Math.random() * 0x1000000)));
        }
    }

    /**
     * Met à jour la position et marque un temps d'arrêt pour la fluidité.
     * @param x coordonnée horizontale
     * @param y coordonnée verticale
     */
    private void step(int x, int y) {
        robi.setPosition(new Point(x, y));
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        new Exercice1_0();
    }
}