import java.util.HashMap;
import java.util.Random;

public class Bank implements Event {


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
            System.out.println(citizen.getFirstName()+" "+ citizen.getLastName() + " takes " + money + "UT$ from" +
                    ((citizen.getGender() == 'm')? "his":"her") + "bank account.");
        }
        else if (wallet > 100) {
            int money = wallet - 100;
            depositMoney(citizen.getSocialSecurityNumber(), money);
            System.out.println(citizen.getFirstName()+" "+ citizen.getLastName() + " puts " + money + "UT$ on" +
                    ((citizen.getGender() == 'm')? "his":"her") + "bank account.");
        }
        else {
            System.out.println(citizen.getFirstName()+ " " + citizen.getLastName() + " has " +
                    checkAccount(citizen.getSocialSecurityNumber()) + "UT$ on " +
                    ((citizen.getGender() == 'm')? "his":"her") + "bank account.");
        }
    }

    @Override
    public void tick() {
        
    }
}
