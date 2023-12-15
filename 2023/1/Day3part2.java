import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day3part2 {

    static int[] dX = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dY = {-1, -1, -1, 0, 0, 1, 1, 1};
    static boolean[][] visited = new boolean[140][140];
    static char[][] schema = new char[140][140];

    public static void main(String[] args) {
        try (FileInputStream fstream = new FileInputStream("2023/1/input3.txt")) {
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null)   {
                schema[lineNumber] = line.toCharArray();
                lineNumber++;
            }

            int sum = 0;

            for(int i = 0; i < 140; i++) {
                for(int j = 0; j < 140; j++) {
                    char c = schema[i][j];

                    if(!Character.isDigit(c) && c == '*') {
                        int firstNum = 0;
                        int secondNum = 0;
                        for(int x = 0; x < dX.length; x++) {
                            for(int y = 0; y < dY.length; y++) {
                                int nextX = i + dX[x];
                                int nextY = j + dY[y];
                                if(isPossible(nextX, nextY)) {
                                    if(firstNum != 0) {
                                        secondNum = getNumber(nextX, nextY);
                                    } else {
                                        firstNum = getNumber(nextX, nextY);
                                    }
                                }
                            }
                        }
                        sum += firstNum * secondNum;
                    }
                }
            }

            System.out.println(sum); // 86879020

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNumber(int x, int y) {
        String num = String.valueOf(schema[x][y]);
        visited[x][y] = true;
        // check right

        int r = y;
        while((r+1)<140) {
            r += 1;
            if(Character.isDigit(schema[x][r])){
                num = num + String.valueOf(schema[x][r]);
                visited[x][r] = true;
            } else {
                break;
            }         
        }

        // check left
        int l = y;
        while((l-1)>=0) {
            l -= 1;
            if(Character.isDigit(schema[x][l])) {
                num = String.valueOf(schema[x][l]) + num;
                visited[x][l] = true;
            } else {
                break;
            }
                
        }

        return Integer.parseInt(num);
    }

    private static boolean isPossible(int x, int y) {
        if(x < 0 || y < 0 || x >= 140 || y >= 140)
            return false;

        if(!Character.isDigit(schema[x][y]))
            return false;

        if(visited[x][y])
            return false;
    
        return true;
    }
}
