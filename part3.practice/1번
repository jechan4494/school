// 1번
#include <iostream>
using namespace std;

class Tower {
    int height;
public:
    Tower() // 기본 생성자
    {
        height = 1; // 변수명 수정
    }
    Tower(int h);
    int getHeight();
};

Tower::Tower(int h) {
    height = h; // 변수명 수정
}

int Tower::getHeight() { // 함수명 수정
    return height; // 변수명 수정
}

int main() {
    Tower myTower;
    Tower seoulTower(100); // 100미터로 수정
    cout << "높이는 " << myTower.getHeight() << "미터" << endl;
    cout << "높이는 " << seoulTower.getHeight() << "미터" << endl;
    return 0; // main 함수에서 반환값을 설정하는 것이 좋습니다.
}

// 2번  ///////////////////////////////////////////////////////

#include <iostream>
#include <string>
using namespace std;

class Date { //구현부
	int year, month, day;

public:
	Date(int y, int m, int d);
	Date(string s);
	void show();
	int getYear() { return year; }
	int getMonth() { return month;}
	int getDay() { return day; }
};

//실현부

Date::Date(int y, int m, int d) {

	year = y, month = m, day = d;
}
Date::Date(string s) {
	int index1 = s.find("/"); // /이 들어간 자리의 인덱스값을 넣는다(4)
	string y = s.substr(0, index1); //0부터4까지를 y에 넣음 index1은 1~4이기에 값이4개
	int index2 = s.find("/", index1 + 1); //index1이후에 /이 들어간 자리에 인덱스자리 값을 넣는다(7)
	string m = s.substr(index1 + 1, index2 - index1 - 1); // index+1(7)에서 시작해서 index1(4)자리를 빼고 -1(/)를 한번 더 빼서 월자리를 구함
	string d = s.substr(index2 + 1, s.length()); //length 문자열의 길이를 반환하는 함수 index2+1부터 전체 길이까지 반환
	year = stoi(y); month = stoi(m); day = stoi(d); // stoi()문자열을 정수로 반환해주는 함수


}
void Date::show() {
	cout << year << "년" << month << "월" << day << "일" << endl; //년 월 일을 출력
}

int main() { 
	Date birth(2024, 3, 20);
	Date independenceDay("1945/8/15");
	independenceDay.show();
	cout << birth.getYear() << ',' << birth.getMonth() << ',' << birth.getDay() << endl;
}

class Date {

	int year, month, day;
public:

	Date(int y,int m,int d);
	Date(string s);
	void show();
	int getYear() {
		return year;
	}
	int getMonth(){
		return month;
	}
	int getday() {

	}
	Date::Date() {

	}
};

// 3번  ///////////////////////////////////////////////////////

#include <iostream>
#include <string>
using namespace std;

//구현부
class Account {
	
	string name; // 멤버변수 주인 이름 
	int id, balance; // 계좌번호,잔액
public:
//생성자
	string getOnwer();
	void deposit(int x); //저금
	void withdraw(int x); //출금
	int inquiry(); //잔액 조회

	Account(string s, int id, int balance);
};
//생성자 정의
Account::Account(string s, int i, int bal) {
	name = s;
	id = i;
	balance = bal;
}
string Account::getOnwer() { //이름 반환 함수
	return name;
}
//저금 함수
void Account::deposit(int x) {
	balance += x;
}
//출금 함수
void Account::withdraw(int x) {
	balance -= x;
}
//잔액 조회 함수
int Account::inquiry() {
	return balance;
}

int main() {
	Account a("kitae", 1, 5000);
	a.deposit(50000);
	cout << a.getOnwer() << "의 잔액은" << a.inquiry() << endl;
	a.withdraw(20000);
	cout << a.getOnwer() << "의 잔액은" << a.inquiry() << endl;
}

// 4번  ///////////////////////////////////////////////////////

#include <iostream>
using namespace std;

