# Smart Home Controller (Event-Driven with Functional Interfaces)

## 🚀 Project Overview

This mini-project implements a basic Smart Home Controller capable of reacting to various sensor data and time-based events. The core component is the `EventProcessor`, which leverages an event-driven architecture to dynamically register and execute actions. It serves as a practical demonstration of applying **Java's Functional Interfaces (Consumer, Runnable)** and **Lambda Expressions** in a real-world scenario.

## ✨ Core Concepts & Learning Objectives

This project was created to deepen the understanding of the following Java programming concepts:

*   **Functional Interfaces**: Understanding of `java.util.function.Consumer<T>` (takes one argument, returns nothing) and `java.lang.Runnable` (takes no arguments, returns nothing).
*   **Lambda Expressions**: Concise syntax for implementing functional interfaces.
*   **Method References**: An even more compact form of lambda expressions (e.g., `Runnable::run`).
*   **Event-Driven Architecture**: Designing a system that flexibly reacts to external events.
*   **Java Collections Framework**: Effective use of `HashMap` and `ArrayList` in conjunction with modern Java features like `computeIfAbsent` and `forEach`.

## 🛠️ Project Structure

The project consists of the following main components:

*   **`EventProcessor.java`**:
    The heart of the controller. This class manages maps that link `LightSensorEvent`, `MotionSensorEvent`, `TemperatureSensorEvent`, and `TimeEvent` with lists of corresponding actions (implemented as `Consumer<T>` or `Runnable`).
    It provides methods for registering (`register...Action`) and triggering (`process...Event`) events.

*   **`LightSensorEvent.java`, `MotionSensorEvent.java`, `TemperatureSensorEvent.java`, `TimeEvent.java`**:
    Enums defining the different types of sensor measurements and time events that the system can react to.

*   **`SmartHomeControllerApp.java`**:
    A demonstration class with a `main` method that initializes the `EventProcessor`, registers example actions using lambda expressions, and simulates various events to showcase the functionality.

## 🚀 Getting Started

### Prerequisites
*   Java Development Kit (JDK) 8 or higher

### Compiling and Running

1.  Clone this repository:
    ```bash
    git clone https://github.com/boeani05/SmartHomeController.git
    cd SmartHomeController
    ```
2.  Compile the Java files:
    ```bash
    javac *.java
    ```
3.  Run the application:
    ```bash
    java SmartHomeControllerApp
    ```

Observe the console output to see how the various smart home actions respond to the simulated sensor data and time events.

## 💡 How It Works

The `EventProcessor` uses `Map<EventType, List<FunctionalInterface>>` to store actions. When an event like `LightSensorEvent.BRIGHTNESS` with a `brightnessValue` occurs, the `processLightSensorEvent(LightSensorEvent event, int brightnessValue)` method is called. This method retrieves all `Consumer<Integer>` registered for that specific `event` from the map and calls their `accept(brightnessValue)` method, passing the current brightness value.

The actual "smart home logic" (e.g., "turn on the lights if it's dark") is defined as lambda expressions and passed to the `EventProcessor`. The processor then only concerns itself with managing and executing this logic, not its content. This design ensures high flexibility and decoupling.

## 🤝 Contributing

Feel free to fork this project, improve it, or add new features. Pull requests are welcome!

## 📄 License

This project is licensed under the [MIT License](LICENSE.md).