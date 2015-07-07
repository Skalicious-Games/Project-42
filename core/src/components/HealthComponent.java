package components;

import com.badlogic.ashley.core.Component;

public class HealthComponent extends Component {
	public int health;
	
	public HealthComponent (int health) {
		this.health = health;
	}
}
