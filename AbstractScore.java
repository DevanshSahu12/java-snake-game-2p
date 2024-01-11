import java.awt.*;

abstract public interface AbstractScore 
{
    abstract public void draw(Graphics g, int applesEaten1, int applesEaten2);
    abstract public void draw(Graphics g, int applesEaten1, int applesEaten2, char winner);
}
