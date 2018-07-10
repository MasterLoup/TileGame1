package tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import tilegame.Game;
import tilegame.Handler;
import tilegame.Options;
import tilegame.gfx.Assets;
import tilegame.gfx.Text;
import tilegame.input.KeyManager;

public class OptionState extends State {
	
	// variables
	private boolean active, acces, retour, langue, inventoryMove, fps;
	private boolean finish, swordDuplicate;
	private int x = (int) (((double)handler.getWidth() / 100) * 50), ySelected = 257;
	private int i = 2, xSword = 26, ySword = 26, orientationSword = 0, lastOri = 0;
	private String lastLanguage = "FR";
	
	// Inputs
	private KeyManager keyManager;
	
	// timer
	double delta = 0;
	long now;
	long lastTime = System.nanoTime();
	long timer = 0;
	int ticks = 0;
	double timePerTick = 1000000000 / 60;
	
	// Menu Shits
	
	String OP[] = {"Bouger dans l'inventaire", "FPS","Retour", "Langue",  "Comment jouer"};
	
	String OPFAKE[] = {"Bouger dans l'inventaire", "FPS","Retour", "Langue",  "Comment jouer",   "Resolution *"};
	
	
	Font OPF[] = {Assets.fontTell32 ,Assets.fontTell45, Assets.fontTell45, Assets.fontTell45, Assets.fontTell45};
	
	Font OPFFAKE[] = {Assets.fontTell32 ,Assets.fontTell45, Assets.fontTell45, Assets.fontTell45, Assets.fontTell45, Assets.fontTell45};

	public OptionState(Handler handler) {
		super(handler);
		
	}

