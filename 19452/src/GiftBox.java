import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by VinhNguyenDinh on 03/11/2016.
 */
public class GiftBox extends GameObject {

    private GiftBox() {

    }

    public GiftBox(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        try {
            this.sprite = ImageIO.read(new File("Resources/GIFT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean check1 = false;
    private boolean check2 = false;
    private boolean check = true;

    public void update() {
        if(check) {
            checkCollision();
            if(check1 == true || check2 == true) {
                this.sprite = null;
                PlaneEnemyManager.getInstance().getVectorPlaneEnemy().clear();
                check = false;
            }
        }
    }

    public void checkCollision() {
        Rectangle rectGift = new Rectangle(positionX, positionY, sprite.getWidth(), sprite.getHeight());
        Rectangle rectPlaneKey =
                new Rectangle(PlaneManager.getInstance().getPlaneMoveByKey().getPositionX()
                        ,PlaneManager.getInstance().getPlaneMoveByKey().getPositionY()
                        ,PlaneManager.getInstance().getPlaneMoveByKey().getWidth()
                        ,PlaneManager.getInstance().getPlaneMoveByKey().getHeight());
        Rectangle rectPlaneMouse =
                new Rectangle(PlaneManager.getInstance().getPlaneMoveByMouse().getPositionX()
                        ,PlaneManager.getInstance().getPlaneMoveByMouse().getPositionY()
                        ,PlaneManager.getInstance().getPlaneMoveByMouse().getWidth()
                        ,PlaneManager.getInstance().getPlaneMoveByMouse().getHeight());
        if(rectGift.intersects(rectPlaneKey)) {
            check1 = true;
        }
        if(rectGift.intersects(rectPlaneMouse)) {
            check2 = true;
        }
     }
}
