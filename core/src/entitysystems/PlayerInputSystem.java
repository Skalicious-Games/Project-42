package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import components.PlayerInputComponent;
import components.VelocityComponent;

public class PlayerInputSystem extends IteratingSystem {
	
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	
	public PlayerInputSystem() {
		super(Family.all(PlayerInputComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		VelocityComponent velocity = vm.get(entity);
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			velocity.x = 300;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			velocity.x = -300;
		}
		else {
			velocity.x = 0;
		}
	}

}
