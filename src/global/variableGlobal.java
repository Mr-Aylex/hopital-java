package global;

import entity.Utilisateur;
import view.Main2;

public class variableGlobal {
	
	private static Utilisateur user;
	

	public static Utilisateur getUser() {
		return user;
	}

	public static void setUser(Utilisateur newuser) {
		user = newuser;
	}
}
