import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class DrawMenu{

    private JFrame frame;
    private int mode;       // 1 => pvp,  2 => player vs ai

    // x : number of pixels from left of the screen
    // y : number of pixels from top of the screen
    // width & height are the size of the frame
    public DrawMenu(LayoutDetails ld, Gate gate){
        mode = 0;

        frame = new JFrame("CONNECT 4");
        //  close button of frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // set location and size of frame
        frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(),ld.getHeight());
        // set fixed size of frame
        frame.setResizable(false);
        frame.setPreferredSize(frame.getSize());


        // set background
        JLabel background = new JLabel(new ImageIcon("images\\backgroundImage.jpg"));
        background.setSize(ld.getWidth(),ld.getHeight());
        background.setPreferredSize(background.getSize());
        background.setLayout(new BoxLayout(background,BoxLayout.Y_AXIS));


        // use this line when not using panel
        //frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        createButtons(background,gate);

        frame.add(background);
        frame.pack();
        frame.setVisible(true);
    }

    //private void createButtons(JFrame f) {
    private void createButtons(JLabel label, Gate gate) {
        JButton st = new JButton("Select Token");
        st.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gate.setValue(false);
                try{
                    System.out.println("here");
                    SoundEffect se = new SoundEffect();
                    se.playBackGround("Sounds\\mixkit-retro-arcade-casino-notification-211.wav");
                    Thread t1 = new Thread(se);
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
                gate.setValue(false);
                mode = 1;
                try{
                    System.out.println("here");
                    SoundEffect se = new SoundEffect();
                    se.playBackGround("Sounds\\mixkit-retro-arcade-casino-notification-211.wav");
                    Thread t1 = new Thread(se);
                    t1.start();
                }catch (Exception ae)
                {
                    System.out.println(ae.getMessage());
                }
            }
        });
        JButton pvai = new JButton("Player vs AI");
        pvai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gate.setValue(false);
                mode = 2;
                try{
                    System.out.println("here");
                    SoundEffect se = new SoundEffect();
                    se.playBackGround("Sounds\\mixkit-retro-arcade-casino-notification-211.wav");
                    Thread t1 = new Thread(se);
                    t1.start();
                }catch (Exception ae)
                {
                    System.out.println(ae.getMessage());
                }
            }
        });
        // exit button
        JButton exit = new JButton("EXIT");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gate.setValue(false);
                mode = 9;
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


    public void hideFrame(){
        frame.setVisible(false);
    }

    // move frame to a new location
    public void refreshFrame(LayoutDetails ld){
        frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(),ld.getHeight());
        frame.setVisible(true);
    }

    public int getMode(){return mode;}


    public Point getlocation(){return frame.getLocation();}

    public void resetMode(){mode = 0;}
    public void closeMenu(){frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));}
}
