package PaooGame.Entity;

public abstract class Enemy extends Entity{
    private int enemyType;
    public Enemy(int x, int y, int s, int type,String dir) {
        super(x, y, s,dir);
        this.enemyType=type;
        initSolidArea(x,y,Width,Height);
    }

    public void update(){
        updateCounter();
    }
    public void updateCounter() {
        counter++;
        if (counter > 12) {
            this.updateNum();
            counter = 0;
        }
    }
    protected void updateNum() {
        if(num==1)
            num = 2;
        else if(num==2)
            num = 3;
        else if(num==3)
            num=4;
        else if(num==4)
            num=1;
    }
    public String getDirection()
    {
        return direction;
    }
}
