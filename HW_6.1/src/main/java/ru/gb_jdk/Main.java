import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import main.java.ru.gb_jdk.Game.Presenter;

public class Main {
        public static void main(String[] args) {
        Presenter presenter = new Presenter(1000);
        presenter.start();
    }

}