class CoffeMachine {

	int cafe, water, sugar; // 커피,물,설탕을 나타내는 멤버변수
public:
	//생성자
	CoffeMachine(int c, int w, int s); //초기 커피머신 상태 설정
	void drinkEspresso(); //에스프레소 생성자
	void show(); // 커피머신상태 생성자
	void drinkAmericano(); //아메리카노 생성자
	void drinkSugarCoffe(); //설탕커피 생성자
	void fill(); //머신 채우기 생성자

};

CoffeMachine::CoffeMachine(int c,int w,int s) { //생성자 정의(멤버변수 초기화)
	cafe = c, water = w, sugar = s;
}

void CoffeMachine::drinkEspresso() { // 에스프레소 커피 1감소, 물 1감소 (수정)
	cafe--;
	water--;
}
void CoffeMachine::drinkAmericano() { // 아메리카노 커피1 감소, 물 2감소 (수정)
	cafe--;
	water -= 2;
}
void CoffeMachine::drinkSugarCoffe() { // 설탕커피 커피 1감소, 물 2감소, 설탕 1감소 (수정)
	cafe--;
	water -= 2;
	sugar--;
}
void CoffeMachine::fill() { // 머신 채우기 커피,물,설탕 전부 10 (수정)
	cafe = 10;
	water = 10;
	sugar = 10;
}
void CoffeMachine::show() { // 머신 상태 출력 (수정)
	cout << "커피 머신 상태 출력 커피 : " << cafe << " 물 : " << water << " 설탕 : " << sugar << endl;
}

int main() {
	CoffeMachine java(5, 10, 3);
	java.drinkEspresso();
	java.show();
	java.drinkAmericano();
	java.show();
	java.drinkSugarCoffe();
	java.show();
	java.fill();
	java.show();
}


// 5번  ///////////////////////////////////////////////////////

#include <iostream>
using namespace std;

class Random {

public:
	int num1, num2;
	int next();
	int nextInRange(int num1, int num2);

};

Random::Random() {
	srand((unsigned int)time(NULL));
	num1 = 0;
	num2 = RAND_MAX; //0부터 MAX값까지 범위를 정하는 함수
}
int Random::next() {
	return rand() % (RAND_MAX + 1); //0부터 MAX까지 난수 생성
}

int Random::nextInRange(int num1,int num2) {

	return rand() % (num2 - num1 + 1) + num1;
}
int main() {

}

// 6번  ///////////////////////////////////////////////////////

#include <iostream>
#include <cstdlib> // rand 함수 사용을 위한 헤더 파일
#include <ctime> // 시드 설정을 위한 헤더 파일
using namespace std;

class EvenRandom {
	int start; //시작을 저장할 멤버변수
	int end; 

public:
	EvenRandom(); //기본 생성자

	int next(); //다음 짝수를 위한 멤버 함수
	int nextInRange(int start, int end);//주어진 범위의 짝수를 위한 멤버 함수

};

EvenRandom::EvenRandom() {
	srand((unsigned int)time(NULL)); //시간에 따른 프로그래밍실시마다 다른변수를 위한 발생기 초기화
	start = 0; //시작값을 설정하는 변수
	end = RAND_MAX; //끝자리수를 RAND_MAX치로 설정 32767
}
int EvenRandom::next() {
	while (true) { //true일시 무한루프
		int num = rand() % (RAND_MAX + 1); //0부터 MAX까지 난수 생성
		if (num % 2 == 0) { //짝수인지 확인
			return num; //맞을시 리턴
		}
	}
}

// 7번  ///////////////////////////////////////////////////////

#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

int start,end; //범위 시작값,끝값

class EvenRandom{

public:
	EvenRandom(); //기본생성자 정의
	int next(int result); //다음 짝수를 위한 멤버함수
	int nextInRange(int result,int start,int end); //주어진 범위에서 짝수를 반환하는 멤버함수
}
EvenRandom::EvenRandom() {
	srand((unsigned)(time)(NULL)); //시간을 기반으로 발생기 초기화
}

