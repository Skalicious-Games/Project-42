package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import components.PlayerInputComponent;
import components.PositionComponent;
import components.VelocityComponent;
import entities.Entities;

public class PlayerInputSystem extends IteratingSystem {
	
	Engine engine;
	World world;
	
	int dir = 1;
	
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	
	public PlayerInputSystem(Engine e, World world) {
		super(Family.all(PlayerInputComponent.class).get());
		this.engine = e;
		this.world = world;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		VelocityComponent velocity = vm.get(entity);
		PositionComponent position = pm.get(entity);
		
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
			Entity bullet = Entities.bullet(position.x + 40, position.y + 20, new Texture(Gdx.files.local("bullet_20x10.png")), world);
			engine.addEntity(bullet);
		}
	}

}
