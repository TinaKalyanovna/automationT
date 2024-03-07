import java.util.ArrayList;

import javax.swing.text.View;

public class Presenter {
    private final MontyHall montyHall;
    private final View view;
    private final int iteration;

    public Presenter(int iteration){
        this.montyHall = MontyHall.getMontyHall();
        this.view = new View();
        this.iteration = iteration;
    }

    public void start(){
        for (double i = 0; i < iteration; i++){
            ArrayList<Door> doors = montyHall.getDoors();
            Boolean[] temp = new Boolean[3];
            montyHall.shuffleDoors();
            int firstChoice = montyHall.random();
            int numberFromLeader = montyHall.getDoorWithGoat(firstChoice);
            int j =0;
            while (j < temp.length) {
                if(j != firstChoice && j != numberFromLeader){
                    montyHall.addResult(i, doors.get(j).openDoor());
                }
                j++;
            }
        }
        view.printResult(percentWinsCount(), "Процент побед: ", Colors.YELLOW);
        view.printResultInt((int)getInfo("Коза"), "Поражения. Количество выбранных дверей с козой: ", Colors.RED);
        view.printResultInt((int)getInfo("Автомобиль"), "Победы. Количество выбранных дверей с автомобилем: ", Colors.GREEN);
    }

    private double percentWinsCount(){
        double wins = montyHall.getResults().values().stream().filter(e -> e.equalsIgnoreCase("автомобиль")).toList().size();
        double allcounted = montyHall.getResults().size();
        return wins * 100 / allcounted;
    }

    private double getInfo(String param){
        double unit = 0;
        for(String value : montyHall.getResults().values()){
            if(value.equalsIgnoreCase(param))
                unit++;
        }
        return unit;
    }
}
