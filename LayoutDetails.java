/**
 * details for each layout
 */
public class LayoutDetails {
    int x, y, width, height;

    /**
     * layout details
     */
    public LayoutDetails(){
        x = y = 0;
        width = 600;
        height = 400;
    }

    /**
     * layout details with specific coordinates passed on
     * @param x x spot
     * @param y y spot
     * @param width width
     * @param height height
     */
    public LayoutDetails(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}


    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height;}
}
