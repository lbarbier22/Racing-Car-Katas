package tddmicroexercises.telemetrysystem;

public class TelemetryDiagnosticControls {
    private final String diagnosticChannelConnectionString = "*111#";
    private final TelemetryClient telemetryClient;
    private String diagnosticInfo = "";

    public TelemetryDiagnosticControls() {
        telemetryClient = new TelemetryClient();
    }

    public String getDiagnosticInfo() {
        return diagnosticInfo;
    }

    public void checkTransmission() throws Exception {
        diagnosticInfo = "";
        disconnectAndReconnect();
        sendMessageAndGetDiagnosticInfo();
    }

    private void disconnectAndReconnect() throws Exception {
        telemetryClient.disconnect();
        int retryLeft = 3;
        while (!telemetryClient.getOnlineStatus() && retryLeft > 0) {
            telemetryClient.connect(diagnosticChannelConnectionString);
            retryLeft--;
        }
        if (!telemetryClient.getOnlineStatus()) {
            throw new Exception("Unable to connect.");
        }
    }

    private void sendMessageAndGetDiagnosticInfo() {
        telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        diagnosticInfo = telemetryClient.receive();
    }
}
