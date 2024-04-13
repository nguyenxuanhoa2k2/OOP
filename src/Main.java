import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("|----------|VUI LONG DANG NHAP|---------|");
            System.out.println("|---------1->Admin Login.---------------|");
            System.out.println("|---------2->Teacher Login.-------------|");
            System.out.println("|---------3->Student Login.-------------|");
            System.out.println("|---------0->Exit.----------------------|");
            System.out.println("|---------------------------------------|");
            System.out.println("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Userlogin.AdminLogin();
                    break;
                case 2:
                    Userlogin.TeacherLogin();
                    break;
                case 3:
                    Userlogin.StudentLogin();
                    break;
                default:
                    if (choice != 0) {
                        System.out.println("Function doesn't  exist!!!");
                    } else {
                        System.out.println("End");
                    }
                    break;
            }
        } while (choice != 0);
    }

}
