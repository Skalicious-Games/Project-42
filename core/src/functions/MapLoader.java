package functions;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import components.BodyComponent;

import static variables.Variables.PIXELS_TO_METERS;

public class MapLoader {
    private static final BodyDef BodyDef = null;
	// The pixels per tile. If your tiles are 16x16, this is set to 16f
    private static float ppt = 0f;

    public static Array<Body> buildShapes(Map map, float pixels, World world, Engine engine) {
        ppt = pixels;
        MapObjects objects = map.getLayers().get("Objects").getObjects();

        Array<Body> bodies = new Array<Body>();

        for(MapObject object : objects) {

            if (object instanceof TextureMapObject) {
                continue;
            }

            Shape shape;
            BodyDef bd = new BodyDef();

            if (object instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject)object, bd);
            }
            else if (object instanceof PolygonMapObject) {
                shape = getPolygon((PolygonMapObject)object);
            }
            else if (object instanceof PolylineMapObject) {
                shape = getPolyline((PolylineMapObject)object);
            }
            else if (object instanceof CircleMapObject) {
                shape = getCircle((CircleMapObject)object);
            }
            else {
                continue;
            }

            bd.type = BodyType.StaticBody;
            Body body = world.createBody(bd);
            Fixture fixture = body.createFixture(shape, 1);
            fixture.setFriction(.5f);

            bodies.add(body);
       
            shape.dispose();
        }
        return bodies;
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject, BodyDef bd) {
    	float x = rectangleObject.getProperties().get("x", Float.class);
		float y = rectangleObject.getProperties().get("y", Float.class);
		float width = rectangleObject.getProperties().get("width", Float.class);
		float height = rectangleObject.getProperties().get("height", Float.class);
		
    	bd.position.set((x + width / 2) / PIXELS_TO_METERS, (y + height / 2) / PIXELS_TO_METERS);
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(width / PIXELS_TO_METERS / 2, height / PIXELS_TO_METERS / 2);
        return polygon;
    }

    private static CircleShape getCircle(CircleMapObject circleObject) {
        Circle circle = circleObject.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius / PIXELS_TO_METERS);
        circleShape.setPosition(new Vector2(circle.x / PIXELS_TO_METERS, circle.y / PIXELS_TO_METERS));
        return circleShape;
    }

    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            //System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] / PIXELS_TO_METERS;
        }

        polygon.set(worldVertices);
        return polygon;
    }

    private static ChainShape getPolyline(PolylineMapObject polylineObject) {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / ppt;
            worldVertices[i].y = vertices[i * 2 + 1] / ppt;
        }

        ChainShape chain = new ChainShape(); 
        chain.createChain(worldVertices);
        return chain;
    }
}
