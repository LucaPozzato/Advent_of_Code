package Day_6;

import java.io.*;
import java.util.regex.*;

public class code {
    public static void main (String[] args) throws Exception{
        System.out.println(new part_1().run());
        System.out.println(new part_2().run());
    }
}

class part_1 {
    private Matcher time;
    private Matcher distance;
    private Integer record = 1;
    private Double result;

    Integer run () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(System.getenv("INPUT_FILE")));
        String line = reader.readLine() + "\n";

        time = Pattern.compile("(?<= )(\\d+)(?=( |\\n))").matcher(line);
        line = reader.readLine() + "\n";
        distance = Pattern.compile("(?<= )(\\d+)(?=( |\\n))").matcher(line);

        while (time.find() && distance.find()) {
            result = get_record.run(Double.parseDouble(time.group(0)), Double.parseDouble(distance.group(0)));
            record *= result.intValue();
        }

        reader.close();
        return record;
    }
}

class part_2 {
    private Matcher time;
    private String s_time = "";
    private String s_distance = "";
    private Matcher distance;
    private Integer record = 1;
    private Double result;

    Integer run () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(System.getenv("INPUT_FILE")));
        String line = reader.readLine() + "\n";

        time = Pattern.compile("(?<= )(\\d+)(?=( |\\n))").matcher(line);
        line = reader.readLine() + "\n";
        distance = Pattern.compile("(?<= )(\\d+)(?=( |\\n))").matcher(line);

        while (time.find() && distance.find()) {
            s_time += time.group(0);
            s_distance += distance.group(0);
        }

        result = get_record.run(Double.parseDouble(s_time), Double.parseDouble(s_distance));
        record *= result.intValue();

        reader.close();
        return record;
    }
}

class get_record {    
    static Double run (Double time, Double distance) {
        return Math.ceil(((-1 * time - Math.sqrt(Math.pow(time, 2.0) -4 * distance))/-2) - 1) - Math.floor(((-1 * time + Math.sqrt(Math.pow(time, 2.0) - 4 *(-1 * -1 * distance)))/-2) + 1) + 1;
    }
}