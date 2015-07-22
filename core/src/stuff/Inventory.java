package stuff;

import com.badlogic.ashley.core.Entity;

public class Inventory {
	
	private Entity[][] inv;
	
	public Inventory(int x, int y) {
		inv = new Entity[x][y];
	}
	
	//no stacking of items yet
	public boolean addItem(Entity ent) {
		for (int i = 0; i < inv.length; i++) {
			for (int j = 0; j < inv[0].length; j++) {
				if (inv[i][j] == null) {
					inv[i][j] = ent;
					return true;
				}
			}
		}
		return false;
	}
	
	public Entity removeItem(Entity ent) {
		for (int i = 0; i < inv.length; i++) {
			for (int j = 0; j < inv[0].length; j++) {
				if (inv[i][j] == ent) {
					inv[i][j] = null;
					return ent;
				}
			}
		}
		return null;
	}
	
	public Entity removeItem(int x, int y) {
		Entity ent = inv[x][y];
		inv[x][y] = null;
		return ent;
	}
	
	public void moveItem(int x1, int y1, int x2, int y2) {
		Entity temp = inv[x2][y2];
		inv[x2][y2] = inv[x1][y1];
		inv[x1][y1] = temp;
	}

}
