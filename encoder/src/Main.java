import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean tmp = true;
        while(tmp) {
            tmp = menu();
        }
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
            int tmpp = 1 * j * 7;
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

    public static boolean menu() {
        System.out.println("Please input operation (encode/decode/exit)");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option) {
            case "encode" -> {
                System.out.println("Input string:");
                String input = sc.nextLine();
                System.out.println("Encoded string:");
                System.out.println(binarytoChuckNorris(stringtoBinary(input)));
                System.out.println();
                return true;
            }
            case "decode" -> {
                System.out.println("Input encoded string:");
                String input = sc.nextLine();
                String[] inputBlocks = input.split(" ");
                boolean inputCorrect = true;
                for (int i = 0; i<input.length(); i++) {                       // The encoded message includes characters other than 0 or spaces;
                    if(input.charAt(i) != '0' && input.charAt(i) != ' ') {
                        inputCorrect = false;
                        break;
                    }
                }

                if(!(inputBlocks[0].equals("0") || inputBlocks[0].equals("00"))) {        //The first block of each sequence is not 0 or 00;
                    inputCorrect = false;
                }

                if(inputBlocks.length % 2 != 0) {     //The number of blocks is odd;
                    inputCorrect = false;
                }

                if(chuckNorristoBinary(input).length() %7 != 0) {
                    inputCorrect = false;
                }

                if (inputCorrect) {
                    System.out.println("Decoded string:");
                    System.out.println(binarytoString(chuckNorristoBinary(input)));
                    System.out.println();
                    return true;
                }
                System.out.println("Encoded string is not valid.");
                System.out.println();
                return true;
            }
            case "exit" -> {
                System.out.println("Bye!");
                return false;
            }
            default -> {
                System.out.println("There is no '" + option + "' operation");
                System.out.println();
                return true;
            }
        }
    }
}
