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

public class Platform extends Entity {
	
	public Platform (float x, float y, Texture texture, World world) {		
		this.add(new SpriteComponent(texture, x, y));
		SpriteComponent sprite = this.getComponent(SpriteComponent.class);
		
		//Create Box2D Body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(x / PIXELS_TO_METERS, y  / PIXELS_TO_METERS);
		Body body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.sprite.getHeight() / 2 / PIXELS_TO_METERS);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 0f;
		Fixture fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
		
		this.add(new BodyComponent(body, fixture));
	}
	
}
