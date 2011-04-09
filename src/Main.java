

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends Canvas implements Serializable{

	private static final long serialVersionUID = -419365737093637821L;
	
	private boolean gameRunning;
	ArrayList<GameObject> gameObjects;
	
	//Swing tools
	BufferStrategy strategy;
	Toolkit toolkit;
	Dimension screensize;
	int midx; int midy;
	double scale;
	
	//Pointer tools
	int pointerX; int pointerY;
	int selectionRadius;
	long lastClick;

	public Main() {
		gameRunning = true;
		gameObjects = new ArrayList<GameObject>();
		
		toolkit = java.awt.Toolkit.getDefaultToolkit();
		screensize = toolkit.getScreenSize();
		midx = screensize.width / 2; midy = screensize.height / 2;
		scale = 1.0;
		
		selectionRadius = 10;
		lastClick = 0;
		pointerX = midx; pointerY = midy;
		
		init();
	}

	public void init(){
		JFrame container = new JFrame("Main");

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(screensize);
		setBounds(0,0,screensize.width,screensize.height);
		panel.setLayout(null);
		panel.add(this);

		setIgnoreRepaint(true);

		container.pack();
		container.setResizable(true);
		container.setVisible(true);

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		addMouseListener(new MouseInputHandler(this));
		addMouseMotionListener(new MouseMotionHandler());
		addKeyListener(new KeyInputHandler(this));
		addMouseWheelListener(new MouseWheelHandler());

		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}

	public void gameLoop() {
		while (gameRunning) {
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,screensize.width,screensize.height);
			g.scale(scale, scale);
			
			g.setColor(Color.green);
			g.drawLine(midx, midy, pointerX, pointerY);
			//g.drawOval(pointerX - radius, pointerY - radius, radius * 2, radius * 2);

			for (int i=0;i<gameObjects.size();i++) {
				gameObjects.get(i).update();
				gameObjects.get(i).draw(g);
			}

			g.dispose();
			if (gameRunning) strategy.show();

			try { Thread.sleep(15); } catch (Exception e) {}
		}
	}


	public static void main(String args[]) {
		Main main = new Main();
		main.init();
		main.gameLoop();
	}


	private class MouseInputHandler extends MouseAdapter{

		public MouseInputHandler(Main m){
		}

		public void mousePressed(MouseEvent e){
			
			for (int i=0;i<gameObjects.size();i++) {
				GameObject gameObject = gameObjects.get(i);
				double x = pointerX / scale - gameObject.getX(); double y = pointerY / scale - gameObject.getY();
				double distance = Math.sqrt(x*x+y*y);
				if (distance < gameObject.getRadius()){
				}
			}
			if (System.currentTimeMillis() - lastClick < 200){
			}
			else{
			}
			lastClick = System.currentTimeMillis();
		}

		public void mouseReleased(MouseEvent e){
		}

	}
	
	
	private class MouseMotionHandler extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e){
			pointerX = e.getX(); pointerY = e.getY();
		}

		public void mouseDragged(MouseEvent e){
			pointerX = e.getX(); pointerY = e.getY();
		}
	}

	
	private class MouseWheelHandler implements MouseWheelListener{
		public void mouseWheelMoved(MouseWheelEvent e){
			if (scale - e.getWheelRotation() * .1 > 0) scale -= e.getWheelRotation() * .1;
		}
	}

	
	private class KeyInputHandler extends KeyAdapter{

		public KeyInputHandler(Main m){
		}

		public void keyPressed(KeyEvent e) {
		} 

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
}

