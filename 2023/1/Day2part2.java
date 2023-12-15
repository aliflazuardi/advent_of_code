import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Day2part2 {
    public static void main(String[] args) {
        int sum = 0;
        try (FileInputStream fstream = new FileInputStream("2023/1/input2.txt")) {
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null)   {
                line = line.replaceAll("\\s+", "");
                String game = line.split(":")[1];
                String[] rounds = game.split(";");
                int maxR = 0;
                int maxG = 0;
                int maxB = 0;
                for(String round : rounds) {
                    String[] balls = round.split(",");
                    for(String ball : balls) {
                        int ballNum = 0;
                        int colorIDX = 0;
                        if(!Character.isDigit(ball.charAt(1))) {
                            ballNum = Integer.parseInt(ball.substring(0, 1));
                            colorIDX = 1;
                        } else {
                            ballNum = Integer.parseInt(ball.substring(0, 2));
                            colorIDX = 2;
                        }

                        switch(ball.charAt(colorIDX)) {
                            case 'r':
                                maxR = Math.max(ballNum, maxR);
                                break;
                            case 'g':
                                maxG = Math.max(ballNum, maxG);
                                break;
                            case 'b':
                                maxB = Math.max(ballNum, maxB);
                                break;
                        }
                    }
                }
                sum += maxR*maxG*maxB;
            }

            System.out.println(sum); // 3035

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
