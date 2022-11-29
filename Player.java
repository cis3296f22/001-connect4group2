//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;

/**
 * Player class to define player color
 */
public class Player {
    private Color token;

    /**
     * constructor default
     */
    public Player() {
        this.token = Color.WHITE;
    }

    /**
     * constructor with color
     * @param t
     */
    public Player(Color t) {
        this.token = t;
    }

    public Color getToken() {
        return this.token;
    }
}
