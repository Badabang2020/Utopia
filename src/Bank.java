import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Bank implements Event {

    static int time = 0;
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

    public void depositMoney(String ssn, int value) {
        Account account = bankAccounts.get(ssn);
        account.depositMoney(value);
    }

    public int checkAccount(String ssn) {
        Account account = bankAccounts.get(ssn);
        return account.getBalance();
    }

    public int takeMoney(String ssn) {
        Random rdm = new Random();
        int value = rdm.nextInt(100) + 50;
        Account account = bankAccounts.get(ssn);
        account.takeMoney(value);
        return value;
    }

    @Override
    public void happens(Citizen citizen) {
        registerBankAccount(citizen.getSocialSecurityNumber(), citizen.getAge());
        int wallet = citizen.getCitizenStatus().getMainStatus().getWallet();
        if (wallet < 20) {
            int money = takeMoney(citizen.getSocialSecurityNumber());
            citizen.getCitizenStatus().getMainStatus().setWallet(wallet + money);
            citizen.getCitizenStatus().getMainStatus().setEvent("takes " + money + "UT$ from" +
                    ((citizen.getGender() == 'm')? "his":"her") + "bank account.");
        }
        else if (wallet > 100) {
            int money = wallet - 100;
            depositMoney(citizen.getSocialSecurityNumber(), money);
            citizen.getCitizenStatus().getMainStatus().setEvent("puts " + money + "UT$ on" +
                    ((citizen.getGender() == 'm')? "his":"her") + "bank account.");
        }
        else {
            citizen.getCitizenStatus().getMainStatus().setEvent("has " +
                    checkAccount(citizen.getSocialSecurityNumber()) + "UT$ on " +
                    ((citizen.getGender() == 'm')? "his":"her") + "bank account.");
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
