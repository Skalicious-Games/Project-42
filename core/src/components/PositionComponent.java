/*
 * Component that connects x and y variables for the position of an entity
 */

package components;

import com.badlogic.ashley.core.Component;

public class PositionComponent extends Component {
	public float x;
	public float y;
	
	public PositionComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
