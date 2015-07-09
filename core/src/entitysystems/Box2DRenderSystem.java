/*
 * Matt 7/8/2015
 * 
 */

package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import components.Box2DBodyComponent;
import components.PositionComponent;
import components.RenderComponent;
import components.SpriteComponent;

public class Box2DRenderSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private World world;
	private Box2DDebugRenderer renderer;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);
	private ComponentMapper<Box2DBodyComponent> bm = ComponentMapper.getFor(Box2DBodyComponent.class);
	
	public Box2DRenderSystem (World world, SpriteBatch batch) {
		this.world = world;
		this.batch = batch;
	}
	
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(RenderComponent.class, SpriteComponent.class, PositionComponent.class, Box2DBodyComponent.class).get());
	}
	
	public void update (float deltaTime) {
		//Update box2d world
		world.step(deltaTime, 6, 2);
		
		//Clear screen then update
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		for (Entity entity : entities) {
			SpriteComponent sprite = sm.get(entity);
			PositionComponent position = pm.get(entity);
			Box2DBodyComponent body = bm.get(entity);
			position.x = body.body.getPosition().x;
			position.y = body.body.getPosition().y;
			batch.draw(sprite.sprite.getTexture(), body.body.getPosition().x, body.body.getPosition().y);
			//System.out.println("x = " + position.x + " y = " + position.y);
		}
		batch.end();

	}
}
