package entitymanager;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import entities.Entities;
import entitysystems.Box2DRenderSystem;
import entitysystems.MovementSystem;
import entitysystems.PlayerInputSystem;
import entitysystems.RenderSystem;

public class EntityManager {
	private Engine engine;
	World world = new World(new Vector2(0, -10f), true);
	Box2DDebugRenderer renderer = new Box2DDebugRenderer();
	
	private float deltaTime = 0;
	
	public EntityManager(Engine e, SpriteBatch batch) {
		engine = e;
		
		//Create all needed systems
		PlayerInputSystem pis = new PlayerInputSystem(e, world);
		Box2DRenderSystem brs = new Box2DRenderSystem(world, batch);
		
		//Add all systems to engine
		engine.addSystem(pis);
		engine.addSystem(brs);

		//Create box2d Player		
		Entity player = Entities.box2DPlayer(640, 700, new Texture(Gdx.files.local("player_40x40.png")), world);
		engine.addEntity(player);
		
		for (int i = 0; i < 20; i++) {
			Entity platform = Entities.platform(0 + i * 60, 50, new Texture(Gdx.files.local("box_60x60.png")), world);
			engine.addEntity(platform);
		}
		
	}

	public void update() {
		deltaTime = Gdx.graphics.getDeltaTime();
		engine.update(deltaTime);
	}
	
}
