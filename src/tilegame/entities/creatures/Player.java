package tilegame.entities.creatures;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;



import tilegame.Handler;
import tilegame.Options;
import tilegame.gfx.Animation;
import tilegame.gfx.AnimationSword;
import tilegame.gfx.Assets;
import tilegame.inventory.Inventory;
import tilegame.entities.Entity;

public class Player extends Creature {
	
	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	private AnimationSword animSwordDown, animSwordUp, animSwordLeft, animSwordRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 300, attackTimer = attackCooldown;
	//  Inventory
	private Inventory inventory;
	
	// Speed of the player
	private int playerSpeed = (int) speed;
	private int playerAnimationSpeed = 75;
	
	// Power
	public static double mana = 500;
	public static double manaMax = 500;
	
	
	private int aHeight, aWidth;
	private static int temp;
	private static boolean aUpp, aDown, aLeft, aRight;
	


	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 21;
		bounds.y = 45;
		bounds.width = 21;
		bounds.height = 15;
		
		
		// Animations Player
		animDown = new Animation(playerAnimationSpeed, Assets.player_down);
		animUp = new Animation(playerAnimationSpeed, Assets.player_up);
		animLeft = new Animation(playerAnimationSpeed, Assets.player_left);
		animRight = new Animation(playerAnimationSpeed, Assets.player_right);
		
		// Animations Sword
		animSwordDown = new AnimationSword(27, Assets.sword_down);
		animSwordUp = new AnimationSword(27, Assets.sword_up);
		animSwordLeft = new AnimationSword(27, Assets.sword_left);
		animSwordRight = new AnimationSword(27, Assets.sword_right);
		
