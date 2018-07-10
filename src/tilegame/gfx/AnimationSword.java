package tilegame.gfx;

import java.awt.image.BufferedImage;

public class AnimationSword {
	


		int speed;
		private static int index;
		private long lastTime, timer;
		private BufferedImage[] frames;
		
		public AnimationSword (int d, BufferedImage[] frames) {
			this.speed = d;
			this.frames = frames;
			index = 0;
			timer = 0;
			lastTime = System.currentTimeMillis();
		}
		
		public void tick() {
			
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			
			if (timer > speed) {
				index++;
				timer = 0;
				
			}
			
		}
		
		
		public BufferedImage getCurrentFrame() {
			return frames[index];
		}
		
	
	
		public static void setIndex(int num) {
			index = num;
		}
		
	
	}
