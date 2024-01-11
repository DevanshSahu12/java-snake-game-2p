import java.awt.*;

public class Score extends Apple implements AbstractScore
{
    int applesEaten1;
    int applesEaten2;
    int winner;

    Score(int height, int width)
    {
        this.height=height;
        this.width=width;
    }

    public void draw(Graphics g, int applesEaten1, int applesEaten2)
    {
        this.applesEaten1=applesEaten1;
        this.applesEaten2=applesEaten2;
        g.setColor(Color.red);
		g.setFont(new Font("Times New Roman", Font.BOLD, 40));
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		g.drawString("P1: " + applesEaten1, (width - metrics.stringWidth("P1: "+applesEaten1))/3, g.getFont().getSize());
        g.drawString("P2: " + applesEaten2, (width + metrics.stringWidth("P2: "+applesEaten2))/2, g.getFont().getSize());
    }


    public void draw(Graphics g, int applesEaten1, int applesEaten2, char winner)
    {
        // score
        g.setColor(Color.red);
		g.setFont( new Font("Times New Roman",Font.BOLD, 40));
		FontMetrics metrics1 = g.getFontMetrics(g.getFont());
		g.drawString("P1: " + applesEaten1, (width - metrics1.stringWidth("P1: "+applesEaten1))/3, g.getFont().getSize());
        g.drawString("P2: " + applesEaten2, (width + metrics1.stringWidth("P2: "+applesEaten2))/2, g.getFont().getSize());


		// Game Over
		g.setColor(Color.red);
		g.setFont(new Font("Times New Roman", Font.BOLD, 75));
		FontMetrics metrics2 = g.getFontMetrics(g.getFont());
        if(winner=='1')
        {
		    g.drawString("Player 1 WINS!", (width - metrics2.stringWidth("Player 1 WINS!"))/2, height/2);
        }
        else
        {
            g.drawString("Player 2 WINS!", (width - metrics2.stringWidth("Player 2 WINS!"))/2, height/2);
        }
    }
}
