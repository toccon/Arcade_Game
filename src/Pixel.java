
public class Pixel {
	Location location;
	int color;

	public Pixel(Location p, int color) {
		this.location = p;
		this.color = color;
	}
	public Location getLocation(){
		return this.location;
	}
	public int getColor(){
		return this.color;
	}

}
