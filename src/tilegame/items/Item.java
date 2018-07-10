package tilegame.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import tilegame.Game;
import tilegame.Handler;
import tilegame.Options;
import tilegame.entities.statics.Rock;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.tile.Tile;

public class Item {

	
	// Handler
	

	
	// Class
	
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String nameFr, nameEng;
	protected final int id;
	
	
	protected Rectangle bounds;

	
	protected int x, y, count;
	public int j;
	protected boolean pickedUp = false;
	
	// Handler
	public static Item[] items = new Item[256];
	public static Item woodItem = new Item(Assets.wood, "Bouleau", "Birch", 0, 1);
	public static Item rockItem = new Item(Assets.rock, "Pierre", "Rock", 1, 2);
	
	
	
	public Item(BufferedImage texture, String nameFr, String nameEng, int id, int j) {
		this.texture = texture;
		this.nameFr = nameFr;
		this.nameEng = nameEng;
		this.id = id;
		this.j = j;
		
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		
		items[id] = this;
		count = items[id].j;
	}
	
	
	public void tick() {
		
		
		if ( handler.getKeyManager().Recup && handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this, items[id].j);
		}
		
	}
	
	public void render(Graphics g) {
		if (handler == null) {return;}
		render(g,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()));
	}
	
	public void render(Graphics g, int x, int y) {


		
		if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			g.setColor(Color.white);
			if (Options.getLanguage() == "FR") {
				g.fillRect(handler.getWidth() - 260, handler.getHeight() - 35, 245, 25);
			} else if (Options.getLanguage() == "ENG") {
			g.fillRect(handler.getWidth() - 190, handler.getHeight() - 35, 175, 25);
			}
			g.setColor(Color.red);
			if (Options.getLanguage() == "FR") {
			g.drawString("Appuyer sur Q pour prendre : " + items[id].nameFr + " x " + items[id].j ,handler.getWidth() - 250,handler.getHeight() - 20);
			
			} else if (Options.getLanguage() == "ENG") {
			g.drawString("Press Q to take : " + items[id].nameEng + " x " + items[id].j ,handler.getWidth() - 180,handler.getHeight() - 20);
			}
			
		}
		
		
		
		if (x >= -50 && y >= -(texture.getHeight() + ITEMHEIGHT) - 69 && y <= +(handler.getHeight() - 86) && x <= +(handler.getWidth()) -14 && id == 0) {
			g.drawImage(texture, x + 20, y + texture.getHeight() + 70, ITEMWIDTH, ITEMHEIGHT, null);
		}  else if (x >= -50 && y >= -texture.getHeight() +12 && y <= +handler.getHeight() - 20 && x <= +(handler.getWidth()) -20 && id == 1) {
			g.drawImage(texture, x + 20, y + 20, ITEMWIDTH, ITEMHEIGHT, null);
		}
			
		
		}
	
	// TEST
	public Item createNew (int count) {
		Item i = new Item(texture, nameFr, nameEng, id, j);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
		// TEST
     
	
	public Item createNew (int x, int y) {
		Item i = new Item(texture, nameFr, nameEng, id, j);
		i.setPostion(x, y);
		i.setNumber(j);
		return i;
	}
	
	private void setNumber(int j) {
		this.j = j;
		
	}


	public void setPostion (int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x + 20;
		if (id == 0) {
		 bounds.y = y + texture.getHeight() + 70;}
		else {
			bounds.y = y + 20;
		}
	}
	
	
	//Getter setter
	
	
	

	public Handler getHandler() {
		return handler;
	}


	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}


	public boolean isPickedUp() {
		return pickedUp;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public BufferedImage getTexture() {
		return texture;
	}


	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}


	public String getName() {
		if (Options.getLanguage() == "FR") {
		return nameFr;} else {
			return nameEng;
		}
	}


	public void setNameFr(String name) {
		this.nameFr = name;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getId() {
		return id;
	}
	
	
	
	
	
	
	
}
