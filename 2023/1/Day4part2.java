import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Day4part2 {
    public static void main(String[] args) {
        
        try (FileInputStream fstream = new FileInputStream("2023/1/input4.txt")) {
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int[] copies = new int[204];
            int cardID = 0;
            String line;
            while ((line = br.readLine()) != null)   {
                copies[cardID] ++;
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
                int count = 0;
                for(String number : numbers) {
                    if(number.isEmpty())
                        continue;
                    if(numberSet.contains(Integer.parseInt(number))) {
                        count++;
                    }
                }
                for(int i = 1; i <= count; i++) {
                    if(cardID + i >= 204)
                        continue;
                    copies[cardID+i] += copies[cardID];
                }
                cardID++;
            }

            int sum = 0;
            for(int i = 0; i < copies.length; i++)
                sum += copies[i];

            System.out.println(sum); // 6857330

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
