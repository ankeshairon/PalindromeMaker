import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *Manao and his friend like to ask each other brainteasers every other day. Recently, Manao's friend brought him a string s
 * and claimed this string has been obtained from a palindrome (see notes for definition of palindrome) by picking two positions and
 * exchanging characters at those positions. The friend asked Manao to determine the palindrome that the string was obtained from.
 Manao suspects his friend could have cheated and the string could not be a result of the abovementioned process. Also, he noticed that t
 here might be several possible answers to the problem posed. Help him out by finding all the possible answers, or determining that there is none.
 Input
 The single line contains the string s consisting of at least 2 characters. The string will contain lowercase letters only.
 The problem consists of two subproblems. The subproblems have different constraints on the input. You will get some score for the correct
 submission of the subproblem. The description of the subproblems follows.
 Output
 In the first line, print a single number p - the number of different palindromes from which the given string can be obtained by exchanging letters at exactly two positions. Print these palindromes in the next p lines, one answer per line. The palindromes should be ordered lexicographically.
 */
public class PalindromeChecker {
    public static void main(String[] args) throws Exception {
        final String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        String[] halves = dutchIt(input);
        final List<Integer> nonAgreeingPositions = findNonAgreeingPositions(halves);
        assert (nonAgreeingPositions.size() < 3);

        final int length = input.length();
        if (nonAgreeingPositions.size() == 1) {
            System.out.println(1);
            String pal = exchangeCharsAtPositions(input, nonAgreeingPositions.get(0), length / 2);
            if (!isPalindrome(pal)) {
                pal = exchangeCharsAtPositions(input, length - 1 - nonAgreeingPositions.get(0), length / 2);
            }
            System.out.println(pal);
        } else if (nonAgreeingPositions.size() == 2) {
            System.out.println(2);
            List<String> palindromes = new ArrayList<>();
            palindromes.add(exchangeCharsAtPositions(input, nonAgreeingPositions.get(0), nonAgreeingPositions.get(1)));
            palindromes.add(exchangeCharsAtPositions(input, length - 1 - nonAgreeingPositions.get(0), length - 1 - nonAgreeingPositions.get(1)));
            Collections.sort(palindromes);
            for (String palindrome : palindromes) {
                System.out.println(palindrome);
            }
        } else if (nonAgreeingPositions.size() == 0) {
            System.out.println(1);
            System.out.println(input);
        } else {
            System.out.println(0);
        }
    }

    private static String exchangeCharsAtPositions(String word, int position1, int position2) {
        StringBuilder builder = new StringBuilder(word);
        char temp = builder.charAt(position1);
        builder.setCharAt(position1, builder.charAt(position2));
        builder.setCharAt(position2, temp);
        return builder.toString();
    }

    private static boolean isPalindrome(String input) {
        final int length = input.length();
        for (int i = 0; i < length / 2; i++) {
            if (input.charAt(i) != input.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> findNonAgreeingPositions(String[] halves) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < halves[0].length(); i++) {
            if (halves[0].charAt(i) != halves[1].charAt(i)) {
                positions.add(i);
            }
        }
        return positions;
    }


    private static String[] dutchIt(String input) {
        String[] halves = new String[2];
        final int length = input.length();

        if (length % 2 == 0) {
            halves[0] = input.substring(0, length / 2);
            halves[1] = new StringBuilder(input.substring(length / 2, length)).reverse().toString();
        } else {
            halves[0] = input.substring(0, (length + 1) / 2);
            halves[1] = new StringBuilder(input.substring(length / 2, length)).reverse().toString();
        }
        return halves;
    }

}
