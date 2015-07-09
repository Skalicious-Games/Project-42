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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
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
	World world;
	
	private float deltaTime = 0;
	
	public EntityManager(Engine e, SpriteBatch batch) {
		engine = e;
		world = new World(new Vector2(0, -100f), true);
		
		//Create all needed systems
		MovementSystem ms = new MovementSystem();
		PlayerInputSystem pis = new PlayerInputSystem(e);
		Box2DRenderSystem brs = new Box2DRenderSystem(batch, world);
		
		//add tile map
		//Entity map = Entities.level(new TmxMapLoader().load("example_tilemap2_2560x1280.tmx"));
		//engine.addEntity(map);
		
		Entity platform = Entities.platform(200, 50, new Texture(Gdx.files.local("box_60x60.png")), world);
		engine.addEntity(platform);
		
		//Add all systems to engine
		engine.addSystem(ms);
		engine.addSystem(pis);
		engine.addSystem(brs);
		
		//Create box2d player entity and add it to engine
		Entity player = Entities.b2DPlayer(200, 400, new Texture(Gdx.files.local("player_40x40.png")), world);
		engine.addEntity(player);
	}

	public void update() {
		deltaTime = Gdx.graphics.getDeltaTime();
		engine.update(deltaTime);
	}
	
}
