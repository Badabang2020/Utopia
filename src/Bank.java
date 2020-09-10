import java.util.HashMap;

public class Bank {


    HashMap<String, Account> bankAccounts = new HashMap<String, Account>();

    public void registerBankAccount(String ssn, int age) {
        if (!bankAccounts.containsKey(ssn)) {
            if (age < 18) {
                bankAccounts.put(ssn, new ChildAccount());
            } else if (age < 65) {
                bankAccounts.put(ssn, new AdultAccount());
            } else {
                bankAccounts.put(ssn, new SeniorAccount());
            }
        } else {
            System.out.println("This client already has a bankaccount.");
        }
    }

    public void depositMoney(String ssn) {

    }

    public int checkAccount(String ssn) {
        Account account = bankAccounts.get(ssn);
        return account.getBalance();
    }

    public void takeMoney(ssn) {

    }

}
