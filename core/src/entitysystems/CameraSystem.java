/*
 * Matt 7/16/2015
 * Entity system that updates the position of the camera accordingly and keeps track of how far the camera has
 * moved in the x and y directions
 * 
 * This class should be able to...
 ** Move the camera when the player reaches a certain distance away from the edges
 ** Keep track of how far the camera has moved
 ** Lock the camera in position
 ** Not move the camera beyond limits specified for each level
 ** Possibly move the camera upon a specified path for cut scenes
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
	public float offsetX;
	public float offsetY;
	OrthographicCamera camera;
	
	private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
	
	public CameraSystem(OrthographicCamera camera) {
		super(Family.all(PlayerInputComponent.class).get());
		this.camera = camera;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		BodyComponent body = bm.get(entity);
		//Set camera position based on player position
		//camera.position.x = body.body.getPosition().x;
		//camera.position.y = body.body.getPosition().y;
		
		//Set difference between initial camera position and current camera position as offset

	}

}
