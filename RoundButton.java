import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.function.IntConsumer;

public class RoundButton extends JButton {
    private Color color;
    public RoundButton(Icon img, Color color) {
        this.color = color;
       // super(img);
        if(img != null){
            setBackground(Color.CYAN);
            setIcon(img);
            setFocusable(false);

            Dimension size = new Dimension(40,40);
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
            // ignore square area, keep round area
            setContentAreaFilled(false);
        }else{
            setBackground(color);
            setFocusable(false);
            // fix the bug that paint square area when hovered
            setRolloverEnabled(false);
            Dimension size = new Dimension(40,40);
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
            // ignore square area, keep round area
            setContentAreaFilled(false);

        }

    }

    public Color getColor(){return color;}
    protected void paintComponent(Graphics g) {
        // when being clicked
        if (getModel().isArmed()) {
            g.setColor(Color.blue);
        } else {
            g.setColor(getBackground());
        }
        // narrow down the size of the button
        g.fillOval(0, 0, getSize().width, getSize().height);
        super.paintComponent(g);
    }

    // hide square border
    protected void paintBorder(Graphics g) {
    }

    private Shape shape;

    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

}
