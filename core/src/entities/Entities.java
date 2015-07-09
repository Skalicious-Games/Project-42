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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import components.*;

public class Entities {
	
	public static Entity bullet (float x, float y, float xvel, float yvel, Texture texture, int priority) {
		Entity entity = new Entity();
		
		entity.add(new PositionComponent(x, y));
		entity.add(new VelocityComponent(xvel, yvel));
		entity.add(new SpriteComponent(texture));
		entity.add(new RenderPriorityComponent(priority));
		entity.add(new RenderComponent());
		
		return entity;
	}
	
	public static Entity enemy (float x, float y, float xvel, float yvel, Texture texture, int priority) {
		Entity entity = new Entity();
		
		entity.add(new PositionComponent(x, y));
		entity.add(new VelocityComponent(xvel, yvel));
		entity.add(new SpriteComponent(texture));
		entity.add(new RenderPriorityComponent(priority));
		entity.add(new RenderComponent());
		
		return entity;
	}
	
	public static Entity level (String[] tilemap) {
		Entity entity = new Entity();
		
		entity.add(new TileMapComponent(tilemap));
		
		return entity;
	}
	
	public static Entity player (float x, float y, float xvel, float yvel, Texture texture, int priority) {
		Entity entity = new Entity();
		
		entity.add(new PositionComponent(x, y));
		entity.add(new VelocityComponent(xvel, yvel));
		entity.add(new SpriteComponent(texture));
		entity.add(new RenderPriorityComponent(priority));
		entity.add(new RenderComponent());
		entity.add(new PlayerInputComponent());
		
		return entity;
	}
	
	public static Entity b2DPlayer (float x, float y, Texture texture, World world) {
		Entity entity = new Entity();
		
		entity.add(new PositionComponent(x, y));
		entity.add(new SpriteComponent(texture));
		
		//Create box2D body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);
		
		Sprite sprite = new Sprite(texture);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		Fixture fixture = body.createFixture(fixtureDef);
		shape.dispose();
		
		entity.add(new BodyComponent(body, fixture));
		
		return entity;
	}
	
	public static Entity platform (float x, float y, Texture texture, int priority) {
		Entity entity = new Entity();
		
		entity.add(new PositionComponent(x, y));
		entity.add(new SpriteComponent(texture));
		entity.add(new RenderPriorityComponent(priority));
		entity.add(new RenderComponent());
		
		return entity;
	}
	
}
