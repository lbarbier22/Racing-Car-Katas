package tddmicroexercises.telemetrysystem;

public interface ITelemetryClient {
    boolean isConnected();
    void connect(String connectionString);
    void disconnect();
    void send(String message);
    String receive();
    void sendDiagnosticMessage();
}
