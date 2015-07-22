package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import components.BodyComponent;
import components.InventoryComponent;
import components.PlayerInputComponent;
import components.SpriteComponent;

public class InventoryDisplaySystem extends EntitySystem {
	
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> players;
	
	private ComponentMapper<InventoryComponent> im = ComponentMapper.getFor(InventoryComponent.class);
	
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class, BodyComponent.class).get());
		players = engine.getEntitiesFor(Family.all(PlayerInputComponent.class).get());
	}
	
	public void update (float deltaTime) {
		
		
		//if inventory is open, display player inventory
		for (Entity player : players) {
			InventoryComponent ic = im.get(player);
			if (ic.isOpen()) {
				//display
			}
		}
	}
}
