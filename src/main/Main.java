package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Player.Controller;
import main.units.bases.Shipyard;
import main.units.ships.Interceptor;

public class Main extends Canvas implements Serializable{

	private static final long serialVersionUID = -419365737093637821L;

	public final static long DOUBLE_CLICK_WAIT_TIME = 200;
	public final static double SCALE_FACTOR = 0.1;
	public final static double RADIUS_SCALE_FACTOR = 2;
	public final static double SCROLL_SPEED = 3;

	private boolean gameRunning;
	private Map map;

	//Swing tools
	BufferStrategy strategy;
	Toolkit toolkit;
	Dimension screensize;
	Point center;
	double scale;

	//Pointer tools
	Point cursor;
	double selectionRadius;
	long lastClick;

	double scrollingX;
	double scrollingY;

	boolean shiftPressed;

	public Main() {
		gameRunning = true;
		map = new Map(new Dimension(500, 400));

		toolkit = java.awt.Toolkit.getDefaultToolkit();
		screensize = toolkit.getScreenSize();
		center = new Point(screensize.width / 2, screensize.height / 2);
		scale = 1.0;

		selectionRadius = 50;
		lastClick = 0;
		cursor = new Point(center.x, center.y);

		scrollingX = scrollingY = 0;

		shiftPressed = false;

		test();
		init();
	}

	public void test(){
		Player red = new Player(0, Color.RED, "Red", Controller.PLAYER);
		Player blue = new Player(1, Color.BLUE, "Blue", Controller.COMPUTER);
		map.addPlayer(red);
		map.addPlayer(blue);

		Unit u1 = new Interceptor(new Location(center.x, center.y), red);
		map.addUnit(u1);

		Unit u2 = new Shipyard(new Location(100, 50), red);
		map.addUnit(u2);
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

	public void scrollY(double speed){
		Iterator<Unit> iter = map.getUnits().iterator();
		while (iter.hasNext()){
			Unit unit = iter.next();
			Location loc = unit.getLocation();
			loc.setY(loc.getY() + speed);
			Location des = unit.getOrder();
			if (des != null)
				des.setY(des.getY() +speed);
		}
	}

	public void scrollX(double speed){
		Iterator<Unit> iter = map.getUnits().iterator();
		while (iter.hasNext()){
			Unit unit = iter.next();
			Location loc = unit.getLocation();
			loc.setX(loc.getX() + speed);
			Location des = unit.getOrder();
			if (des != null)
				des.setX(des.getX() +speed);
		}
	}

	public void gameLoop() {
		while (gameRunning) {
			//Scroll
			if (scrollingX == 0){
				if (cursor.y < screensize.height / 10){
					scrollY(SCROLL_SPEED * 1 / scale);
				}
				else if (cursor.y > screensize.height * 0.9){
					scrollY(-SCROLL_SPEED * 1 / scale);
				}
			}
			if (scrollingY == 0){
				if (cursor.x < screensize.width / 10){
					scrollX(SCROLL_SPEED * 1 / scale);
				}
				else if (cursor.x > screensize.width * 0.9){
					scrollX(-SCROLL_SPEED * 1 / scale);
				}
			}
			scrollX(scrollingX); scrollY(scrollingY);


			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, screensize.width, screensize.height);
			g.scale(scale, scale);

			g.setColor(Color.green);
			int scaledCursorX = (int)(cursor.getX() / scale); int scaledCursorY = (int)(cursor.getY() / scale);
			g.drawOval(scaledCursorX - (int)selectionRadius, scaledCursorY - (int)selectionRadius, (int)selectionRadius * 2, (int)selectionRadius * 2);

			g.drawString(scale + "", 10, 10);

			Iterator<Unit> iter = map.getUnits().iterator();
			while (iter.hasNext()){
				Unit unit = iter.next();
				unit.attack();
			}
			iter = map.getUnits().iterator();
			while (iter.hasNext()){
				Unit unit = iter.next();
				unit.update();
				unit.draw(g);
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

		private Set<Unit> selected;

		public MouseInputHandler() {
			selected = new HashSet<Unit>();
		}

		/**
		 * Selects units within the selection radius.
		 */
		public void mousePressed(MouseEvent e){

			//Order units to move.
			Location cursorLoc = new Location(cursor.x / scale, cursor.y / scale);
			selected.addAll(map.getAllUnitsInRange(selectionRadius, cursorLoc));

			//Detect double-click.
			if (System.currentTimeMillis() - lastClick < DOUBLE_CLICK_WAIT_TIME){
			}
			else{
			}
			lastClick = System.currentTimeMillis();
		}

		/**
		 * Issues a move order to selected units.
		 */
		public void mouseReleased(MouseEvent e){
			Location target = new Location(cursor.getX() / scale, cursor.getY() / scale);
			for (Unit unit : selected){
				unit.issueMoveOrder(target);
			}
			selected.clear();
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
			cursor = e.getPoint();
		}

		/**
		 * Activates when dragging.
		 */
		public void mouseDragged(MouseEvent e){
			cursor = e.getPoint();
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
			if (shiftPressed){
				double scaleUnits = selectionRadius - e.getWheelRotation() * RADIUS_SCALE_FACTOR;
				if (scaleUnits > 0) selectionRadius = scaleUnits;
			}
			else{
				double scaleUnits = scale - e.getWheelRotation() * SCALE_FACTOR;
				if (scaleUnits > 0) scale = scaleUnits;
			}
		}
	}


	private class KeyInputHandler extends KeyAdapter{

		public KeyInputHandler() {}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				scrollingY = SCROLL_SPEED * 1 / scale;
			}
			else if (e.getKeyCode() == KeyEvent.VK_S) {
				scrollingY = -SCROLL_SPEED * 1 / scale;
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				scrollingX = SCROLL_SPEED * 1/ scale;
			}
			else if (e.getKeyCode() == KeyEvent.VK_D) {
				scrollingX = -SCROLL_SPEED * 1 / scale;
			}

			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				shiftPressed = true;
			}
		} 

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
				scrollingY = 0;
			}

			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
				scrollingX = 0;
			}

			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				shiftPressed = false;
			}
		}

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
}

