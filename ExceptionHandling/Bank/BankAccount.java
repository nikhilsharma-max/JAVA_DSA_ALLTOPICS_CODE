// package ExceptionHandling.Bank;

public class BankAccount {
    private double balance;
    public BankAccount(double balance){
        this.balance=balance;
    }

    public double getBalance(){
        return this.balance;
    }

    public void withDrawAmount(double amount) throws InsufficientBalanceException{

        if(amount>balance){
            throw new InsufficientBalanceException("Not Enough balance");
        }
        balance-=amount;
        System.out.println("Amount deducted successfully. Rem. balance: "+balance);
    }
}
