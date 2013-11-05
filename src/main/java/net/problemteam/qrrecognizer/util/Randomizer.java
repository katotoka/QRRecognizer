package net.problemteam.qrrecognizer.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    public static String getAlphabetical(int length) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int uppercaseLowerBound = 'A', uppercaseUpperBound = 'Z';
        int lowercaseLowerBound = 'a', lowercaseUpperBound = 'z';
        int max = Math.max(uppercaseUpperBound, lowercaseUpperBound);

        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rnd = 0;

            do {
                rnd = random.nextInt(max + 1);
            } while (!((rnd >= uppercaseLowerBound && rnd <= uppercaseUpperBound) || (rnd >= lowercaseLowerBound && rnd <= lowercaseUpperBound)));

            result.append((char) rnd);
        }
        return result.toString();
    }
}
