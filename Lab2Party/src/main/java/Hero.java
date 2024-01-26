/*
	Name: Sudarshan Krishnan
	Username: kriss03
*/

public class Hero {

    // Properties
    String name = "";
    String role = "";
    int level;
    int experience;
    final int MAX_LEVEL = 100; 

    // Predefined roles
    public static final String[] ROLES = { "Warrior", "Priest", "Wizard", "Thief" };

    // Constructor to initialize hero with a name
    public Hero(String nam) {
        level = 1; 
        experience = 0; 
        role = "Unassigned"; 
        this.name = nam;
    }

    // Assigns a valid role to the hero
    public void setRole(String duty) {
        boolean roleFound = false;

        for (String validRole : ROLES) {
            if (validRole.equals(duty)) {
                this.role = duty;
                roleFound = true;
                break;
            }
        }

        if (!roleFound) {
            System.out.println("Invalid role");
            role = "Unassigned";
        }
    }

    // Getter methods
    public String getName() { return name; }
    public String getRole() { return role; }
    public int getLevel() { return level; }
    public int getExperience() { return experience; }

    // Calculates experience required for the next level
    public int expToLevelUp() {
        return (int) Math.pow(level, 2);
    }

    // Adds experience and handles leveling up
    public int gainExperience(int expGained) {
        experience += expGained;

        while (level < MAX_LEVEL && experience >= expToLevelUp()) {
            experience -= expToLevelUp(); 
            level++; 
            System.out.println(name + " is now level " + level + "!");
        }
        return level;
    }

    // Returns a string representation of the hero
    public String toString() {
        return name + " the " + role + " is level " + level + " with " + experience + " experience.";
    }

}
