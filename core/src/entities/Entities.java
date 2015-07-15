/*
 * Class that contains constructors for all types of entities
 * This is not made to be final in any way, but shows roughly how entities will be made
 * 
 * How to properly create entities
 * 	Entity player = Entities.player(data);
 * 	engine.addEntity(player);
 */

package entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import components.*;

import static variables.Variables.PIXELS_TO_METERS;

public class Entities {
	
	public static Entity bullet (float x, float y, Texture texture, World world, float x2, float y2) {
		Entity entity = new Entity();
		
		entity.add(new SpriteComponent(texture, x, y));
		SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
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
		
		entity.add(new BodyComponent(body, fixture));
		
		return entity;
	}
	
	public static Entity enemy (float x, float y, Texture texture) {
		Entity entity = new Entity();
		
		entity.add(new SpriteComponent(texture, x, y));
		//Add body component
		
		return entity;
	}
	
	public static Entity level (String[] tilemap) {
		Entity entity = new Entity();
		
		entity.add(new TileMapComponent(tilemap));
		
		return entity;
	}
	
	public static Entity box2DPlayer (float x, float y, Texture texture, World world) {
		Entity entity = new Entity();
		
		entity.add(new SpriteComponent(texture, x, y));
		SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
		
		//Create Box2D Body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x / PIXELS_TO_METERS, y  / PIXELS_TO_METERS);
		bodyDef.fixedRotation = true;
		Body body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.sprite.getHeight() / 2 / PIXELS_TO_METERS);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.restitution = 0f;
		Fixture fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
		
		entity.add(new BodyComponent(body, fixture));
		entity.add(new PlayerInputComponent());
		
		return entity;
	}
	
	public static Entity platform (float x, float y, Texture texture, World world) {
		Entity entity = new Entity();
		
		entity.add(new SpriteComponent(texture, x, y));
		SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
		
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
		
		entity.add(new BodyComponent(body, fixture));
		
		return entity;
	}
	
	public static Entity invisiblePlatform (float x, float y, float ts, World world) {
		Entity entity = new Entity();
		
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
		
		entity.add(new BodyComponent(body, fixture));
		
		return entity;
	}
}
