import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // запускает программу и считавает введеные значения в консоль
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) {
        Converter converter = new Converter();

        // массив занков
        String[] actions = {"+", "-", "/", "*"};
        // просто так плюс или умножить не передать, перед ним нужно поставить два слеша
        String[] regexActions = {" \\+ ", " - ", " / ", " \\* "};

        int actionIndex = 0;
        for (int i = 0; i < actions.length; i++) {
            // проверияет введеный символ на его наличие в массиве
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        // исключения которые нельза вводить, можно добавить другие варианты
        if(input.contains("IIII") || input.contains("VIIII") || input.contains("IIIIV")
                || input.contains("IIIV") || input.contains("IIV") || input.contains("XIIII") || input.contains("IIIIX")
                || input.contains("IIIX") || input.contains("IIX") || input.contains("XVII") || input.contains("IIVX")
                || input.contains("IIIVX") || input.contains("XVIIII") || input.contains("IIIIVX") || input.contains("XXIIII")
                || input.contains("IIIIXX") || input.contains("VV") || input.contains("XVVIIII") || input.contains("XVV")
                || input.contains("IIIIXX") || input.contains("VVX") || input.contains("IIIIVVX") || input.contains("IIIVVX")
        ) {
            try {
                throw new Exception("т.к. нет таких чисел");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //создает массив всех чисел и разбивает строку на части с помощью split, знаки +-*/ кладет в массив
        String[] data = input.split(regexActions[actionIndex]);
        // ошибки
        if(data.length < 2)  try {
            System.out.println("Error: По заданию нужно вводить через пробел");
            throw new Exception("т.к. строка не является математической операцией");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } else if(data.length > 2)  try {
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Определяет, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int result;
            int a, b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                //римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            } else {
                //арабские, конвертирует в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            // ошибка
            if(a > 10 || b > 10) {
                try {
                    throw new Exception("т.к. калькулятор принимает от 1 до 10");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


            //выполняет арифметическое действие через switch case
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            // конвертирует в римские цифры
            if (isRoman) {
                // еслм римские, то конвертируем из арабских в римские и выводим ответ
                if(result <= 0) {
                    try {
                        throw new Exception("т.к. в римской системе нет отрицательных чисел");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(converter.intToRoman(result));
                return "";
            } else {
                //арабские
                System.out.println(result);
                return "";
            }
        } else {
            try {
                throw new Exception("т.к. числа должны быть в одном формате");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
