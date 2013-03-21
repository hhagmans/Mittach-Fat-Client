package models;

public class User {

	private String shortname;
	private boolean isAdmin;

	public User(String shortname, boolean isAdmin) {
		this.shortname = shortname;
		this.isAdmin = isAdmin;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
