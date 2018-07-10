package tilegame.gfx;

import java.awt.image.BufferedImage;

import tilegame.Handler;

public class Animation {

	private static int speed;
	private int indexMouvement;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation (int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		indexMouvement = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		//System.out.println(speed);
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if (timer > speed) {
			
			indexMouvement++;
			timer = 0;
			if (indexMouvement >= frames.length) {indexMouvement = 0;}
		}
		
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[indexMouvement];
	}

	public static void setSpeed(int s) {
		speed = s;
	}

	
	
	
	
}
