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
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Player.Controller;
import main.units.ships.Interceptor;

public class Main extends Canvas implements Serializable{

	private static final long serialVersionUID = -419365737093637821L;

	public final static long DOUBLE_CLICK_WAIT_TIME = 200;
	public final static double SCALE_FACTOR = 0.1;
	public final static double RADIUS_SCALE_FACTOR = 2;

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

	boolean shiftPressed = false;

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

		test();
		init();
	}

	public void test(){
		Player red = new Player(0, Color.RED, "Red", Controller.PLAYER);
		Player blue = new Player(1, Color.BLUE, "Blue", Controller.COMPUTER);
		map.addPlayer(red);
		map.addPlayer(blue);
		
		Unit u1 = new Interceptor(center, red);
		map.addUnit(u1);
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
			int scaledCursorX = (int)(cursor.x / scale); int scaledCursorY = (int)(cursor.y / scale);
			g.drawOval(scaledCursorX - (int)selectionRadius, scaledCursorY - (int)selectionRadius, (int)selectionRadius * 2, (int)selectionRadius * 2);

			g.drawString(scale + "", 10, 10);

			int unitCount = map.getUnits().size();
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
			selected.addAll(map.getAllUnitsInRange(selectionRadius, cursor));

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
			Point target = new Point((int)(cursor.x / scale), (int)(cursor.y / scale));
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
			if (e.getKeyCode() == KeyEvent.VK_UP) {

			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			}
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				shiftPressed = true;
			}
		} 

		public void keyReleased(KeyEvent e) {
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

