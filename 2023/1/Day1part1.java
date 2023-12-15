import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Day1part1 {
    public static void main(String[] args) {
        try (FileInputStream fstream = new FileInputStream("2023/1/input.txt")) {
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            int sum = 0;
            while ((line = br.readLine()) != null)   {
                for(int i = 0; i < line.length(); i++) {
                    Character c = line.charAt(i);
                    if(Character.isDigit(c)) {
                        sum += Integer.parseInt(String.valueOf(c)) * 10;
                        break;
                    }
                }
                for(int i = line.length()-1; i >=0 ; i--) {
                    Character c = line.charAt(i);
                    if(Character.isDigit(c)) {
                        sum += Integer.parseInt(String.valueOf(c));
                        break;
                    }
                }
            }

            System.out.println(sum); // 55816

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}