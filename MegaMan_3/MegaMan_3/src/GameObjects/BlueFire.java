package GameObjects;
import GameEffect.Animation;
import GameEffect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BlueFire extends Bullet {
    private Animation forwardBulletAnim, backBulletAnim;

    public BlueFire(float x, float y , GameWorld gameWorld){
        super(x, y, 60, 30, 1.0f, 10, gameWorld);
        forwardBulletAnim= CacheDataLoader.getInstance().getAnimation("bluefire");
        backBulletAnim= CacheDataLoader.getInstance().getAnimation("bluefire");
        backBulletAnim.flipAllImage();
    }
    @Override
    public Rectangle getBoundForCollisionWithEnemy(){
        return getBoundForCollisionWithEnemy();
    }
    @Override
    public void draw(Graphics2D g2){
        if(getSpeedX()>0){
            if(forwardBulletAnim.isIgnoreFrame(0)&& forwardBulletAnim.getCurrentFrame()==3){
                forwardBulletAnim.isIgnoreFrame(1);
                forwardBulletAnim.isIgnoreFrame(2);
                forwardBulletAnim.isIgnoreFrame(3);
            }
            forwardBulletAnim.Update(System.nanoTime());
            forwardBulletAnim.draw((int) (getPosX()- getGameWorld().camera.getPosX()),(int) (getPosY()- getGameWorld().camera.getPosY()) , g2);
        }else{
            if(backBulletAnim.isIgnoreFrame(0)&& backBulletAnim.getCurrentFrame()==3){
                backBulletAnim.isIgnoreFrame(1);
                backBulletAnim.isIgnoreFrame(2);
                backBulletAnim.isIgnoreFrame(3);
            }
            backBulletAnim.Update(System.nanoTime());
            backBulletAnim.draw((int) (getPosX()- getGameWorld().camera.getPosX()),(int) (getPosY()- getGameWorld().camera.getPosY()) , g2);
        }

    }
    @Override
    public void Update() {
        if(forwardBulletAnim.isIgnoreFrame(0) || backBulletAnim.isIgnoreFrame(0))
            setPosX(getPosX() + getSpeedX());
        super.Update();
    }

    @Override
    public void attack() {}


    
}
