import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;

class BankAccount {
    private String accountName; //  //Unique name for each account
    private String accountNumber;   //Unique identifier for each account
    private double balance;      // Stores the balance of the account

    //the constructor to initialise an account with the given account number
    public BankAccount( String accountName,String accountNumber) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = 0.0; // this initial balance is set to 0.0
    }

    //Getter for accountName {
        public String getAccountName(){
            return accountName;
        }
   

    // Getter for accountNumber {
        public String getAccountNumber() {
            return accountNumber;
        }

    //Getter for balance 
        public double getBalance(){
            return balance;
        }
    
    //Deposits the specified amount into the account if it's positive
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit Successful! New Balance: + balance");
            } else {
                System.out.println("Invalid amount.Deposit failed.");
            }
        }
    
    // Withdrawal  if there are suffiicient funds
       public void withdraw(double amount) {
           if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal Successful! New balance: + balance");
           } else {
                System.out.println("Insufficient balance or Invalid amount.Withdrawal failed.");
           }
       }

    }
    
    //BankingSystem Class for creating accpounts,depositing,withdrawing and checking the balance
    public class BankingSystem {
        private ArrayList<BankAccount> accounts = new ArrayList<>();

        //Main method to run the banking system application
        public static void main(String[] args){
            BankingSystem system = new BankingSystem();
            system.showMenu(); //launcg the main menu
        }
        
        //Displays the menu and processes user choices to perform banking operations
        private void showMenu() {
          Scanner scanner = new Scanner(System.in);
          int choice = -1;

          
          //Loop to keep showing the menu until the user chooses to exit
          do{
              //Display Menu
                System.out.println("\nWelcome to the Banking System");
                System.out.println("1.Create account");
                System.out.println("2.Deposit Money");
                System.out.println("3.Withdraw Money");
                System.out.println("4.Check Balance");
                System.out.println("5.Exit");
                System.out.println("6.listAccounts");
                System.out.println("7.Enter Choice");

                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input.Please enter a number between 1 and 5.");
                    scanner.next(); // clear the invalid input
                    continue;
                }

                switch(choice){
                    case 1 -> createAccount(scanner);
                    case 2 -> depositMoney(scanner);
                    case 3 -> withdrawMoney(scanner);
                    case 4 -> checkBalance(scanner);
                    case 5 -> System.out.println("Exiting... Thank You for using the Banking System");
                    default -> System.out.println("Invalid choice.Try again.");
                }

        } while (choice != 5);

        scanner.close();
    }

    //method to create a new account
    private void createAccount(Scanner scanner) {
        System.out.print("Enter a new account number: ");
        String accountNumber = scanner.next();
        if (findAccount(accountNumber).isEmpty()) {
            accounts.add(new BankAccount(accountNumber, accountNumber));
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account number already exists.Try a different number");
        }

    }

    //method to deposit money
    private void depositMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Optional<BankAccount> account = findAccount(accountNumber);

        if (account.isPresent()) {
            System.out.print("Enter amount to deposit:");
            double amount = scanner.nextDouble();
        } else {
            System.out.println("Account not found.");
        }
    }

    //method to withdraw money
    private void withdrawMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Optional<BankAccount> account = findAccount(accountNumber);

        if (account.isPresent()) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.get().withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    //method to check balance
    private void checkBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Optional<BankAccount> account = findAccount(accountNumber);

        if (account.isPresent()) {
            System.out.println("Current Balance:" + account.get().getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to list all accounts
    private void listAccount() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("Account List:");
            accounts.forEach(account ->
                System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance()));
        }
    }

    private Optional<BankAccount> findAccount(String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
    }



}
    
