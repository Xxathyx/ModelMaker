package fr.xxathyx.shadowz.modelmaker.part.hitbox.side;

public enum SideType {
	
	TOP, FRONT, BACK, DOWN, RIGHT, LEFT, NONE;
	
	public String toString() {
		
		if(this.equals(TOP)) {
			return "TOP";
		}
		
		if(this.equals(FRONT)) {
			return "FRONT";
		}
		
		if(this.equals(BACK)) {
			return "BACK";
		}
		
		if(this.equals(DOWN)) {
			return "DOWN";
		}
		
		if(this.equals(RIGHT)) {
			return "RIGHT";
		}
		
		if(this.equals(LEFT)) {
			return "LEFT";
		}
		
		if(this.equals(NONE)) {
			return "NONE";
		}
		return "null";
	}
}