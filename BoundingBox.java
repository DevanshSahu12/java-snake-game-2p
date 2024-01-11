import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BoundingBox extends JPanel implements ActionListener
{
	static final int WIDTH = 1000;
	static final int HEIGHT = 750;
	static final int UNIT = 25;
	static final int GAME_UNITS = (WIDTH*HEIGHT)/(UNIT*UNIT);
	private static final int DELAY = 100;


	int bodyParts1 = 5;
	int bodyParts2 = 5;
	int applesEaten1;
	int applesEaten2;
	private int appleX;
	private int appleY;
	char direction1 = 'R';
	char direction2 = 'R';
	boolean running = false;
	Timer timer;
	char color1='G';
	char color2='O';
	char winner;

	// creating objects
	Apple apple=new Apple(WIDTH, HEIGHT, UNIT);
	Snake snake1=new Snake(bodyParts1, direction1, GAME_UNITS, UNIT, color1, UNIT, UNIT);
	Snake snake2=new Snake(bodyParts2, direction2, GAME_UNITS, UNIT, color2, UNIT*5, UNIT*5);
	Score score=new Score(HEIGHT, WIDTH);


    BoundingBox()
    {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
    }

	// start game
	public void startGame()
	{
		newApple();
		running=true;
		timer=new Timer(DELAY, this);
		timer.start();
	}

	// generate new apple
	public void newApple()
	{
		apple.generateApple();
		appleX=apple.setAppleX();
		appleY=apple.setAppleY();
	}

	// initialize paint component
	public void paint(Graphics g)
	{
		super.paint(g);
		draw(g);
	}

	// draws objects on the panel
	public void draw(Graphics g)
	{
		if(running)
		{
			apple.draw(g);
			snake1.draw(g);
			snake2.draw(g);
			score.draw(g, applesEaten1, applesEaten2);
		}
		else
		{
			gameOver(g);
		}
	}

	// snake movement
	public void move()
	{
		snake1.move(direction1);
		snake2.move(direction2);
	}
	
	// check if apple is eaten and spawn new apple
	public void checkApple()
	{
		if((snake1.x[0] == appleX) && (snake1.y[0] == appleY)) 
		{
			bodyParts1++;
			applesEaten1++;
			snake1.bodyAdd();
			newApple();
		}
		if((snake2.x[0] == appleX) && (snake2.y[0] == appleY)) 
		{
			bodyParts2++;
			applesEaten2++;
			snake2.bodyAdd();
			newApple();
		}
	}


	public void checkCollisions()
	{
		// check if head collides with body
		for(int i = bodyParts1; i>0; i--) 
		{
			if((snake1.x[0] == snake1.x[i]) && (snake1.y[0] == snake1.y[i])) 
			{
				running = false;
				winner = '2';
			}
		}
		//check if head touches borders
		if(snake1.x[0] < 0) 
		{
			running = false;
			winner = '2';
		}
		
		if(snake1.x[0] > WIDTH) 
		{
			running = false;
			winner = '2';
		}
		
		if(snake1.y[0] < 0) 
		{
			running = false;
			winner = '2';
		}
		
		if(snake1.y[0] > HEIGHT) 
		{
			running = false;
			winner = '2';
		}
		
		if(!running) {
			timer.stop();
		}

		
		for(int i = bodyParts2; i>0; i--) 
		{
			if((snake2.x[0] == snake2.x[i]) && (snake2.y[0] == snake2.y[i])) 
			{
				running = false;
				winner = '1';
			}
		}
		//check if head touches borders
		if(snake2.x[0] < 0) 
		{
			running = false;
			winner = '1';
		}
		
		if(snake2.x[0] > WIDTH) 
		{
			running = false;
			winner = '1';
		}
		
		if(snake2.y[0] < 0) 
		{
			running = false;
			winner = '1';
		}
		
		if(snake2.y[0] > HEIGHT) 
		{
			running = false;
			winner = '1';
		}
		
		if(!running) {
			timer.stop();
		}
	}

	// Check collision with opponent
	public void checkOpponentCollision()
	{
		for(int i = bodyParts2; i>=0; i--) 
		{
			if((snake1.x[0] == snake2.x[i]) && (snake1.y[0] == snake2.y[i])) 
			{
				running = false;
				winner = '2';
			}
		}
		for(int i = bodyParts1; i>=0; i--) 
		{
			if((snake2.x[0] == snake1.x[i]) && (snake2.y[0] == snake1.y[i])) 
			{
				running = false;
				winner = '1';
			}
		}
	}

	// Game Over screen
	public void gameOver(Graphics g)
	{
		score.draw(g, applesEaten1, applesEaten2, winner);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(running) 
		{
			move();
			checkApple();
			checkCollisions();
			checkOpponentCollision();
		}
		repaint();
	}

	// Keyboard inputs
	public class MyKeyAdapter extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyCode()) 
			{
				case KeyEvent.VK_LEFT:
					if(direction1 != 'R') 
					{
						direction1 = 'L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(direction1 != 'L') 
					{
						direction1 = 'R';
					}
					break;
				case KeyEvent.VK_UP:
					if(direction1 != 'D') 
					{
						direction1 = 'U';
					}
					break;
				case KeyEvent.VK_DOWN:
					if(direction1 != 'U') 
					{
						direction1 = 'D';
					}
					break;
			}
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_W:
					if(direction2 != 'D')
					{
						direction2 = 'U';
					}
					break;
				case KeyEvent.VK_S:
					if(direction2 != 'U')
					{
						direction2 = 'D';
					}
					break;
				case KeyEvent.VK_A:
					if(direction2 != 'R')
					{
						direction2 = 'L';
					}
					break;
				case KeyEvent.VK_D:
					if(direction2 != 'L')
					{
						direction2 = 'R';
					}
					break;
			}
		}
	}
}