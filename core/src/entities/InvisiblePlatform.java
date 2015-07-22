package entities;

import static variables.Variables.PIXELS_TO_METERS;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import components.BodyComponent;

public class InvisiblePlatform extends Entity {
		
	public InvisiblePlatform (float x, float y, float ts, World world) {		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(x / PIXELS_TO_METERS, y  / PIXELS_TO_METERS);
		Body body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(ts / 2 / PIXELS_TO_METERS, ts / 2 / PIXELS_TO_METERS);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = .5f;
		Fixture fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
		
		this.add(new BodyComponent(body, fixture));
		
	}

}
