import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Class that draws the menus for the start screen
 */
public class DrawMenu {

    private JFrame frame;
    private static int randomToken = 1;
    public static int numPlayers = 2;
    private AI ai_algorithm;
    private JLabel container;
    private String clickSoundFilePath;
    private Color player_1_token,player_2_token;

    private boolean soundPlaying = true;

    private BackgroundSound background = new BackgroundSound();


    // x : number of pixels from left of the screen
    // y : number of pixels from top of the screen
    // width & height are the size of the frame

    /**
     * constructor
     */
    public DrawMenu(){}

    /**
     * constructor with layout details
     * @param ld layout details
     */
    public DrawMenu(LayoutDetails ld){
        clickSoundFilePath = "/res/sounds/mixkit-unlock-game-notification-253.wav";
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

        background.play();

    }

    //private void createButtons(JFrame f) {

    /**
     * creates each button needed
     * @param container container to draw on
     * @param ld layout details
     */
    private void createButtons(JLabel container, LayoutDetails ld) {
        // button size of menu
        Dimension d = new Dimension(130,30);
        int[][] colors = {{250,0,0},{250,90,0},{250,175,0},{250,250,0},{175,250,0},{0,250,0},{0,250,150},{0,250,250},{0,150,250},
                {0,0,250},{150,0,250},{250,0,250},{250,0,150},{200,200,200},{0,0,0}};
        JButton selectToken = new JButton("Select Token");
        selectToken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound(clickSoundFilePath, soundPlaying);
                displayPlayerToken(colors,ld);
                container.repaint();
                frame.pack();
            }
        });

        JButton pvp = new JButton("Player vs Player");
        pvp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound(clickSoundFilePath, soundPlaying);
                createGame(ld, false, ai_algorithm);
            }

        });

        // this button sets up the AI level
        JButton pvai = new JButton("Player vs AI");
        pvai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound(clickSoundFilePath, soundPlaying);

                // remove buttons & blocks
                container.removeAll();

                // set up AI buttons
                // easy
                JButton AI_easy_button = new JButton("EASY");
                AI_easy_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clickSound(clickSoundFilePath, soundPlaying);
                        ai_algorithm = new AI_easy();
                        createGame(ld, true, ai_algorithm);
                    }
                });

                // hard
                JButton AI_hard_button = new JButton("HARD");
                AI_hard_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clickSound(clickSoundFilePath, soundPlaying);
                        ai_algorithm = new AI_hard();
                        createGame(ld, true, ai_algorithm);
                    }
                });

                // return to menu button
                JButton backbt = new JButton("BACK");
                backbt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clickSound(clickSoundFilePath, soundPlaying);
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
                container.add(Box.createRigidArea(new Dimension(0,10)));
                container.add(AI_hard_button);
                container.add(Box.createRigidArea(new Dimension(0,10)));
                container.add(backbt);
                System.out.println("easy button added");
                container.repaint();
                System.out.println("label repainted");
                frame.pack();

            }
        });
        //
        JButton sound = new JButton("Mute/Unmute");
        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mute sound
                toggleSound();

            }
        });

        // exit button
        JButton exit = new JButton("EXIT");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound("/res/sounds/mixkit-retro-arcade-casino-notification-211.wav", soundPlaying);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        // center buttons
        selectToken.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvp.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvai.setAlignmentX(Component.CENTER_ALIGNMENT);
        sound.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectToken.setMaximumSize(d);
        pvp.setMaximumSize(d);
        pvai.setMaximumSize(d);
        sound.setMaximumSize(d);
        exit.setMaximumSize(d);

        // space between buttons
        container.add(Box.createRigidArea(new Dimension(0,150)));
        // space between buttons
        container.add(selectToken);
        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(pvp);
        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(pvai);
        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(sound);
        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(exit);

    }

    /**
     * creates the game screen
     * @param ld layout details
     * @param hasAI boolean for AI
     * @param algorithm type of AI
     */
    private void createGame(LayoutDetails ld, Boolean hasAI, AI algorithm){
        ld.setX(frame.getLocation().x);
        ld.setY(frame.getLocation().y);
        Token[] tokens = new Token[numPlayers];
        Player[] players = new Player[numPlayers];
        if(player_1_token == null && player_2_token == null){
            for(int i = 0; i < players.length; i++){
                tokens[i] = new Token(randomToken);
                players[i] = new Player(tokens[i].getColorToken());
                System.out.println("Player[" + (i+1) + "] has been created.");
            }
            System.out.println("plays have no tokens.");
            new DrawGrid(players, ld, hasAI, this, algorithm);
            frame.setVisible(false);
        }else{
            System.out.println("play with selected token");
            // assign selected token to player
            Token random = new Token(randomToken);
            if(player_1_token != null && player_2_token == null){
                System.out.println("player 1 has token, player 2 has no token");
                players[0] = new Player(player_1_token);
                players[1] = new Player(random.genToken());
            }else if(player_1_token == null && player_2_token != null){
                System.out.println("player 1 has no token, player 1 has token");
                players[0] = new Player(random.genToken());
                players[1] = new Player(player_2_token);
            }else{
                System.out.println("player 1 has token, player 2 has token");
                players[0] = new Player(player_1_token);
                players[1] = new Player(player_2_token);
            }

            // enter game
            new DrawGrid(players, ld, hasAI, this, algorithm);
            frame.setVisible(false);
        }

        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

    }

    // move frame to a new location

    /**
     * refreshing the game screen
     * @param ld
     */
    public void refreshFrame(LayoutDetails ld){
        frame.setLocation(ld.getX(),ld.getY());
        frame.setVisible(true);
        // fixing the bug the menu shows AI level buttons instead of PVP PVAI
        // remove buttons & block
        container.removeAll();
        // regenerate menu buttons
        createButtons(container,ld);
        container.repaint();
        frame.pack();
    }


    /**
     * closes the window
     */
    public void closeMenu(){frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));}

    /**
     * plays the sound effects
     * @param path URL path name
     * @param mute boolean for mute
     */
    private void clickSound(String path, boolean mute){
        if(!mute)
        {
            return;
        }

        try{
            SoundEffect se = new SoundEffect();
            se.playBackGround(path);
            Thread t1 = new Thread(se);
            t1.start();
        }catch (Exception ae)
        {
            System.out.println(ae.getMessage());
        }
    }

    /**
     * play/pause the BGM
     */
    private void toggleSound(){
        soundPlaying = !soundPlaying;
        if(soundPlaying){
            background.resume();
        }
        else{
            background.pause();
        }
    }

    /**
     * Shows the token changing screen to change token colors
     * @param colors color of the token
     * @param ld layout details
     */
    private void displayPlayerToken(int[][] colors, LayoutDetails ld){
        // remove buttons
        container.removeAll();
        // add two player buttons & token next to it
        JPanel player_1_container = new JPanel();
        // transparent background
        player_1_container.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        // flow layout with 0 gap
        player_1_container.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        player_1_container.setMaximumSize(new Dimension(130,30));

        JButton player_1_bt = new JButton("PLAYER 1");
        player_1_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound(clickSoundFilePath, soundPlaying);
                container.removeAll();
                // set up available tokens
                displayTokens(1, colors,ld);
            }
        });
        player_1_container.add(player_1_bt);

        JButton player1_tokenIndicator = new RoundButton(null,new Color(0.0f,0.0f,0.0f,0.0f));
        player1_tokenIndicator.setMaximumSize(new Dimension(20,20));
        player1_tokenIndicator.setPreferredSize(new Dimension(20,20));
        if(player_1_token == null){
            // not showing anything if token is not selected
            player1_tokenIndicator.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        }else{
            player1_tokenIndicator.setBackground(player_1_token);
        }
        player_1_container.add(player1_tokenIndicator);


        JPanel player_2_container = new JPanel();
        // transparent background
        player_2_container.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        // flowlayout with 0 gap
        player_2_container.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        player_2_container.setMaximumSize(new Dimension(130,30));

        JButton player_2_bt = new JButton("PLAYER 2");
        //player_2_bt.setMaximumSize(new Dimension(130,30));
        player_2_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound(clickSoundFilePath, soundPlaying);
                container.removeAll();
                // set up available tokens
                displayTokens(2, colors,ld);
            }
        });
        player_2_container.add(player_2_bt);

        JButton player2_tokenIndicator = new RoundButton(null,new Color(0.0f,0.0f,0.0f,0.0f));
        player2_tokenIndicator.setMaximumSize(new Dimension(20,20));
        player2_tokenIndicator.setPreferredSize(new Dimension(20,20));
        if(player_2_token == null){
            // not showing anything if token is not selected
            player2_tokenIndicator.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        }else{
            player2_tokenIndicator.setBackground(player_2_token);
        }
        player_2_container.add(player2_tokenIndicator);




        JButton resetToken = new JButton("RESET");
        resetToken.setLocation(90,0);
        resetToken.setMaximumSize(new Dimension(115,25));
        resetToken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound(clickSoundFilePath, soundPlaying);
                player_1_token = null;
                player_2_token = null;
                // refresh
                displayPlayerToken(colors,ld);
                container.repaint();
                frame.pack();
            }
        });

        JButton backToMenu = new JButton("BACK");
        backToMenu.setMaximumSize(new Dimension(115,25));
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound(clickSoundFilePath, soundPlaying);
                container.removeAll();
                createButtons(container,ld);
                container.repaint();
                frame.pack();
            }
        });


        player_1_container.setAlignmentX(Component.CENTER_ALIGNMENT);
        player_2_container.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetToken.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(0,200)));
        // container.add(player_1_bt);
        container.add(player_1_container);
        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(player_2_container);
        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(resetToken);
        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(backToMenu);

    }

    /**
     * displays the token on screen
     * @param indexOfPlayer player number
     * @param colors color
     * @param ld layout details
     */
    private void displayTokens(int indexOfPlayer, int[][] colors, LayoutDetails ld){

        Dimension tokenD = new Dimension(10,10);
        Token temp = new Token();
        // remove contents
        container.removeAll();
        // change layout
        JPanel innerContainer = new JPanel();
        innerContainer.setMaximumSize(new Dimension(200,120));
        innerContainer.setPreferredSize(new Dimension(100,100));
        innerContainer.setLayout(new GridLayout(3,5));
        // make transparent background
        innerContainer.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        // add tokens
        for(int i = 0; i< colors.length; i++){

            JButton[] tokens = new JButton[colors.length];
            // create token
            temp.setToken(colors[i]);
            tokens[i] = new RoundButton(null,temp.getColorToken());
            tokens[i].setMaximumSize(tokenD);
            innerContainer.add(tokens[i]);
            tokens[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // bug that default repaint() cause unwanted button background color
                    // when click, assign token to player and back to menu(need an indicator that shows token)
                    // find index of the clicked button, set up user token
                    clickSound(clickSoundFilePath, soundPlaying);
                    for(int j = 0; j < colors.length; j++){
                        if(e.getSource() == tokens[j]){
                            if(indexOfPlayer == 1){
                                player_1_token = new Color(colors[j][0],colors[j][1], colors[j][2]);
                            }else if(indexOfPlayer == 2){
                                player_2_token = new Color(colors[j][0],colors[j][1], colors[j][2]);
                            }

                        }
                    }
                    // remove buttons & set up menu
                    container.removeAll();
                    // back to player token
                    displayPlayerToken(colors, ld);
                    container.repaint();
                    frame.pack();
                }
            });
        }
        // refresh label after setting up tokens
        container.add(Box.createRigidArea(new Dimension(0,150)));
        container.add(innerContainer);
        container.repaint();
        frame.pack();

    }

    /**
     * getter for mute
     * @return a boolean
     */
    boolean getIsMute()
    {
        return soundPlaying;
    }
}
