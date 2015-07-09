package components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class BodyComponent extends Component {
	public Body body;
	public Fixture fixture;
	
	public BodyComponent(Body body, Fixture fixture) {
		this.body = body;
		this.fixture = fixture;
	}
}
