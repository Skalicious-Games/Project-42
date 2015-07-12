package entitysystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;

import components.AIComponent;

public class AIMovementSystem extends IteratingSystem {

	public AIMovementSystem(Engine e, World world) {
		super(Family.all(AIComponent.class).get());

	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
