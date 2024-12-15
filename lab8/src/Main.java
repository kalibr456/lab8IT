import handlers.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        // Регистрация обработчиков
        dataManager.registerDataProcessor(new FilterHandler());
        dataManager.registerDataProcessor(new TransformHandler());
        dataManager.registerDataProcessor(new AggregateHandler());
        dataManager.registerDataProcessor(new SortHandler());

        try {
            dataManager.loadData("input.txt");
            dataManager.processData();
            dataManager.saveData("output.txt");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            dataManager.shutdown();
        }
    }
}
