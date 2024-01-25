/*
	Name: Sudarshan Krishnan
	Username: kriss03
*/

public class Hero {

	String name = "";
	String role = "";
	int level;
	int experience;

	final int MAX_LEVEL = 100;

	public static final String[] ROLES = { "Warrior", "Priest", "Wizard", "Thief" };

	public Hero(String nam) {

		level = 1;
		experience = 0;
		role = "Unassigned";
		this.name = nam;

	}

	public void setRole(String duty) {
		boolean bool = false;

		for (int index = 0; index < ROLES.length; index++) {
			if (ROLES[index].equals(duty)) {
				this.role = duty;
				bool = true;
				break; // Exit the loop once a valid role is found
			}
		}

		if (!bool) {
			System.out.println("Invalid role");
			role = "Unassigned";
		}
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}

	public int expToLevelUp() {
		int r = (int) Math.pow(level, 2);
		return r;
	}

	public int gainExperience(int e) {

		experience += e;

		while (level < MAX_LEVEL && experience >= this.expToLevelUp()) {

			experience -= this.expToLevelUp();
			level++;

			System.out.println(name + " is now level " + level + "!");

		}
		return level;
	}

	public String toString() {

		String s = "";
		return this.name + " the " + this.role + " is level " + this.level + " with " + this.experience
				+ " experience.";

	}

}
