package Day_1;

import java.io.*;

public class part_1 {
    public static void main(String[] args) throws Exception{
        BufferedReader reader;
        Integer sum_calval = 0;
        Integer temp_calval = 0;

        reader = new BufferedReader(new FileReader("Day_1/input.txt"));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split("");
            for (int i = 0; i < split.length; i++) {
                if (split[i].matches("[0-9]")) {
                    temp_calval = Integer.parseInt(split[i]) * 10;
                    break;
                }
            }
            for (int i = split.length - 1; i >= 0; i--) {
                if (split[i].matches("[0-9]")) {
                    temp_calval += Integer.parseInt(split[i]);
                    break;
                }
            }
            line = reader.readLine();
            sum_calval += temp_calval;
        }
        System.err.println(sum_calval);
        reader.close();
    }
}
