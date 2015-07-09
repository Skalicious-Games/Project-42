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
	World world = new World(new Vector2(0, -10), true);
	Box2DDebugRenderer renderer = new Box2DDebugRenderer();
	
	private float deltaTime = 0;
	
	public EntityManager(Engine e, SpriteBatch batch) {
		engine = e;
		
		//Create all needed systems
		MovementSystem ms = new MovementSystem();
		RenderSystem rs = new RenderSystem(batch);
		PlayerInputSystem pis = new PlayerInputSystem(e);
		Box2DRenderSystem b2drs = new Box2DRenderSystem(world, batch);
		
		//Add all systems to engine
		engine.addSystem(ms);
		//engine.addSystem(rs);
		engine.addSystem(pis);
		engine.addSystem(b2drs);
		
		/*
		//Example level data
		String[] tilemap1 = new String[] {
				"000000000000000000000000000000",
				"000000000000000000000000000000",
				"000000000000001110000000000000",
				"000000000011100000000000000000",
				"000000111000000000000000000000",
				"000001000000000000000000000000",
				"110000000000000000000000000000",
				"111111110011110001111100111111"
		};
		
		//Temporary loop for making the level map
		for (int i = 0; i < tilemap1.length ; i++) {
			String line = tilemap1[i]; //a row
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
					case '0':
						break;
					case '1':
						Entity platform = Entities.platform(j*60, (tilemap1.length - i)*60 - 60, new Texture(Gdx.files.local("box_60x60.png")), 2);
						engine.addEntity(platform);
						break;
				}
			}
		}*/	
		
		//Create player entity and add it to engine
		//Entity player = Entities.player(10, 120, 0, 0, new Texture(Gdx.files.local("player_40x40.png")), 1);
		//engine.addEntity(player);
		
		//Create box2d Player		
		Entity player2 = Entities.box2DPlayer(200, 200, new Texture(Gdx.files.local("player_40x40.png")), world);
		engine.addEntity(player2);
		
		
	}

	public void update() {
		deltaTime = Gdx.graphics.getDeltaTime();
		engine.update(deltaTime);
	}
	
}
