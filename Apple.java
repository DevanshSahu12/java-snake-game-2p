import java.awt.*;
import java.util.Random;

public class Apple extends AbstractApple
{
    Random random;
    int appleX;
    int appleY;
    int height;
    int width;
    int unit;

    Apple(){}

    Apple(int width, int height, int unit)
    {
        random = new Random();
        this.height=height;
        this.width=width;
        this.unit=unit;
    }

    void generateApple()
    {
        appleX=random.nextInt((int)(width/unit))*unit;
		appleY=random.nextInt((int)(height/unit))*unit;
    }

    int setAppleX()
    {
       return appleX;
    }

    int setAppleY()
    {
       return appleY;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.red);
		g.fillRect(appleX, appleY, unit, unit);
    }
}
