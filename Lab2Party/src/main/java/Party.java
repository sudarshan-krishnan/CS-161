/*
	Name: Sudarshan Krishnan
	Username: kriss03
*/

public class Party {

	Hero[] heroes;

	public Party() {
		this.heroes = new Hero[3]; 

	}

	public void addHero(Hero hero ,int integer){

        for (int i = 0; i < heroes.length; i++) {
			Hero h = heroes[i]; // Getting the hero at the current index
				
				// Checking if names match
				if (h.getName().equals(hero.getName())) {
					System.out.println(hero.getName() + " is already in the party.");
					return; 
				}
			}
			heroes[integer] = hero;

		}

	public void removeHero(int index) {
		heroes[index] = null;
	}
 
	public Hero getHero(int index) {
		return heroes[index];
	}

    public void gainExperience(int experience) {
        System.out.println("The party gained " + experience + " experience.");
        for (Hero hero : heroes) {
            if (hero != null) {
                hero.gainExperience(experience);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Party:\n");
        for (Hero hero : heroes) {
            if (hero != null) {
                sb.append(hero.toString()).append("\n");
            }
        }
        return sb.toString();
}
}