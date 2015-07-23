package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class TempSplash implements Screen {
	
	private Stage myStage = new Stage();
	private Texture backgroundTxtr = new Texture(Gdx.files.internal("frontbackground_1280x720.png"));
	private Image backgroundImg = new Image(backgroundTxtr);
	
//	public TempSplash() {
//		myStage = new Stage();
//		backgroundTxtr = new Texture(Gdx.files.internal("frontbackground_1280x720.png"));
//		backgroundImg = new Image(backgroundTxtr);
//	}

	@Override
	public void show() {
		myStage.addActor(backgroundImg);
		backgroundImg.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(0.5f),Actions.delay(2),Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TempSplash());
                System.out.println("done");
            }
        })));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        myStage.act(); //update all actors
        myStage.draw(); //draw all actors on the Stage.getBatch()
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		backgroundTxtr.dispose();
        myStage.dispose();
	}

}
