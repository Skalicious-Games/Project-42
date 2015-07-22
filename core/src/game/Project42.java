package game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import entitymanager.EntityManager;

public class Project42 extends Game {
	
	SpriteBatch batch;
	Texture img;
	
	
	private EntityManager entityManager;
	
	@Override
	public void create () {
		Engine engine = new Engine();
		batch = new SpriteBatch();
		entityManager = new EntityManager(engine, batch);
	}

	@Override
	public void render () {
		entityManager.update();
	}
	
	public void main (String[] args) {
		
	}
}
