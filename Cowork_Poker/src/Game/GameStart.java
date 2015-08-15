package Game;

import Frame.Window;

public class GameStart {
	
	public static void main(String[] args) {
		
		Game game = new Game();
		Window window = new Window(800, 600, "Game", game);
		
		game.start();
	}
}
