package components;

import stuff.Inventory;

import com.badlogic.ashley.core.Component;

public class InventoryComponent extends Component {
	
	private Inventory inventory;
	private boolean isOpen;
	
	public InventoryComponent(int x, int y) {
		inventory = new Inventory(x, y);
	}
	
	public void setOpen(boolean open) {
		isOpen = open;
	}
	
	public boolean isOpen() {
		return isOpen;
	}

}
