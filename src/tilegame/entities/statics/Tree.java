package tilegame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.items.Item;
import tilegame.tile.Tile;

public class Tree extends StaticEntity{


	
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT *2);
	
	
	bounds.x = 30;
	bounds.y = 118;
	bounds.width = 4;
	bounds.height = 1 ;
	
	}
 
	@Override
	public void tick() {
		
	}
	
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x,(int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//		g.setColor(Color.CYAN);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	
	
	
	
}
