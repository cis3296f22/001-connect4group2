import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class DrawMenu {

    private JFrame frame;
    private static int playerChooseColorToken = 1;
    public static int numPlayers = 2;
    private JButton pvp, pvai, exit, AI_easy_button, AI_hard_button;
    private AI ai_algorithm;
    private JLabel container;

    // x : number of pixels from left of the screen
    // y : number of pixels from top of the screen
    // width & height are the size of the frame
    public DrawMenu(){}
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
        container = new JLabel(new ImageIcon(getClass().getResource("/res/images/backgroundImage.jpg")));
        container.setSize(ld.getWidth(),ld.getHeight());
        container.setMinimumSize(container.getSize());
        container.setMaximumSize(container.getSize());
        container.setPreferredSize(container.getSize());
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        //---------
       // frame.setContentPane(background);

        createButtons(container,ld);

        frame.add(container);
        frame.pack();
        frame.setVisible(true);
    }

    //private void createButtons(JFrame f) {
    private void createButtons(JLabel container, LayoutDetails ld) {
        Dimension d = new Dimension(130,30);

        pvp = new JButton("Player vs Player");
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
                createGame(ld, false, ai_algorithm);

            }

        });

        // this button sets up the AI level
        pvai = new JButton("Player vs AI");
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

                // remove buttons & blocks
                container.removeAll();

                // set up AI buttons
                // easy
                AI_easy_button = new JButton("EASY");
                AI_easy_button.addActionListener(new ActionListener() {
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
                        ai_algorithm = new AI_easy();
                        createGame(ld, true, ai_algorithm);
                    }
                });

                // hard
                AI_hard_button = new JButton("HARD");
                AI_hard_button.addActionListener(new ActionListener() {
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
                        ai_algorithm = new AI_hard();
                        createGame(ld, true, ai_algorithm);
                    }
                });

                // return to menu button
                JButton backbt = new JButton("BACK");
                backbt.addActionListener(new ActionListener() {
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
                        // remove buttons & block
                        container.removeAll();
                        // regenerate menu buttons
                        createButtons(container,ld);
                        container.repaint();
                        frame.pack();
                    }
                });


                // refresh the frame
                AI_easy_button.setAlignmentX(Component.CENTER_ALIGNMENT);
                AI_hard_button.setAlignmentX(Component.CENTER_ALIGNMENT);
                backbt.setAlignmentX(Component.CENTER_ALIGNMENT);
                Dimension di = new Dimension(90,30);
                AI_easy_button.setMaximumSize(di);
                AI_hard_button.setMaximumSize(di);
                backbt.setMaximumSize(di);

                container.add(Box.createRigidArea(new Dimension(0,200)));
                container.add(AI_easy_button);
                container.add(Box.createRigidArea(new Dimension(0,15)));
                container.add(AI_hard_button);
                container.add(Box.createRigidArea(new Dimension(0,15)));
                container.add(backbt);
                System.out.println("easy button added");
                container.repaint();
                System.out.println("label repainted");
                frame.pack();

            }
        });
        // exit button
        exit = new JButton("EXIT");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SoundEffect se = new SoundEffect();
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
        pvp.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvai.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvp.setMaximumSize(d);
        pvai.setMaximumSize(d);
        exit.setMaximumSize(d);

        // space between buttons
        container.add(Box.createRigidArea(new Dimension(0,200)));
        // space between buttons
        container.add(pvp);
        container.add(Box.createRigidArea(new Dimension(0,15)));
        container.add(pvai);
        container.add(Box.createRigidArea(new Dimension(0,15)));
        container.add(exit);

    }
    private void createGame(LayoutDetails ld, Boolean hasAI, AI algorithm){


        ld.setX(frame.getLocation().x);
        ld.setY(frame.getLocation().y);
        Token[] tokens = new Token[numPlayers];
        Player[] players = new Player[numPlayers];

        for(int i = 0; i < players.length; i++){
            tokens[i] = new Token(playerChooseColorToken);
            players[i] = new Player(tokens[i].getColorToken());
            System.out.println("Player[" + (i+1) + "] has been created.");
        }
        System.out.println("Players has been created.");

        new DrawGrid(players, ld, hasAI, this, algorithm);
        frame.setVisible(false);
        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

    }

    // move frame to a new location
    public void refreshFrame(LayoutDetails ld){
        frame.setLocation(ld.getX(),ld.getY());
        frame.setVisible(true);
        // fixing the bug the menu shows AI level instead of PVP PVAI
        // remove buttons & block
        container.removeAll();
        // regenerate menu buttons
        createButtons(container,ld);
        container.repaint();
        frame.pack();
    }


    public void closeMenu(){frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));}
}
