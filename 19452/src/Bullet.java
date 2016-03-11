import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Tdh4vnPC on 3/1/2016.
 */
public class Bullet extends GameObject {
    private int speed;
    private boolean check = true;

    private Bullet() {
        positionX = 0;
        positionY = 0;
    }

    public Bullet(int positionX, int positionY, int speed) {//Alt + Inseart
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        try {
            this.sprite = ImageIO.read(new File("Resources/DAN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(){
        this.positionY -= this.speed;
    }

    public void update() {
        this.move();
        if(check) {
            if(checkCollisionEnemy()) {
                sprite = null;
                check = false;
            }
        }
    }

    public boolean checkCollisionEnemy() {
        Rectangle rectBullet = new Rectangle(positionX, positionY, sprite.getWidth(), sprite.getHeight());
        for(PlaneEnemy planeEnemy : PlaneEnemyManager.getInstance().getVectorPlaneEnemy()) {
            Rectangle rectPlaneEnemy = new Rectangle(planeEnemy.getPositionX(), planeEnemy.getPositionY(), planeEnemy.getWidth(), planeEnemy.getHeight());
            if(rectBullet.intersects(rectPlaneEnemy)) {
                PlaneEnemyManager.getInstance().getVectorPlaneEnemy().remove(PlaneEnemyManager.getInstance().getVectorPlaneEnemy().indexOf(planeEnemy));
                return true;
            }
        }
        return false;
    }
    //Lay toa do cua 2 may bay
    //PlaneManager.getInstance()....

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
