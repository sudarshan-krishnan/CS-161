/*
	Name: Sudarshan Krishnan
	Username: kriss03
*/

public class Party {

    Hero[] heroes; // Array to store heroes in the party

    // Constructor initializes the party with space for 3 heroes
    public Party() {
        this.heroes = new Hero[3];
    }

    // Adds a hero to the party if not already present
    public void addHero(Hero hero, int index) {
        String heroName = hero.getName();

        for (Hero existingHero : heroes) {
            if (existingHero != null && existingHero.getName().equals(heroName)) {
                System.out.println(heroName + " is already in the party.");
                return;
            }
        }
        heroes[index] = hero; // Place hero in specified index
    }

    // Removes a hero from the party by setting their slot to null
    public void removeHero(int index) {
        heroes[index] = null;
    }

    // Retrieves a hero from the party by index
    public Hero getHero(int index) {
        return heroes[index];
    }

    // Distributes experience to all heroes in the party
    public void gainExperience(int experience) {
        System.out.println("The party gained " + experience + " experience.");
        for (Hero hero : heroes) {
            if (hero != null) {
                hero.gainExperience(experience);
            }
        }
    }

    // Generates and returns a string representation of the party
    public String toString() {
        StringBuilder sb = new StringBuilder("Party:\n");
        for (Hero hero : heroes) {
            if (hero != null) {
                sb.append(hero.toString()).append("\n");		// Used a string builder append function
            }
        }
        return sb.toString();		//Return final string
    }
}
