
public abstract class PlayerUnit extends Unit {

	public PlayerUnit(int x, int y, int speedX, int speedY, String name) {
		super(x, y, speedX, speedY);
		setProperties(name);
	}
	public PlayerUnit(int x, int y, String name) {
		super(x, y);
		setProperties(name);
	}
	
	private void setProperties(String name) {
		maxMagazine = 50;
		magazine = maxMagazine;
		this.name = name;
	}
	
	public void shoutClass() {
		System.out.printf("%s\n", this.name);
	}
	
	public String getName() {
		return this.name;
	}

}
