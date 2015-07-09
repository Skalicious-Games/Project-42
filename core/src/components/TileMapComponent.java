/*
 * Component that holds a string to be used for a tile map. Meant for prototype purposes
 */

package components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class TileMapComponent extends Component {
	public TiledMap tilemap;
	
	public TileMapComponent (TiledMap tilemap) {
		this.tilemap = tilemap;
	}
}