		inventory = new Inventory(handler);
		
		
	}
	

	public void swordTick() {
		if (aDown == true) {
		animSwordDown.tick();} else if (aUpp == true) {
		animSwordUp.tick();} else if (aLeft == true) {
		animSwordLeft.tick(); } else {
		animSwordRight.tick();}
	}
	
	public void tick() {
		// Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		
		
		// Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		// Attaque
		checkAttacks();
		// Inventory
		inventory.tick();

		
		
	}
	
	public void checkAttacks() {

		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown) {
			return;
		}
		
		Rectangle atkBoxes = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 17;
		int xrSize = 20;
		ar.width = xrSize;
		ar.height = arSize;
		
		if (handler.getKeyManager().aUp) {
			ar.x = atkBoxes.x;
			ar.y = atkBoxes.y + atkBoxes.height - arSize * 2 + 2;
			getAtt(ar.height, ar.width);
			aUpp = true;
		} else if (handler.getKeyManager().aDown) {
			ar.x = atkBoxes.x;
			ar.y = atkBoxes.y + atkBoxes.height + arSize / 2 - 8;
			getAtt(ar.height, ar.width);
			aDown = true;
		} else if (handler.getKeyManager().aLeft) {
			ar.x = atkBoxes.x - xrSize;
			ar.y = atkBoxes.y + atkBoxes.height / 2 - arSize / 2;
			getAtt(ar.height, ar.width);
			aLeft = true;
		} else if (handler.getKeyManager().aRight) {
			ar.x = atkBoxes.x + atkBoxes.width;
			ar.y = atkBoxes.y + atkBoxes.height / 2 - arSize / 2;
			getAtt(ar.height, ar.width);
			aRight = true;
		} else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) { continue; }
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				System.out.println("TOUCHERRRRR");
				return;
			}
			
		}
	}
	
	public void die() {
		System.out.println("You lose");
	}
	
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if (handler.getKeyManager().shift && mana > 1.5 && !aDown && !aLeft && !aRight && !aUpp) {
			if (!handler.getKeyManager().left && !handler.getKeyManager().right && !handler.getKeyManager().up && !handler.getKeyManager().down || handler.getKeyManager().down && handler.getKeyManager().up || handler.getKeyManager().left && handler.getKeyManager().right) {
			} else {
			if (Inventory.isactiveInventory() && !Options.inventoryMove) {
				
			} else {
			playerSpeed = 5;
			mana -= 2;
			Animation.setSpeed(40);
			}
			}
			
		} 
		
		if (mana < manaMax) {
			
			if (handler.getKeyManager().shift) {
				
				if (handler.getKeyManager().left && handler.getKeyManager().right || handler.getKeyManager().down && handler.getKeyManager().up) {
					mana += 0.5;
				} else if (handler.getKeyManager().left || handler.getKeyManager().right || handler.getKeyManager().up || handler.getKeyManager().down) {
					if (Inventory.isactiveInventory() && !Options.inventoryMove) {mana += 0.5;} else if (aDown || aLeft || aRight || aUpp) {mana += 0.5;}
				} else {
					mana += 0.5;
				}
				
				
			} else {
				mana += 0.5;
			}
			
			
		} 
		
		if (!handler.getKeyManager().shift || mana <= 1.5){
			Animation.setSpeed(75);
		}
		
		
		// Si linventaire es alumer, et que le joueur ne veut pas bouger dans linventaire, ne pas bouger
		if (!Options.inventoryMove && Inventory.isactiveInventory()) {}
		
		// droite et gauche, haut et bas
		else if (handler.getKeyManager().right && handler.getKeyManager().left || handler.getKeyManager().up && handler.getKeyManager().down) {}
		// haut + gauche
		else if (handler.getKeyManager().up && handler.getKeyManager().left  && aDown == false && aUpp == false && aLeft == false && aRight == false && handler.getKeyManager().aUp == false && handler.getKeyManager().aDown == false && handler.getKeyManager().aLeft == false && handler.getKeyManager().aRight == false) {xMove = -speed; yMove = -playerSpeed;}
		//haut + droite
		else if (handler.getKeyManager().up && handler.getKeyManager().right  && aDown == false && aUpp == false && aLeft == false && aRight == false && handler.getKeyManager().aUp == false && handler.getKeyManager().aDown == false && handler.getKeyManager().aLeft == false && handler.getKeyManager().aRight == false) {xMove = playerSpeed; yMove = -playerSpeed;}
		// haut
		else if (handler.getKeyManager().up  && aDown == false && aUpp == false && aLeft == false && aRight == false && handler.getKeyManager().aUp == false && handler.getKeyManager().aDown == false && handler.getKeyManager().aLeft == false && handler.getKeyManager().aRight == false) {yMove = -playerSpeed;}
		// bas + gauche
		else if (handler.getKeyManager().down && handler.getKeyManager().left  && aDown == false && aUpp == false && aLeft == false && aRight == false && handler.getKeyManager().aUp == false && handler.getKeyManager().aDown == false && handler.getKeyManager().aLeft == false && handler.getKeyManager().aRight == false) {xMove = -playerSpeed; yMove = playerSpeed;}
		// bas + droite
		else if (handler.getKeyManager().down && handler.getKeyManager().right  && aDown == false && aUpp == false && aLeft == false && aRight == false && handler.getKeyManager().aUp == false && handler.getKeyManager().aDown == false && handler.getKeyManager().aLeft == false && handler.getKeyManager().aRight == false) {xMove = playerSpeed; yMove = playerSpeed;}
		// bas
		else if (handler.getKeyManager().down && aDown == false && aUpp == false && aLeft == false && aRight == false && handler.getKeyManager().aUp == false && handler.getKeyManager().aDown == false && handler.getKeyManager().aLeft == false && handler.getKeyManager().aRight == false) {yMove = playerSpeed;}
		// gauche
		else if (handler.getKeyManager().left  && aDown == false && aUpp == false && aLeft == false && aRight == false && handler.getKeyManager().aUp == false && handler.getKeyManager().aDown == false && handler.getKeyManager().aLeft == false && handler.getKeyManager().aRight == false) {xMove -= playerSpeed;}
		// droite
		else if (handler.getKeyManager().right && aDown == false && aUpp == false && aLeft == false && aRight == false && handler.getKeyManager().aUp == false && handler.getKeyManager().aDown == false && handler.getKeyManager().aLeft == false && handler.getKeyManager().aRight == false) {xMove = playerSpeed;}
		
		
		
		if (playerSpeed == 5) {
			playerSpeed = 3;
			
		}
		
		
		
	
	
	
	}
		
	
	
	
	
	

	public void render(Graphics g) {
		if (aDown == false && aUpp == false && aLeft == false && aRight == false) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		}
