package Common;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Validation {
    static final Scanner sc = new Scanner(System.in);
    private static final String ACCOUNT_NUMBER_VALID = "^\\d{10}$";
    private static final char[] chars = { '1', 'A', 'a', 'B', 'b', 'C',
            'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G', 'g', 'H', 'h',
            'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n',
            'O', 'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
            '6', '7', 'U', 'u', 'V', 'v', 'U', 'u', 'W', 'w', '8', 'X', 'x',
            'Y', 'y', 'Z', 'z', '9' };

    public static void getWordLanguage(Locale curLocate, String key) {
        ResourceBundle words = ResourceBundle.getBundle("Language/" + curLocate, curLocate);
        String wordLanguage = words.getString(key);
        System.out.print(wordLanguage);
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine();
        while (s.isEmpty()) {
            System.out.print("Enter again(Can not blank): ");
            s = sc.nextLine();
        }
        return s;
    }

    public static int getInt(String prompt) {
        int i = 0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            sc.nextLine();
        }
        return i;
    }

    public static int getInt(String prompt, int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (isValid == false) {
            i = getInt(prompt);
            if (i < min)
                System.out.println("Error! Number must be greater than " + min + ".");
            else if (i > max) {
                System.out.println("Error! Number must be less than " + max + ".");
            } else
                isValid = true;
        }
        return i;
    }

    public static String getString(Locale language) {
        while (true) {
            String result = sc.nextLine();
            if (result.length() == 0) {
                getWordLanguage(language, "errCheckInputIntLimit");
                System.out.println();
            } else {
                return result;
            }
        }
    }

    public static int getInt(String prompt, Locale language) {
        int i = 0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                getWordLanguage(language, "errorCheckInputIntLimit");
            }
            sc.nextLine();
        }
        return i;
    }

    public static int getInt(String prompt, int min, int max, Locale language) {
        int i = 0;
        boolean isValid = false;
        while (isValid == false) {
            i = getInt(prompt, language);
            if (i < min)
                getWordLanguage(language, "errorCheckInputIntLimit");
            else if (i > max) {
                getWordLanguage(language, "errorCheckInputIntLimit");
            } else
                isValid = true;
        }
        return i;
    }

    public static String getAccount(Locale language) {
        while (true) {
            getWordLanguage(language, "enterAccountNumber");
            String result = sc.nextLine();
            if (result.matches(ACCOUNT_NUMBER_VALID)) {
                return result;
            }
            getWordLanguage(language, "errCheckInputAccount");
            System.out.println();
        }
    }

    public static String getPassword(Locale language) {
        while (true) {
            getWordLanguage(language, "enterPassword");
            String result = sc.nextLine();
            if (isValidPassword(result, language)) {
                return result;
            }
        }
    }

    public static boolean isValidPassword(String password, Locale language) {
        int lengthPassword = password.length();
        if (lengthPassword < 8 || lengthPassword > 31) {
            getWordLanguage(language, "errCheckLengthPassword");
            System.out.println();
            return false;
        } else {
            int countDigit = 0;
            int countLetter = 0;
            for (int i = 0; i < lengthPassword - 1; i++) {
                if (Character.isDigit(password.charAt(i))) {
                    countDigit++;
                } else if (Character.isLetter(password.charAt(i))) {
                    countLetter++;
                }
            }
            if (countDigit < 1 || countLetter < 1) {
                getWordLanguage(language, "errCheckAlphanumericPassword");
                System.out.println();
                return false;
            }
        }
        return true;
    }

    public static boolean getCaptcha(String captchaGenerated, Locale language) {
        System.out.println(captchaGenerated);
        getWordLanguage(language, "enterCaptcha");
        String captchaInput = getString(language);
        for (int i = 0; i < captchaInput.length(); i++) {
            if (!captchaGenerated.contains(Character.toString(captchaInput.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static String generateCaptchaText() {
        String randomStrValue = "";
        final int LENGTH = 6;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < LENGTH; i++) {
            index = (int) (Math.random() * (chars.length - 1));
            sb.append(chars[index]);
        }
        return sb.toString();
    }
}
