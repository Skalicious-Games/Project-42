package game;

import screens.Story;
import screens.TempSplash;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import entitymanager.EntityManager;

public class Project42 extends Game {
	
	Texture img;
	
	private EntityManager entityManager;
	
	@Override
	public void create () {
//		Engine engine = new Engine();
//		entityManager = new EntityManager(engine);
		setScreen(new Story());
	}

	@Override
	public void render () {
//		entityManager.update();
		//get time elapsed since last render
		float deltaTime = Gdx.graphics.getDeltaTime();
		//Call current screens render function
		getScreen().render(deltaTime);
	}
	
	public void main (String[] args) {
		
	}
}
