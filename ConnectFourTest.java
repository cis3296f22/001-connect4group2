import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ConnectFourTest {
    ConnectFour c4;
    LayoutDetails ld;
    DrawMenu mu;
    Player[] pl;
    Token[] tokens;
    DrawGrid dg;
    @BeforeEach
    void setUp() {
        c4 = new ConnectFour();
        ld = new LayoutDetails();
        mu = new DrawMenu();
        pl = new Player[2];
        pl[0] = new Player();
        pl[1] = new Player();
        tokens = new Token[2];
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void shouldCallMenu(){
        try {
            c4.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testDefaultLayoutInfo(){
        // default layout size and location
        assertEquals(ld.getX(), 0);
        assertEquals(ld.getY(), 0);
        assertEquals(ld.getWidth(), 600);
        assertEquals(ld.getHeight(), 400);
    }

    @Test
    void shouldMakeMenu(){
        mu = new DrawMenu(ld);
    }

    @Test
    void shouldSetTwoPlayers(){
        assertEquals(pl[0].getToken(), Color.WHITE);
        assertEquals(pl[1].getToken(), Color.WHITE);
    }
    @Test
    void shouldSetTwoTokens(){
        tokens[0] = new Token();
        tokens[1] = new Token();
        assertEquals(tokens[0].getColorToken(), Color.WHITE);
        assertEquals(tokens[1].getColorToken(), Color.WHITE);
    }
    @Test
    void shouldMakeGrid(){
        dg = new DrawGrid(pl,ld,false,mu);
    }
    @Test
    void shouldMakeRoundButton(){
        RoundButton rb = new RoundButton(new ImageIcon(getClass().getResource("/res/images/replay.png")));
    }
    @Test
    void shouldPlayMusic(){
        Sound bgm = new Sound();
        bgm.playBackGround("/res/sounds/mixkit-unlock-game-notification-253.wav");
    }

}