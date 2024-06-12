package tddmicroexercises.telemetrysystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TelemetryDiagnosticControlsTest {
    private TelemetryDiagnosticControls telemetryDiagnosticControls;

    @BeforeEach
    void setUp() {
        telemetryDiagnosticControls = new TelemetryDiagnosticControls();
    }

    @Test
    void testCheckTransmissionSuccessfulConnection() throws Exception {
        // Arrange
        TelemetryClient telemetryClient = new TelemetryClient() {
            private boolean onlineStatus = true;

            @Override
            public boolean getOnlineStatus() {
                return onlineStatus;
            }

            @Override
            public void connect(String telemetryServerConnectionString) {
                // Simulate connection logic
            }

            @Override
            public void disconnect() {
                // Simulate disconnection logic
            }

            @Override
            public void send(String message) {
                // Simulate sending message
            }

            @Override
            public String receive() {
                return "Diagnostic message response";
            }
        };
        telemetryDiagnosticControls.setTelemetryClient(telemetryClient);

        // Act
        telemetryDiagnosticControls.checkTransmission();

        // Assert
        assertEquals("Diagnostic message response", telemetryDiagnosticControls.getDiagnosticInfo());
    }

    @Test
    void testCheckTransmissionFailedConnection() {
        // Arrange
        TelemetryClient telemetryClient = new TelemetryClient() {
            private boolean onlineStatus = false;

            @Override
            public boolean getOnlineStatus() {
                return onlineStatus;
            }

            @Override
            public void connect(String telemetryServerConnectionString) {
                // Simulate connection logic
            }

            @Override
            public void disconnect() {
                // Simulate disconnection logic
            }

            @Override
            public void send(String message) {
                // Simulate sending message
            }

            @Override
            public String receive() {
                // Simulate receiving message
                return "";
            }
        };
        telemetryDiagnosticControls.setTelemetryClient(telemetryClient);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            telemetryDiagnosticControls.checkTransmission();
        });

        assertEquals("Unable to connect.", exception.getMessage());
    }

    @Test
    void testCheckTransmissionReceivesRandomSequence() throws Exception {
        // Arrange
        TelemetryClient telemetryClient = new TelemetryClient() {
            private boolean onlineStatus = true;

            @Override
            public boolean getOnlineStatus() {
                return onlineStatus;
            }

            @Override
            public void connect(String telemetryServerConnectionString) {
                // Simulate connection logic
            }

            @Override
            public void disconnect() {
                // Simulate disconnection logic
            }

            @Override
            public void send(String message) {
                // Simulate sending message
            }

            @Override
            public String receive() {
                return "Random sequence";
            }
        };
        telemetryDiagnosticControls.setTelemetryClient(telemetryClient);

        // Act
        telemetryDiagnosticControls.checkTransmission();

        // Assert
        assertEquals("Random sequence", telemetryDiagnosticControls.getDiagnosticInfo());
    }
}
