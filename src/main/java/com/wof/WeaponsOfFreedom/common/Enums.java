package com.wof.WeaponsOfFreedom.common;

public class Enums {

    public static enum StarLevel {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4);

        private final int value;

        StarLevel(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
