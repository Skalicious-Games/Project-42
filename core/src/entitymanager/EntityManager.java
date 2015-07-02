package entitymanager;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entities.Entities;
import entitysystems.MovementSystem;
import entitysystems.RenderSystem;

public class EntityManager {
	private Engine engine;
	
	private float deltaTime = 0;
	
	public EntityManager(Engine e, SpriteBatch batch) {
		
		engine = e;
		
		//Create all needed systems
		MovementSystem ms = new MovementSystem();
		RenderSystem rs = new RenderSystem(batch);
		
		//Add all systems to engine
		engine.addSystem(ms);
		engine.addSystem(rs);
		
		//Example level data
		String[] tilemap1 = new String[] {
				"000000000000000000000000000000",
				"000000000000000000000000000000",
				"000000000000001110000000000000",
				"000000000011100000000000000000",
				"000000111000000000000000000000",
				"000001000000000000000000000000",
				"110000000000000000000000000000",
				"000111000000000000000000000000",
				"000000001110000000000000000000",
				"000000000000011100000000020000",
				"000001110000000000011100011000",
				"111111110011110001111100111111"
		};
		
		//Temporary loop for making the level map
		for (int i = 0; i < tilemap1.length; i++) {
			String line = tilemap1[i]; //a row
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
					case '0':
						break;
					case '1':
						Entity platform = Entities.platform(i*60, j*60, new Texture("assets/box_60x60.png"));
						engine.addEntity(platform);
						break;
				}
			}
		}
		
		//Create player entity and add it to engine
		Entity player = Entities.player(100, 600, 0, 0, new Texture("assets/player_40x40.png"));
		engine.addEntity(player);
	}

	public void update() {
		deltaTime = Gdx.graphics.getDeltaTime();
		engine.update(deltaTime);		
	}
	
}
