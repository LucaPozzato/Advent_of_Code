package Day_4;

import java.io.*;
import java.util.regex.*;
import java.util.*;

public class code {
    public static void main (String[] args) throws Exception {
        System.out.println(new part_1().run());
        System.out.println(new part_2().run());
    }
}

class part_1 {
    Integer run () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(System.getenv("INPUT_FILE")));

        Matcher num_m;
        Integer sum = 0;
        String line;
        Integer card_value = 0;
        int i = 0;
        String regex = "(?<= )(\\d+)(?= .*\\|.*(?<= )\\1(?=( |\\n)))";

        line = reader.readLine() + "\n";

        while (line != null) {
            num_m = Pattern.compile(regex).matcher(line);
            while (num_m.find()) {
                card_value = (int) (Math.pow(2, i));
                i++;
            }
            sum += card_value;
            card_value = 0;
            i = 0;
            line = reader.readLine();
            if (line != null) line += "\n";
        }

        reader.close();
        return sum;
    }
}

class part_2 {
    private List<String> matrix = new ArrayList<String>();
    private Integer total = 0;

    Integer run () throws Exception {
        read_input();
        tot_card(0, matrix.size() - 1);
        return total + matrix.size();
    }

    private void read_input () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(System.getenv("INPUT_FILE")));
        String line;

        line = reader.readLine();

        while (line != null) {
            matrix.add(line + "\n");
            line = reader.readLine();
        }

        reader.close();
    }

    private void tot_card (int index_i, int index_f) {
        Matcher num_m;
        String regex = "(?<= )(\\d+)(?= .*\\|.*(?<= )\\1(?=( |\\n)))";
        int wins = 0;

        for (int i = index_i; i <= index_f; i++) {
            String line;
            line = matrix.get(i);

            num_m = Pattern.compile(regex).matcher(line);
            while (num_m.find()) {
                wins++;
            }

            if (wins != 0 && i + wins < matrix.size()) {
                total += wins;
                tot_card(i + 1, i + wins);
            }
            wins = 0;
        }
    }
}
