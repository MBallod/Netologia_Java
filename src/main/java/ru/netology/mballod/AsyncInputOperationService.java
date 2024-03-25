package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;
@Getter
@Setter
public class AsyncInputOperationService {
    private Queue<Operation> queue = new LinkedList<>();
    private OperationService operationService;
    AsyncInputOperationService(OperationService operationService){
        this.operationService = operationService;
        //this.queue = new LinkedList<>();
    }
    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }
    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation: " + operation);
                operationService.addOperation(operation);
            }
        }
    }
    public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }

}
