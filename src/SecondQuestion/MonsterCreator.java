package SecondQuestion;
import java.util.*;

class Monster {
    // Traits
    String eyeColor;
    String featherColor;
    boolean magicalAbilities;
    int size;
    int strength;
    int durability;
    String weakness;
    int aggressionLevel;

    // Constructor
    public Monster(String eyeColor, String featherColor, boolean magicalAbilities,
                   int size, int strength, int durability, String weakness, int aggressionLevel) {
        this.eyeColor = eyeColor;
        this.featherColor = featherColor;
        this.magicalAbilities = magicalAbilities;
        this.size = size;
        this.strength = strength;
        this.durability = durability;
        this.weakness = weakness;
        this.aggressionLevel = aggressionLevel;
    }

    // Display monster traits
    public void displayTraits() {
        System.out.println("Monster Traits:");
        System.out.println("Eye Color: " + eyeColor);
        System.out.println("Feather Color: " + featherColor);
        System.out.println("Magical Abilities: " + magicalAbilities);
        System.out.println("Size: " + size);
        System.out.println("Strength: " + strength);
        System.out.println("Durability: " + durability);
        System.out.println("Weakness: " + weakness);
        System.out.println("Aggression Level: " + aggressionLevel);
    }

    // Generate a baby monster by copying traits randomly
    public static Monster generateBabyMonster(Monster parent1, Monster parent2) {
        Random random = new Random();
        Monster babyMonster = new Monster(
                random.nextBoolean() ? parent1.eyeColor : parent2.eyeColor,
                random.nextBoolean() ? parent1.featherColor : parent2.featherColor,
                random.nextBoolean() ? parent1.magicalAbilities : parent2.magicalAbilities,
                random.nextBoolean() ? parent1.size : parent2.size,
                random.nextBoolean() ? parent1.strength : parent2.strength,
                random.nextBoolean() ? parent1.durability : parent2.durability,
                random.nextBoolean() ? parent1.weakness : parent2.weakness,
                random.nextBoolean() ? parent1.aggressionLevel : parent2.aggressionLevel
        );

        return babyMonster;
    }
}

public class MonsterCreator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating Monster 1:");
        Monster monster1 = createMonster(scanner);

        System.out.println("\nCreating Monster 2:");
        Monster monster2 = createMonster(scanner);

        // Display traits of created monsters
        monster1.displayTraits();
        System.out.println();
        monster2.displayTraits();
        System.out.println();

        // Generate a baby monster
        System.out.println("\nGenerating Baby Monster:");
        Monster babyMonster = Monster.generateBabyMonster(monster1, monster2);
        babyMonster.displayTraits();

        scanner.close();
    }

    // Helper method to create a monster by reading user input
    private static Monster createMonster(Scanner scanner) {
        System.out.print("Enter Eye Color: ");
        String eyeColor = scanner.nextLine();

        System.out.print("Enter Feather Color: ");
        String featherColor = scanner.nextLine();

        System.out.print("Has Magical Abilities? (true/false): ");
        boolean magicalAbilities = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter Size: ");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Strength: ");
        int strength = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Durability: ");
        int durability = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Weakness: ");
        String weakness = scanner.nextLine();

        System.out.print("Enter Aggression Level: ");
        int aggressionLevel = Integer.parseInt(scanner.nextLine());

        return new Monster(eyeColor, featherColor, magicalAbilities,
                size, strength, durability, weakness, aggressionLevel);
    }
}
