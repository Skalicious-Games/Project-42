/*
 * Matt 7/12/2015
 * This entity system is an alternative render system that only shows the outlines of the box2d physics bodies
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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;
import components.BodyComponent;
import components.PlayerInputComponent;
import components.SpriteComponent;

import static variables.Variables.PIXELS_TO_METERS;

public class DebugRenderSystem extends EntitySystem {
	private ImmutableArray<Entity> bodies;
	private ImmutableArray<Entity> players;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
	private World world;
	
	private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
	
	public DebugRenderSystem(World world, Box2DDebugRenderer renderer) {
		this.world = world;
		this.renderer = renderer;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / PIXELS_TO_METERS * 2, Gdx.graphics.getHeight() / PIXELS_TO_METERS * 2);
	}
	
	public void addedToEngine(Engine engine) {
		bodies = engine.getEntitiesFor(Family.all(BodyComponent.class).get());
		players = engine.getEntitiesFor(Family.all(PlayerInputComponent.class).get());
	}
	
	public void update (float deltaTime) {
		camera.update();
		//step the physics simulation
		world.step(deltaTime, 6, 2);
		
		//Clear Screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Render bodies
		renderer.render(world, camera.combined);		
	}
}
