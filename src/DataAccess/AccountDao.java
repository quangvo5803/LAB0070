package DataAccess;

import java.util.Locale;

import Common.Validation;
import Model.Account;

public class AccountDao {
    private static AccountDao instance = null;

    public static AccountDao Instance(){
        if( instance == null){
            synchronized (AccountDao.class){
                if(instance == null){
                    instance = new AccountDao();
                }
            }
        }
        return instance;
    }

    public void login(Locale language){
        Validation.getWordLanguage(language, "enterAccountNumber");
        String accountNumber = Validation.getAccount(language);
        Validation.getWordLanguage(language, "enterPassword");
        String accountPassWord = Validation.getPassword(language);
        Account a = new Account(accountNumber, accountPassWord);
        String captchaGenerated = Validation.generateCaptchaText();
        while (true) {
            if (Validation.getCaptcha(captchaGenerated, language)) {
                Validation.getWordLanguage(language, "loginSuccess");
                System.out.println();
                return;
            } else {
                Validation.getWordLanguage(language, "errCaptchaIncorrect");
                System.out.println();
            }
        }
    }
}
