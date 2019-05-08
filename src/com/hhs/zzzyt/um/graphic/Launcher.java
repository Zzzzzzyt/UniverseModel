package com.hhs.zzzyt.um.graphic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hhs.zzzyt.um.graphic.Stage;
import com.hhs.zzzyt.um.physics.UMObject;

public class Launcher {
	public static List<UMObject> objs = new ArrayList<UMObject>();
	public static int n;
	public static int width, height;
	public static LwjglApplicationConfiguration config;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		for (int i = 0; i < n; i++) {
			float x, y, z, vx, vy, vz, m;
			x = s.nextFloat();
			y = s.nextFloat();
			z = s.nextFloat();
			vx = s.nextFloat();
			vy = s.nextFloat();
			vz = s.nextFloat();
			m = s.nextFloat();
			objs.add(new UMObject(x, y, z, vx, vy, vz, m));
		}
		s.close();
		config = new LwjglApplicationConfiguration();
		height = 810;
		width = 1440;
		config.height = height;
		config.width = width;
		config.foregroundFPS = 5;
		config.backgroundFPS = 5;
		new LwjglApplication(new Stage(), config);
	}
}