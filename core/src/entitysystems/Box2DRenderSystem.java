package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import components.BodyComponent;
import components.PositionComponent;
import components.RenderComponent;
import components.SpriteComponent;

public class Box2DRenderSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private SpriteBatch batch;
	private World world;
	
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);
	private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
	
	public Box2DRenderSystem (SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
	}
	
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(BodyComponent.class, SpriteComponent.class, PositionComponent.class).get());
	}
	
	public void update (float deltaTime) {
		world.step(deltaTime, 6, 2);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(Entity entity : entities) {
			SpriteComponent sprite = sm.get(entity);
			PositionComponent position = pm.get(entity);
			BodyComponent body = bm.get(entity);
			position.x = body.body.getPosition().x;
			position.y = body.body.getPosition().y;
			batch.draw(sprite.sprite.getTexture(), position.x, position.y);
		}
		batch.end();
	}
}
