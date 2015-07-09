/*
 * Matt 7/8/2015
 * 
 */

package entitysystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import components.PositionComponent;
import components.RenderComponent;
import components.SpriteComponent;

public class Box2DRenderSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private World world;
	private Box2DDebugRenderer renderer;
	private OrthographicCamera cam;
	
	public Box2DRenderSystem (World world, Box2DDebugRenderer renderer) {
		this.world = world;
		this.renderer = renderer;
	}
	
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(RenderComponent.class, SpriteComponent.class, PositionComponent.class).get());
	}
	
	public void update (float deltaTime) {
		renderer.render(world, cam.combined);
	}
}
