package GameObjects;

public abstract class GameObject{
    private  float posX;
    private float posY;
    private GameWorld gameWorld;

    public GameObject(GameWorld gameWorld, float x, float y) {
        this.gameWorld = gameWorld;
        this.posX = x;
        this.posY = y;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float x) {
        this.posX = x;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float y) {
        this.posY = y;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }
    public abstract void Update();

    

}


