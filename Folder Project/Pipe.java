import java.awt.*;

public class Pipe {
    private  int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityY;
    boolean passed = false;
    private boolean isUpper;

    public Pipe(int posX, int posY, int width, int height, Image image, boolean isUpper) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.isUpper = isUpper;

        this.velocityY = -4;
        this.passed = false;
    }

    // Getter dan Setter untuk posX
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Getter dan Setter untuk posY
    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Getter dan Setter untuk width
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // Getter dan Setter untuk height
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Getter dan Setter untuk image
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    // Getter dan Setter untuk velocityY
    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    // Getter dan Setter untuk passed
    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    // Getter dan Setter untuk isUpper
    public boolean isUpper() {
        return isUpper;
    }

    public void setUpper(boolean upper) {
        isUpper = upper;
    }
}
