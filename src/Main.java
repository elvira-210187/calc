
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String z_str_in;

            //System.out.println("i = " + i);
        //}

        System.out.println("Введите арифметическое выражение: ");
        Scanner scanner = new Scanner(System.in);
        z_str_in = scanner.nextLine();
        //проверка на пробелы между цифрами
        Pattern pattern = Pattern.compile("(\\d\\s\\d)");
        Matcher matcher = pattern.matcher(z_str_in);
        if (matcher.find())
        {
            //System.out.println(matcher.group(1));
            throw new RuntimeException("Выражение содержит некорректные данные");
        }
        System.out.println(calc(z_str_in));

    }

    public static String calc(String str_input) {
        //String str;

        str_input = str_input.replaceAll("\\s", "");
        //Проверка на корректность арифметических операций
        int i=0;
        String z_ar_s=null;
        String z_tmp_ar="+-*/";
        for (char symbol : str_input.toCharArray()) {
            if (z_tmp_ar.indexOf(symbol)!=-1) {
                i++;
                if ((symbol=='*')||(symbol=='+')) {
                    z_ar_s = "\\"+String.valueOf(symbol);
                    //z_ar_s = String.join("\\", String.valueOf(symbol));
                }
                else {
                    z_ar_s = String.valueOf(symbol);
                }
            }
        }

        //Проверка на наличие только одной арифметической операции
        if (i==1) {
            //System.out.println("Строка содержит арифметическую операцию " + z_ar_s);
            String [] z_num = str_input.split(z_ar_s);
            //проверка на целые числа
            if ((z_num[0].matches("^\\d+$"))&&(z_num[1].matches("^\\d+$"))){
                int a=Integer.parseInt(z_num[0]), b=Integer.parseInt(z_num[1]);
                if (a>0 && a<11 && b>0 && b<11){
                    switch (z_ar_s){
                        case "-" -> str_input = Integer.toString(a-b);
                        case "\\+" -> str_input = Integer.toString(a+b);
                        case "\\*" -> str_input = Integer.toString(a*b);
                        case "/" -> str_input = Integer.toString(a/b);

                    }
                }
                else throw new RuntimeException("Выражение содержит некорректные операнды");

            }
            else {
                throw new RuntimeException("Выражение содержит некорректные операнды");
            }
        }
        else {
            throw new RuntimeException("Строка не соответствует условию, количество операторов "+i);
        }
        return str_input;
    }

}