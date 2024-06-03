import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return String.format("Deposited $%.2f. New balance: $%.2f", amount, balance);
        } else {
            return "Deposit amount must be positive.";
        }
    }

    public String withdraw(double amount) {
        if (amount > balance) {
            return "Insufficient balance for this withdrawal.";
        } else if (amount <= 0) {
            return "Withdrawal amount must be positive.";
        } else {
            balance -= amount;
            return String.format("Withdrew $%.2f. New balance: $%.2f", amount, balance);
        }
    }

    public String checkBalance() {
        return String.format("Current balance: $%.2f", balance);
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void withdraw(double amount) {
        String message = bankAccount.withdraw(amount);
        System.out.println(message);
    }

    public void deposit(double amount) {
        String message = bankAccount.deposit(amount);
        System.out.println(message);
    }

    public void checkBalance() {
        String message = bankAccount.checkBalance();
        System.out.println(message);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        withdraw(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;
                case "2":
                    try {
                        System.out.print("Enter amount to deposit: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        deposit(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;
                case "3":
                    checkBalance();
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100);  // Initial balance of $100
        ATM atm = new ATM(account);
        atm.run();
    }
}
