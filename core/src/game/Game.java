package game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entitymanager.EntityManager;

public class Game extends ApplicationAdapter {
	
	SpriteBatch batch;
	Texture img;
	
	private EntityManager entityManager;
	
	@Override
	public void create () {
		Engine engine = new Engine();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		entityManager = new EntityManager(engine, batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		entityManager.update();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	public void main (String[] args) {
		Game game = new Game();
		game.create();
		game.render();
	}
}
