public class Calculate {
    public static String[] separate;
    public static char sign;

    public static void operations(String text) {
        signDetector(text);

        exception();

        for (int i = 0; i < separate.length; i++) {
            separate[i] = separate[i].replace("\"","");
        }

        String resultText = calculator(text);

        if (resultText.length() > 40) {
            System.out.println("\"" + resultText.substring(0,40) + "...\"");
        } else {
            System.out.println("\"" + resultText + "\"");
        }
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

    private static void exception() {
        if(!separate[0].contains("\"")) {
            try {
                throw new Exception("Первый аргумент не строка");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (sign == '*' || sign == '/') {
            if (separate[1].contains("\"")) {
                try {
                    throw new Exception("Строчку умножаем или делим только на число");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (Integer.parseInt(separate[1]) > 10 || Integer.parseInt(separate[1]) < 1) {
                try {
                    throw new Exception("Принимаются только числа от 1 до 10");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            if (!separate[1].contains("\"")) {
                try {
                    throw new Exception("Прибавить или вычесть можно только строку");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (separate[0].length() > 10 || separate[1].length() > 10) try {
            throw new Exception("Длина строки не должна быть больше 10");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String calculator(String text) {
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
        return resultText;
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
