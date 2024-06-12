package tddmicroexercises.tirepressuremonitoringsystem;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AlarmTest {

    private class TestSensor extends Sensor {
        private double pressure;
    
    public void setPressure(double pressure) {
            this.pressure = pressure;
        }

    @Override
    public double popNextPressurePsiValue() {
        return pressure;
       }
    }

    @Test
    public void foo() {
        Alarm alarm = new Alarm();
        assertFalse(alarm.isAlarmOn());
    }


    @Test
    void testAlarmOnLowPressure() {
        TestSensor testSensor = new TestSensor();
        testSensor.setPressure(16); 
        Alarm alarm = new Alarm();
        alarm.sensor = testSensor;

        alarm.check();

        assertTrue(alarm.isAlarmOn());
    }

    @Test
    void testAlarmOnHighPressure() {
        TestSensor testSensor = new TestSensor();
        testSensor.setPressure(22); 
        Alarm alarm = new Alarm();
        alarm.sensor = testSensor;

        alarm.check();

        assertTrue(alarm.isAlarmOn());
    }

    @Test
    void testAlarmOffForNormalPressure() {
        TestSensor testSensor = new TestSensor();
        testSensor.setPressure(18); 
        Alarm alarm = new Alarm();
        alarm.sensor = testSensor;

        alarm.check();

        assertFalse(alarm.isAlarmOn());
    }
    
}
