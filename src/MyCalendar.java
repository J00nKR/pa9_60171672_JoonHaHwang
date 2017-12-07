import java.util.*;

public class MyCalendar {
	boolean TF = false;
	Scanner scanner = new Scanner(System.in);
	int default_year = 1;
	int default_month = 1;
	int default_day_number = 1;
	int[] Day_number = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	int[] Day_number_leapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	String[] Day_string = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
	
	public int getDefault_year() {return default_year;}
	public void setDefault_year(int default_year) {this.default_year = default_year;}
	public int getDefault_month() {return default_month;}
	public void setDefault_month(int default_month) {this.default_month = default_month;}
	public int getDefault_day_number() {return default_day_number;}
	public void setDefault_day_number(int default_day_number) {this.default_day_number = default_day_number;}
	
	void Choice(int choice) {
		if(choice == 1) {
			setDate();
			Number1();
		}
		else if(choice == 2) {
			Number2();
		}
		else if(choice == 3) {
			Number3();
		}
		else if(choice == 4) {
			Number4();
		}
		else {
			System.out.println("잘 못 입력하셨습니다.\n");
		}
	}
	
	void setDate() {
		
		System.out.println("년, 월, 일을 입력하세요.");
		int[] integers = new int[3];
		for(int i = 0; i < 3; i++)
		{
			integers[i] = scanner.nextInt();
		}
		try {
		if(integers[0] > 0 && integers[1] > 0 && integers[2] > 0) {
			if(integers[1] <= 12) {
				if(integers[0]%4 == 0) {
					if(integers[0]%400 == 0) {
						if(integers[2] <= Day_number_leapYear[integers[1]-1]) {
							setDefault_year(integers[0]);
							setDefault_month(integers[1]);
							setDefault_day_number(integers[2]);
						}else {
							setDate();
						}
					}
					// 윤년
					else if(integers[0]%100 != 0) {
						if(integers[2] <= Day_number[integers[1]-1]) {
							setDefault_year(integers[0]);
							setDefault_month(integers[1]);
							setDefault_day_number(integers[2]);
						}else {
							if(integers[2] <= Day_number_leapYear[integers[1]-1]) {
								setDefault_year(integers[0]);
								setDefault_month(integers[1]);
								setDefault_day_number(integers[2]);
							}else {
								setDate();	
							}
						}
					}
					else {
						if(integers[2] <= Day_number_leapYear[integers[1]-1]) {
							setDefault_year(integers[0]);
							setDefault_month(integers[1]);
							setDefault_day_number(integers[2]);
						}else {
							setDate();
						}
					}
					// 윤년
				}else{
					if(integers[2] <= Day_number[integers[1]-1]) {
						setDefault_year(integers[0]);
						setDefault_month(integers[1]);
						setDefault_day_number(integers[2]);
					}else {
						setDate();
					}
				}
				}else{
					if(integers[2] <= Day_number[integers[1]-1]) {
						setDefault_year(integers[0]);
						setDefault_month(integers[1]);
						setDefault_day_number(integers[2]);
					}else {
						setDate();
					}
				}
			}else{
				setDate();
			}
		}catch(Exception e){
			setDate();
		}
	}
	
	void Number1() {
		int sum = 0;
		for(int i = 1; i <getDefault_year(); i++) {
			if(i%4 == 0) {
				if(i%400 == 0) {
					sum += 366;
				}
				// 윤년
				else if(i%100 != 0) {
					sum += 365;
				}
				// not 윤년
				else {
					sum += 366;
				}
				// 윤년
			}
			else {
				sum += 365;
			}
			// not 윤년
		}
		for(int i = 1; i < getDefault_month(); i++) {
			if(getDefault_year()%4 == 0 && getDefault_year()%100 != 0 && getDefault_year()%400 ==0) {
				sum += Day_number_leapYear[i];
			}
			// 윤년
			else {
				sum += Day_number[i];
			}
			// not 윤년
		}
		sum += getDefault_day_number();
		System.out.printf("%d년 %d월 %d일 %s입니다\n\n", getDefault_year(), getDefault_month(), getDefault_day_number(), Day_string[(sum-1)%7]);
	}
	void Number2(){
		int input = 0;
		int change = 0;
		System.out.println("Field를 선택하시오 : 1) Year 2) Month 3) Day 4) All");
		input = scanner.nextInt();
		if(input == 1) {
			System.out.println("값을 입력하세요.");
			change = scanner.nextInt();
			setDefault_year(getDefault_year()+change);
			Number1();
		}
		// Year
		else if(input == 2) {
			System.out.print("값을 입력하세요.");
			change = scanner.nextInt();
			setDefault_month(getDefault_month()+change);
			Number1();
		}
		// Month
		else if (input == 3) {
			System.out.println("값을 입력하세요.");
			change = scanner.nextInt();
			setDefault_day_number(getDefault_day_number()+change);
			Number1();
		}
		// Day
		else if (input == 4) {
			System.out.println("값을 입력하세요.");
			int[] integers = new int[3];
			for(int i = 0; i < 3; i++)
			{
				integers[i] = scanner.nextInt();
			}
			setDefault_year(getDefault_year() + integers[0]);
			if(integers[2] >= Day_number[getDefault_month()]) {
				setDefault_month((getDefault_month() + integers[1])+(getDefault_day_number() + integers[2])/Day_number[getDefault_month()]);
				setDefault_day_number((getDefault_day_number() + integers[2])%Day_number[getDefault_month()]);
				Number1();
			}
			else {
				setDefault_month(getDefault_month() + integers[1]);
				setDefault_day_number(getDefault_day_number() + integers[2]);
				Number1();
			}
		}
		// All
		else {
			System.out.println("Wrong Input!");
		}
		// Out of Boundary
	}
	void Number3() {
		int input = 0;
		int change = 0;
		int rest = 0;

		System.out.println("1) Year 2) Month 3) Day");
		input = scanner.nextInt();
		if(input == 1) {
			System.out.println("값을 입력하세요.");
			change = scanner.nextInt();
			setDefault_month(getDefault_year()+change);
			Number1();
		}
		// Year
		else if(input == 2) {
			System.out.println("값을 입력하세요.");
			change = scanner.nextInt();
			setDefault_month((getDefault_month()+change)%12);
			Number1();
		}
		// Month
		else if (input == 3) {
			System.out.println("값을 입력하세요.");
			change = (scanner.nextInt());
			if(getDefault_year()%4 == 0 && getDefault_year()%100 != 0 && getDefault_year()%400 ==0) {
				rest = change%Day_number_leapYear[getDefault_day_number()];
				setDefault_day_number((getDefault_day_number()+rest)%Day_number_leapYear[getDefault_day_number()]);
			}
			else {
				rest = change%Day_number[getDefault_day_number()];
				setDefault_day_number((getDefault_day_number()+rest)%Day_number[getDefault_day_number()]);
			}
			Number1();
		}
		// Day
		else {
			System.out.println("Wrong Input!");
		}
		// Out of Boundary
	}
	void Number4() {
		TF = true;
	}

	public static void main(String[] args) {
		MyCalendar mycalendar = new MyCalendar();
		Scanner scanner = new Scanner(System.in);
		int choice;
		while(mycalendar.TF != true) {
			System.out.print("메뉴를 선택하세요.\n 1. 날짜 선택\n 2. Add\n 3. Roll\n 4. 종료\n > ");
			choice = scanner.nextInt();
			mycalendar.Choice(choice);
		}
	}

}