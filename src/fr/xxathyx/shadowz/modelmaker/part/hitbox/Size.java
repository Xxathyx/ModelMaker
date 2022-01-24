package fr.xxathyx.shadowz.modelmaker.part.hitbox;

public enum Size {
	
	NORMAL, SMALL, MINIMUM, ATOMIQUE;
	
	public String toString() {
		
		if(this.equals(NORMAL)) {
			return "NORMAL";
		}
		
		if(this.equals(SMALL)) {
			return "SMALL";
		}
		
		if(this.equals(MINIMUM)) {
			return "MINIMUM";
		}
		
		if(this.equals(ATOMIQUE)) {
			return "ATOMIQUE";
		}
		return "null";
	}
}