import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventProcessor {
    private final Map<LightSensorEvent, List<Consumer<Integer>>> lightSensorActions;
    private final Map<MotionSensorEvent, List<Consumer<Boolean>>> motionSensorActions;
    private final Map<TemperatureSensorEvent, List<Consumer<Integer>>> temperatureSensorActions;
    private final Map<TimeEvent, List<Runnable>> timeEventActions;

    public EventProcessor() {
        this.lightSensorActions = new HashMap<>();
        this.motionSensorActions = new HashMap<>();
        this.temperatureSensorActions = new HashMap<>();
        this.timeEventActions = new HashMap<>();
    }

    public void registerLightSensorAction(LightSensorEvent event, Consumer<Integer> action) {
        List<Consumer<Integer>> actions = lightSensorActions.computeIfAbsent(event, k -> new ArrayList<>());

        actions.add(action);
    }

    public void processLightSensorEvent(LightSensorEvent event, int brightnessValue) {
        List<Consumer<Integer>> actions = lightSensorActions.get(event);

        if (actions != null) {
            actions.forEach(action -> action.accept(brightnessValue));
        }
    }

    public void registerMotionSensorAction(MotionSensorEvent event, Consumer<Boolean> action) {
        List<Consumer<Boolean>> actions = motionSensorActions.computeIfAbsent(event, k -> new ArrayList<>());

        actions.add(action);
    }

    public void processMotionSensorEvent(MotionSensorEvent event, boolean didMove) {
        List<Consumer<Boolean>> actions = motionSensorActions.get(event);

        if (actions != null) {
            actions.forEach(action -> action.accept(didMove));
        }
    }

    public void registerTemperatureSensorActions(TemperatureSensorEvent event, Consumer<Integer> action) {
        List<Consumer<Integer>> actions = temperatureSensorActions.computeIfAbsent(event, k -> new ArrayList<>());

        actions.add(action);
    }

    public void processTemperatureSensorEvent(TemperatureSensorEvent event, int degressInCelsius) {
        List<Consumer<Integer>> actions = temperatureSensorActions.get(event);

        if (actions != null) {
            actions.forEach(action -> action.accept(degressInCelsius));
        }
    }

    public void registerTimeEventAction(TimeEvent event, Runnable time) {
        List<Runnable> times = timeEventActions.computeIfAbsent(event, k -> new ArrayList<>());
        times.add(time);
    }

    public void processTimeEvent(TimeEvent event) {
        List<Runnable> times = timeEventActions.get(event);

        if (times != null) {
            times.forEach(Runnable::run);
        }
    }
}
