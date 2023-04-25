import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String word = scanner.nextLine();
        System.out.println("String to binary: ");
        System.out.println(stringtoBinary(word));
        System.out.println("Binary to chuckNorris: ");
        System.out.println(binarytoChuckNorris(binarytoChuckNorris(stringtoBinary(word))));
        System.out.println("Chuck Norris to string");
        System.out.println(binarytoString(chuckNorristoBinary(binarytoChuckNorris(stringtoBinary(word)))));
    }
    public static String stringtoBinary(String word) {
        String binary = "";
        for(int i = 0; i<word.length();i++) {
            char tmp = word.charAt(i);
            binary += String.format("%07d",Integer.parseInt(Integer.toBinaryString(tmp)) );
        }
        return binary;
    }

    public static String binarytoChuckNorris(String binary) {
        String chuckNorris = "";
        for (int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1'){
                chuckNorris += "0 0";
                for (int j = i + 1; j < binary.length(); j++) {
                    if (binary.charAt(j) == '1') {
                        chuckNorris +='0';
                        i++;
                    }else {
                        chuckNorris += " ";
                        break;
                    }
                }
            }else if (binary.charAt(i) == '0') {
                chuckNorris += "00 0";
                for (int j = i + 1; j < binary.length(); j++) {
                    if (binary.charAt(j) == '0') {
                        chuckNorris +='0';
                        i++;
                    }else {
                        chuckNorris += " ";
                        break;
                    }
                }
            }
        }
        return chuckNorris;
    }
    public static String chuckNorristoBinary(String chuckNorris) {
        String decode = "";
        for (int i = 0; i < chuckNorris.length() - 1; i++) {
            if(chuckNorris.charAt(i) == '0' && chuckNorris.charAt(i+1) == ' ') {
                for(int j = i + 2; j<chuckNorris.length(); j++) {
                    if(chuckNorris.charAt(j) == '0') {
                        decode += '1';
                        i++;
                    }else {
                        i += 2;
                        break;
                    }
                }
            }else if (chuckNorris.charAt(i) == '0' && chuckNorris.charAt(i+1) == '0') {
                for(int j = i+3; j<chuckNorris.length() ; j++) {
                    if(chuckNorris.charAt(j) == '0') {
                        decode += '0';
                        i++;
                    }else {
                        i += 3;
                        break;
                    }
                }
            }
        }
        return decode;
    }
    public static String binarytoString(String binary) {
        String [] table = new String[binary.length() / 7];
        int [] values = new int[binary.length() / 7];
        String tmp = "";
        for(int j = 0; j < table.length; j++) {
            int tmpp = j * 7;
            for (int i = 0 ; i < 7; i++) {
                tmp += binary.charAt(tmpp + i);
            }
            table[j] = tmp;
            tmp = "";
        }

        for(int i = 0; i < table.length; i++){
            int number = 0;
            for(int j = 0; j < table[i].length(); j++) {
                if(table[i].charAt(j) == '1') {
                    number += Math.pow(2,table[i].length() - 1 - j);
                }
            }
            values[i] = number;
            number = 0;
        }
        String end = "";
        for(int i = 0; i < values.length; i++) {
            end += (char)values[i];
        }
        return end;
    }
}