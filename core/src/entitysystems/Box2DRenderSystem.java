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

import components.BodyComponent;
import components.PositionComponent;
import components.RenderComponent;
import components.SpriteComponent;

import static variables.Variables.PIXELS_TO_METERS;

public class Box2DRenderSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private World world;
	private Box2DDebugRenderer renderer;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);
	private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
	
	public Box2DRenderSystem (World world, SpriteBatch batch) {
		this.world = world;
		this.batch = batch;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class, BodyComponent.class).get());
	}
	
	public void update (float deltaTime) {
		camera.update();
		//Update box2d world
		world.step(1f/60f, 6, 2);
		
		//Clear screen then update
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Entity entity : entities) {
			SpriteComponent sprite = sm.get(entity);
			BodyComponent body = bm.get(entity);;
			sprite.sprite.setPosition((body.body.getPosition().x * PIXELS_TO_METERS) - sprite.sprite.getWidth()/2,
					(body.body.getPosition().y * PIXELS_TO_METERS) - sprite.sprite.getHeight()/2);
			batch.draw(sprite.sprite, sprite.sprite.getX() - Gdx.graphics.getWidth()/2, sprite.sprite.getY() - Gdx.graphics.getHeight()/2,sprite.sprite.getOriginX(),
                    sprite.sprite.getOriginY(), sprite.sprite.getWidth(), sprite.sprite.getHeight(), 
                    sprite.sprite.getScaleX(), sprite.sprite.getScaleY(), sprite.sprite.getRotation());
		}
		batch.end();

	}
}
