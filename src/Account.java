public abstract class Account {
    protected int balance;
    protected int fee;
    //for the fees, should get activated every 24ticks
    public void calculateFees() {
        balance -= fee;
    }
    //client can check his bankaccount
    public int getBalance() {
        return balance;
    }
    //client can take money from account to wallet IF he got enough in his account
    public int takeMoney(int value) {
        if (balance != 0) {
            if (balance > value) {
                balance -= value;
                System.out.println(this.balance);
                return value;
            } else {
                value = balance;
                balance = 0;
                System.out.println(this.balance);
                return value;
            }
        }
        return 0;
    }
    //client can deposit money onto his account
    public void depositMoney(int value) {
        balance += value;
    }
}