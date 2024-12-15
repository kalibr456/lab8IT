package handlers;

import annotations.DataProcessor;
import java.util.List;

@DataProcessor
public class TransformHandler implements DataHandler {
    @Override
    public void handleData(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            data.set(i, data.get(i).toUpperCase());
        }
    }
}
