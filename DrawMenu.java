import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class DrawMenu{

    private JFrame frame;
    private static int playerChooseColorToken = 1;
    public static int numPlayers = 2;

    // x : number of pixels from left of the screen
    // y : number of pixels from top of the screen
    // width & height are the size of the frame
    public DrawMenu(LayoutDetails ld){
        frame = new JFrame("CONNECT 4");
        //  close button of frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // set location and size of frame
        frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(),ld.getHeight());
        // set fixed size of frame
        frame.setResizable(false);
        frame.setPreferredSize(frame.getSize());


        // set background
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/res/images/backgroundImage.jpg")));
        background.setSize(ld.getWidth(),ld.getHeight());
        background.setPreferredSize(background.getSize());
        background.setLayout(new BoxLayout(background,BoxLayout.Y_AXIS));


        createButtons(background,ld);

        frame.add(background);
        frame.pack();
        frame.setVisible(true);
    }

    //private void createButtons(JFrame f) {
    private void createButtons(JLabel label, LayoutDetails ld) {
        JButton st = new JButton("Select Token");
        //st.setSize(200,40);
        //st.setPreferredSize(new Dimension(200,10));
        st.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    SoundEffect se = new SoundEffect();
                    se.playBackGround("/res/sounds/mixkit-unlock-game-notification-253.wav");
                    Thread t1 = new Thread(se);
                    t1.start();
                    t1.start();
                }catch (Exception ae)
                {
                    System.out.println(ae.getMessage());
                }
            }
        });

        JButton pvp = new JButton("Player vs Player");
        pvp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    SoundEffect se = new SoundEffect();
                    se.playBackGround("/res/sounds/mixkit-unlock-game-notification-253.wav");
                    Thread t1 = new Thread(se);
                    t1.start();
                }catch (Exception ae)
                {
                    System.out.println(ae.getMessage());
                }
                createGame(ld, false);

            }

        });

        JButton pvai = new JButton("Player vs AI");
        pvai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    SoundEffect se = new SoundEffect();
                    se.playBackGround("/res/sounds/mixkit-unlock-game-notification-253.wav");
                    Thread t1 = new Thread(se);
                    t1.start();
                }catch (Exception ae)
                {
                    System.out.println(ae.getMessage());
                }
                createGame(ld, true);
            }
        });

        // exit button
        JButton exit = new JButton("EXIT");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    SoundEffect se = new SoundEffect();//SE for exit
                    se.playBackGround("/res/sounds/mixkit-retro-arcade-casino-notification-211.wav");
                    Thread t1 = new Thread(se);
                    t1.start();
                }catch (Exception ae)
                {
                    System.out.println(ae.getMessage());
                }
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        // center buttons
        st.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvp.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvai.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);


        // space between buttons
        label.add(Box.createRigidArea(new Dimension(0,200)));
        label.add(st);
        // space between buttons
        label.add(Box.createRigidArea(new Dimension(0,15)));
        label.add(pvp);
        label.add(Box.createRigidArea(new Dimension(0,15)));
        label.add(pvai);
        label.add(Box.createRigidArea(new Dimension(0,15)));
        label.add(exit);


    }
    private void createGame(LayoutDetails ld, Boolean hasAI){


        ld.setX(frame.getLocation().x);
        ld.setY(frame.getLocation().y);
        // get number of players   ~  should get from user
        // Token class will implement custom token(need more work)
        Token[] tokens = new Token[numPlayers];
        Player[] players = new Player[numPlayers];

        for(int i = 0; i < players.length; i++){
            tokens[i] = new Token(playerChooseColorToken);
            players[i] = new Player(tokens[i].getColorToken());
            System.out.println("Player[" + (i+1) + "] has been created.");
        }
        System.out.println("Players has been created.");

        new DrawGrid(players, ld, hasAI, this);
        frame.setVisible(false);
        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

    }

    // move frame to a new location
    public void refreshFrame(LayoutDetails ld){
       // frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(),ld.getHeight());
        frame.setLocation(ld.getX(),ld.getY());
        frame.setVisible(true);
    }


    public void closeMenu(){frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));}
}
