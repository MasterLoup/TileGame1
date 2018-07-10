package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import tilegame.Game;
import tilegame.Handler;
import tilegame.Options;
import tilegame.gfx.Assets;
import tilegame.gfx.Text;
import tilegame.input.KeyManager;

public class MenuState extends State{
	
	// Variables
	private boolean active;
	private static boolean start_Continue;
	private boolean acces;
	private int x = (int) (((double)handler.getWidth() / 100) * 50);
	private int y = 75;
	private int choice = 1;
	private int ySword, xSword;
	
	// timer
	double delta = 0;
	long now;
	long lastTime = System.nanoTime();
	long timer = 0;
	int ticks = 0;
	double timePerTick = 1000000000 / 60;
	
	
	
	
	// Inputs
	private KeyManager keyManager;
	
	public MenuState(Handler handler) {
		super(handler);

		keyManager = new KeyManager();
		
		
	}
	
	
	
	public void tick() {
		
		
		if (handler.getKeyManager().aDown && handler.getKeyManager().aUp || handler.getKeyManager().up && handler.getKeyManager().down || handler.getKeyManager().aDown && handler.getKeyManager().up || handler.getKeyManager().aUp && handler.getKeyManager().down) {
		} else if (handler.getKeyManager().aDown && !acces || handler.getKeyManager().down && choice == 1 && !acces) {
			choice = 2;
		} else if (handler.getKeyManager().aUp && !acces || handler.getKeyManager().up && choice == 2 && !acces) {
			choice = 1;
		}
		
		
		if (choice == 1 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !acces) {
			choice = 1;
			start_Continue = true;
					State.setState(handler.getGame().gameState);
		} else if (choice == 2 && handler.getKeyManager().enter && !acces) {
			acces = true;
		}
		
		
		
		
		}
		
	
	
	public void render(Graphics g) {
			// Menu
		
		if (acces) { // transition , Options
			g.drawImage(Assets.optionsMenu, x + 375, 0, handler.getWidth(), handler.getHeight(), null);
			g.drawImage(Assets.optionsMenu_Selected, x + 217 + 375, 232, 316, 55, null);
			if (Options.getLanguage() == "FR") {
			Text.drawString(g, "Menu options", x + 375 + 375, 55, true, Color.YELLOW, Assets.fontTell45);
			Text.drawString(g, "Retour", x + 375 + 375, 257, true, Color.WHITE, Assets.fontTell45);
			Text.drawString(g, "FPS", x + 375 + 375, 200, true, Color.GRAY, Assets.fontTell45);
			Text.drawString(g, "Bouger dans l'inventaire", x + 375 + 375, 135, true, Color.GRAY, Assets.fontTell32);
			Text.drawString(g, "Langue", x + 375 + 375, 318, true, Color.GRAY, Assets.fontTell45);
			Text.drawString(g, "Comment jouer", x + 375 + 375, 378, true, Color.GRAY, Assets.fontTell45);
			
			} else if (Options.getLanguage() == "ENG") {
			Text.drawString(g, "Options menu", x + 375 + 375, 55, true, Color.YELLOW, Assets.fontTell45);
			Text.drawString(g, "Return", x + 375 + 375, 257, true, Color.WHITE, Assets.fontTell45);
			Text.drawString(g, "FPS", x + 375 + 375, 200, true, Color.GRAY, Assets.fontTell45);
			Text.drawString(g, "Move in inventory", x + 375 + 375, 135, true, Color.GRAY, Assets.fontTell32);
			Text.drawString(g, "Language", x + 375 + 375, 318, true, Color.GRAY, Assets.fontTell45);
			Text.drawString(g, "How to play", x + 375 + 375, 378, true, Color.GRAY, Assets.fontTell45);
			}
			
		}
		
		g.drawImage(Assets.mainMenu,x - 375,0, handler.getWidth(), handler.getHeight(), null);
		
		if (Options.getLanguage() == "FR") {
		Text.drawString(g, "Menu principal", x, y, true, Color.BLACK, Assets.fontTell60);
		if (!start_Continue) {
		Text.drawString(g, "Commencer", x, y + 135, true, Color.WHITE, Assets.fontTell60);
		} else { Text.drawString(g, "Continuer", x, y + 135, true, Color.WHITE, Assets.fontTell60); }
		Text.drawString(g, "Options", x, y + 305, true, Color.YELLOW, Assets.fontTell60);
		
		} else if (Options.getLanguage() == "ENG") {
			Text.drawString(g, "Main menu", x, y, true, Color.BLACK, Assets.fontTell60);
			if (!start_Continue) {
			Text.drawString(g, "Start", x, y + 135, true, Color.WHITE, Assets.fontTell60);
			} else { Text.drawString(g, "Continue", x, y + 135, true, Color.WHITE, Assets.fontTell60); }
			Text.drawString(g, "Options", x, y + 305, true, Color.YELLOW, Assets.fontTell60);
		}
		
			// Cursor
		if (choice == 1) {
			if (!acces) {
			g.drawImage(Assets.menuCursor[5], x - 310, 270, 75, 75, null); 
			} else {
				g.drawImage(Assets.menuCursor[3], (int) (((double)handler.getWidth() / 100) * 50) - 310 + xSword, 270 - ySword, 85, 25, null);
			}
			
		} else {
			if (!acces) {
			g.drawImage(Assets.menuCursor[6], x - 310, 270, 75, 75, null); 
			} else {
				g.drawImage(Assets.menuCursor[3], (int) (((double)handler.getWidth() / 100) * 50) - 310 + xSword, 270 - ySword, 85, 25, null);
			}
			
		}
		
		
		if (acces) {
			
			
			
				now = System.nanoTime();
				
				delta += (now - lastTime) / timePerTick;
				timer += now - lastTime;
				lastTime = now;
				
				if(delta >= 1) {
				ticks++;
				delta --;
				
				}
				
				if(timer >= 1) {
					x -= 16;
					if (x <= -200) {
						ySword += 2;
						xSword += 2;
					}
					ticks = 0;
					timer = 0;
				}
				
			}
			
		
		    if (x <= -378 && acces) {	
			acces = false;
			choice = 1;
			ySword = 0;
			xSword = 0;
			x = (int) (((double)handler.getWidth() / 100) * 50);
			State.setState(handler.getGame().optionState);
		
			}
		
		
		
		
		
	}



	public static boolean isStart_Continue() {
		return start_Continue;
	}
	
	

}
