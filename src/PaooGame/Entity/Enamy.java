package PaooGame.Entity;

public abstract class Enamy extends Entity{
    private int enamyType;
    public Enamy(int x, int y, int s, int type) {
        super(x, y, s);
        this.enamyType=type;
        initSolidArea(x,y,Width,Height);
    }

    public void update(){
        updateCounter();
    }
    private void updateCounter() {
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
