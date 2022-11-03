<<<<<<< Updated upstream
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class DrawMenu{

    private JFrame frame;
    private int mode;       // 1 => pvp,  2 => player vs ai

    // x : number of pixels from left of the screen
    // y : number of pixels from top of the screen
    // width & height are the size of the frame
    public DrawMenu(LayoutDetails ld){
        mode = 0;

        frame = new JFrame("DrawMenu");
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
        createButtons(background);

        frame.add(background);
        frame.pack();
        frame.setVisible(true);
    }

    //private void createButtons(JFrame f) {
    private void createButtons(JLabel label) {
        JButton st = new JButton("Select Token");
        st.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton pvp = new JButton("Player vs Player");
        pvp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 1;
            }
        });
        JButton pvai = new JButton("Player vs AI");
        pvai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 2;
            }
        });
        // center buttons
        st.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvp.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvai.setAlignmentX(Component.CENTER_ALIGNMENT);

        // space between buttons
        label.add(Box.createRigidArea(new Dimension(0,200)));
        label.add(st);
        // space between buttons
        label.add(Box.createRigidArea(new Dimension(0,20)));
        label.add(pvp);
        label.add(Box.createRigidArea(new Dimension(0,20)));
        label.add(pvai);


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
}
=======
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class DrawMenu{

    private JFrame frame;
    private int mode;       // 1 => pvp,  2 => player vs ai

    // x : number of pixels from left of the screen
    // y : number of pixels from top of the screen
    // width & height are the size of the frame
    public DrawMenu(LayoutDetails ld){
        mode = 0;

        frame = new JFrame("DrawMenu");
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
        createButtons(background);

        frame.add(background);
        frame.pack();
        frame.setVisible(true);
    }

    //private void createButtons(JFrame f) {
    private void createButtons(JLabel label) {
        JButton st = new JButton("Select Token");
        st.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton pvp = new JButton("Player vs Player");
        pvp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 1;
            }
        });
        JButton pvai = new JButton("Player vs AI");
        pvai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 2;
            }
        });
        // center buttons
        st.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvp.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvai.setAlignmentX(Component.CENTER_ALIGNMENT);

        // space between buttons
        label.add(Box.createRigidArea(new Dimension(0,200)));
        label.add(st);
        // space between buttons
        label.add(Box.createRigidArea(new Dimension(0,20)));
        label.add(pvp);
        label.add(Box.createRigidArea(new Dimension(0,20)));
        label.add(pvai);


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
}
>>>>>>> Stashed changes