//				g.setColor(Color.CYAN);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
		
		g.setColor(Color.RED);
		
		
		 // Attack RIGHT
		if (attackTimer < attackCooldown && aRight == true) {
//		g.fillRect( (int) (x + bounds.x - handler.getGameCamera().getxOffset() + aWidth) , 
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), aWidth, aHeight - 2);
		if (temp <= 9) { swordTick();
		g.drawImage(getCurrentAnimationFrameSword() , (int) (x + bounds.x - handler.getGameCamera().getxOffset() + aWidth) - 3 , 
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()) - 6, null);} 		temp ++;
		g.drawImage(Assets.player_right[2],  (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		if (attackTimer > attackCooldown - 25) { aRight = false; temp = 0; AnimationSword.setIndex(0);} 
		}// Attack LEFT
		
		
		else if (attackTimer < attackCooldown && aLeft == true) {
//			g.fillRect( (int) (x + bounds.x - handler.getGameCamera().getxOffset() - aWidth) , 
//					(int) (y + bounds.y - handler.getGameCamera().getyOffset()), aWidth, aHeight - 2);
			g.drawImage(Assets.player_left[5],  (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			if (temp <= 9) { swordTick();
				g.drawImage(getCurrentAnimationFrameSword() ,(int) (x + bounds.x - handler.getGameCamera().getxOffset() - aWidth + 2) , 
						(int) (y + bounds.y - handler.getGameCamera().getyOffset()) - 4, null);} 			temp ++;
			
			if (attackTimer > attackCooldown - 25) { aLeft = false; temp = 0; AnimationSword.setIndex(0);} 
		}// Attack UPP
		
		
		else if (attackTimer < attackCooldown && aUpp == true) {
//			g.fillRect( (int) (x + bounds.x - handler.getGameCamera().getxOffset()) , 
//					(int) (y + bounds.y - handler.getGameCamera().getyOffset() - aHeight), aWidth + 1, aHeight);
			if (temp <= 9) { swordTick();
				g.drawImage(getCurrentAnimationFrameSword() , (int) (x + bounds.x - handler.getGameCamera().getxOffset()) + 8 , 
						(int) (y + bounds.y - handler.getGameCamera().getyOffset() - aHeight) - 7, null);} 			temp ++;
			g.drawImage(Assets.player_up[3],  (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			if (attackTimer > attackCooldown - 25) { aUpp = false; temp = 0; AnimationSword.setIndex(0);}
		}// Attack DOWN
		
		
		else if (attackTimer < attackCooldown && aDown == true) {
//			g.fillRect( (int) (x + bounds.x - handler.getGameCamera().getxOffset() ) , 
//					(int) (y + bounds.y - handler.getGameCamera().getyOffset() + aHeight - 2), aWidth + 1, aHeight);
            g.drawImage(Assets.player_down[3],  (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			if (temp <= 9) { swordTick();
				g.drawImage(getCurrentAnimationFrameSword() ,  (int) (x + bounds.x - handler.getGameCamera().getxOffset() - 5) , 
						(int) (y + bounds.y - handler.getGameCamera().getyOffset() + aHeight - 2 - 9), null);} 			temp ++;
			if (attackTimer > attackCooldown - 25) { aDown = false; temp = 0; AnimationSword.setIndex(0);}
		}
	
	

	
	} 
	
	
	public void postRender(Graphics g) {
		inventory.render(g);
		
	}

	
	
	
	public void getAtt(int aHeight, int aWidth) {
		this.aHeight = aHeight;
		this.aWidth = aWidth;
	}
	
	
	private BufferedImage getCurrentAnimationFrame() { // Animation joueur
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0){
			return animDown.getCurrentFrame();
		} else {
			return Assets.player_down[5];
		}
	}
	

	private BufferedImage getCurrentAnimationFrameSword() {
		if (aUpp) {
			return animSwordUp.getCurrentFrame();
		} else if (aDown) {
			return animSwordDown.getCurrentFrame();
		} else if (aLeft) {
			return animSwordLeft.getCurrentFrame();
		} else if (aRight) {
			return animSwordRight.getCurrentFrame();
		} else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public static boolean isUpp() {
		return aUpp;
	}

	public static boolean isDown() {
		return aDown;
	}

	public static boolean isLeft() {
		return aLeft;
	}

	public static boolean isRight() {
		return aRight;
	}

	public static void setaUpp(boolean aUpp) {
		Player.aUpp = aUpp;
	}

	public static void setaDown(boolean aDown) {
		Player.aDown = aDown;
	}

	public static void setaLeft(boolean aLeft) {
		Player.aLeft = aLeft;
	}

	public static void setaRight(boolean aRight) {
		Player.aRight = aRight;
	}

	public static void setTemp(int t) {
		temp = t;
	}

	public static double getMana() {
		return mana;
	}

	public static void setMana(double mana) {
		Player.mana = mana;
	}

	public static double getManaMax() {
		return manaMax;
	}

	public static void setManaMax(double manaMax) {
		Player.manaMax = manaMax;
	}

	
	
	
	

}