int EvenRandom::next(int num) {
		
	int num1;
	while (true) {
		int rad = rand(); //0부터 RAND_MAX사이의 난수 생성
		if (rad == num) {//생성된 난수가 num과 같으면 리턴
			return num;
		}
	}
}

int EvenRandom::nextInRange(int num, int start, int end) {
	int result;
	while (true) {
		result = rand() % (end - start + 1) + start; // start와 end값 사이의 난수 생성

		if (result % 2 == num) { //리턴된 난수가 결과와 같을시 리턴
			return result;
		}
	}
}

// 8번  ///////////////////////////////////////////////////////

#include <iostream>
#include <string>
using namespace std;

class Integer {

	int number; //정수를 저장하는 함수
public: 
	int get(); //정수를 반환하는 함수 
	bool isEven(); // 함수의 참값 또는 거짓값을 반환하는 함수
	Integer(int n); //정수 초기화
};
Integer::Integer(int n) {
	number = n;
}

inline int Integer::get() {
	return number;
}
inline bool Integer::isEven() {
	if (number % 2 == 0) //짝수일시 참값
		return true;
	else 
		false;
}

// 9번  ///////////////////////////////////////////////////////

#include <iostream>
using namespace std;

class Oval {
	
public:

	int width, height; // 너비와 높이 멤버변수
	
	Oval();//생성자 
	~Oval(); //소멸자
	Oval(int w, int h); //너비와 높이를 재정의
	int getWidth(); //너비를 반환하는 함수
	int getHeight(); //높이를 반환하는 함수
	void set(int w, int h); //너비와 높이를 설정하는 함수
	void show(); //너비와 높이를 출력하는 함수
	
};
//기본 생성자
Oval::Oval() { 
}
//너비와 높이 설정하는 생성자
Oval::Oval(int w, int h) {
	width = w;
	height = h;
}
//소멸자 정의
Oval::~Oval() {
	cout << "Oval 소멸 width : " << width << "height : " << height << endl;
}
//높이를 반환하는 함수 정의
int Oval::getHeight() {
	return height;
}
//너비를 반환하는 함수 정의
int Oval::getWidth() {
	return width;
}
//너비와 높이를 설정하는 함수 정의
void Oval::set(int w, int h) {
	width = w;
	height = h;
}
//너비와 높이를 출력하는 함수 정의
void Oval::show() {
	cout << width << "," << height << endl;
}

// 10번  ///////////////////////////////////////////////////////

#include <iostream>
using namespace std;

class Add {
	int a, b;
public:
	void setValue(int x, int y) {
		a = x, b = y;
	}
	int calculate() {
		return a + b;
	}
};

class Sub {
	int a, b;
public:
	void setValue(int x, int y) {
		a = x, b = y;
	}
	int calculate() {
		return a - b;
	}
};

class Mul {
	int a, b;
public:
	void setValue(int x, int y) {
		a = x; b = y;
	}
	int calculate() {
		return a * b;
	}
};

class Div {
	int a, b;
public:
	void setValue(int x, int y) {
		a = x; b = y;
	}
	int calcualte() {
		return a / b;
	}
};

int main() {
	Add a;
	Sub s;
	Mul m;
	Div d;

	char c;

	while (true) {
		cout << "두 정수와 연산자를 입력하세요.>>";
		int x, y;
		cin >> x >> y >> c;

		if (c == '+') {
			a.setValue(x, y);
			cout << a.calculate() << endl;
		}
		else if (c == '-') {
			s.setValue(x, y);
			cout << s.calculate() << endl;
		}
		else if (c == '*') {
			m.setValue(x, y);
			cout << m.calculate() << endl;
		}
		else if (c == '/') {
			d.setValue(x, y);
			cout << d.calcualte() << endl;
		}
	}
}
