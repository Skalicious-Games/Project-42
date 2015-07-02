/*
 * Entity System used to render all entities
 */

package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import components.PositionComponent;
import components.RenderComponent;
import components.SpriteComponent;

public class RenderSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private SpriteBatch batch;
	
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);
	
	public RenderSystem (SpriteBatch batch) {
		this.batch = batch;
	}
	
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(RenderComponent.class, SpriteComponent.class, PositionComponent.class).get());
	}
	
	public void update (float deltaTime) {
		for(Entity entity : entities) {
			//SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
			//PositionComponent position = entity.getComponent(PositionComponent.class);
			SpriteComponent sprite = sm.get(entity);
			PositionComponent position = pm.get(entity);
			batch.draw(sprite.sprite.getTexture(), position.hashCode(), position.y);
		}
	}
}
