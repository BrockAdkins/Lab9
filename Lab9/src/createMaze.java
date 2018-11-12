
public class createMaze {
	private int block;
	private int returnValue;

	public createMaze(int block) {
		super();
		this.block = block;
	}
	
	public int setUp() {
		if (block==1) {
			returnValue=1;
		}
		else if (block==0) {
			returnValue=0;
		}
		return returnValue;
	}

}
