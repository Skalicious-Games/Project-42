/*
 * Component that specifies in what order things should be rendered
 * ex, 4=far background, 3=near background, 2=static objects, 1=moving objects
 * to be rendered in order of largest to smallest
 */

package components;

import com.badlogic.ashley.core.Component;

public class RenderPriorityComponent extends Component {
	public float priority;
	
	public RenderPriorityComponent (int priority) {
		this.priority = priority;
	}
}
