import java.util.Scanner;

class EmployeeManagement {

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        String surname = scanner.next();

        String completeEmail = createEmail(name, surname);

        System.out.println(completeEmail);
    }

    public static String createEmail(String name, String surname) {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(surname);
        builder.append("@work.net");
        return builder.toString();
    }
}