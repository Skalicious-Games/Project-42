/*
 * Class that contains constructors for all types of entities
 * This is not made to be final in any way, but shows roughly how entities will be made
 * 
 * How to properly create entities
 * 	Entity player = Entities.player(data);
 * 	engine.addEntity(player);
 */

package entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;

import components.*;

public class Entities {
	
	public static Entity player (float x, float y, float xvel, float yvel, Texture texture) {
		Entity entity = new Entity();
		
		entity.add(new PositionComponent(x, y));
		entity.add(new VelocityComponent(xvel, yvel));
		entity.add(new SpriteComponent(texture));
		entity.add(new RenderComponent());
		
		return entity;
	}
	
	public static Entity platform (float x, float y, Texture texture) {
		Entity entity = new Entity();
		
		entity.add(new PositionComponent(x, y));
		entity.add(new SpriteComponent(texture));
		entity.add(new RenderComponent());
		
		return entity;
	}
	
	public static Entity level (String[] tilemap) {
		Entity entity = new Entity();
		
		entity.add(new TileMapComponent(tilemap));
		
		return entity;
	}
	
}
