import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Tdh4vnPC on 3/1/2016.
 */
public class PlaneEnemy extends GameObject implements Observer{
    private int speed;
    private int count = 0;
    public int dead = 0;
    private Vector<BulletOfEnemy> vecBul = new Vector<>();

    private PlaneEnemy(){

    }

    public void shot(){
        BulletOfEnemy bulletOfEnemy = new BulletOfEnemy(positionX+30,positionY+59,-5);
        vecBul.add(bulletOfEnemy);
    }

    public PlaneEnemy(int positionX, int positionY, int speed) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        try {
            this.sprite = ImageIO.read(new File("Resources/PLANE1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void move(){
        this.positionX += this.speed;
        if(this.positionX <= 0)
        {
            this.speed =- this.speed;
        }
        if(this.positionX >= 540) {
            this.speed =- this.speed;
        }
    }

    public void update() {//60 lan 1 giay
        count++;
        if(count >= 120){
            this.shot();
            count=0;
        }
        for(BulletOfEnemy bulletOfEnemy : vecBul){
            bulletOfEnemy.update();
        }
        move();
    }

    public void draw(Graphics g) {
        super.draw(g);
        for (BulletOfEnemy bulletOfEnemy : vecBul){
            bulletOfEnemy.draw(g);
        }
    }

    @Override
    public void update(String msg) {
        if(msg.equals("Bo Vua An Duoc Qua")){
            //System.out.println("Thang Player Vua An Duoc Qua Roi");

        }
    }


    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
