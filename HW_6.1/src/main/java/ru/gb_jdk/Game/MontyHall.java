import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import lombok.Value;
import org.apache.commons.math3.random.RandomDataGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.text.View;

public class MontyHall {
    private static final RandomDataGenerator random = new RandomDataGenerator();
    private static MontyHall montyHall;
    HashMap<Double, String> results;
    ArrayList<Door> doors = new ArrayList<>();

    private MontyHall() {
        results = new HashMap<>();
        doors.add(0, new Door(new Auto()));
        doors.add(1, new Door(new Goat()));
        doors.add(2, new Door(new Goat()));
        Collections.shuffle(doors);
    }

    public static MontyHall getMontyHall() {
        if (montyHall == null)
            montyHall = new MontyHall();
        return montyHall;
    }

    public HashMap<Double, String> getResults() {
        return results;
    }

    public void addResult(Double iteration, String result) {
        results.put(iteration, result);
    }

    public void shuffleDoors() {
        Collections.shuffle(doors);
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public int random() {
        return random.nextInt(0, 2);
    }

    public int getDoorWithGoat(int firstChoice) {
        boolean flag = false;
        int doorNumberWithGoat = -1;
        try {
            while (!flag) {
                int random = random();
                Door door = doors.get(random);
                if (random != firstChoice && door.openDoor().equalsIgnoreCase("коза")) {
                    doorNumberWithGoat = random;
                    flag = true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            View.println(e.getMessage());
        }
        return doorNumberWithGoat;
    }
}
