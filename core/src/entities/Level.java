//Thinking about changing this into not being an entity since it will be a more major component

package entities;

import com.badlogic.ashley.core.Entity;
import components.TileMapComponent;

public class Level extends Entity {
		
	public Level (String[] tilemap) {		
		this.add(new TileMapComponent(tilemap));
	}
	
}
