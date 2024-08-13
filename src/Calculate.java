public class Calculate {
    public static String[] separate;
    public static char sign;

    public static void operations(String text) {
        signDetector(text);

        if (sign == '*' || sign == '/') {
            if (separate[1].contains("\"")) try {
                throw new Exception("Строчку умножаем или делим только на число");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < separate.length; i++) {
            separate[i] = separate[i].replace("\"","");
        }

        String resultText;
        if (sign == '+') {
            resultText = sum(text);
        } else if (sign == '*') {
            resultText = multiply(text);
        } else if (sign == '-') {
            resultText = subtraction(text);
        } else {
            resultText = division(text);
        }

        System.out.println("\"" + resultText + "\"");
    }

    private static char signDetector(String text) {
        if (text.contains(" + ")) {
            separate = text.split(" \\+ ");
            sign = '+';
        } else if (text.contains(" - ")) {
            separate = text.split(" - ");
            sign = '-';
        } else if (text.contains(" * ")) {
            separate = text.split(" \\* ");
            sign = '*';
        } else if (text.contains(" / ")) {
            separate = text.split(" / ");
            sign = '/';
        } else {
            try {
                throw new Exception("Некорректный знак");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sign;
    }

    private static String sum(String text) {
        return separate[0] + separate[1];
    }

    private static String subtraction(String text) {
        int sub = separate[0].indexOf(separate[1]);
        if (sub == -1) {
            return separate[0];
        } else {
            String result = separate[0].substring(0,sub);
            result += separate[0].substring(sub + separate[1].length());
            return result;
        }
    }

    private static String multiply(String text) {
        int multi = Integer.parseInt(separate[1]);
        String result = "";
        for (int i = 0; i < multi; i++) {
            result += separate[0];
        }
        return result;
    }

    private static String division(String text) {
        int div = separate[0].length()/Integer.parseInt(separate[1]);
        String result = separate[0].substring(0,div);
        return result;
    }
}
