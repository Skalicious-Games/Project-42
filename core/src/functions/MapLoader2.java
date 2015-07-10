package functions;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static variables.Variables.PIXELS_TO_METERS;

public class MapLoader2 {
	
	public static void buildShapes(TiledMap tileMap, World world, Engine engine) {
		Array<Body> bodies = new Array<Body>();
		
		MapLayer layer = tileMap.getLayers().get("Objects");
		
		if (layer == null) return;
		
		for (MapObject object : layer.getObjects()) {
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyType.StaticBody;
			Body body;
			FixtureDef fDef;
	
			float x = object.getProperties().get("x", Float.class);
			float y = object.getProperties().get("y", Float.class);
			float width = object.getProperties().get("width", Float.class);
			float height = object.getProperties().get("height", Float.class);
			
			bodyDef.position.set((x + width / 2) / PIXELS_TO_METERS, (y + height / 2) / PIXELS_TO_METERS);
			body = world.createBody(bodyDef);
			
			fDef = new FixtureDef();
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(width / PIXELS_TO_METERS / 2 , height / PIXELS_TO_METERS / 2);
			
			fDef.shape = shape;
			fDef.friction = 0.5f;
			body.createFixture(fDef);
			bodies.add(body);
			shape.dispose();
		}
	}
}
