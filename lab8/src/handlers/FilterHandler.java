package handlers;

import annotations.DataProcessor;
import java.util.List;

@DataProcessor
public class FilterHandler implements DataHandler {
    @Override
    public void handleData(List<String> data) {
        data.removeIf(s -> s.length() < 5);
    }
}
