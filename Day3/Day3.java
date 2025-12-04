import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//hi

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Day3 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ArrayList<String> lines = getFileData("Day3/data");

        long partOneAnswer = 0;
        long partTwoAnswer = 0;
        System.out.println(lines);
        partOneAnswer = getPartOneNumber(lines);
        partTwoAnswer = getPartTwoNumber(lines);
        System.out.println("Part one answer: " + partOneAnswer);
        System.out.println("Part two answer: " + partTwoAnswer);
    }

    public static long getPartOneNumber(ArrayList<String> info) {
        // do part 1
        long total = 0;
        for (String line : info) {
            // process line
            long max = 0;
           HashMap<Integer, String> map = new HashMap<>();
            for (int i = 0; i < line.length(); i++) {
                String digit = line.substring(i, i + 1);
                map.put(i, digit);
            }
            for (Map.Entry<Integer, String> entry: map.entrySet()) {
                int index = entry.getKey();
                String value = entry.getValue();

                for (int i = line.length() - 1; i >= index; i--) {
                    if (i != index) {
                        long num = Long.parseLong(value + "" + line.substring(i, i + 1));
                        if (num > max) {
                            max = num;
                        }
                    }
                }
            }
            total += max;
        }
        return total;
    }

    public static long getPartTwoNumber(ArrayList<String> info) {
        // do part 2
        long total = 0;
        for (String line : info) {
            // process line
            long max = 0;
            ArrayList<String> digits = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                String digit = line.substring(i, i + 1);
                if (!digits.contains(digit)) {
                    digits.add(digit);
                }
            }
            total += max;
            
            ArrayList<Integer> usedIndices = new ArrayList<Integer>();
            String buffer = "";
            int digitsLeft = 12;
            int lastIndex = -1;

            while (digitsLeft > 0) {
                int nextMax = 0;
                int nextIndex = -1;
                for (String digit : digits) {
                    for (int i = 0; i < line.length(); i++) {
                        if (line.substring(i, i + 1).equals(digit)) {
                            if (i <= lastIndex) {
                                continue;
                            }
                            if (usedIndices.contains(i)) {
                                continue;
                            }
                            if (i > line.length() - digitsLeft) {
                                continue;
                            }
                            if (Integer.parseInt(digit) > nextMax) {
                                nextMax = Integer.parseInt(digit);
                                nextIndex = i;
                            }
                        }
                    }
                }
                digitsLeft--;
               // System.out.println(nextMax);
                usedIndices.add(nextIndex);
                lastIndex = nextIndex;
                buffer += nextMax;
            }
            System.out.println(buffer);
            total += Long.parseLong(buffer);
        }
        return total;
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}