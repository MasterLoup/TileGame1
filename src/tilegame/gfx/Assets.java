package tilegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private  static final int width = 64, height = 64;
	private static final int swordWidth = 20, swordHeight = 17;
	
	public static Font font5, font10, font15, font20, font25, font30, font35, font40, font45, font50, font55, font60;
	public static Font fontTell60, fontTell50, fontTell45, fontTell32;
	
	// Tile
	public static BufferedImage dirt, grass, stone, tree, rock;
	// Item
	public static BufferedImage wood;
	// Player
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	// Épé
	public static BufferedImage[] sword_up, sword_right, sword_down, sword_left;
	// Invente ton ris
	public static BufferedImage inventoryScreen;
	
	
	// Menu
			// Main Menu
	public static BufferedImage mainMenu;
			// Options Menu
	public static BufferedImage optionsMenu;
			// Menu Cursor (Sword, may be temporarly)
	public static BufferedImage[] menuCursor;
			// Options Menu Fond
	public static BufferedImage optionsMenuBackground;
			// confirm
	public static BufferedImage confirmer;
			// Langue_invBouger
	public static BufferedImage option_Langue_invBouger;
			// optionsMenu_Selected
	public static BufferedImage optionsMenu_Selected;
			// FPS
	public static BufferedImage options_FPS;
	
	//End of instance
	
	
	public static void init() {
		// Font
		font5 = FontLoader.loadFont("res/fonts/slkscr.ttf", 5);
		font10 = FontLoader.loadFont("res/fonts/slkscr.ttf", 10);
		font15 = FontLoader.loadFont("res/fonts/slkscr.ttf", 15);
		font20 = FontLoader.loadFont("res/fonts/slkscr.ttf", 20);
		font25 = FontLoader.loadFont("res/fonts/slkscr.ttf", 25);
		font30 = FontLoader.loadFont("res/fonts/slkscr.ttf", 30);
		font35 = FontLoader.loadFont("res/fonts/slkscr.ttf", 35);
		font40 = FontLoader.loadFont("res/fonts/slkscr.ttf", 40);
		font45 = FontLoader.loadFont("res/fonts/slkscr.ttf", 45);
		font50 = FontLoader.loadFont("res/fonts/slkscr.ttf", 50);
		font55 = FontLoader.loadFont("res/fonts/slkscr.ttf", 55);
		font60 = FontLoader.loadFont("res/fonts/slkscr.ttf", 60);
		
		fontTell60 = FontLoader.loadFont("res/fonts/Just tell me what regular-Italic (Version 2).otf", 60);
		fontTell50 = FontLoader.loadFont("res/fonts/Just tell me what regular-Italic (Version 2).otf", 50);
		fontTell45 = FontLoader.loadFont("res/fonts/Just tell me what regular-Italic (Version 2).otf", 45);
		fontTell32 = FontLoader.loadFont("res/fonts/Just tell me what regular-Italic (Version 2).otf", 32);
		
		
		
		//Game Sprite (not the boisson.... Vivaa la Root beer)..ca.com.point!
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/crissdemardesaledephotoshop.png"));
		SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/Player.png"));
		SpriteSheet sword = new SpriteSheet(ImageLoader.loadImage("/textures/Épé_Sheet1_0_20x17.png"));
		
		// Menu SHIIIT... but maybe sexy menu !!
		mainMenu = ImageLoader.loadImage("/textures/Menu_Shit/MainMenu.png");
		optionsMenu = ImageLoader.loadImage("/textures/Menu_Shit/OptionMenu.png");
		SpriteSheet meMenuCursor = new SpriteSheet(ImageLoader.loadImage("/textures/Menu_Shit/Menu_Cursor.png"));
		optionsMenuBackground = ImageLoader.loadImage("/textures/Menu_Shit/Options_Fond.png");
		confirmer = ImageLoader.loadImage("/textures/Menu_Shit/Confirmer.png");
		option_Langue_invBouger = (ImageLoader.loadImage("/textures/Menu_Shit/Option_Langue_invBouger.png"));
		optionsMenu_Selected = ImageLoader.loadImage("/textures/Menu_Shit/OptionMenu_Selected.png");
		options_FPS = ImageLoader.loadImage("/textures/Menu_Shit/Options_FPS.png");
		
		// // Inventory as you can fucking see you litlle son of a... SON !!!! (wich is a lso a son of a mother.. yeah its important okay.!)
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		wood = sheet.crop(0, 0, 30, 26);
		
		
		player_up = new BufferedImage[9];
		player_left = new BufferedImage[9];
		player_down = new BufferedImage[9];
		player_right = new BufferedImage[9];
		
		
		sword_up = new BufferedImage[9];
		sword_right = new BufferedImage[9];
		sword_down = new BufferedImage[9];
		sword_left = new BufferedImage[9];
		
		
		
		menuCursor = new BufferedImage[8];
		
		
		// Player UP
		player_up[0] = player.crop(0, 0, width, height);
		player_up[1] = player.crop(width, 0, width, height);
		player_up[2] = player.crop(width * 2, 0, width, height);
		player_up[3] = player.crop(width * 3, 0, width, height);
		player_up[4] = player.crop(width * 4, 0, width, height);
		player_up[5] = player.crop(width * 5, 0, width, height);
		player_up[6] = player.crop(width * 6, 0, width, height);
		player_up[7] = player.crop(width * 7, 0, width, height);
		player_up[8] = player.crop(width * 8, 0, width, height);
		// Player LEFT
		player_left[0] = player.crop(0, height, width, height);
		player_left[1] = player.crop(width, height, width, height);
		player_left[2] = player.crop(width * 2, height, width, height);
		player_left[3] = player.crop(width * 3, height, width, height);
		player_left[4] = player.crop(width * 4, height, width, height);
		player_left[5] = player.crop(width * 5, height, width, height);
		player_left[6] = player.crop(width * 6, height, width, height);
		player_left[7] = player.crop(width * 7, height, width, height);
		player_left[8] = player.crop(width * 8, height, width, height);
		// Player DOWN
		player_down[0] = player.crop(0, height * 2, width, height);
		player_down[1] = player.crop(width, height * 2, width, height);
		player_down[2] = player.crop(width * 2, height * 2, width, height);
		player_down[3] = player.crop(width * 3, height * 2, width, height);
		player_down[4] = player.crop(width * 4, height * 2, width, height);
		player_down[5] = player.crop(width * 5, height * 2, width, height);
		player_down[6] = player.crop(width * 6, height * 2, width, height);
		player_down[7] = player.crop(width * 7, height * 2, width, height);
		player_down[8] = player.crop(width * 8, height * 2, width, height);
		// Player RIGHT
		player_right[0] = player.crop(0, height * 3, width, height);
		player_right[1] = player.crop(width, height * 3, width, height);
		player_right[2] = player.crop(width * 2, height * 3, width, height);
		player_right[3] = player.crop(width * 3, height * 3, width, height);
		player_right[4] = player.crop(width * 4, height * 3, width, height);
		player_right[5] = player.crop(width * 5, height * 3, width, height);
		player_right[6] = player.crop(width * 6, height * 3, width, height);
		player_right[7] = player.crop(width * 7, height * 3, width, height);
		player_right[8] = player.crop(width * 8, height * 3, width, height);
		
		
		// Sword Left
		sword_left[0] = sword.crop(0, 0, 20, 17);
		sword_left[1] = sword.crop(swordWidth, 0, 20, 17);
		sword_left[2] = sword.crop(swordWidth * 2, 0, 20, 17);
		sword_left[3] = sword.crop(swordWidth * 3, 0, 20, 17);
		sword_left[4] = sword.crop(swordWidth * 4, 0, 20, 17);
		sword_left[5] = sword.crop(swordWidth * 5, 0, 20, 17);
		sword_left[6] = sword.crop(swordWidth * 6, 0, 20, 17);
		sword_left[7] = sword.crop(swordWidth * 7, 0, 20, 17);
		sword_left[8] = sword.crop(swordWidth * 8, 0, 20, 17);
		// Sword Right
		sword_right[0] = sword.crop(0, swordHeight, 20, 17);
		sword_right[1] = sword.crop(swordWidth, swordHeight, 20, 17);
		sword_right[2] = sword.crop(swordWidth * 2, swordHeight, 20, 17);
		sword_right[3] = sword.crop(swordWidth * 3, swordHeight, 20, 17);
		sword_right[4] = sword.crop(swordWidth * 4, swordHeight, 20, 17);
		sword_right[5] = sword.crop(swordWidth * 5, swordHeight, 20, 17);
		sword_right[6] = sword.crop(swordWidth * 6, swordHeight, 20, 17);
		sword_right[7] = sword.crop(swordWidth * 7, swordHeight, 20, 17);
		sword_right[8] = sword.crop(swordWidth * 8, swordHeight, 20, 17);
		// Sword Up
		sword_up[0] = sword.crop(0, swordHeight * 2, 20, 17);
		sword_up[1] = sword.crop(swordWidth, swordHeight * 2, 20, 17);
		sword_up[2] = sword.crop(swordWidth * 2, swordHeight * 2, 20, 17);
		sword_up[3] = sword.crop(swordWidth * 3, swordHeight * 2, 20, 17);
		sword_up[4] = sword.crop(swordWidth * 4, swordHeight * 2, 20, 17);
		sword_up[5] = sword.crop(swordWidth * 5, swordHeight * 2, 20, 17);
		sword_up[6] = sword.crop(swordWidth * 6, swordHeight * 2, 20, 17);
		sword_up[7] = sword.crop(swordWidth * 7, swordHeight * 2, 20, 17);
		sword_up[8] = sword.crop(swordWidth * 8, swordHeight * 2, 20, 17);
		// Sword Down
		sword_down[0] = sword.crop(0, swordHeight * 3, 20, 17);
		sword_down[1] = sword.crop(swordWidth, swordHeight * 3, 20, 17);
		sword_down[2] = sword.crop(swordWidth * 2, swordHeight * 3, 20, 17);
		sword_down[3] = sword.crop(swordWidth * 3, swordHeight * 3, 20, 17);
		sword_down[4] = sword.crop(swordWidth * 4, swordHeight * 3, 20, 17);
		sword_down[5] = sword.crop(swordWidth * 5, swordHeight * 3, 20, 17);
		sword_down[6] = sword.crop(swordWidth * 6, swordHeight * 3, 20, 17);
		sword_down[7] = sword.crop(swordWidth * 7, swordHeight * 3, 20, 17);
		sword_down[8] = sword.crop(swordWidth * 8, swordHeight * 3, 20, 17);
		
		// crop tout les carrer du sprite sheet
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);              
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, height, width, height *2);
		rock = sheet.crop(width * 3, height, width, height);
		
		// menu Cursor
		menuCursor[0] = meMenuCursor.crop(0, 0, 16, 61);
		menuCursor[1] = meMenuCursor.crop(16, 0, 16, 61);
		menuCursor[2] = meMenuCursor.crop(32, 0, 61, 16);
		menuCursor[3] = meMenuCursor.crop(32, 16, 61, 16);
		menuCursor[4] = meMenuCursor.crop(99, 0, 46, 52);
		menuCursor[5] = meMenuCursor.crop(148, 0, 46, 52);
		menuCursor[6] = meMenuCursor.crop(147, 55, 48, 48);
		menuCursor[7] = meMenuCursor.crop(98, 55, 47, 47);
		
		
		
	}

		
		
}
