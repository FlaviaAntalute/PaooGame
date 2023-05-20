package PaooGame.Entity;

public class EnemyFactory {
    public Enemy createEnemy(String type,int x,int y,int s,String direction,int l)
    {
        if(type=="Max")
            return new Max(x,y,s,direction);
        else if (type=="Snake")
            return new Snake(x,y,s,direction,l);
        else if(type=="Rex")
            return new Rex(x,y,s,direction);
        else
            throw new IllegalArgumentException("Tip de inamic necunoscut:"+type);
    }
}
