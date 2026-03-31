package exercice2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import stree.parser.SParser;
import tools.Tools;

/**
 * Exercice 2 — Interpréteur de scripts S-expression.
 *
 * 2.1 : (space setColor black) (robi setColor yellow)
 * 2.2 : translate + sleep
 */
public class Exercice2 {

    GSpace space = new GSpace("Exercice 2", new Dimension(200, 100));
    GRect  robi  = new GRect();

    // Script 2.2 : animation complète
    String script =
        "(space setColor white)"  +
        "(robi  setColor red)"    +
        "(robi  translate 10 0)"  +
        "(space sleep 100)"       +
        "(robi  translate 0 10)"  +
        "(space sleep 100)"       +
        "(robi  translate -10 0)" +
        "(space sleep 100)"       +
        "(robi  translate 0 -10)";

    public Exercice2() {
        space.addElement(robi);
        space.open();
        runScript();
    }

    // ---------------------------------------------------------------
    // Parsing + exécution
    // ---------------------------------------------------------------
    private void runScript() {
        SParser<SNode> parser = new SParser<>();
        List<SNode> rootNodes = null;
        try {
            rootNodes = parser.parse(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<SNode> itor = rootNodes.iterator();
        while (itor.hasNext()) {
            run(itor.next());
        }
    }

    /**
     * Interprète une S-expression.
     * Structure attendue : (cible commande arg1 arg2 ...)
     */
    private void run(SNode expr) {
        String target = expr.get(0).contents(); // "space" ou "robi"
        String cmd    = expr.get(1).contents(); // "setColor", "translate", "sleep"

        switch (target) {
            case "space" -> {
                switch (cmd) {
                    case "setColor", "color" -> {
                        Color c = Tools.getColorByName(expr.get(2).contents());
                        space.setColor(c);
                    }
                    case "sleep" -> {
                        int ms = Integer.parseInt(expr.get(2).contents());
                        Tools.sleep(ms);
                    }
                    default -> throw new Error("Commande space inconnue : " + cmd);
                }
            }
            case "robi" -> {
                switch (cmd) {
                    case "setColor", "color" -> {
                        Color c = Tools.getColorByName(expr.get(2).contents());
                        robi.setColor(c);
                    }
                    case "translate" -> {
                        int dx = Integer.parseInt(expr.get(2).contents());
                        int dy = Integer.parseInt(expr.get(3).contents());
                        robi.translate(new Point(dx, dy));
                    }
                    default -> throw new Error("Commande robi inconnue : " + cmd);
                }
            }
            default -> throw new Error("Cible inconnue : " + target);
        }
    }

    public static void main(String[] args) {
        new Exercice2();
    }
}
