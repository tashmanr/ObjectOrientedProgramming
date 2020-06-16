/**
 * 336423124
 * Rebecca Tashman
 */
package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * KeyPressStoppableAnimation class - wraps the special screen animations.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor    keyboard sensor
     * @param key       to see if was pressed
     * @param animation animation to stop if key was pressed
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        stop = false;
        isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!keyboardSensor.isPressed(key)) {
            isAlreadyPressed = false;
        } else {
            if (!isAlreadyPressed) {
                stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}