/*
 * Component that holds a string to be used for a tile map. Meant for prototype purposes
 */

package components;

import com.badlogic.ashley.core.Component;

public class TileMapComponent extends Component {
	public String[] tilemap;
	
	public TileMapComponent (String[] tilemap) {
		this.tilemap = tilemap;
	}
}
