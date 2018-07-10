package tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import tilegame.display.Display;
import tilegame.entities.creatures.Player;
import tilegame.gfx.AnimationSword;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.input.MouseManager;
import tilegame.states.GameState;
import tilegame.states.MenuState;
import tilegame.states.OptionFpsState;
import tilegame.states.OptionInventaireMoveState;
import tilegame.states.OptionLangueState;
import tilegame.states.OptionState;
import tilegame.states.State;




public class Game implements Runnable{
	
	private Display display;
	private int width, height;
	public String title;
	private boolean active;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	
	//States
	public State gameState;
	public State menuState;
	public State optionState;
	public State optionLangueState;
	public State optionInventoryMoveState;
	public State optionFpsState;
	
	//Imputs
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	
	
	public Game(String title, int width, int height) {// constructeur (comme tempShop pour crafta)
		this.width = width;
		this.height = height;            
		this.title = title;
		keyManager = new KeyManager();
		}
	
	
	
	private void init() {  // le screen et les images
		display = new Display (title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		optionState = new OptionState(handler);
		optionLangueState = new OptionLangueState(handler);
		optionInventoryMoveState = new OptionInventaireMoveState(handler);
		optionFpsState = new OptionFpsState(handler);
		State.setState(menuState); // state de base
		
		
		
	}
	
	private void tick() {     
		keyManager.tick();
		//mouseManager.tick();
		
		if (State.getState() != null) {
			State.getState().tick();		}
		
		
		
		
		
	}
	
	



	private void render() throws InterruptedException {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		
		
		//Clear Screen !
		width = display.getWidth();
		height = display.getHeight();
		g.clearRect(0, 0, width, height);
		//Draw Here !
		
		if (State.getState() != null) {
			State.getState().render(g);		}
		
		
		
		
		//End Drawing!
		bs.show();
		g.dispose();
		
		
		
	}
	
	public void run() { // methode "main" + certaines variables pour les fps
		init();
		
		
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			double timePerTick = 1000000000 / Options.fps;
			now = System.nanoTime();
			
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			
			if(delta >= Options.fps / 60) {
			tick();
			try {
				render();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			delta -= Options.fps / 60;
			ticks+= Options.fps / 60;
			}
			
			
			
			if(timer >= 1000000000) {
				System.out.println("FPS :" + ticks);
				ticks = 0;
				timer = 0;
			}
		
			// option State
			optionTick();
			
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeigth() {
		return height;
	}
	
	public synchronized void start() {   //start
		if (running) {  return;  }
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {             // stop, compris.
		if(!running) { return; }
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void optionTick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			active = !active;}
		if (!active) {return;}
		
		if (getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) && State.getState() == gameState) {
			
			if (Player.isRight() || Player.isLeft() || Player.isDown() || Player.isUpp()) {
				Player.setaDown(false); Player.setaUpp(false); Player.setaLeft(false); Player.setaRight(false); 
				Player.setTemp(0);
				AnimationSword.setIndex(0);
			}
				
				
		State.setState(menuState);
		
		
			
		}
	}
	

	
	
	
	
	
	
	
	
	
	
}