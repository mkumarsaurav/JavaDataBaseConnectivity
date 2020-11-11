import java.util.Scanner;

public class Runner extends DBDemo {
	public static void main(String[] args) {
		Runner r = new Runner();
		int option;
		try (Scanner sc = new Scanner(System.in)) {
			boolean flag=true;
			while(flag) {
				System.out.println("------MENU------");
				System.out.println("1.Create_Table 2.Drop_Table 3.Insert 4.Display 5.Exit");
				option=sc.nextInt();
				switch(option) {
				case 1:
					r.create_table();
					break;
				case 2:
					r.drop_table();
					break;
				case 3:
					r.insert();
					break;
				case 4:
					r.display();
					break;
				case 5:
					flag=false;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
				}
			}
		}
		
	}
}
