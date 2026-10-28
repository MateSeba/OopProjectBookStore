package ec.edu.espe.projectBookstore.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class JsonUtil {

    private JsonUtil() {
    }

    public static String toJson(Object value) {
        StringBuilder sb = new StringBuilder();
        write(sb, value, 0);
        return sb.toString();
    }

    private static void write(StringBuilder sb, Object value, int level) {
        if (value == null) {
            sb.append("null");
        } else if (value instanceof String text) {
            writeString(sb, text);
        } else if (value instanceof Boolean || value instanceof Number) {
            sb.append(value);
        } else if (value instanceof Map<?, ?> map) {
            if (map.isEmpty()) {
                sb.append("{}");
                return;
            }
            sb.append("{\n");
            int count = 0;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                pad(sb, level + 1);
                writeString(sb, String.valueOf(entry.getKey()));
                sb.append(": ");
                write(sb, entry.getValue(), level + 1);
                if (++count < map.size()) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            pad(sb, level);
            sb.append("}");
        } else if (value instanceof List<?> list) {
            if (list.isEmpty()) {
                sb.append("[]");
                return;
            }
            sb.append("[\n");
            for (int i = 0; i < list.size(); i++) {
                pad(sb, level + 1);
                write(sb, list.get(i), level + 1);
                if (i < list.size() - 1) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            pad(sb, level);
            sb.append("]");
        } else {
            writeString(sb, value.toString());
        }
    }

    private static void pad(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
    }

    private static void writeString(StringBuilder sb, String text) {
        sb.append('"');
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default -> {
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        sb.append('"');
    }

    public static Object parse(String text) {
        Parser parser = new Parser(text);
        parser.skipSpaces();
        Object result = parser.readValue();
        parser.skipSpaces();
        if (parser.pos != text.length()) {
            throw new IllegalArgumentException("Unexpected data at position " + parser.pos);
        }
        return result;
    }

    private static class Parser {

        private final String text;
        private int pos = 0;

        Parser(String text) {
            this.text = text;
        }

        void skipSpaces() {
            while (pos < text.length() && Character.isWhitespace(text.charAt(pos))) {
                pos++;
            }
        }

        char peek() {
            if (pos >= text.length()) {
                throw new IllegalArgumentException("Unexpected end of JSON");
            }
            return text.charAt(pos);
        }

        void expect(char c) {
            if (peek() != c) {
                throw new IllegalArgumentException("Expected '" + c + "' at position " + pos);
            }
            pos++;
        }

        Object readValue() {
            skipSpaces();
            char c = peek();
            if (c == '{') {
                return readObject();
            }
            if (c == '[') {
                return readArray();
            }
            if (c == '"') {
                return readString();
            }
            if (text.startsWith("true", pos)) {
                pos += 4;
                return Boolean.TRUE;
            }
            if (text.startsWith("false", pos)) {
                pos += 5;
                return Boolean.FALSE;
            }
            if (text.startsWith("null", pos)) {
                pos += 4;
                return null;
            }
            return readNumber();
        }

        Map<String, Object> readObject() {
            Map<String, Object> map = new LinkedHashMap<>();
            expect('{');
            skipSpaces();
            if (peek() == '}') {
                pos++;
                return map;
            }
            while (true) {
                skipSpaces();
                String key = readString();
                skipSpaces();
                expect(':');
                map.put(key, readValue());
                skipSpaces();
                if (peek() == ',') {
                    pos++;
                } else {
                    expect('}');
                    return map;
                }
            }
        }

        List<Object> readArray() {
            List<Object> list = new ArrayList<>();
            expect('[');
            skipSpaces();
            if (peek() == ']') {
                pos++;
                return list;
            }
            while (true) {
                list.add(readValue());
                skipSpaces();
                if (peek() == ',') {
                    pos++;
                } else {
                    expect(']');
                    return list;
                }
            }
        }

        String readString() {
            expect('"');
            StringBuilder sb = new StringBuilder();
            while (true) {
                char c = peek();
                pos++;
                if (c == '"') {
                    return sb.toString();
                }
                if (c == '\\') {
                    char e = peek();
                    pos++;
                    switch (e) {
                        case 'n' -> sb.append('\n');
                        case 'r' -> sb.append('\r');
                        case 't' -> sb.append('\t');
                        case 'b' -> sb.append('\b');
                        case 'f' -> sb.append('\f');
                        case 'u' -> {
                            if (pos + 4 > text.length()) {
                                throw new IllegalArgumentException("Bad unicode escape");
                            }
                            sb.append((char) Integer.parseInt(text.substring(pos, pos + 4), 16));
                            pos += 4;
                        }
                        default -> sb.append(e); // \" \\ \/
                    }
                } else {
                    sb.append(c);
                }
            }
        }

        Object readNumber() {
            int start = pos;
            while (pos < text.length() && "+-0123456789.eE".indexOf(text.charAt(pos)) >= 0) {
                pos++;
            }
            if (start == pos) {
                throw new IllegalArgumentException("Unexpected character '" + text.charAt(pos) + "' at position " + pos);
            }
            String number = text.substring(start, pos);
            if (number.contains(".") || number.contains("e") || number.contains("E")) {
                return Double.valueOf(number);
            }
            return Long.valueOf(number);
        }
    }
}
