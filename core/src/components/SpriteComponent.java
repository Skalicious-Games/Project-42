/*
 * Component that specifies if an entity has a sprite and stores that sprite
 */

package components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component {
	public Sprite sprite;
	
	//Constructor takes a texture, and sets a sprite
	//This is because sprites have some added functionality
	public SpriteComponent(Texture texture) {
		sprite = new Sprite(texture);
	}

}
