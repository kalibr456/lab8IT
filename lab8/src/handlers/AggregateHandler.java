package handlers;

import annotations.DataProcessor;
import java.util.List;

@DataProcessor
public class AggregateHandler implements DataHandler {
    @Override
    public void handleData(List<String> data) {
        int totalLength = data.stream().mapToInt(String::length).sum();
        System.out.println("Total length of all strings: " + totalLength);
    }
}
