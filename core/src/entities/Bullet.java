package entities;

import static variables.Variables.PIXELS_TO_METERS;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import components.BodyComponent;
import components.SpriteComponent;

public class Bullet extends Entity {
		
	public Bullet (float x, float y, Texture texture, World world, float x2, float y2) {		
		this.add(new SpriteComponent(texture, x, y));
		SpriteComponent sprite = this.getComponent(SpriteComponent.class);
		//Set bullet direction based on player click
		float mag = (float) Math.pow((Math.pow(x2 - (x * PIXELS_TO_METERS), 2) + Math.pow(y2 - (y * PIXELS_TO_METERS), 2)), 0.5);
		float vX = (x2 - (x * PIXELS_TO_METERS)) / (mag);
		float vY = (y2 - (y * PIXELS_TO_METERS)) / (mag);
		
		//Create box2d body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.KinematicBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);
		body.setLinearVelocity(vX * 10f, vY * 10f);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.sprite.getHeight() / 2 / PIXELS_TO_METERS);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		Fixture fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
		
		this.add(new BodyComponent(body, fixture));
	}
	
}
