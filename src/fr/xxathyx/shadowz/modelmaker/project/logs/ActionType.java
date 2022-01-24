package fr.xxathyx.shadowz.modelmaker.project.logs;

public enum ActionType {
	
	CREATE, PLACE, DESTRUCT, MOVE, ROTATE;
	
	public String toString() {
		
		if(this.equals(CREATE)) {
			return "CREATE";
		}
		
		if(this.equals(PLACE)) {
			return "PLACE";
		}
		
		if(this.equals(DESTRUCT)) {
			return "DESTRUCT";
		}
		
		if(this.equals(MOVE)) {
			return "MOVE";
		}
		
		if(this.equals(ROTATE)) {
			return "ROTATE";
		}
		return null;
	}
}