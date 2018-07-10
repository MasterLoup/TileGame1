package tilegame;

public class Options {
	
	// Bouger + inventaire
	public static boolean inventoryMove = true;
	
	// Languages
	public static String Language = "FR";
	
	// FPS
	public static int fps = 120;
	
	
	
	
	
	
	
	
	
	public static boolean isInventoryMove() {
		return inventoryMove;
	}

	public static void setInventoryMove(boolean inventoryMove) {
		Options.inventoryMove = inventoryMove;
	}

	public static String getLanguage() {
		return Language;
	}

	public static void setLanguage(String language) {
		Language = language;
	}

	public static void setFps(int fps) {
		Options.fps = fps;
	}
	
	
	
	
	

}
