package me.ruende.minigame.arrow;

import me.ruende.minigame.arrow.effect.*;

public class ArrowEffectFactory {
    public ArrowEffect getArrowEffect(String arrowType) {
        return switch (arrowType) {
            case "타입 1 화살" -> new Type1ArrowEffect();
            case "타입 2 화살" -> new Type2ArrowEffect();
            case "타입 3 화살" -> new Type3ArrowEffect();
            default -> null;
        };
    }
}