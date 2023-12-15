import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Day4part1 {
    public static void main(String[] args) {
        
        try (FileInputStream fstream = new FileInputStream("2023/1/input4.txt")) {
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int sum = 0;
            String line;
            while ((line = br.readLine()) != null)   {
                String card = line.split(":")[1];
                
                String left = card.split("\\|")[0];
                String right = card.split("\\|")[1];
                
                String[] winningNumbers = left.split("\\s+");
                TreeSet<Integer> numberSet = new TreeSet<>();
                for(String winningNumber : winningNumbers) {
                    if(winningNumber.isEmpty())
                        continue;
                    numberSet.add(Integer.parseInt(winningNumber));
                }

                String[] numbers = right.split("\\s+");
                int score = 0;
                for(String number : numbers) {
                    if(number.isEmpty())
                        continue;
                    if(numberSet.contains(Integer.parseInt(number))) {
                        if(score == 0) {
                            score = 1;
                        } else {
                            score *= 2;
                        }
                    }
                }
                
                sum += score;
            }

            System.out.println(sum); // 27454

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
