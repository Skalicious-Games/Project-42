package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import components.AIComponent;
import components.BodyComponent;
import components.PlayerInputComponent;

public class AIMovementSystem extends IteratingSystem {
	
	ImmutableArray<Entity> players;
	
	private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

	public AIMovementSystem(Engine e, World world) {
		super(Family.all(AIComponent.class).get());
		players = e.getEntitiesFor(Family.all(PlayerInputComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		BodyComponent AIBody = bm.get(entity);
		Vector2 AIPos = AIBody.body.getPosition();
		float targetDist = Float.MAX_VALUE;
		Entity target = null;
		Vector2 targetPos = Vector2.Zero;
		for (Entity p : players) {
			BodyComponent playerBody = bm.get(p);
			Vector2 playerPos = playerBody.body.getPosition();
			float dist = Vector2.dst(AIPos.x, AIPos.y, playerPos.x, playerPos.y);
			if (dist <= targetDist) {
				targetDist = dist;
				target = p;
				targetPos = playerPos;
			}
		}
		if (target == null) {
			return;
		} else {
			//TODO: Figure out enemy pathing and deciding when theres an opening to attack, etc
			//Following is testing for enemy following
			Vector2 AIVel = AIBody.body.getLinearVelocity();
			float direction = targetPos.x - AIPos.x;
			if (direction > 0) {
				AIBody.body.setLinearVelocity(3f, AIVel.y);
			} else if (direction < 0) {
				AIBody.body.setLinearVelocity(-3f, AIVel.y);
			} else if (direction == 0) {
				AIBody.body.setLinearVelocity(0f, AIVel.y);
			}
		}
	}

}
