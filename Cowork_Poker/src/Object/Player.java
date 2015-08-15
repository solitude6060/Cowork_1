package Object;

public class Player {

	private int cash;
	
	public Player(int cash) {
		this.cash = cash;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}
	
	public void addCash(int cash) {
		this.cash += cash;
	}
	
}
