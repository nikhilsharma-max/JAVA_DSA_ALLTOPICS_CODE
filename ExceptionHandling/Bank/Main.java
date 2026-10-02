// package ExceptionHandling.Bank;

public class Main {
    public static void main(String[] args){
        BankAccount a1 = new BankAccount(1000);
        try{
            a1.withDrawAmount(2000);
        }
        catch(InsufficientBalanceException e){
            e.printStackTrace();
        }
    }
}
