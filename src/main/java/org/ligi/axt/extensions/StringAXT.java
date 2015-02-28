package org.ligi.axt.extensions;

import android.graphics.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAXT<T> {

    private final String string;

    public StringAXT(String string) {
        this.string = string;
    }

    public int parseColor(int defaultValue) {
        if (string == null) {
            return defaultValue;
        }

        if (string.startsWith("rgb")) {
            return parseColorRGBStyle(string, defaultValue);
        }

        if (string.startsWith("#")) {
            try {
                return Color.parseColor(string);
            } catch (Exception ignored) {
                // fall through to default color
            }
        }

        return defaultValue;
    }


    private static int parseColorRGBStyle(String color_str, int defaultValue) {
        final Pattern pattern = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        final Matcher matcher = pattern.matcher(color_str);

        if (matcher.matches()) {
            return (255 << 24 |
                    Integer.valueOf(matcher.group(1)) << 16 |  // r
                    Integer.valueOf(matcher.group(2)) << 8 |  // g
                    Integer.valueOf(matcher.group(3))); // b

        }

        return defaultValue;
    }
}
