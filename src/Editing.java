import java.util.regex.Pattern;

public abstract class Editing {

    public static String addZeros(String a, int howMany) {
        //Method which add zeros on start of string to length equal howMany
        int tempLength = howMany - a.length();
        String addString = "";
        for (int i = 0; i < tempLength; i++) {
            addString += "0";
        }

        return (addString + a);
    }

    public static String addZerosOnEnd(String a, int howMany) {
        //Method which add zeros on end of string to length equal howMany
        int tempLength = howMany - a.length();
        for (int i = 0; i < tempLength; i++) {
            a += "0";
        }

        return a;
    }

    public static String changeMaskIntToString(int mask) {
        //Changing (int)mask argument to (String)mask in 0-1 32bits code
        String string = "";
        for (int i = 0; i < mask; i++) {
            string += "1";
        }
        string = addZerosOnEnd(string, 32);
        return string;
    }

    public static String addDotsToMaskAdress(String mask) {
        mask = mask.substring(0, 8) + '.' + mask.substring(8, 16) + '.' + mask.substring(16, 24) + '.' + mask.substring(24, 32);
        return mask;
    }


    public static String binaryToDecimalWithDots35chars(String string) {
        int[] decimal = new int[4];
        int from = 0, to = 8;
        for (int i = 0; i < 4; i++) {
            decimal[i] = Integer.parseInt(string.substring(from, to), 2);
            from += 9;
            to += 9;
        }
//        decimal[0]=Integer.parseInt(string.substring(0,8),2);
//        decimal[1]=Integer.parseInt(string.substring(9,17),2);
//        decimal[2]=Integer.parseInt(string.substring(18,26),2);
//        decimal[3]=Integer.parseInt(string.substring(27,35),2);

        string = "";
        for (int value : decimal) {
            string += Integer.toString(value) + '.';
        }

        //Delete last dot anyway how many characters are there
        string = string.substring(0, string.length() - 1);

        return string;

    }

    public static int[] stringDecimalToArrayOfInts(String string) {
        String[] arrayString = string.split(Pattern.quote("."));
        int[] array = new int[arrayString.length];

        for (int i = 0; i < arrayString.length; i++) {
            array[i] = Integer.parseInt(arrayString[i], 10);
        }

        return array;
    }
}
