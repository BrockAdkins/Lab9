
public class Player {
	private String name;
	private String image;
	private int score;
	private String movement;
	private int countup = 0;
	private int countdown = 0;
	private int countleft = 0;
	private int countright = 0;
	
	
	
	
	public Player() {
		super();

	}




	public String getImage() {
		return image;
	}




	public int move(String movement) {
		
		if (movement.equals("up")) {
			//countup+=1;
			return -5; //* countup;
		}
		else if (movement.equals("down")) {
			//countdown+=1;
			return 5;// * countdown;
		}
		else if (movement.equals("right")) {
			//countright+=1;
			return 5;// * countright;
		}
		else if (movement.equals("left")) {
			//countleft+=1;
			return -5;// * countleft;
		}
		else {
			return 5;
		}
		
	}
	
		

}
