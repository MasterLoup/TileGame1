package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import tilegame.Handler;
import tilegame.Options;
import tilegame.gfx.Assets;
import tilegame.gfx.Text;

public class OptionFpsState extends State{
	
	public OptionFpsState(Handler handler) {
		super(handler);
	}


	// timer
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		double timePerTick = 1000000000 / 60;
	
	// Variables
	private int x = (int) (((double)handler.getWidth() / 100) * 50), i = 0, iY = 0;
	private int ySelected = 49, xSelected, ySword = 114, xSword = 440, pos = 0, xMoveSword, yMoveSword, xMoveSword2, yMoveSword2;
	private boolean active, retour, retour2;
	private boolean actif1 = true, actif2, acces, perm;
	private boolean confirm, oui, non;
	
	private boolean swordDuplicate = true;
	private int orientationSword = 2, lastOri;
	
	
	

	

	
	public void tick() {
		
				// confirm *state*
				if (confirm && i == 1 && iY == 1 && pos == 0 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
					if (oui) {Options.setFps(60);} else if (non) {Options.setFps(120);}
					oui = false; confirm = false;
					retour2 = true;
					perm = true;
					active = true;
					}
				
		
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_D) && !active && i == 0 || handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && !active && i == 0) {
			i = 1;
			if (iY == 0) {actif2 = true; actif1 = false;} else {pos = 1; active = true; }
			
			
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_A) && !active && i == 1 || handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && !active && i == 1) {
			i = 0;
			if (iY == 0) {actif1 = true; actif2 = false;} else {pos = 2; active = true;}
			
			
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) && !active && iY == 0 || handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && !active && iY == 0) {
			iY = 1;
			if (i == 0) {pos = 3; actif1 = false;} else {pos = 4; actif2 = false;}
			active = true;
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) && !active && iY == 1 || handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && !active && iY == 1) {
			iY = 0;
			if (i == 0) {pos = 5;} else {pos = 6;}
			active = true;
		}
		
		
		
		
		if (i == 0 && iY == 0 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !active) {
			
			active = true; confirm = true; non = false; oui = true;
			actif1 = false; actif2 = false;
			i = 1; iY = 1;
			pos = 4;
			
		} else if (i == 1 && iY == 0 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !active) {
			active = true; confirm = true; oui = false; non = true;
			actif1 = false; actif2 = false;
			i = 1; iY = 1;
			pos = 4;
			
			
		} else if (i == 0 && iY == 1 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !active) {
			active = true;
			retour = true;
			perm = true;
			
		}
		
		
		

		
		
		
	}

	
	public void render(Graphics g) {
		
		if (retour || retour2) {
			g.drawImage(Assets.optionsMenu, x - 1125, 0, handler.getWidth(), handler.getHeight(), null);
			
			if (Options.getLanguage() == "FR") {
			Text.drawString(g, "Menu options", x - 755, 55, true, Color.YELLOW, Assets.fontTell45);
			
			Text.drawString(g, "Resolution *", x - 740, 135, true, Color.GRAY, Assets.fontTell45);
			Text.drawString(g, "Bouger dans l'inventaire", x - 740, 200, true, Color.GRAY, Assets.fontTell32);
			Text.drawString(g, "Retour", x - 740, 318, true, Color.GRAY, Assets.fontTell45);
			Text.drawString(g, "Langue", x - 740, 378, true, Color.GRAY, Assets.fontTell45);
			
			} else if (Options.getLanguage() == "ENG") {
			Text.drawString(g, "Options menu", x - 755, 55, true, Color.YELLOW, Assets.fontTell45);
			
			Text.drawString(g, "Resolution *", x - 740, 135, true, Color.GRAY, Assets.fontTell45);
			Text.drawString(g, "Move in inventory", x - 740, 200, true, Color.GRAY, Assets.fontTell32);
			Text.drawString(g, "Return", x - 740, 318, true, Color.GRAY, Assets.fontTell45);
			Text.drawString(g, "Language", x - 740, 378, true, Color.GRAY, Assets.fontTell45);
			}
		}
		
		g.drawImage(Assets.optionsMenuBackground, x - 375, 0, handler.getWidth() + 20, handler.getHeight(), null);
		
		g.drawImage(Assets.optionsMenu_Selected, 215, ySelected - 25, 316, 55, null);
		
		
		g.drawImage(Assets.confirmer, x + 107, 425, null);
		g.drawImage(Assets.confirmer, x - 293, 425, null);
		g.drawImage(Assets.option_Langue_invBouger, x - 226, 295, 450, 50, null);
		if (Options.getLanguage() == "FR") {
			Text.drawString(g, "FPS", 375, ySelected, true, Color.WHITE, Assets.fontTell45);
		Text.drawString(g, "60", x - 143, 320, true, Color.PINK, Assets.fontTell32);
		Text.drawString(g, "120", x + 137, 320, true, Color.PINK, Assets.fontTell32);
		Text.drawString(g, "Confirmer", x + 200, 448 , true, Color.RED, Assets.fontTell32);
		Text.drawString(g, "Retour", x - 200, 448 , true, Color.YELLOW, Assets.fontTell32);
		
		} else if (Options.getLanguage() == "ENG") {
			Text.drawString(g, "FPS", 375, ySelected, true, Color.WHITE, Assets.fontTell45);
		Text.drawString(g, "60", x - 143, 320, true, Color.PINK, Assets.fontTell32);
		Text.drawString(g, "120", x + 137, 320, true, Color.PINK, Assets.fontTell32);
		Text.drawString(g, "Confirm", x + 200, 448 , true, Color.RED, Assets.fontTell32);
		Text.drawString(g, "Return", x - 200, 448 , true, Color.YELLOW, Assets.fontTell32);	
		}
		
		if (i == 0 && active  && swordDuplicate || i == 0 && iY == 1  && swordDuplicate || i == 1 && iY == 1 && active && swordDuplicate) {
			g.drawImage(Assets.menuCursor[0], 218 + xMoveSword, (270 - ySword) + yMoveSword, 25, 85, null);
		} else if (i != 0 && !actif1 && swordDuplicate) {
		g.drawImage(Assets.menuCursor[0], 218, 270 - ySword, 25, 85, null);
		} else if (i == 0 && iY == 0 && actif1 && swordDuplicate){
		g.drawImage(Assets.menuCursor[0], 218, 300 - ySword, 25, 85, null);
			}	
		
		
		if (i == 1 && active|| i == 1 && iY == 1 || i == 0 && iY == 1 && active) {
			
				if (orientationSword == 2) {
					g.drawImage(Assets.menuCursor[0], (int) (((double)handler.getWidth() / 100) * 50) - 320 + xSword + xMoveSword2, (270 - ySword) + yMoveSword2, 25, 85, null);
				} else if (orientationSword == 1) {
					g.drawImage(Assets.menuCursor[6], (int) (((double)handler.getWidth() / 100) * 50) - 320 + xSword + xMoveSword2, (270 - ySword) + yMoveSword2, 75, 75, null);
				} else if (orientationSword == 0) {
					g.drawImage(Assets.menuCursor[3], (int) (((double)handler.getWidth() / 100) * 50) - 310 + xSword + xMoveSword2, (270 - ySword) + yMoveSword2, 85, 25, null);
				}
				
				
			} else if (i != 1 && !actif2) {
		g.drawImage(Assets.menuCursor[0], (int) (((double)handler.getWidth() / 100) * 50) - 320 + xSword, 270 - ySword, 25, 85, null);
			}  else if (i == 1 && iY == 0 && actif2) {
				g.drawImage(Assets.menuCursor[0], (int) (((double)handler.getWidth() / 100) * 50) - 320 + xSword, 300 - ySword, 25, 85, null);
			}	
		
		
		
		if (active && !retour) { 
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
			ticks++;
			delta --; }
			
			if(timer >= 1) {
				ticks = 0;
				timer = 0; 
				
				
				
				} }
		
		
		if (pos == 1) { // Mouvement Cursor 
			
			if (yMoveSword > 0) {yMoveSword -= 7;}
			if (yMoveSword <= 0 && xMoveSword < 0) {xMoveSword += 5;}
			
			if (xMoveSword2 <= +110) {xMoveSword2 += 5;}
			if (xMoveSword2 >= +110 && yMoveSword2 < 175) {yMoveSword2 += 7;}
			
			if (xMoveSword == 0 && yMoveSword2 >= 175) {pos = 0;}
			if (pos == 0) { acces = true; }
		}
		
		if (pos == 2) {
			
			if (xMoveSword >= -110) {xMoveSword -= 5;}
			if (xMoveSword <= -110 && yMoveSword < 175) {yMoveSword += 7;}
			
			if (yMoveSword2 > 0) {yMoveSword2 -= 7;}
			if (yMoveSword2 <= 0 && xMoveSword2 > 0) {xMoveSword2 -= 5;}
			
			if (yMoveSword >= 175 && xMoveSword2 == 0) {pos = 0;}
			if (pos == 0) { acces = true; }
			
		}
		
		if (pos == 3) {
			
			if (xMoveSword >= -110) { xMoveSword -= 5;}
			if (xMoveSword <= -110 && yMoveSword <= 175) { yMoveSword += 7;}
			
			if (yMoveSword >= 175) {pos = 0;}
			
			if (pos == 0) {
			acces = true; }
		}
		
		if (active && pos == 4) {
			
			if (xMoveSword2 <= +110) {xMoveSword2 += 5;}
			if (xMoveSword2 >= +110 && yMoveSword2 <= 175) {yMoveSword2 += 7;}
			
			if (yMoveSword2 >= 175) {pos = 0;}
			
			if (pos == 0) {
				acces = true; }
		}
		
		if (active && pos == 5) {
			
			
			if (yMoveSword > 0) {yMoveSword -= 7;}
			if (yMoveSword <= 0 && xMoveSword < 0) {xMoveSword += 5;}
			
			
			if (xMoveSword == 0) {pos = 0;}
			
			
			if (pos == 0) {
				actif1 = true;
				acces = true; }
		}
		
		if (active && pos == 6) {
			
			if (yMoveSword2 > 0) {yMoveSword2 -= 7;}
			if (yMoveSword2 <= 0 && xMoveSword2 > 0) {xMoveSword2 -= 5;}
			
			
			if (xMoveSword2 == 0) {pos = 0;}
			
			
			if (pos == 0) {
				actif2 = true;
				acces = true; }
		}
		
		
		
		if (acces) {
			acces = false;
			if (!perm) {
			active = false;}
			if (perm) {perm = false;}
		} // End movement cursor
		
		
		
		
		
		
		// retour normal
		
					if (retour) {
						now = System.nanoTime();
						delta += (now - lastTime) / timePerTick;
						timer += now - lastTime;
						lastTime = now;
						if(delta >= 1) {
						ticks++;
						delta --; }
						
						if(timer >= 1) { 
							if (perm) {pos = 5;}
							if (xSword > 26) {xSword -= 6;
							
							if (xSword <= 155) {swordDuplicate = false;}
							
							}
							
							if (xSword <= 200 && ySelected < 250) {
								ySelected += 8;
							}
							if (xSword == 26 && ySword > 26) {ySword -= 4;
							
							if (ySword > 26 && orientationSword != 0) {
								lastOri ++;
								if (lastOri >= 7) {
								orientationSword --;
								lastOri = 0;}
							}
							}
							
							if (ySword == 26) {x += 14;}
							ticks = 0;
							timer = 0; 
							
						
						}}
					
					
					// retour confirmer
					
					
					if (retour2) {
						now = System.nanoTime();
						delta += (now - lastTime) / timePerTick;
						timer += now - lastTime;
						lastTime = now;
						if(delta >= 1) {
						ticks++;
						delta --; }
						
						if(timer >= 1) { 
							if (perm) {pos = 6;}
							if (xSword > 26 && pos == 0) {xSword -= 6;
							
							if (xSword <= 155) {swordDuplicate = false;}
							
							}
							
							if (xSword <= 200 && ySelected < 250) {
								ySelected += 8;
							}
							if (xSword == 26 && ySword > 26) {ySword -= 4;
							
							if (ySword > 26 && orientationSword != 0) {
								lastOri ++;
								if (lastOri >= 7) {
								orientationSword --;
								lastOri = 0;}
							}
							}
							
							if (ySword == 26) {x += 14;}
							ticks = 0;
							timer = 0; 
							
						
						}}
					
				
				    if (retour && x >= +1130 || retour2 && x >= +1130) {
					retour = false;
					retour2 = false;
					non = false; oui = false;
					active = false;
					xSword = 440;
					ySelected = 49;
					ySword = 114;
					orientationSword = 2;
					actif1 = true; actif2 = false;
					i = 0; iY = 0;
					swordDuplicate = true;
					
					x = (int) (((double)handler.getWidth() / 100) * 50);
					
					State.setState(handler.getGame().optionState);
					}
		
		
		
	}

}
