package stuff;

import com.badlogic.ashley.core.Entity;

public class Inventory {
	
	private Entity[][] inv;
	
	public Inventory(int x, int y) {
		inv = new Entity[x][y];
	}

}
