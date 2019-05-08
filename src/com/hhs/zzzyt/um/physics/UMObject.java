package com.hhs.zzzyt.um.physics;

public class UMObject {
	public float x, y, z, vx, vy, vz, m;

	@Override
	public String toString() {
		return "UMObject [x=" + x + ", y=" + y + ", z=" + z + ", vx=" + vx + ", vy=" + vy + ", vz=" + vz + ", m=" + m
				+ "]";
	}

	public UMObject() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(m);
		result = prime * result + Float.floatToIntBits(vx);
		result = prime * result + Float.floatToIntBits(vy);
		result = prime * result + Float.floatToIntBits(vz);
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UMObject other = (UMObject) obj;
		if (Float.floatToIntBits(m) != Float.floatToIntBits(other.m))
			return false;
		if (Float.floatToIntBits(vx) != Float.floatToIntBits(other.vx))
			return false;
		if (Float.floatToIntBits(vy) != Float.floatToIntBits(other.vy))
			return false;
		if (Float.floatToIntBits(vz) != Float.floatToIntBits(other.vz))
			return false;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	public UMObject(float x, float y, float z, float vx, float vy, float vz, float m) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.m = m;
	}

}
