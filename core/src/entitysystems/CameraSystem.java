/*
 * Matt 7/15/2015
 * Entity system that updates the position of the camera accordingly and keeps track of how far the camera has
 * moved in the x and y directions
 */
package entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;

import components.BodyComponent;
import components.PlayerInputComponent;

public class CameraSystem extends IteratingSystem {
	OrthographicCamera camera;
	
	private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
	
	public CameraSystem(OrthographicCamera camera) {
		super(Family.all(PlayerInputComponent.class).get());
		this.camera = camera;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		//BodyComponent body = bm.get(entity);
		
	}

}
