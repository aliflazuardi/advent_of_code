import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day1part2 {

    static final String[] numberWords = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static int[][] numberWordsIdx = new int[9][2];

    public static void main(String[] args) {
        try (FileInputStream fstream = new FileInputStream("2023/1/input.txt")) {
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            int sum = 0;
            while ((line = br.readLine()) != null)   {
                numberWordsIdx = new int[9][2];
                int minIdx = 999;
                int minNum = 0;
                int maxIdx = -1;
                int maxNum = 0;
                for(int i = 0; i < numberWords.length; i++) {
                    if (line.indexOf(numberWords[i]) != -1) {
                        numberWordsIdx[i][0] = line.indexOf(numberWords[i]);
                        if(numberWordsIdx[i][0] < minIdx) {
                            minIdx = numberWordsIdx[i][0];
                            minNum = i + 1;
                        }
                        numberWordsIdx[i][1] = line.lastIndexOf(numberWords[i]);
                        if(numberWordsIdx[i][1] > maxIdx) {
                            maxIdx = numberWordsIdx[i][1];
                            maxNum = i + 1;
                        }
                    }
                }

                // first number
                int numberIdx = 999;
                int firstNumber = 0;
                for(int i = 0; i < line.length(); i++) {
                    Character c = line.charAt(i);
                    if(Character.isDigit(c)) {
                        firstNumber = Integer.parseInt(String.valueOf(c));
                        numberIdx = i;
                        break;
                    }
                }

                if(numberIdx < minIdx) {
                    sum += firstNumber * 10;
                } else {
                    sum += minNum * 10;
                }

                // last number
                numberIdx = -1;
                int lastNumber = 0;
                for(int i = line.length()-1; i >=0 ; i--) {
                    Character c = line.charAt(i);
                    if(Character.isDigit(c)) {
                        lastNumber = Integer.parseInt(String.valueOf(c));
                        numberIdx = i;
                        break;
                    }
                }

                if(numberIdx > maxIdx) {
                    sum += lastNumber;
                } else {
                    sum += maxNum;
                }
            }

            System.out.println(sum); //54980

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
