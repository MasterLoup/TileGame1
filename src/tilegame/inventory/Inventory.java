package tilegame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.gfx.Text;
import tilegame.items.Item;
import ui.UIManager;

public class Inventory {
	
	
	private Handler handler;
	private static boolean activeInventory = false;
	private ArrayList<Item> inventoryItems;
	
	//Inventory things
	
	private int invXStart, invXEnd, invYStart, invYEnd;
	
	private int selectedItem = 0;
	
	// Mouse things
	
	
	
	public Inventory (Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		
	}
	
	
	
	public void tick() {
		
		
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			activeInventory = !activeInventory;}
		if (!activeInventory) {return;}
		
		
		   
		        
		        	
		        
		        
		        
		        
		       
		
	      
	        
	        
	    
	      
	      
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
			selectedItem--; }
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
			selectedItem++; }
		
		
		if (selectedItem < 0) {
			selectedItem = inventoryItems.size() - 1;
		} else if (selectedItem >= inventoryItems.size()) {
				selectedItem = 0;
			}
		
		
		invXStart = (int) (((double)handler.getWidth() / 100) * 10);
		invXEnd = (int) (((double) handler.getWidth() / 100) * 90);
		invYStart = (int) (((double)handler.getHeight() / 100) * 10);
		invYEnd = (int) (((double) handler.getHeight() / 100) * 90);
		
	}
	
	



	public void render(Graphics g) {
		if (!activeInventory) {return;}
		
		g.drawImage(Assets.inventoryScreen,(int) (((double)handler.getWidth() / 100) * 10),(int) (((double)handler.getHeight() / 100) * 10),(int) (((double) handler.getWidth() / 100) * 80),(int) (((double) handler.getHeight() / 100) * 80), null);
		
		int len = inventoryItems.size();
		if (len == 0) {return;}
		
		for (int i = -5; i < 6; i++) {
			if (selectedItem + i < 0 || selectedItem + i >= len) {
				continue;}
			if (i == 0) {
							Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <" , ((invXEnd - invXStart) / 100) * 33 + invXStart, 
					((invYEnd - invYStart) / 100) * 51 + invYStart + (i * 32), true, Color.YELLOW, Assets.font25);
			} else {
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), ((invXEnd - invXStart) / 100) * 33 + invXStart, 
						((invYEnd - invYStart) / 100) * 51 + invYStart + (i * 32), true, Color.white, Assets.font20);
			} 
		
		}
		
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), (((invXEnd - invXStart) / 100) * 77)+ invXStart, (((invYEnd - invYStart) / 100) * 22), 60, 60, null);
		Text.drawString(g, Integer.toString(item.getCount()), (((invXEnd - invXStart) / 100) * 82)+ invXStart, (((invYEnd - invYStart) / 100) * 45), true, Color.WHITE, Assets.font25);
	
	
	}
	
	// Inventory Methods
	
	public void addItem(Item item, int j) {
		for (Item i : inventoryItems) {
			if (i.getId() == item.getId()) {
				i.setCount(i.getCount() + j);
				return;
			}
		}
		inventoryItems.add(item);
		
	}
	
	
	    
   
	  

	
	
	// getter setter

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}



	public static boolean isactiveInventory() {
		return activeInventory;
	}
	
	
	
	
	
}
