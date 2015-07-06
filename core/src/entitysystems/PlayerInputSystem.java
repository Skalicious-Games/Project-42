package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import components.PlayerInputComponent;
import components.PositionComponent;
import components.VelocityComponent;
import entities.Entities;

public class PlayerInputSystem extends IteratingSystem {
	
	Engine engine;
	
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	
	public PlayerInputSystem(Engine e) {
		super(Family.all(PlayerInputComponent.class).get());
		this.engine = e;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		VelocityComponent velocity = vm.get(entity);
		PositionComponent position = pm.get(entity);
		
		int dir = 1; //1 is right, -1 is left
		float speed = 300.0f;
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			dir = 1;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			dir = -1;
		}
		else {
			speed = 0;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) {
			speed = 0;
		}
		
		velocity.x = dir * speed;
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			Entity bullet = Entities.bullet(position.x + 40, position.y + 20, 500 * dir, 0, new Texture(Gdx.files.local("bullet_20x10.png")), 2);
			engine.addEntity(bullet);
		}
	}

}
