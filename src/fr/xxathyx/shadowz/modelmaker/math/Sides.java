package fr.xxathyx.shadowz.modelmaker.math;

import org.bukkit.util.Vector;

public class Sides {
	
	public Vector getTopMaximum() {
		return new Vector(0.3, 2.08, 0.30);
	}
	
	public Vector getTopMinimum() {
		return new Vector(-0.3, 2, -0.35);
	}
	
	public Vector getFrontMaximum() {
		return new Vector(0.3, 2, 0.35);
	}
	
	public Vector getFrontMinimum() {
		return new Vector(-0.3, 1.4, 0);
	}
	
	public Vector getBackMaximum() {
		return new Vector(0.3, 2, 0);
	}
	
	public Vector getBackMinimum() {
		return new Vector(-0.3, 1.4, -0.35);
	}
	
	public Vector getRightMaximum() {
		return new Vector(0.35, 2, 0.3);
	}
	
	public Vector getRightMinimum() {
		return new Vector(0, 1.4, -0.3);
	}
	
	public Vector getLeftMaximum() {
		return new Vector(0, 2, 0.3);
	}
	
	public Vector getLeftMinimum() {
		return new Vector(-0.35, 1.4, -0.3);
	}
}