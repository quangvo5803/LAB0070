package Repository;

import java.util.Locale;

import DataAccess.AccountDao;

public class AccountRepository implements IAccountRepository {

    @Override
    public void logic(Locale language) {
        AccountDao.Instance().login(language);
    }
    
}
