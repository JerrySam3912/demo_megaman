package GameObjects;

public class Camera extends GameObject{
    private float widthView;
    private float heightView;
    public boolean isBlocked=false;

    public Camera(float heightView, float widthView, GameWorld gameWorld, float x, float y) {
        super(gameWorld, x, y);
        this.heightView = heightView;
        this.widthView = widthView;
    }
    public void lock(){
        isBlocked=true;
    }
    public void unblock(){
        isBlocked=false;
    }
    @Override
    public void Update(){
        if(!isBlocked){
            Megaman mainCharacter = getGameWorld().megaMan;
            if(mainCharacter.getPosX()-getPosX()>400) setPosX(mainCharacter.getPosX()-400);
            if(mainCharacter.getPosX()-getPosX()<200) setPosX(mainCharacter.getPosX()-200);
            if(mainCharacter.getPosY()-getPosY()>400) setPosY(mainCharacter.getPosY()-400);
            else if(mainCharacter.getPosY()-getPosY()<250) setPosY(mainCharacter.getPosY()-250);
        }
    }

    public float getWidthView() {
        return widthView;
    }

    public void setWidthView(float widthView) {
        this.widthView = widthView;
    }

    public float getHeightView() {
        return heightView;
    }

    public void setHeightView(float heightView) {
        this.heightView = heightView;
    }
    

    
}
