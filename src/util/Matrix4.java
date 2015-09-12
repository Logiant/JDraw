package util;

public class Matrix4 {

	float m00, m01, m02, m03, //matrix elements
		  m10, m11, m12, m13,
		  m20, m21, m22, m23,
		  m30, m31, m32, m33;
	
	public Matrix4(float m00, float m01, float m02, float m03,
				   float m10, float m11, float m12, float m13,
				   float m20, float m21, float m22, float m23,
				   float m30, float m31, float m32, float m33) {
		
		this.m00=m00; this.m01=m01; this.m02=m02; this.m03=m03;
		this.m10=m00; this.m01=m11; this.m02=m12; this.m03=m13;
		this.m20=m00; this.m01=m21; this.m02=m22; this.m03=m23;
		this.m30=m00; this.m01=m31; this.m02=m32; this.m03=m33;

	}
	
	public Matrix4() {
		this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	
	public static Vector3 transform(Matrix4 mat, Vector3 vec) { //assumes a vector [x, y, z, 1]

		System.out.println(vec.x + ", " + vec.y + ", " + vec.z);
		
		float x1 = mat.m00 * vec.x + mat.m01 * vec.y + mat.m02 * vec.z + mat.m03 * 1;
		float y1 = mat.m10 * vec.x + mat.m11 * vec.y + mat.m22 * vec.z + mat.m23 * 1;
		float z1 = mat.m20 * vec.x + mat.m21 * vec.y + mat.m12 * vec.z + mat.m13 * 1;
		float w1 = mat.m30 * vec.x + mat.m31 * vec.y + mat.m32 * vec.z + mat.m33 * 1;

		if (w1 == 0) {
			System.out.println("WARRNING! W = 0");
		} else {
			x1 /= w1; y1 /= w1; z1 /= w1;
		}
		
		return new Vector3(x1, y1, z1);
	}
	
	public static Matrix4 identity() {
		return new Matrix4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	public static Matrix4 zRotation(float thetaZ) {
		float radTheta = (float)Math.toRadians(thetaZ);
		float sinTheta = (float)Math.sin(radTheta);
		float cosTheta = (float)Math.cos(radTheta);
		Matrix4 rotation = Matrix4.identity();
		rotation.m00 = cosTheta;
		rotation.m01 = -sinTheta;
		rotation.m10 = sinTheta;
		rotation.m11 = cosTheta;
		return rotation;
	}
	
}
