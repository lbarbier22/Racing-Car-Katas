package tddmicroexercises.telemetrysystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TelemetryDiagnosticControlsTest {
    private TelemetryDiagnosticControls telemetryDiagnosticControls;

    @BeforeEach
    void setUp() {
        TelemetryClient telemetryClient = new TelemetryClient();
        telemetryDiagnosticControls = new TelemetryDiagnosticControls(telemetryClient);
    }

    @Test
    void testCheckTransmissionSuccessfulConnection() throws Exception {
        // Act
        telemetryDiagnosticControls.checkTransmission();

        // Assert
        assertNotNull(telemetryDiagnosticControls.getDiagnosticInfo());
        assertFalse(telemetryDiagnosticControls.getDiagnosticInfo().isEmpty());
    }

    @Test
    void testCheckTransmissionReceivesRandomSequence() throws Exception {
        // Arrange
        TelemetryClient telemetryClient = new TelemetryClient() {
            @Override
            public String receive() {
                return "Random sequence";
            }
        };
        telemetryDiagnosticControls = new TelemetryDiagnosticControls(telemetryClient);

        // Act
        telemetryDiagnosticControls.checkTransmission();

        // Assert
        assertEquals("Random sequence", telemetryDiagnosticControls.getDiagnosticInfo());
    }


    @Test
    void testCheckTransmissionFailedConnection() {
        // Arrange
        TelemetryClient telemetryClient = new TelemetryClient() {
            @Override
            public void connect(String connectionString) {
                // Simulate failed connection
            }
        };
        telemetryDiagnosticControls = new TelemetryDiagnosticControls(telemetryClient);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            telemetryDiagnosticControls.checkTransmission();
        });

        assertEquals("Unable to connect.", exception.getMessage());
    }
}
