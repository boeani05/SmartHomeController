public class SmartHomeControllerApp {
    public static void main(String[] args) {
        EventProcessor processor = new EventProcessor();

        processor.registerLightSensorAction(LightSensorEvent.BRIGHTNESS, brightness -> {
            if (brightness < 0) {
                System.out.println("[ERROR] Licht benötigt Reperatur!");
            } else if (brightness < 50) {
                System.out.println("[WARNING] Licht ist bei " + brightness + "! Licht AN!");
            } else if (brightness < 100) {
                System.out.println("[INFO] Licht ist bei " + brightness + "!");
            } else {
                System.out.println("[WARNING] Licht ist bei " + brightness + "! Jalousien werden runtergefahren!");
            }
        });

        processor.registerMotionSensorAction(MotionSensorEvent.DID_MOVE, didMove -> {
            if (didMove) {
                System.out.println("[WARNING] Bewegung erkannt!");
            } else {
                System.out.println("[INFO] Zzz...");
            }
        });

        processor.registerTemperatureSensorActions(TemperatureSensorEvent.TEMPERATURE, celsius -> {
            if (celsius != 21) {
                System.out.println("[INFO] Temperatur wird reguliert!");
            } else {
                System.out.println("[INFO] Perfekte Temperatur!");
            }
        });

        processor.registerTimeEventAction(TimeEvent.SUNRISE, () -> System.out.println("[INFO] Jalousien werden hochgefahren. Guten Morgen!"));

        processor.registerTimeEventAction(TimeEvent.SUNSET, () -> System.out.println("[INFO] Bereite auf Abend vor..."));

        processor.registerTimeEventAction(TimeEvent.MIDNIGHT, () -> System.out.println("[INFO] Jalousien werden runtergefahren. Gute Nacht!"));

        System.out.println("=== Simulation startet ===\n");

        System.out.println("--- Lichtsensor-Test ---");
        processor.processLightSensorEvent(LightSensorEvent.BRIGHTNESS, -1);
        processor.processLightSensorEvent(LightSensorEvent.BRIGHTNESS, 25);
        processor.processLightSensorEvent(LightSensorEvent.BRIGHTNESS, 75);
        processor.processLightSensorEvent(LightSensorEvent.BRIGHTNESS, 100);

        System.out.println("\n--- Motion-sensor-Test ---");
        processor.processMotionSensorEvent(MotionSensorEvent.DID_MOVE, true);
        processor.processMotionSensorEvent(MotionSensorEvent.DID_MOVE, false);

        System.out.println("\n--- Temperatursensor-Test ---");
        processor.processTemperatureSensorEvent(TemperatureSensorEvent.TEMPERATURE, 25);
        processor.processTemperatureSensorEvent(TemperatureSensorEvent.TEMPERATURE, 21);

        System.out.println("\n--- Time-Event-Test ---");
        processor.processTimeEvent(TimeEvent.SUNRISE);
        processor.processTimeEvent(TimeEvent.SUNSET);
        processor.processTimeEvent(TimeEvent.MIDNIGHT);
    }
}
