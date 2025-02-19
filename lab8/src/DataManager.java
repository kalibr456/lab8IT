import handlers.DataHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

public class DataManager {
    private final List<DataHandler> handlers = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private List<String> data;

    public void registerDataProcessor(Object processor) {
        if (processor instanceof DataHandler) {
            if (handlers.stream().anyMatch(h -> h.getClass().equals(processor.getClass()))) {
                System.out.println("Handler already registered: " + processor.getClass().getSimpleName());
                return;
            }
            handlers.add((DataHandler) processor);
        }
    }

    public void loadData(String filePath) throws IOException {
        data = Files.readAllLines(Path.of(filePath));
        System.out.println("Data loaded from file: " + filePath);
    }

    public void processData() throws InterruptedException {
        if (data == null) {
            throw new IllegalStateException("Data not loaded. Call loadData() first.");
        }

        CountDownLatch latch = new CountDownLatch(handlers.size());
        for (DataHandler handler : handlers) {
            executorService.submit(() -> {
                handler.handleData(data);
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("Data processed: " + data);
    }

    public void saveData(String filePath) throws IOException {
        Files.write(Path.of(filePath), data);
        System.out.println("Data saved to file: " + filePath);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
