import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//hi
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Day2 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ArrayList<String> lines = getFileData("Day2/data");

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
            long firstNum = Long.parseLong(line.split("-")[0]);
            long secondNum = Long.parseLong(line.split("-")[1]);
            for (long i = firstNum; i <= secondNum; i++) {
              String stringNum = Long.toString(i);
              int length = stringNum.length();
              if (stringNum.substring(0, length/2).equals(stringNum.substring(length/2))) {
                total += i;
              }
            }
        }
        return total;
    }

    public static long getPartTwoNumber(ArrayList<String> info) {
        // do part 2
        long total = 0;
        for (String line : info) {
            long firstNum = Long.parseLong(line.split("-")[0]);
            long secondNum = Long.parseLong(line.split("-")[1]);
            for (long i = firstNum; i <= secondNum; i++) {
                String stringNum = Long.toString(i);
                Pattern pattern = Pattern.compile("^([0-9]+)\\1+$");
                Matcher matcher = pattern.matcher(stringNum);
                if (matcher.find()) {
                    total += i;
                }
            }
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
                if (!line.equals(""))
                    for (String part : line.split(",")) {
                        fileData.add(part);
                    }
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}