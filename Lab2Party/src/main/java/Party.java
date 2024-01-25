/*
	Name: Sudarshan Krishnan
	Username: kriss03
*/

public class Party {

	Hero[] heroes;

	public Party() {
		this.heroes = new Hero[3]; 

	}

	public void addHero(Hero hero, int index) {
	       String name = hero.getName();

	       for (Hero existingHero : heroes) {
	          
	    	   if (existingHero != null && existingHero.getName().equals(name)) 
	    	   
	    	   {
	               
	    		   System.out.println(name + " is already in the party.");
	    		   return;
	           }
	       }

	       // Add the hero to the specified index in the party
	       heroes[index] = hero;
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