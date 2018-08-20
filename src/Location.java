
public class Location {
	int xcord;
	int ycord;

	public Location(int x, int y) {
		this.xcord = x;
		this.ycord = y;
	}
	public int xCoord(){
		return this.xcord;
	}
	public int yCoord(){
		return this.ycord;
	}
	public int compareTo(Location p){
		if(this.xcord == p.xcord && this.ycord == p.ycord){
			return 0;
		}
		else if(this.xcord > p.xcord){
			return 1;
		}
		else if(this.xcord == p.xcord && this.ycord > p.ycord){
			return 1;
		}
		else if(p.xcord > this.xcord){
			return -1;
		}
		else{
			return -1;
		}
	}

}
