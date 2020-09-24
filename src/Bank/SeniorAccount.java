import Bank.Account;

public class SeniorAccount extends Account {
    //set startbalance and fees
    public SeniorAccount(int credit) {
        balance = credit;
        fee = 8;
    }
}
