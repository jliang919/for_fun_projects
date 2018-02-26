//JUSTINS RECORD IS 62

package snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.xml.soap.Text;

@SuppressWarnings("serial")
public class gameWindow extends JPanel {
	ArrayList<SnakeNode> snake = new ArrayList<SnakeNode>();
	int x; 
	int y ;
	int width=600;
	int height=600;
	static int direction = 0; // 0 is east 1 is south 2 is west and 3 is north
	int ballSize = 30;
	
	static JFrame myFrame;
	
	int power_width;
	int power_height;
	
	public gameWindow () {
		x = 0;
		y = 150;
		snake.add((new SnakeNode(x, y)));
		
		make_power();
		
	}
	
	public void make_power() {
		
		
		while (true) {
			power_width = (int) ( Math.random() * width/30) *30;
			power_height = (int) ( Math.random() * height/30) * 30;
			SnakeNode temp_node = new SnakeNode(power_width,power_height);
			for (int i = 0 ; i < snake.size() ; i ++){
				if (snake.get(i).width == power_width && snake.get(i).height == power_height) {
					continue;
				}
				else {
					return;
				}
			}
		}
	}
	public void move() throws InterruptedException {
		if (direction == 0) {
			if (x <= width-ballSize) {
				x += ballSize;
			}
			else {
				game_over();
			}
		}
		else if (direction == 1){
			if (y <= height-ballSize) {
				y += ballSize;
			}
			else {
				game_over();
			}
		}
		else if (direction == 2){
			if (x >= 0) {
				x -= ballSize;
			}
			else {
				game_over();
			}
		}
		else {
			if (y >= 0) {
				y -= ballSize;
			}
			else {
				game_over();
			}
		}
		if (x == power_width && y == power_height) {
			snake.add(0, new SnakeNode(x,y));
			make_power();
		}
		else {
			for (int i = snake.size()-1 ; i > 0 ; i--){
				SnakeNode temp = snake.get(i);
				if (temp.height == y && temp.width == x) {
					game_over();
				}
				temp.height = snake.get(i-1).height;
				temp.width = snake.get(i-1).width;
				//snake.set(i, snake.get(i-1));
			}
			snake.get(0).width = x;
			snake.get(0).height = y;
		}
		
		/*
		int temp_width = snake.get(snake.size()-1 ).width - 30;
		int temp_height = snake.get(snake.size()-1).height;
		//System.out.println(x);
		
		
		if (snake.get(0).width == power_width && snake.get(0).height == power_height){
			boolean tester = true;
			for (int i = 0 ; i < snake.size(); i ++) {
				temp_width = snake.get(snake.size()-1 ).width - 30;
				temp_height = snake.get(snake.size()-1).height;
				if (snake.get(i).width == temp_width && snake.get(i).height == temp_height){
					tester = false;
					break;
				}
			}
			if (tester){
				snake.add(new SnakeNode (temp_width, temp_height));
				make_power();
				return;
			}
			
			System.out.println("WTF");
			//game_over();
			
		}
		*/
	}
	
	public void game_over() throws InterruptedException {
		//Text text = new Text(Integer.toString(snake.size()));
		myFrame = new JFrame("Game Over Frame");
		StringBuilder sb = new StringBuilder();
		sb.append("GAME OVER !!! ");
		sb.append(snake.size());
		Label textLbl = new Label(sb.toString());
        myFrame.add(textLbl);
        myFrame.setSize(600,600);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
        //repaint();
		
        Thread.sleep(10000);
		System.exit(0);
	}
	
	@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
       // if (snake.size())
        for (int i = 0  ; i < snake.size(); i ++) {
        	g2d.fillRect(snake.get(i).width, snake.get(i).height, 30, 30);
        }
        
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(power_width, power_height, 30, 30);
    }
	
	 public static void main(String args[]) throws InterruptedException{
	        myFrame = new JFrame("Sample Frame");
	        gameWindow game = new gameWindow();
	        myFrame.add(game);
	        myFrame.setSize(600,600);
	        
	        
	       
	        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        myFrame.setVisible(true);
	        
	        myFrame.addKeyListener(new KeyAdapter() {
	        	@Override
	        	public void keyTyped(KeyEvent e) {
	        		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        			direction = 0;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	        			direction = 1;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        			direction = 2;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_UP) {
	        			direction = 3;
	        		}
	        		
	        	}

	        	@Override
	        	public void keyPressed(KeyEvent e) {
	        		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        			direction = 0;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	        			direction = 1;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        			direction = 2;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_UP) {
	        			direction = 3;
	        		}
	        		
	        	}

	        	@Override
	        	public void keyReleased(KeyEvent e) {
	        		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        			direction = 0;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	        			direction = 1;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        			direction = 2;
	        		}
	        		else if (e.getKeyCode() == KeyEvent.VK_UP) {
	        			direction = 3;
	        		}
	        		
	        	}
	          });
	        
	        while ( true ) {
	        	game.move();
	        	game.repaint();
	        	Thread.sleep(75);
	        }

	    }

	
}