	public void tick() {
		
		
		
		
		
		if (lastLanguage != Options.getLanguage()) {
			lastLanguage = Options.getLanguage();
			
			if (Options.getLanguage() == "FR") {
			
			OPFAKE[0] = "Bouger dans l'inventaire";
			OPFAKE[1] = "FPS";
			OPFAKE[2] = "Retour";
			OPFAKE[3] = "Langue";
			OPFAKE[4] = "Comment jouer";
			OPFAKE[5] = "Resolution *";
			
				OP[0] = OPFAKE[1]; OP[1] = OPFAKE[2]; OP[2] = OPFAKE[3]; OP[3] = OPFAKE[4]; OP[4] = OPFAKE[5];
				OPF[0] = OPFFAKE[1]; OPF[1] = OPFFAKE[2]; OPF[2] = OPFFAKE[3]; OPF[3] = OPFFAKE[4]; OPF[4] = OPFFAKE[5];
			
			
			} else if (Options.getLanguage() == "ENG") {
			
			OPFAKE[0] = "Move in inventory";
			OPFAKE[1] = "FPS";
			OPFAKE[2] = "Return";
			OPFAKE[3] = "Language";
			OPFAKE[4] = "How to play";
			OPFAKE[5] = "Resolution *";
			
				OP[0] = OPFAKE[1]; OP[1] = OPFAKE[2]; OP[2] = OPFAKE[3]; OP[3] = OPFAKE[4]; OP[4] = OPFAKE[5];
				OPF[0] = OPFFAKE[1]; OPF[1] = OPFFAKE[2]; OPF[2] = OPFFAKE[3]; OPF[3] = OPFFAKE[4]; OPF[4] = OPFFAKE[5];
			
			
			}
		}
		
		
	
		if (handler.getKeyManager().aDown && handler.getKeyManager().aUp || handler.getKeyManager().down && handler.getKeyManager().up || handler.getKeyManager().aDown && handler.getKeyManager().up || handler.getKeyManager().down && handler.getKeyManager().aUp) {	
		
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) && !acces|| handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && !acces) {
			
			if (i == 2) {
				OP[0] = OPFAKE[5]; OP[1] = OPFAKE[0]; OP[2] = OPFAKE[1]; OP[3] = OPFAKE[2]; OP[4] = OPFAKE[3];
				OPF[0] = OPFFAKE[5]; OPF[1] = OPFFAKE[0]; OPF[2] = OPFFAKE[1]; OPF[3] = OPFFAKE[2]; OPF[4] = OPFFAKE[3];
				i = 7;
				
			} else if (i == 7) {
				OP[0] = OPFAKE[4]; OP[1] = OPFAKE[5]; OP[2] = OPFAKE[0]; OP[3] = OPFAKE[1]; OP[4] = OPFAKE[2];
				OPF[0] = OPFFAKE[4]; OPF[1] = OPFFAKE[5]; OPF[2] = OPFFAKE[0]; OPF[3] = OPFFAKE[1]; OPF[4] = OPFFAKE[2];
				i = 6;
				
			} else if (i == 6) {
				OP[0] = OPFAKE[3]; OP[1] = OPFAKE[4]; OP[2] = OPFAKE[5]; OP[3] = OPFAKE[0]; OP[4] = OPFAKE[1];
				OPF[0] = OPFFAKE[3]; OPF[1] = OPFFAKE[4]; OPF[2] = OPFFAKE[5]; OPF[3] = OPFFAKE[0]; OPF[4] = OPFFAKE[1];
				i = 5;
				
			} else if (i == 5) {
				OP[0] = OPFAKE[2]; OP[1] = OPFAKE[3]; OP[2] = OPFAKE[4]; OP[3] = OPFAKE[5]; OP[4] = OPFAKE[0];
				OPF[0] = OPFFAKE[2]; OPF[1] = OPFFAKE[3]; OPF[2] = OPFFAKE[4]; OPF[3] = OPFFAKE[5]; OPF[4] = OPFFAKE[0];
				i = 4;
				
			} else if (i == 4) {
				OP[0] = OPFAKE[1]; OP[1] = OPFAKE[2]; OP[2] = OPFAKE[3]; OP[3] = OPFAKE[4]; OP[4] = OPFAKE[5];
				OPF[0] = OPFFAKE[1]; OPF[1] = OPFFAKE[2]; OPF[2] = OPFFAKE[3]; OPF[3] = OPFFAKE[4]; OPF[4] = OPFFAKE[5];
				i = 3;
				
			} else if (i == 3) {
				OP[0] = OPFAKE[0]; OP[1] = OPFAKE[1]; OP[2] = OPFAKE[2]; OP[3] = OPFAKE[3]; OP[4] = OPFAKE[4];
				OPF[0] = OPFFAKE[0]; OPF[1] = OPFFAKE[1]; OPF[2] = OPFFAKE[2]; OPF[3] = OPFFAKE[3]; OPF[4] = OPFFAKE[4];
				i = 2;
			}
			
			
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) && !acces|| handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && !acces) {
			
			if (i == 2) {
				OP[0] = OPFAKE[i - 1]; OP[1] = OPFAKE[i]; OP[2] = OPFAKE[i + 1]; OP[3] = OPFAKE[i + 2]; OP[4] = OPFAKE[i + 3];
				OPF[0] = OPFFAKE[i - 1]; OPF[1] = OPFFAKE[i]; OPF[2] = OPFFAKE[i + 1]; OPF[3] = OPFFAKE[i + 2]; OPF[4] = OPFFAKE[i + 3];
				i++;
				
			} else if (i == 3) {
				OP[0] = OPFAKE[i - 1]; OP[1] = OPFAKE[i]; OP[2] = OPFAKE[i + 1]; OP[3] = OPFAKE[i + 2]; OP[4] = OPFAKE[i - 3];
				OPF[0] = OPFFAKE[i - 1]; OPF[1] = OPFFAKE[i]; OPF[2] = OPFFAKE[i + 1]; OPF[3] = OPFFAKE[i + 2]; OPF[4] = OPFFAKE[i - 3];
				i++;
				
			} else if (i == 4) {
				OP[0] = OPFAKE[i - 1]; OP[1] = OPFAKE[i]; OP[2] = OPFAKE[i + 1]; OP[3] = OPFAKE[i - 4]; OP[4] = OPFAKE[i - 3];
				OPF[0] = OPFFAKE[i - 1]; OPF[1] = OPFFAKE[i]; OPF[2] = OPFFAKE[i + 1]; OPF[3] = OPFFAKE[i - 4]; OPF[4] = OPFFAKE[i - 3];
				i++;
				
			} else if (i == 5) {
				OP[0] = OPFAKE[i - 1]; OP[1] = OPFAKE[i]; OP[2] = OPFAKE[0]; OP[3] = OPFAKE[1]; OP[4] = OPFAKE[2];
				OPF[0] = OPFFAKE[i - 1]; OPF[1] = OPFFAKE[i]; OPF[2] = OPFFAKE[0]; OPF[3] = OPFFAKE[1]; OPF[4] = OPFFAKE[2];
				i ++;
				
				} else if (i == 6) {
					OP[0] = OPFAKE[i - 1]; OP[1] = OPFAKE[0]; OP[2] = OPFAKE[1]; OP[3] = OPFAKE[2]; OP[4] = OPFAKE[3];
					OPF[0] = OPFFAKE[i - 1]; OPF[1] = OPFFAKE[0]; OPF[2] = OPFFAKE[1]; OPF[3] = OPFFAKE[2]; OPF[4] = OPFFAKE[3];
					i ++;
					
				} else if (i == 7) {
					i = 2;
					OP[0] = OPFAKE[0]; OP[1] = OPFAKE[1]; OP[2] = OPFAKE[2]; OP[3] = OPFAKE[3]; OP[4] = OPFAKE[4];
					OPF[0] = OPFFAKE[0]; OPF[1] = OPFFAKE[1]; OPF[2] = OPFFAKE[2]; OPF[3] = OPFFAKE[3]; OPF[4] = OPFFAKE[4];
					
				}
		}
		
		//Retour
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !acces && i == 2) {
			acces = true;
			retour = true;
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !acces && i == 3) { // langue
			acces = true;
			langue = true;
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && i == 4) { // comment jouer
			if (Options.getLanguage() == "FR") {JOptionPane.showMessageDialog(null, "Bonne question !", "Comment jouer", JOptionPane.INFORMATION_MESSAGE);
			} else if (Options.getLanguage() == "ENG") {JOptionPane.showMessageDialog(null, "Good question !", "How to play", JOptionPane.INFORMATION_MESSAGE);}
			
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !acces && i == 6) { // Inventory + bouger
			acces = true;
			inventoryMove = true;
			
		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !acces && i == 7) {
			acces = true;
			fps = true;
		}
		
		
		
		
		
		
		
		
		
		
				
	}


	public void render(Graphics g) {
		
		if (acces && retour) { // transition, Main menu
			g.drawImage(Assets.mainMenu,x - 1125,0, handler.getWidth(), handler.getHeight(), null);
			
			if (Options.getLanguage() == "FR") {
			Text.drawString(g, "Menu principal", x - 750, 75, true, Color.BLACK, Assets.fontTell60);
			if (!MenuState.isStart_Continue()) {
			Text.drawString(g, "Commencer", x - 750, 210, true, Color.WHITE, Assets.fontTell60);
			} else { Text.drawString(g, "Continuer", x - 750, 210, true, Color.WHITE, Assets.fontTell60); }
			Text.drawString(g, "Options", x - 750, 380, true, Color.YELLOW, Assets.fontTell60);
			
			} else if (Options.getLanguage() == "ENG") {
			Text.drawString(g, "Main menu", x - 750, 75, true, Color.BLACK, Assets.fontTell60);
			if (!MenuState.isStart_Continue()) {
			Text.drawString(g, "Start", x - 750, 210, true, Color.WHITE, Assets.fontTell60);
			} else { Text.drawString(g, "Continue", x - 750, 210, true, Color.WHITE, Assets.fontTell60); }
			Text.drawString(g, "Options", x - 750, 380, true, Color.YELLOW, Assets.fontTell60);
			}
		}
		
		if (acces && langue) { // Transit, Language
			g.drawImage(Assets.optionsMenuBackground, x + 375, 0, handler.getWidth() + 20, handler.getHeight(), null);
			g.drawImage(Assets.confirmer, x + 875, 425, null);
			g.drawImage(Assets.confirmer, x + 475, 425, null);
			g.drawImage(Assets.option_Langue_invBouger, x + 542 , 295, 450, 50, null);
			
			if (Options.getLanguage() == "FR") {
			Text.drawString(g, "Français", x + 625, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "English", x + 905, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "Confirmer", x + 968, 448 , true, Color.RED, Assets.fontTell32);
			Text.drawString(g, "Retour", x + 568, 448 , true, Color.YELLOW, Assets.fontTell32);
			
			} else if (Options.getLanguage() == "ENG") {
			Text.drawString(g, "Français", x + 625, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "English", x + 905, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "Confirm", x + 968, 448 , true, Color.RED, Assets.fontTell32);
			Text.drawString(g, "Return", x + 568, 448 , true, Color.YELLOW, Assets.fontTell32);
			}
		}
		
		if (acces && inventoryMove) { // Transit, Inventory Move
			g.drawImage(Assets.optionsMenuBackground, x + 375, 0, handler.getWidth() + 20, handler.getHeight(), null);
			g.drawImage(Assets.confirmer, x + 875, 425, null);
			g.drawImage(Assets.confirmer, x + 475, 425, null);
			g.drawImage(Assets.option_Langue_invBouger, x + 542 , 295, 450, 50, null);
			
			if (Options.getLanguage() == "FR") {
			Text.drawString(g, "Activer", x + 625, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "Désactiver", x + 905, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "Confirmer", x + 968, 448 , true, Color.RED, Assets.fontTell32);
			Text.drawString(g, "Retour", x + 568, 448 , true, Color.YELLOW, Assets.fontTell32);
			
			} else if (Options.getLanguage() == "ENG") {
			Text.drawString(g, "On", x + 625, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "Off", x + 905, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "Confirm", x + 968, 448 , true, Color.RED, Assets.fontTell32);
			Text.drawString(g, "Return", x + 568, 448 , true, Color.YELLOW, Assets.fontTell32);
			}
		}
		
		
		if (acces && fps) { // Transit, fps
			g.drawImage(Assets.optionsMenuBackground, x + 375, 0, handler.getWidth() + 20, handler.getHeight(), null);
			g.drawImage(Assets.confirmer, x + 875, 425, null);
			g.drawImage(Assets.confirmer, x + 475, 425, null);
			g.drawImage(Assets.option_Langue_invBouger, x + 542 , 295, 450, 50, null);
			
			if (Options.getLanguage() == "FR") {
			Text.drawString(g, "60", x + 625, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "120", x + 905, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "Confirmer", x + 968, 448 , true, Color.RED, Assets.fontTell32);
			Text.drawString(g, "Retour", x + 568, 448 , true, Color.YELLOW, Assets.fontTell32);
			
			} else if (Options.getLanguage() == "ENG") {
			Text.drawString(g, "60", x + 625, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "120", x + 905, 320, true, Color.PINK, Assets.fontTell32);
			Text.drawString(g, "Confirm", x + 968, 448 , true, Color.RED, Assets.fontTell32);
			Text.drawString(g, "Return", x + 568, 448 , true, Color.YELLOW, Assets.fontTell32);
			}
		}
		
		
		
		g.drawImage(Assets.optionsMenu, x - 375, 0, handler.getWidth(), handler.getHeight(), null);
		if (!langue && !inventoryMove && !fps) {g.drawImage(Assets.optionsMenu_Selected, x - 158, 232, 316, 55, null);
		} else if (langue || inventoryMove || fps) {
			g.drawImage(Assets.optionsMenu_Selected, 215, ySelected - 25, 316, 55, null);
		}
		if (Options.getLanguage() == "FR") {
		Text.drawString(g, "Menu options", x, 55, true, Color.YELLOW, Assets.fontTell45);
		
		} else if (Options.getLanguage() == "ENG") {
		Text.drawString(g, "Options menu", x, 55, true, Color.YELLOW, Assets.fontTell45);
		}
		
		Text.drawString(g, OP[0], x, 135, true, Color.GRAY, OPF[0]);
		Text.drawString(g, OP[1], x, 200, true, Color.GRAY, OPF[1]);
		
		if (!langue && !inventoryMove && !fps) {Text.drawString(g, OP[2], x, 257, true, Color.WHITE, OPF[2]);
		} else if (langue || inventoryMove || fps) {
			Text.drawString(g, OP[2], 375, ySelected, true, Color.WHITE, OPF[2]);
		}
		
		
		Text.drawString(g, OP[3], x, 318, true, Color.GRAY, OPF[3]);
		Text.drawString(g, OP[4], x, 378, true, Color.GRAY, OPF[4]);
		
		if (!langue && !inventoryMove && !fps) {
		g.drawImage(Assets.menuCursor[3], (int) (((double)handler.getWidth() / 100) * 50) - 310 + xSword, 270 - ySword, 85, 25, null);
		
		} else if (langue && orientationSword == 0 || inventoryMove && orientationSword == 0 || fps && orientationSword == 0) {
			g.drawImage(Assets.menuCursor[3], (int) (((double)handler.getWidth() / 100) * 50) - 310 + xSword, 270 - ySword, 85, 25, null);
		} else if (langue && orientationSword == 1 || inventoryMove && orientationSword == 1|| fps && orientationSword == 1) {
			g.drawImage(Assets.menuCursor[6], (int) (((double)handler.getWidth() / 100) * 50) - 310 + xSword, 270 - ySword, 75, 75, null);
		} else if (langue && orientationSword == 2 || inventoryMove && orientationSword == 2 || fps && orientationSword == 2) {
			
			if (!swordDuplicate) {
			g.drawImage(Assets.menuCursor[0], (int) (((double)handler.getWidth() / 100) * 50) - 310 + xSword, 270 - ySword, 25, 85, null);
			} else if (swordDuplicate) {
				g.drawImage(Assets.menuCursor[0], 218, 270 - ySword, 25, 85, null);
				g.drawImage(Assets.menuCursor[0], (int) (((double)handler.getWidth() / 100) * 50) - 320 + xSword, 270 - ySword, 25, 85, null);
			
			}
		}
		
		
		
		
		if (acces && retour) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
			ticks++;
			delta --; }
			
			if(timer >= 1) { x += 16;
				if (x <= +778 && xSword >= 0) {
					ySword -= 2;
					xSword -= 2; }
				ticks = 0;
				timer = 0; }}
		
	
	    if (x > +1122 && acces && retour) {
	    ySword = 26;
		xSword = 26;	
		acces = false;
		retour = false;
		
		x = (int) (((double)handler.getWidth() / 100) * 50);
		State.setState(handler.getGame().menuState);
		}
	    
	    
		
		if (acces && langue || acces && inventoryMove || acces && fps) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
			ticks++;
			delta --; }
			
			if(timer >= 1) { 
				 if (x > -378) {x -= 16;}
				if (x <= -378 && ySelected >= 50) {ySelected -= 8; }
				
				if (ySword >= 27 && orientationSword != 2) {
					lastOri ++;
					if (lastOri >= 7) {
					orientationSword ++;
					lastOri = 0;}
				}
				
				if (x == -393 && ySword <= 110) {
					ySword += 4;
				}
				
				if (ySword >= 110 && xSword <= 455) {
					xSword += 6;
					if (xSword >= 160) {
						swordDuplicate = true;
					}
				}
				
				if (xSword >= 440) {
					finish = true;
				}
				
				}
				ticks = 0;
				timer = 0; 
		}
			
	    if (x <= -363 && acces && ySelected <= 50 && finish) {
		ySelected = 257;
		xSword = 26;
		ySword = 26;
		orientationSword = 0;
		x = (int) (((double)handler.getWidth() / 100) * 50);
		swordDuplicate = false;
		finish = false;
		acces = false;
		
		if (langue) {
		langue = false;	
		State.setState(handler.getGame().optionLangueState);
		} else if (inventoryMove) {
		inventoryMove = false;
		State.setState(handler.getGame().optionInventoryMoveState);
		} else if (fps) {
		fps = false;
		State.setState(handler.getGame().optionFpsState);
		}
		}
		
		
		
		
		
		
		
		

	}
	
	
	
	
	
	

}
