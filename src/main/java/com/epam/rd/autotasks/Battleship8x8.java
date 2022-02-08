package com.epam.rd.autotasks;

import java.util.BitSet;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    private int Attack(char s) {
        switch (s) {
            case 'A':
            case '8':
                return 1;
            case 'B':
            case '7':
                return 2;
            case 'C':
            case '6':
                return 3;
            case 'D':
            case '5':
                return 4;
            case 'E':
            case '4':
                return 5;
            case 'F':
            case '3':
                return 6;
            case 'G':
            case '2':
                return 7;
            default:
                return 8;
        }
    }

    private boolean bit(int position, long map) {
        return ((map >>> position) & 1) != 0;
    }

    public static long flipBit(int position, long value) {
        BitSet bs = BitSet.valueOf(new long[]{value});
        bs.flip(position);
        return bs.toLongArray()[0];
    }

    public boolean shoot(String shot) {
        //throw new UnsupportedOperationException();
        int one = Attack(shot.charAt(0));
        int two = Attack(shot.charAt(1));
        boolean result = bit(two * 8 - one, ships);
        shots = flipBit(two * 8 - one, shots);
        return result;
    }

    public String state() {
        //throw new UnsupportedOperationException();
        String result = "";
        int i = 63;
        while (i != -1) {
            if (bit(i, ships) && !bit(i, shots)) {
                result = result.concat("☐");
            } else if (!bit(i, ships) && bit(i, shots)) {
                result = result.concat("×");
            } else if (bit(i, ships) && bit(i, shots)) {
                result = result.concat("☒");
            } else {
                result = result.concat(".");
            }
            if (i % 8 == 0) {
                result += '\n';
            }
            i--;
        }
        return result;
    }
}