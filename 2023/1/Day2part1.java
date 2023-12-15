import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Day2part1 {
    public static void main(String[] args) {
        int sum = 0;
        try (FileInputStream fstream = new FileInputStream("2023/1/input2.txt")) {
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            
            int gameID = 1;
            while ((line = br.readLine()) != null)   {
                line = line.replaceAll("\\s+", "");
                String game = line.split(":")[1];
                boolean isPossible = true;
                String[] rounds = game.split(";");
                for(String round : rounds) {
                    String[] balls = round.split(",");
                    for(String ball : balls) {
                        if(!Character.isDigit(ball.charAt(1)))
                            continue;

                        int ballNum = Integer.parseInt(ball.substring(0, 2));
                        
                        switch(ball.charAt(2)) {
                            case 'r':
                                if(ballNum > 12)
                                    isPossible = false;
                                break;
                            case 'g':
                                if(ballNum > 13)
                                    isPossible = false;
                                break;
                            case 'b':
                                if(ballNum > 14)
                                    isPossible = false;
                                break;
                        }
                    }
                }
                
                if(isPossible) {
                    sum += gameID;
                }
                gameID++;
            }

            System.out.println(sum); // 3035

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
