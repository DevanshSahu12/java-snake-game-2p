import java.awt.*;

public class Snake extends AbstractSnake
{
    int[] x;
    int[] y;
    char direction;
    int bodyParts;
    int unit;
	char color;

    Snake(int bodyParts, char direction, int gameunits, int unit, char color, int initialX, int initialY)
    {
        x=new int[gameunits];
        y=new int[gameunits];
		x[0]=initialX;
		y[0]=initialY;
        this.direction=direction;
        this.bodyParts=bodyParts;
        this.unit=unit;
		this.color=color;
    }

    public void bodyAdd()
    {
        bodyParts++;
    }

    public void move(char direction)
	{
        this.direction=direction;
		for(int i = bodyParts; i>0; i--) 
		{
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) 
		{
			case 'U':
				y[0] = y[0] - unit;
				break;
			case 'D':
				y[0] = y[0] + unit;
				break;
			case 'L':
				x[0] = x[0] - unit;
				break;
			case 'R':
				x[0] = x[0] + unit;
				break;
		}
	}

    public void draw(Graphics g)
    {
        for(int i = 0; i<bodyParts; i++)
		{
			if(color == 'G')
			{
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], unit, unit);
			}
			else if(color == 'O')
			{
				g.setColor(Color.orange);
				g.fillRect(x[i], y[i], unit, unit);
			}
		}
    }
}
