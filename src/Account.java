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
        if (balance < value) {
            balance -= value;
            return value;
        }
        else {
            balance = 0;
            return balance;
        }
    }
    public void deployMoney(int value) {
        balance += balance;
    }
}