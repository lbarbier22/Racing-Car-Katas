package tddmicroexercises.tirepressuremonitoringsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlarmTest {

    @InjectMocks
    Sensor mockSensor = mock(Sensor.class);

    @Spy
    Alarm spyAlarm = new Alarm(mockSensor);

    @BeforeEach
    void init(){

    }

    @Test
    void testAlarmOnLowPressure() {
        //WHEN
        when(mockSensor.popNextPressurePsiValue()).thenReturn(16.00);

        spyAlarm.check();
        //THEN
        assertTrue(spyAlarm.isAlarmOn());
    }

    @Test
    void testAlarmOnHighPressure() {
        //WHEN
        when(mockSensor.popNextPressurePsiValue()).thenReturn(22.00);

        spyAlarm.check();
        //THEN
        assertTrue(spyAlarm.isAlarmOn());
    }

    @Test
    void testAlarmOffForNormalPressure() {
        //WHEN
        when(mockSensor.popNextPressurePsiValue()).thenReturn(18.00);

        spyAlarm.check();
        //THEN
        assertFalse(spyAlarm.isAlarmOn());
    }
    
}
