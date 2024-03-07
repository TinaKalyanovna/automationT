import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class View {
    public void printResult(double result, String message, Enum displayColor){
        System.out.printf(displayColor + message + "%.2f\n",result, Colors.RESET);
    }

    public void printResultInt(int result, String message, Enum displayColor){
        System.out.printf(displayColor + message + "%d\n",result, Colors.RESET);
    }

    public static void println(String message) {
        System.out.println(message);
    }

}
