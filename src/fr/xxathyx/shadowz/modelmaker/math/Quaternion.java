package fr.xxathyx.shadowz.modelmaker.math;

import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class Quaternion {
	
	private double w;
	private double x;
	private double y;
	private double z;
	
	public double i, j, k = -1;
	
	public Quaternion(double w, double x, double y, double z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Quaternion(EulerAngle eulerAngle) {
				
		double cy = Math.cos(eulerAngle.getZ() * 0.5);
	    double sy = Math.sin(eulerAngle.getZ() * 0.5);
	    double cp = Math.cos(eulerAngle.getY() * 0.5);
	    double sp = Math.sin(eulerAngle.getY() * 0.5);
	    double cr = Math.cos(eulerAngle.getX() * 0.5);
	    double sr = Math.sin(eulerAngle.getX() * 0.5);
		
	    this.w = cr * cp * cy + sr * sp * sy;
	    this.x = sr * cp * cy - cr * sp * sy;
	    this.y = cr * sp * cy + sr * cp * sy;
	    this.z = cr * cp * sy - sr * sp * cy;
	}
	
	public Quaternion() {
		
	}
	
	public double getW() {
		return w;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setW(double w) {
		this.w = w;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public Quaternion fromEuler(EulerAngle eulerAngle) {
		
	    double cy = Math.cos(eulerAngle.getZ() * 0.5);
	    double sy = Math.sin(eulerAngle.getZ() * 0.5);
	    double cp = Math.cos(eulerAngle.getY() * 0.5);
	    double sp = Math.sin(eulerAngle.getY() * 0.5);
	    double cr = Math.cos(eulerAngle.getX() * 0.5);
	    double sr = Math.sin(eulerAngle.getX() * 0.5);
		
	    this.w = cr * cp * cy + sr * sp * sy;
	    this.x = sr * cp * cy - cr * sp * sy;
	    this.y = cr * sp * cy + sr * cp * sy;
	    this.z = cr * cp * sy - sr * sp * cy;
	    
	    return this;
	}
	
	public EulerAngle toEuler() {
		
		double x;
		double y;
		double z;
		
	    double sinr_cosp = 2 * (this.w * this.x + this.y * this.z);
	    double cosr_cosp = 1 - 2 * (this.x * this.x + this.y * this.y);
	    
	    x = Math.atan2(sinr_cosp, cosr_cosp);

	    double sinp = 2 * (this.w * this.y - this.z * this.x);
	    
	    if (Math.abs(sinp) >= 1) {
	        y = Math.copySign(Math.PI / 2, sinp);
	    }else {
	    	y = Math.asin(sinp);
	    }
	    
	    double siny_cosp = 2 * (w * this.z + this.x * this.y);
	    double cosy_cosp = 1 - 2 * (this.y * this.y + this.z * this.z);
	    
	    z = Math.atan2(siny_cosp, cosy_cosp);
	    
	    return new EulerAngle(x, y, z);
	}
	
	public Vector rotateVector(Vector vector) {
				
		Quaternion normalized = this.normalize();
		Quaternion conjugated = this.conjugate();
		
		Quaternion pure = getPure(vector);
		
		//normalized*pure = result
		//result * conjugated
		
		double w = normalized.getW() * pure.getW() - normalized.getX() * pure.getX() - normalized.getY() * pure.getY() - normalized.getZ() * pure.getZ();
		double x = normalized.getW() * pure.getX() + normalized.getX() * pure.getW() + normalized.getY() * pure.getZ() - normalized.getZ() * pure.getY();
		double y = normalized.getW() * pure.getY() - normalized.getX() * pure.getZ() + normalized.getY() * pure.getW() + normalized.getZ() * pure.getX();
		double z = normalized.getW() * pure.getZ() + normalized.getX() * pure.getY() - normalized.getY() * pure.getX() + normalized.getZ() * pure.getW();
		
		Quaternion result = new Quaternion(w, x, y, z);
		
		//double wn = result.getW() * conjugated.getW() - result.getX() * conjugated.getX() - result.getY() * conjugated.getY() - result.getZ() * conjugated.getZ();
		double xn = result.getW() * conjugated.getX() + result.getX() * conjugated.getW() + result.getY() * conjugated.getZ() - result.getZ() * conjugated.getY();
		double yn = result.getW() * conjugated.getY() - result.getX() * conjugated.getZ() + result.getY() * conjugated.getW() + result.getZ() * conjugated.getX();
		double zn = result.getW() * conjugated.getZ() + result.getX() * conjugated.getY() - result.getY() * conjugated.getX() + result.getZ() * conjugated.getW();
		
	    return new Vector(xn, yn, zn);
	}
	
	public Quaternion normalize() {
		
		double magnitude = getMagnitude();
		
		this.w = w / magnitude;
		this.x = x / magnitude;
		this.y = y / magnitude;
		this.z = z / magnitude;
		
		return this;
	}
	
	public Quaternion conjugate() {
		
		this.x = -x;
		this.y = -y;
		this.z = -z;
		
		return this;
	}
	
	public double getMagnitude() {
		
		double magnitude = Math.sqrt((w * w) + (x * x) + (y * y) + (z * z));
		
		if(magnitude < 1 && magnitude > 0.99) {
			magnitude = 1;
		}
		return magnitude;
	}
	
	public Quaternion getPure(Vector vector) {
		return new Quaternion(0, vector.getX() * i, vector.getY() * j, vector.getZ() * k);
	}
	
	public void rotateX(double rad) {
		this.w = w + Math.cos(rad / 2);
		this.x = x + Math.sin(rad / 2);
	}
	
	public void rotateY(double rad) {
		this.w = w + Math.cos(rad / 2);
		this.y = y + Math.sin(rad/2);
	}
	
	public void rotateZ(double rad) {
		this.w = w + Math.cos(rad / 2);
		this.z = z + Math.sin(rad / 2);
	}
}