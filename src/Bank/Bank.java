package Bank;

import java.util.*;
import Citizen.*;
import UtopiaCore.Category;
import UtopiaCore.Event;
import UtopiaCore.UtopiaMain;

public class Bank implements Event {

    private long bankTresor;
    private int startCredit;
    static int time = 0; // UtopiaCore.Event time for ticks of the day
    private HashMap<String, Account> bankAccounts; //Bank.Bank accounts List <SocialSecurityNumber, new Bank.Account>

    // Constructor start
    public Bank() {
        bankTresor = 1000000; //bank gets 1 mio for start
        time = 0; //bank time starts at 0
        bankAccounts = new HashMap<String, Account>(); //register library for Accounts
    }
    // Constructor end

    public boolean registerBankAccount(String ssn, int age) { //register a new Bank.Bank Bank.Account
        if (!bankAccounts.containsKey(ssn)) { //ONLY if the Account not exists
            if (age < 18) {
                startCredit = 500;
                bankAccounts.put(ssn, new ChildAccount(startCredit)); // create a child account for the citizen
                bankTresor -= startCredit;
            } else if (age < 65) {
                startCredit = 1000;
                bankAccounts.put(ssn, new AdultAccount(startCredit)); // create a adult account for the citizen
                bankTresor -= startCredit;
            } else {
                startCredit = 1500;
                bankAccounts.put(ssn, new SeniorAccount(startCredit)); // create a senior account for the citizen
                bankTresor -= startCredit;
            }
            return true;  //Account created
        }
        return false; //no Account created
    }

    public void depositMoney(String ssn, int value) { // citizen can deposit their money
        Account account = bankAccounts.get(ssn); // get Bank.Account
        account.depositMoney(value);
    }

    public int checkAccount(String ssn) { // citizen can check how much money they have
        Account account = bankAccounts.get(ssn); // get Bank.Account
        return account.getBalance();
    }

    public int takeMoney(String ssn) { // citizen can take their money from the bank account

        //////////          !! FOR TESTING !!         /////////////
        Random rdm = new Random();
        ////////// citizen takes a rdm value of money /////////////
        int value = rdm.nextInt(100) + 50; // min 50 UT$
        Account account = bankAccounts.get(ssn);
        int output = account.takeMoney(value); // citizen takes their money
        return output;
    }

    @Override
    public void happens(Citizen citizen) {
        boolean newAccount = registerBankAccount(citizen.getSocialSecurityNumber(), citizen.getAge()); // checks if the citizen has a account every time (s)he goes to bank
        String msg = (newAccount ? "Register an account and ": ""); //String for creating new Bank.Account
        int wallet = citizen.getCitizenStatus().getMainStatus().getWallet(); // get citizens wallet
        if (wallet < 20) { // if wallet int is smaller than 20, citizen takes money(random value between 50 and 149)
            int money = takeMoney(citizen.getSocialSecurityNumber());
            citizen.getCitizenStatus().getMainStatus().setWallet(wallet + money); // put the money in the wallet
            if (money != 0) {
                citizen.getCitizenStatus().getMainStatus().setEvent(msg + "takes " + money + "UT$ from " +
                        ((citizen.getGender() == 'm') ? "his" : "her") + " bank account.");
            }
            else {
                citizen.getCitizenStatus().getMainStatus().setEvent("don't have money on " +((citizen.getGender() == 'm') ? "his" : "her")+ " bank account.");
            } }
        else if (wallet > 100) { // if wallet int is larger than 100, citizen deposit all money above 100
            int money = wallet - 100;
            depositMoney(citizen.getSocialSecurityNumber(), money);
            citizen.getCitizenStatus().getMainStatus().setWallet(100);
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
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(UtopiaMain.myController.getUtopiaTime());
        if (cal.get(Calendar.HOUR_OF_DAY) == 0) {
            Set<String> keys = bankAccounts.keySet();
            for (String key : keys) {
                Account account = bankAccounts.get(key);
                account.calculateFees();
                ///// Bonus money for each Utopian!! /////
                /////    remove if 'work' exists     /////
                account.depositMoney(100);
            }
        }
    }

    @Override
    public Category[] getCategory() {
        Category[] category = new Category[] {Category.Money};
        return category;
    }
}
