package com.hhs.zzzyt.um.physics;

import com.hhs.zzzyt.um.graphic.Launcher;
import com.hhs.zzzyt.um.graphic.Stage;

public class Static {
	public static float G = 6.67259f;// remeber real G=6.67259*10^-11

	public static float dis(UMObject obj1, UMObject obj2) {
		float xdif = Math.abs(obj1.x - obj2.x);
		float ydif = Math.abs(obj1.y - obj2.y);
		float zdif = Math.abs(obj1.z - obj2.z);
		return (float) Math.cbrt(xdif * xdif + ydif * ydif + zdif * zdif);
	}

	public static float force(float d, float m1, float m2) {
		return (float) ((G * m1 * m2) / (d * d) / 10000d);
	}

	public static float a(float f, float m) {
		return f / m;
	}

	public static void spilt(float f, UMObject obj1, UMObject obj2) {
		float xdif = obj2.x - obj1.x;
		float ydif = obj2.y - obj1.y;
		float zdif = obj2.z - obj1.z;
		float a1 = a(f, obj1.m);
		float a2 = a(f, obj2.m);
		float tot = Math.abs(xdif) + Math.abs(ydif) + Math.abs(zdif);

		obj1.vx += a1 * xdif / tot;
		obj1.vy += a1 * ydif / tot;
		obj1.vz += a1 * zdif / tot;
		obj2.vx -= a2 * xdif / tot;
		obj2.vy -= a2 * ydif / tot;
		obj2.vz -= a2 * zdif / tot;
	}

	public static void calc(UMObject obj1, UMObject obj2) {
		float d = dis(obj1, obj2);
		float f = force(d, obj1.m, obj2.m);
		spilt(f, obj1, obj2);
	}

	public static void sim() {
		for (int i = 0; i < Launcher.objs.size() - 1; i++) {
			for (int j = i + 1; j < Launcher.objs.size(); j++) {
				UMObject a = Launcher.objs.get(i);
				UMObject b = Launcher.objs.get(j);
				float disx = Math.abs(a.x - b.x);
				float disy = Math.abs(a.y - b.y);
				if (Math.sqrt(disx * disx + disy * disy) < 2e8) {
					a.vx = (a.vx + b.vx) / 4;
					a.vy = (a.vy + b.vy) / 4;
					a.vz = (a.vz + b.vz) / 4;
					a.m += b.m;
					Launcher.objs.remove(j);
					j--;
					System.out.println("Collision: Count=" + Launcher.objs.size());
				}

			}
		}

		for (int i = 0; i < Launcher.objs.size() - 1; i++) {
			for (int j = i + 1; j < Launcher.objs.size(); j++) {
				Static.calc(Launcher.objs.get(i), Launcher.objs.get(j));
			}
		}
		for (int i = 0; i < Launcher.objs.size(); i++) {
			UMObject obj = Launcher.objs.get(i);
			obj.x += obj.vx / Stage.fps * 10000000000d;
			obj.y += obj.vy / Stage.fps * 10000000000d;
			obj.z += obj.vz / Stage.fps * 10000000000d;
//			System.out.println("#"+(i+1)+":"+obj);
		}

	}
}
