import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by VinhNguyenDinh on 03/11/2016.
 */
public class BulletOfEnemy extends Bullet {

    public BulletOfEnemy(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        try {
            this.sprite = ImageIO.read(new File("Resources/DAN_ENEMY.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean check = true;

    public void update() {
        super.move();
        // Trừ máu nếu bị trúng đạn
        if(check) {
            if (checkCollisionKey()) {
                PlaneManager.getInstance().getPlaneMoveByKey().setHealth(PlaneManager.getInstance().getPlaneMoveByKey().getHealth()- 5);
                this.sprite = null;
                check = false;
            }
            else if(checkCollisionMouse()) {
                PlaneManager.getInstance().getPlaneMoveByMouse().setHealth(PlaneManager.getInstance().getPlaneMoveByMouse().getHealth()- 5);
                this.sprite = null;
                check = false;
            }
        }
    }

    public boolean checkCollisionKey() {
        Rectangle rectBullet = new Rectangle(positionX, positionY, sprite.getWidth(), sprite.getHeight());
        Rectangle rectPlaneKey = new Rectangle(PlaneManager.getInstance().getPlaneMoveByKey().getPositionX()
                ,PlaneManager.getInstance().getPlaneMoveByKey().getPositionY()
                ,PlaneManager.getInstance().getPlaneMoveByKey().getWidth()
                ,PlaneManager.getInstance().getPlaneMoveByKey().getHeight());
        return rectBullet.intersects(rectPlaneKey);
    }

    public boolean checkCollisionMouse() {
        Rectangle rectBullet = new Rectangle(positionX, positionY, sprite.getWidth(), sprite.getHeight());
        Rectangle rectPlaneMouse = new Rectangle(PlaneManager.getInstance().getPlaneMoveByMouse().getPositionX()
                ,PlaneManager.getInstance().getPlaneMoveByMouse().getPositionY()
                ,PlaneManager.getInstance().getPlaneMoveByMouse().getWidth()
                ,PlaneManager.getInstance().getPlaneMoveByMouse().getHeight());
        return rectBullet.intersects(rectPlaneMouse);
    }
}
