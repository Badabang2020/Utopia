import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Bank implements Event {

    static int time = 0; // Event time for ticks of the day
    HashMap<String, Account> bankAccounts = new HashMap<String, Account>(); //Bank accounts List <SocialSecurityNumber, new Account>

    public boolean registerBankAccount(String ssn, int age) { //register a new Bank Account
        if (!bankAccounts.containsKey(ssn)) {
            if (age < 18) {
                bankAccounts.put(ssn, new ChildAccount()); // create a child account for the citizen
            } else if (age < 65) {
                bankAccounts.put(ssn, new AdultAccount()); // create a adult account for the citizen
            } else {
                bankAccounts.put(ssn, new SeniorAccount()); // create a senior account for the citizen
            }
            return true;
        }
        return false;
    }

    public void depositMoney(String ssn, int value) { // citizen can deposit their money
        Account account = bankAccounts.get(ssn); // get Account
        account.depositMoney(value);
    }

    public int checkAccount(String ssn) { // citizen can check how much money they have
        Account account = bankAccounts.get(ssn); // get Account
        return account.getBalance();
    }

    public int takeMoney(String ssn) { // citizen can take their money from the bank account

        //////////          !! FOR TESTING !!         /////////////
        Random rdm = new Random();
        ////////// citizen takes a rdm value of money /////////////
        int value = rdm.nextInt(100) + 50; // min 50 UT$
        Account account = bankAccounts.get(ssn);
        account.takeMoney(value); // citizen takes their money
        return value;
    }

    @Override
    public void happens(Citizen citizen) {
        boolean newAccount = registerBankAccount(citizen.getSocialSecurityNumber(), citizen.getAge()); // checks if the citizen has a account every time (s)he goes to bank
        String msg = (newAccount ? "Register an Account and ": ""); //String for creating new Account
        int wallet = citizen.getCitizenStatus().getMainStatus().getWallet(); // get citizens wallet
        if (wallet < 20) { // if wallet int is smaller than 20, citizen takes money(random value between 50 and 149)
            int money = takeMoney(citizen.getSocialSecurityNumber());
            citizen.getCitizenStatus().getMainStatus().setWallet(wallet + money); // put the money in the wallet
            citizen.getCitizenStatus().getMainStatus().setEvent(msg + "takes " + money + "UT$ from " +
                    ((citizen.getGender() == 'm')? "his":"her") + " bank account.");
        }
        else if (wallet > 100) { // if wallet int is larger than 100, citizen deposit all money above 100
            int money = wallet - 100;
            depositMoney(citizen.getSocialSecurityNumber(), money); //
            citizen.getCitizenStatus().getMainStatus().setEvent(msg + "puts " + money + "UT$ on " +
                    ((citizen.getGender() == 'm')? "his":"her") + " bank account.");
        }
        else {
            citizen.getCitizenStatus().getMainStatus().setEvent(msg + "has " +
                    checkAccount(citizen.getSocialSecurityNumber()) + "UT$ on " +
                    ((citizen.getGender() == 'm')? "his":"her") + " bank account.");
        }
    }

    @Override
    public void tick() {
        time++;
        if (time / 24 == 1) {
            time = 0;
            Set<String> keys = bankAccounts.keySet();
            for (String key : keys) {
                Account account = bankAccounts.get(keys);
                account.calculateFees();
                ///// Bonus money for each Utopian!! /////
                /////    remove if 'work' exists     /////
                account.depositMoney(100);
            }
        }
    }
}
