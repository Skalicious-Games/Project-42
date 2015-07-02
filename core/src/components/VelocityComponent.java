/*
 * Component that contains x and y variables for speeds in x and y directions of an entity
 */

package components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent extends Component {
	public float x;
	public float y;
	
	public VelocityComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
