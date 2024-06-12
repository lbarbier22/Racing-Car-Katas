package tddmicroexercises.telemetrysystem;

import java.util.Random;

public class TelemetryClient {
    public static final String DIAGNOSTIC_MESSAGE = "AT#UD";
    private boolean onlineStatus;
    private String diagnosticMessageResult = "";
    private final Random connectionEventsSimulator = new Random(42);

    public boolean isConnected() {
        return onlineStatus;
    }

    public void connect(String connectionString) {
        simulateConnection();
    }

    public void disconnect() {
        onlineStatus = false;
    }

    public void send(String message) {
        simulateMessageSending(message);
    }

    public String receive() {
        return diagnosticMessageResult.isEmpty() ? generateRandomMessage() : diagnosticMessageResult;
    }

    private void simulateConnection() {
        onlineStatus = connectionEventsSimulator.nextInt(10) <= 8;
    }

    private void simulateMessageSending(String message) {
        if (message.equals(DIAGNOSTIC_MESSAGE)) {
            diagnosticMessageResult = "Diagnostic info...";
        }
    }

    private String generateRandomMessage() {
        StringBuilder message = new StringBuilder();
        int messageLength = connectionEventsSimulator.nextInt(50) + 60;
        for (int i = messageLength; i >= 0; i--) {
            message.append((char) (connectionEventsSimulator.nextInt(40) + 86));
        }
        return message.toString();
    }
}
