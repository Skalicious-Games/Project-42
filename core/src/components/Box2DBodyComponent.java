package components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Box2DBodyComponent extends Component {
	public Body body;
	private Fixture fixture;
	
	public Box2DBodyComponent (Body body, Fixture fixture) {
		this.body = body;
		this.fixture = fixture;
	}
}
