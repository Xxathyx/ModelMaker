package fr.xxathyx.shadowz.modelmaker.part.hitbox;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.util.Vector;

import fr.xxathyx.shadowz.modelmaker.math.Quaternion;
import fr.xxathyx.shadowz.modelmaker.math.Sides;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.side.SideType;

public class HitBox {
	
	private Quaternion quaternion;
	private Sides sides = new Sides();
	
	private ArrayList<Vector> points = new ArrayList<Vector>();
		
	private Size boxSize;
		
	public HitBox(Quaternion quaternion, Size size) {
		this.quaternion = quaternion;
		this.boxSize = size;
		
		setupPoints();
	}
	
	public Vector getHitBoxCenter() {
		
		Vector center = new Vector();
		
		center.setX((getD().getX() + getF().getX()) / 2);
		center.setY((getD().getY() + getF().getY()) / 2);
		center.setZ((getD().getZ() + getF().getZ()) / 2);
		
		return center;
	}
	
	public double getMinimumX() {
		
		double[] x = getX();
		Arrays.sort(x);
		
		return new Double(x[0]);
	}
	
	public double getMinimumY() {
		
		double[] y = getY();
		Arrays.sort(y);
		
		return new Double(y[0]);
	}
	
	public double getMinimumZ() {
		
		double[] z = getZ();
		Arrays.sort(z);
		
		return new Double(z[0]);
	}
	
	public double getMaximumX() {
		
		double[] x = getX();
		Arrays.sort(x);
		
		return new Double(x[x.length-1]);
	}
	
	public double getMaximumY() {
		
		double[] y = getY();
		Arrays.sort(y);
		
		return new Double(y[y.length-1]);
	}
	
	public double getMaximumZ() {
		
		double[] z = getZ();
		Arrays.sort(z);
		
		return new Double(z[z.length-1]);
	}
	
	public boolean hitBox(Vector vector) {
				
		if(getMinimumX() <= vector.getX() && vector.getX() <= getMaximumX() && vector.getY() >= getMinimumY() && vector.getY() <= getMaximumY()
				&& vector.getZ() >= getMinimumZ() && vector.getZ() <= getMaximumZ()) {
			return true;
		} 
		return false;
	}
	
	public Vector getA() {
		
		if(boxSize.equals(Size.NORMAL)) {
			return new Vector(-0.35, 1.4, 0.35);
		}
		return null;
	}
	
	public Vector getB() {
		
		if(boxSize.equals(Size.NORMAL)) {
			return new Vector(0.35, 1.4, 0.35);
		}
		return null;
	}
	
	public Vector getC() {
		
		if(boxSize.equals(Size.NORMAL)) {
			return new Vector(0.35, 1.4, -0.35);
		}
		return null;
	}
	
	public Vector getD() {
		
		if(boxSize.equals(Size.NORMAL)) {
			return new Vector(-0.35, 1.4, -0.35);
		}
		return null;
	}
	
	public Vector getE() {
		
		if(boxSize.equals(Size.NORMAL)) {
			return new Vector(-0.35, 2.08, 0.35);
		}
		return null;
	}
	
	public Vector getF() {
		
		if(boxSize.equals(Size.NORMAL)) {
			return new Vector(0.35, 2.08, 0.35);
		}
		return null;
	}
	
	public Vector getG() {
		
		if(boxSize.equals(Size.NORMAL)) {
			return new Vector(0.35, 2.08, -0.35);
		}
		return null;
	}
	
	public Vector getH() {
		
		if(boxSize.equals(Size.NORMAL)) {
			return new Vector(-0.35, 2.08, -0.35);
		}
		return null;
	}
	
	public ArrayList<Vector> getPoints() {
		return points;
	}
	
	public double[] getX() {
		
		double[] x = new double[8];
		
		for(int i = 0; i < getPoints().size(); i++) {
			x[i] = getPoints().get(i).getX();
		}
		
		return x;
	}
	
	public double[] getY() {
		
		double[] y = new double[8];
		
		for(int i = 0; i < getPoints().size(); i++) {
			y[i] = getPoints().get(i).getY();
		}
		
		return y;
	}
	
	public double[] getZ() {
		
		double[] z = new double[8];
		
		for(int i = 0; i < getPoints().size(); i++) {
			z[i] = getPoints().get(i).getZ();
		}
		
		return z;
	}
	
	public SideType getClickedSide(Vector vector, boolean down) {
		
		if(down) {
			return SideType.DOWN;
		}
		
		if(vector.getX() >= setup(sides.getTopMinimum()).getX() && vector.getX() <= setup(sides.getTopMaximum()).getX() && vector.getY() >= setup(sides.getTopMinimum()).getY() &&
				vector.getY() <= setup(sides.getTopMaximum()).getY() && vector.getZ() >= setup(sides.getTopMinimum()).getZ() && vector.getZ() <= setup(sides.getTopMaximum()).getZ()) {
			return SideType.TOP;
		}
		
		if(vector.getX() >= setup(sides.getFrontMinimum()).getX() && vector.getX() <= setup(sides.getFrontMaximum()).getX() && vector.getY() >= setup(sides.getFrontMinimum()).getY() &&
				vector.getY() <= setup(sides.getFrontMaximum()).getY() && vector.getZ() >= setup(sides.getFrontMinimum()).getZ() && vector.getZ() <= setup(sides.getFrontMaximum()).getZ()) {
			return SideType.FRONT;
		}
		
		if(vector.getX() >= setup(sides.getBackMinimum()).getX() && vector.getX() <= setup(sides.getBackMaximum()).getX() && vector.getY() >= setup(sides.getBackMinimum()).getY() &&
				vector.getY() <= setup(sides.getBackMaximum()).getY() && vector.getZ() >= setup(sides.getBackMinimum()).getZ() && vector.getZ() <= setup(sides.getBackMaximum()).getZ()) {
			return SideType.BACK;
		}
		
		if(vector.getX() >= setup(sides.getRightMinimum()).getX() && vector.getX() <= setup(sides.getRightMaximum()).getX() && vector.getY() >= setup(sides.getRightMinimum()).getY() &&
				vector.getY() <= setup(sides.getRightMaximum()).getY() && vector.getZ() >= setup(sides.getRightMinimum()).getZ() && vector.getZ() <= setup(sides.getRightMaximum()).getZ()) {
			return SideType.RIGHT;
		}
		
		if(vector.getX() >= setup(sides.getLeftMinimum()).getX() && vector.getX() <= setup(sides.getLeftMaximum()).getX() && vector.getY() >= setup(sides.getLeftMinimum()).getY() &&
				vector.getY() <= setup(sides.getLeftMaximum()).getY() && vector.getZ() >= setup(sides.getLeftMinimum()).getZ() && vector.getZ() <= setup(sides.getLeftMaximum()).getZ()) {
			return SideType.LEFT;
		}
		return SideType.NONE;
	}
	
	public Vector setup(Vector vector) { // a edit quand on aura finit les quaternions rotate avec la method rotate()
		return vector;
	}
	
	public void setupPoints() {
				
		points.add(getA());
		points.add(getB());
		points.add(getC());
		points.add(getD());
		points.add(getE());
		points.add(getF());
		points.add(getG());
		points.add(getH());
				
		//rotate();
	}
	
	public void rotate() {
		for(int i = 0; i < points.size(); i++) {
			points.set(i, quaternion.rotateVector(points.get(i)));
		}
	}
	
	public Quaternion getQuaternion() {
		return quaternion;
	}
}