package tilegame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import tilegame.Handler;
import tilegame.entities.creatures.Player;
import tilegame.gfx.Assets;
import tilegame.items.Item;
import tilegame.tile.Tile;

public class Rock extends StaticEntity{


	
	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 0;
		bounds.y = 20;
		bounds.width = 63;
		bounds.height = 40 ;
		
	}

	@Override
	public void tick() {}
	
	public void die() {
		Player.setManaMax(Player.getManaMax() + 100);
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x,(int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//		g.setColor(Color.CYAN);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset() - 1),
//				bounds.width, bounds.height + 2);
	}
	
	

}



