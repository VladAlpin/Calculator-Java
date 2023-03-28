import java.util.TreeMap;

class Converter {
    // римские числа
    TreeMap<Character, Integer> romanKey = new TreeMap<>();

    // арабские числа
    TreeMap<Integer, String> arabianKey = new TreeMap<>();

    // массив содержащий римские цифры и ключ к ним
    public Converter() {
        romanKey.put('I', 1);
        romanKey.put('V', 5);
        romanKey.put('X', 10);

        arabianKey.put(1000, "M");
        arabianKey.put(900, "CM");
        arabianKey.put(500, "D");
        arabianKey.put(400, "CD");
        arabianKey.put(100, "C");
        arabianKey.put(90, "XC");
        arabianKey.put(50, "L");
        arabianKey.put(40, "XL");
        arabianKey.put(10, "X");
        arabianKey.put(9, "IX");
        arabianKey.put(5, "V");
        arabianKey.put(4, "IV");
        arabianKey.put(1, "I");
    }

    // проверяет ввыденную строчку, и конвертирует с помощью chatAt в 'V' из "V", с помощью containsKey проверяет его наличие под данным индексом
    // берет ключ, и с ним проводит логические операции, если написать '2', то он ее не найдет это число т.к. его нет в массиве
    boolean isRoman(String number){
        return romanKey.containsKey(number.charAt(0));
    }

    //римские в арабские, если IV
    int romanToInt(String s) {
        int end = s.length() - 1;
        // создает массив, и метод toCharArray когда получает число, делает из него массив, {'I', 'V'}
        char[] arr = s.toCharArray();
        int arabian;
        // добавляет все в результат
        int result = romanKey.get(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = romanKey.get(arr[i]);
            // ищит подходящее число, конвертируем в арабское, и прибовляет его к результату
            if (arabian < romanKey.get(arr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        return result;
    }

    //арабские в римские, если получается 6
    String intToRoman(int number) {
        String roman = "";
        int arabianKey;
        do {
            // floorKey ищет ключ из массива начиная с низу
            arabianKey = this.arabianKey.floorKey(number);
            // конвертирует в римское число и прибовляет к roman
            roman += this.arabianKey.get(arabianKey);
            // отнимает 5 от начального числа, так как прижайшее число по массиву было 5(V), и остается 1
            number -= arabianKey;
            // так как осталось еще 1, массив заного запускается
        } while (number != 0);
        // возвращает VI
        return roman;
    }
}

