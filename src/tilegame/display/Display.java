package tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tilegame.inventory.Inventory;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}
	
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width , height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
		
		
	}
	
	

    
	
	public int getWidth () {
		width = canvas.getWidth();
		return width;
	}
	
	public int getHeight () {
		width = canvas.getHeight();
		return width;
	}
	
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	
	public JFrame getFrame() {
		return frame;
	}
	
	
	
	
	
	
	
	
	
}