package main;

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

import javax.swing.JFrame;
import javax.swing.JPanel;

import units.Unit;

public class Main extends Canvas implements Serializable{

	private static final long serialVersionUID = -419365737093637821L;

	public final static long DOUBLE_CLICK_WAIT_TIME = 200;
	public final static double SCALE_FACTOR = 0.1;

	private boolean gameRunning;
	public static ArrayList<Unit> units;
	public static ArrayList<Base> bases;

	//Swing tools
	BufferStrategy strategy;
	Toolkit toolkit;
	Dimension screensize;
	int midx; int midy;
	double scale;

	//Pointer tools
	int cursorX; int cursorY;
	int selectionRadius;
	long lastClick;

	public Main() {
		gameRunning = true;
		units = new ArrayList<Unit>();
		bases = new ArrayList<Base>();

		toolkit = java.awt.Toolkit.getDefaultToolkit();
		screensize = toolkit.getScreenSize();
		midx = screensize.width / 2; midy = screensize.height / 2;
		scale = 1.0;

		selectionRadius = 10;
		lastClick = 0;
		cursorX = midx; cursorY = midy;

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
		addMouseListener(new MouseInputHandler());
		addMouseMotionListener(new MouseMotionHandler());
		addKeyListener(new KeyInputHandler());
		addMouseWheelListener(new MouseWheelHandler());

		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}

	public void gameLoop() {
		while (gameRunning) {
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, screensize.width, screensize.height);
			g.scale(scale, scale);

			g.setColor(Color.green);
			g.drawOval(cursorX - selectionRadius, cursorY - selectionRadius, selectionRadius * 2, selectionRadius * 2);

			for (int i=0; i < units.size(); i++) {
				units.get(i).update();
				
			}
			
			for (int i=0; i < units.size(); i++) {
				units.get(i).draw(g);
			}
			
			for (int i=0; i < bases.size(); i++) {
				bases.get(i).update();
				bases.get(i).draw(g);
			}

			g.dispose();
			if (gameRunning) strategy.show();

			try { Thread.sleep(15); } catch (Exception e) {}
		}
	}


	public static void main(String args[]) {
		Main main = new Main();
		main.gameLoop();
	}


	private class MouseInputHandler extends MouseAdapter{

		public MouseInputHandler() {}

		public void mousePressed(MouseEvent e){
			
			//Order units to move.
			for (int i=0; i < units.size(); i++) {
				Unit unit = units.get(i);
				double x = cursorX / scale - unit.getX(); double y = cursorY / scale - unit.getY();
				double distance = Math.sqrt(x*x+y*y);
				if (distance < selectionRadius){
					
				}
			}
			
			//Detect double-click.
			if (System.currentTimeMillis() - lastClick < DOUBLE_CLICK_WAIT_TIME){
			}
			else{
			}
			lastClick = System.currentTimeMillis();
		}

		public void mouseReleased(MouseEvent e){
		}

	}

	/**
	 * Update the location of the cursor.
	 * @author Ceasar
	 *
	 */
	private class MouseMotionHandler extends MouseMotionAdapter{
		
		public MouseMotionHandler() {}
		
		/**
		 * Activates when not dragging.
		 */
		public void mouseMoved(MouseEvent e){
			cursorX = e.getX(); cursorY = e.getY();
		}

		/**
		 * Activates when dragging.
		 */
		public void mouseDragged(MouseEvent e){
			cursorX = e.getX(); cursorY = e.getY();
		}
	}

	/**
	 * For zooming.
	 * @author Ceasar
	 *
	 */
	private class MouseWheelHandler implements MouseWheelListener{
		
		public MouseWheelHandler() {}
		
		/**
		 * Zooms in or out.
		 */
		public void mouseWheelMoved(MouseWheelEvent e){
			double scaleUnits = e.getWheelRotation() * SCALE_FACTOR - scale;
			if (scaleUnits > 0) scale += scaleUnits;
		}
	}


	private class KeyInputHandler extends KeyAdapter{

		public KeyInputHandler() {}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {

			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			}
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

