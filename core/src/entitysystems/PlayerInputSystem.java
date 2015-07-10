package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import components.BodyComponent;
import components.PlayerInputComponent;
import entities.Entities;

public class PlayerInputSystem extends IteratingSystem {	
	Engine engine;
	World world;
	
	int dir = 1;
	float max = 3f;
	
	private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
	
	public PlayerInputSystem(Engine e, World world) {
		super(Family.all(PlayerInputComponent.class).get());
		this.engine = e;
		this.world = world;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		BodyComponent body = bm.get(entity);
		Vector2 pos = body.body.getPosition();
		Vector2 vel = body.body.getLinearVelocity();
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) {
			body.body.setLinearVelocity(0f, vel.y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < max) {
			body.body.setLinearVelocity(4f, vel.y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -max) {
			body.body.setLinearVelocity(-4f, vel.y);
		}
		else if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
			body.body.setLinearVelocity(0f, vel.y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) {
			body.body.setLinearVelocity(0f, vel.y);
		}
		
		if (vel.x > 0) {
			dir = 1;
		}
		else if (vel.x < 0) {
			dir = -1;
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			body.body.applyLinearImpulse(0f, 1f, pos.x, pos.y, true);
		}
	}

}
