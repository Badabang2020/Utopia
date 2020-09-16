public abstract class Account {
    protected int balance;
    protected int fee;

    public void calculateFees() {
        balance -= fee;
    }
    public int getBalance() {
        return balance;
    }
    public int takeMoney(int value) {
        if (balance > value) {
            balance -= value;
            return value;
        }
        else {
            value = balance;
            balance = 0;
            return value;
        }
    }
    public void depositMoney(int value) {
        balance += value;
    }
}