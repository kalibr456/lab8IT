package handlers;

import annotations.DataProcessor;
import java.util.List;

@DataProcessor
public class SortHandler implements DataHandler {
    @Override
    public void handleData(List<String> data) {
        data.sort(String::compareTo);
    }
}
