package com.hhs.zzzyt.um.graphic;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hhs.zzzyt.um.physics.UMObject;
import com.hhs.zzzyt.um.physics.Static;

public class Stage extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, img2;
	List<Sprite> sprs = new ArrayList<Sprite>();

	int n = Launcher.n;
	public static float fps = 1000f;
	public static long lastTime;

	@Override
	public void create() {

		batch = new SpriteBatch();
		img = new Texture("D:/ÕÔí²ì÷/Pictures/Funny/ZHD.jpg");
		img2 = new Texture("D:/ÕÔí²ì÷/Pictures/Funny/GiantBaby.jpg");
		for (int i = 0; i < n; i++) {
			sprs.add(new Sprite(img));
			UMObject obj = Launcher.objs.get(i);
			sprs.get(i).setBounds((float) obj.x, (float) obj.y, 3, 3);
		}
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		while (true) {
			Static.sim();
			if (System.currentTimeMillis() - lastTime >= (1000 / Launcher.config.backgroundFPS)) {
				lastTime = System.currentTimeMillis();
				break;
			}
		}

		batch.begin();
		float max = 0, cx = 0, cy = 0;
		for (int i = 0; i < Launcher.objs.size(); i++) {
			cx += Launcher.objs.get(i).x;
			cy += Launcher.objs.get(i).y;
			if (Math.abs(Launcher.objs.get(i).x) > max) {
				max = Math.abs(Launcher.objs.get(i).x);
			}
			if (Math.abs(Launcher.objs.get(i).y) > max) {
				max = Math.abs(Launcher.objs.get(i).y);
			}
		}
		cx /= n;
		cy /= n;
		max /=400;
		max=(float) 5e9;
		for (int i = 0; i < Launcher.objs.size(); i++) {
			if (Math.abs(Launcher.objs.get(i).x - cx) > 3e12 || Math.abs(Launcher.objs.get(i).y - cy) > 3e12) {
				Launcher.objs.remove(i);
				i--;
			}
		}

		int actcnt = 0;
		for (int i = 0; i < Launcher.objs.size(); i++) {
			sprs.get(i).setX(Math.round((Launcher.objs.get(i).x - cx) / max + Launcher.width / 2));
			sprs.get(i).setY(Math.round((Launcher.objs.get(i).y - cy) / max + Launcher.height / 2));
			if (sprs.get(i).getX() > 0 && sprs.get(i).getX() < Launcher.width && sprs.get(i).getY() > 0
					&& sprs.get(i).getY() < Launcher.height) {
				actcnt++;
			}
			sprs.get(i).setSize((float) (Math.sqrt(Launcher.objs.get(i).m) / 2e7),
					(float) (Math.sqrt(Launcher.objs.get(i).m) / 2e7));
			sprs.get(i).draw(batch);
		}
		System.out.println("Active:" + actcnt);
		Sprite tmp = new Sprite(img2);
		tmp.setSize(20, 20);
		tmp.setAlpha(0.5f);
		tmp.setX(Math.round(1000000000000d / max));
		tmp.setY(0);
		tmp.draw(batch);

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
