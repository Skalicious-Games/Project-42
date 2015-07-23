package screens;

import static variables.Variables.PIXELS_TO_METERS;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import entities.Enemy;
import entities.Player;
import entitysystems.AIMovementSystem;
import entitysystems.Box2DRenderSystem;
import entitysystems.CameraSystem;
import entitysystems.DebugRenderSystem;
import entitysystems.PlayerInputSystem;
import functions.MapLoader;

public class Story implements Screen {

	private Engine engine;
	public OrthogonalTiledMapRenderer tmRenderer;
	public Box2DDebugRenderer debugRenderer;
	public TiledMap tileMap;
	public RayHandler rayHandler;
	private OrthographicCamera camera;

	private float deltaTime = 0;

	private World world = new World(new Vector2(0, -10f), true);

	@Override
	public void show() {
		engine = new Engine();
		SpriteBatch batch = new SpriteBatch();

		//old EntityManager Code:
		//create light ray handler
		rayHandler = new RayHandler(world);

		//Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / PIXELS_TO_METERS, Gdx.graphics.getHeight() / PIXELS_TO_METERS);

		//load tilemap and create box2d bodies for collidable objects
		//tileMap = new TmxMapLoader().load("mapfiles/example_tilemap3_1280x704.tmx");
		tileMap = new TmxMapLoader().load("mapfiles/example_tilemap_2560x1280.tmx");
		MapLoader.buildShapes(tileMap, 32f, world, engine);

		//Create renderer
		tmRenderer = new OrthogonalTiledMapRenderer(tileMap);
		debugRenderer = new Box2DDebugRenderer();

		//Create all needed systems
		PlayerInputSystem pis = new PlayerInputSystem(engine, world);
		AIMovementSystem aims = new AIMovementSystem(engine, world);
		CameraSystem cs = new CameraSystem(camera);
		Box2DRenderSystem brs = new Box2DRenderSystem(world, batch, tmRenderer, camera,rayHandler);
		DebugRenderSystem drs = new DebugRenderSystem(world, debugRenderer, camera); //use only 1 render system at a time

		//Add all systems to engine
		engine.addSystem(pis);
		engine.addSystem(cs);
		//engine.addSystem(brs); //only add one render system at a time
		engine.addSystem(drs);
		engine.addSystem(aims);

		//Create box2d Player		
		Entity player = new Player(640, 360, new Texture(Gdx.files.local("player_40x40.png")), world);
		Entity enemy = new Enemy(700, 500, new Texture(Gdx.files.local("enemy_40x40.png")), world);
		engine.addEntity(player);
		engine.addEntity(enemy);

		new PointLight(rayHandler, 200, Color.CYAN, 100, 500, 500);
	}

	@Override
	public void render(float delta) {
		deltaTime = Gdx.graphics.getDeltaTime();
		engine.update(deltaTime);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
