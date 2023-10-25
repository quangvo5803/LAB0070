package Controller;

import java.util.Locale;

import Repository.AccountRepository;
import Repository.IAccountRepository;
import View.Menu;

public class Program extends Menu<String> {
    Locale vietnamese = Locale.forLanguageTag("vn");
    Locale english = Locale.ENGLISH;
    static String[] menuChoice = {"Vietnamese","English","Exit"};
    IAccountRepository iAccountRepository;

    public Program(){
        super("========== Bank Login Program ==========", menuChoice);
        iAccountRepository = new AccountRepository();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:{
                iAccountRepository.logic(vietnamese);
                break;
            }
            case 2:{
                iAccountRepository.logic(english);
                break;
            }
            case 3:{
                System.exit(0);
            }
        }
    }

}
