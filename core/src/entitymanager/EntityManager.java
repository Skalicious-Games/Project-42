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
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
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
import functions.MapLoader;
import functions.MapLoader2;

public class EntityManager {
	private Engine engine;
	public OrthogonalTiledMapRenderer tmRenderer;
	public TiledMap tileMap;
	
	private float deltaTime = 0;
	
	World world = new World(new Vector2(0, -10f), true);
	
	public EntityManager(Engine e, SpriteBatch batch) {
		engine = e;
		
		//tileMap = new TmxMapLoader().load("mapfiles/example_tilemap3_1280x704.tmx");
		tileMap = new TmxMapLoader().load("mapfiles/example_tilemap_2560x1280.tmx");
		MapLoader.buildShapes(tileMap, 32f, world, e);
		tmRenderer = new OrthogonalTiledMapRenderer(tileMap);
		
		//Create all needed systems
		PlayerInputSystem pis = new PlayerInputSystem(e, world);
		Box2DRenderSystem brs = new Box2DRenderSystem(world, batch, tmRenderer);
		
		//Add all systems to engine
		engine.addSystem(pis);
		engine.addSystem(brs);

		//Create box2d Player		
		Entity player = Entities.box2DPlayer(640, 360, new Texture(Gdx.files.local("player_40x40.png")), world);
		engine.addEntity(player);		
		
	}

	public void update() {
		deltaTime = Gdx.graphics.getDeltaTime();
		engine.update(deltaTime);
	}
	
}
