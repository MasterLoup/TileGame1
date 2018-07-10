package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Tree;
import tilegame.gfx.Assets;
import tilegame.gfx.Text;
import tilegame.tile.Tile;
import tilegame.worlds.World;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	public void tick() {
		world.tick();
	}
	
	public void render(Graphics g) {
		world.render(g);
		g.setColor(Color.BLACK);
		g.fillRect(handler.getWidth() / 100 * 2, handler.getHeight() / 100 * 92 , 125, 25);
		g.setColor(Color.GREEN);
		g.fillRect(handler.getWidth() / 100 * 3 - 3, handler.getHeight() / 100 * 93 - 1, (int) (Player.getMana() / (Player.getManaMax() / 118)), 18);
		Text.drawString(g, String.valueOf(Player.getMana()), handler.getWidth() / 100 * 10, handler.getHeight() / 100 * 94 + 2, true, Color.WHITE, Assets.font20);
	}
	
	
}
