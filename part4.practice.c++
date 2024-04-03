// 1번

#include <iostream>
using namespace std;
class Color {
	int red, green, blue; //
public:
	Color() { red = green = blue = 0; }
	Color(int r, int g, int b) { red = r, green = g, blue = b; }
	void setColor(int r, int g, int b) { red = r; green = g, blue = b; }
	void show() { cout << red << ' ' << green << ' ' << blue << endl; }
};

int main() {

	Color screenColor(255, 0, 0);
	Color* p; // Color 타입의 포인터 변수 p선언
	p = &screenColor; // screenColor주소를 p에 넣음 
	p->show(); // screenColor 색 출력
	Color colors[3]; // color의 일차원 배열 colors선언.
	p = colors; // p가 colors 배열을 가르키는 코드

	p->setColor(255, 0, 0); //빨강 초록 파랑 가짐
	(p+1)->setColor(0, 255, 0);
	(p + 2)->setColor(0, 0, 255);

	for (int i = 0; i < 3; i++) {
		p->show();
		p++;
	}
}

// 2번 ///////////////////////////////////////////////////////

#include <iostream>

using namespace std;

int main() {

	int evr = 0; // 평균 변수
	
	int* p = new int[5]; // 동적 메모리 할
	cout << "정수 5개 입력";
	for(int i=0; i<5; i++){
		cin >> p[i];  // 정수 5개를 입력받음
		evr += p[i];  //평균 변수에 정수를 다 더함
	}
	cout << "평균" << evr / 5;
}

// 3번  ///////////////////////////////////////////////////////

#include <iostream>
#include <string>
using namespace std;

int main() {

	string s;
	cout << "문자열을 입력 >>";
	getline(cin, s, '\n'); // 문자열 입력받는 함수

	int num = 0;
	for (int i = 0; i < s.length(); i++) { // 문자열 길이까지 반복
		if (s[i] == 'a') // a가 있으면 더하기
			num++;
	}
	cout << "문자 a는 " << num << "개 있습니다."; 
}

// 4번  ///////////////////////////////////////////////////////

#include <iostream>
#include <algorithm>
using namespace std;

class Sample {
	int* p;
	int size;
public:
	Sample(int n) {
		size = n; p = new int [n]; // 동적 메모리 할당
	}
	void read(); 
	void write();
	int big();
	~Sample();
};
void Sample::read() {
	for (int i = 0; i <10; i++) {
		cin >> p[i]; // 정수 10개를 입력받음
	}
}
void Sample::write() {
	for (int i = 0; i < 10; i++) {
		cout << p[i] << " "; 
	}
}
int Sample::big() {
	int m = -500; // 낮은값 -500으로 기본 맥스값 설정
	for (int i = 0; i < 10; i++) {
	m = std::max(p[i], m); //10번을 반복하며 큰값 반환해주는 라이브러리함수 #include <algorithm> 사용
	}
	return m;
}

Sample::~Sample() {
	delete[] p; // 동적메모리 반환
}


int main() {
	Sample s(10);
	s.read();
	s.write();

	cout << "가장 큰 수는 ", s.big();
}

// 5번  ///////////////////////////////////////////////////////

#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

int main() {

	string s;
	srand((unsigned)time(0));

	cout << "아래에 한 줄을 입력하세요.(exit를 입력하면 종료합니다.)\n>>";

	while (true) {
		getline(cin, s, '\n'); // 문자열 입력함수
		if (s == "exit")
			break;
	}
	int index = rand() % (s.length()); // 바뀔자리를 문자 최대길이까지 정함
	s[index] = rand() % 26 + 'a'; // a~z값 랜덤생성하여 문자열 바뀔자리 index에 값을 넣음

	cout << s << endl;

}

// 6번  ///////////////////////////////////////////////////////

#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main() {

	string s;

	cout << "아래에 한줄을 입력하세요.(exit)를 입력하면 종료합니다.";

	while (true) { // 무한루프
		cout << ">>";
		getline(cin, s, '\n');
		if (s == "eixt")
			break;
		reverse(s.begin(), s.end()); // 문자열을 거꾸로 반환하는 라이브러리함수 #include <algorithm> 
		cout << s << endl;
	}
}

// 7번  ///////////////////////////////////////////////////////

//circle.h
#ifndef CIRCLE_H 
#define CIRCLE_H
#include <iostream>
#include <string>

using namespace std;

class Circle{

	int radius; string name;

public:
	double getArea(); string getName();
	void setRadius(int redius);
	void setCircle(string name, int redius);
};

#endif 

//circle.cpp

#include "circle.h"
#include <iostream>

using namespace std;

void Circle::setRadius(int radius) {
	this-> radius = radius; // Circle 객체의 반지름 멤버 변수를 설정한다.
}
void Circle::setCircle(string name, int radius) {
	this->name = name; this->radius = radius;
}
double Circle::getArea() {
	return 3.14 * radius * radius;
}
string Circle::getName() {
	return name;
}

//main.cpp

#include <iostream>
#include "circle.h"

using namespace std;
int main() {
	Circle c[3];

	int radius, count = 0;

	for (int i = 0; i < 3; i++) {
		cout << "원" << i + 1 << "의 반지름";
		cin >> radius;
		c[i].setRadius(radius);
	}
	for (int i = 0; i < 3; i++) {
		if (c[i].getArea() > 100) {
			count++;
		}
	}
	cout << "면적이 100보다 큰 원은" << count << "개 입니다.";
}


// 8번  ///////////////////////////////////////////////////////

#include <iostream>
#include "circle.h"

using namespace std;

int main() {

	Circle* p;
	int size; int count = 0;
	cout << "원의 개수>>"; 
	cin >> size;
	if (size < 1)
		return 0;

	p = new Circle[size];
	int radius;
	for (int i = 0; i < size; i++) {
		cout << "원" << i + 1 << "의 반지름>>"; 
		cin >> radius; p[i].setRadius(radius);
	}
	for (int i = 0; i < size; i++) {
		if (p[i].getArea() > 100) {
			count++;
		}
	}
	cout << "면적이 100보다 큰 원은" << count << "개 입니다.";
	delete[] p;
}

// 9번  ///////////////////////////////////////////////////////

//main.cpp
#include <iostream>
#include <string>
#include "person.h"

using namespace std;

int main() {
    string name, tel; // 이름과 전화번호를 저장할 변수 선언
    Person* p; // Person 객체를 가리키는 포인터 선언
    
    p = new Person[3]; // Person 객체 배열 동적 할당
    
    cout << "이름과 전화번호를 입력해주세요" << endl;

    // 사용자로부터 이름과 전화번호 입력 받아 객체에 설정
    for (int i = 0; i < 3; i++) {
        cout << "사람" << i + 1 << ">>";
        cin >> name >> tel;
        p[i].set(name, tel); // Person 객체의 이름과 전화번호 설정
    }

    // 모든 사람의 이름 출력
    for (int i = 0; i < 3; i++) {
        cout << "모든 사람의 이름은 " << p[i].getName() << endl;
    }

    cout << "전화번호를 검색합니다. 이름을 입력하세요>>";
    cin >> name;

    bool found = false; // 이름을 찾았는지 여부를 저장하는 변수

    // 입력한 이름에 해당하는 사람의 전화번호 출력
    for (int i = 0; i < 3; i++) {
        if (name == p[i].getName()) {
            cout << "전화번호는 " << p[i].getTel() << endl;
            found = true; // 이름을 찾았음을 표시
            break; // 이름을 찾았으면 루프 종료
        }
    }

    if (!found) { // 이름을 찾지 못한 경우
        cout << "없는 사람입니다." << endl;
    }

    delete[] p; // 동적으로 할당된 메모리 해제
    
    return 0; // 프로그램 종료
}

// Person.cpp 파일
#include <iostream> // 표준 입출력 라이브러리 포함
#include <string> // 문자열 라이브러리 포함
#include "person.h" // person.h 헤더 파일 포함

using namespace std; // std 네임스페이스 사용

// 이름과 전화번호를 설정하는 메서드 정의
void Person::set(string name, string tel) {
    this->name = name; // 멤버 변수 name에 전달받은 name 값 설정
    this->tel = tel; // 멤버 변수 tel에 전달받은 tel 값 설정
}

// 기본 생성자 정의
Person::Person() {
    // 아무 동작도 수행하지 않음
}

// person.h 파일
#pragma once // 중복 포함 방지를 위한 pragma once 지시문
#ifndef PERSON_H // ifndef 지시문으로 PERSON_H 심볼이 정의되지 않았을 때만 아래 코드를 처리
#define PERSON_H // PERSON_H 심볼 정의

#include <iostream> // 표준 입출력 라이브러리 포함
#include <string> // 문자열 라이브러리 포함

using namespace std; // std 네임스페이스 사용

class Person { // Person 클래스 정의

    string name; // 이름 멤버 변수
    string tel; // 전화번호 멤버 변수

public:
    // 기본 생성자 선언
    Person();
    
    // 이름을 반환하는 메서드 선언
    string getName() {
        return name;
    }

    // 전화번호를 반환하는 메서드 선언
    string getTel() {
        return tel;
    }

    // 이름과 전화번호를 설정하는 메서드 선언
    void set(string name, string tel);
};

#endif // PERSON_H 종료

#include <iostream> 
#include <string> 

using namespace std; 

class Person {
	string name; 
public:
	// 기본 생성자
	Person();
	// 이름을 받는 생성자
	Person(string name) {
		this->name = name;
	}
	// 이름을 반환하는 메서드
	string getName() {
		return name;
	}
	// 이름을 설정하는 메서드
	void setName(string name) {
		this->name = name;
	}
};

class Family {
	Person* p; 
	int size; // 배열 크기
	string name; // 가족 이름
public:
	// 생성자
	Family(string name, int size);
	// 소멸자
	~Family();

	// 가족 구성원의 이름 설정 메서드
	void setName(int index, string name) {
		p[index].setName(name);
	}

	// 가족 정보 출력 메서드
	void show();
};

// 생성자 정의
Family::Family(string name, int size) {
	this->name = name; // 가족 이름 설정
	this->size = size; // 배열 크기 설정
	p = new Person[size]; // 가족 구성원 배열 동적 할당
}

// 소멸자 정의
Family::~Family() {
	delete[] p; // 배열 메모리 해제
}

// 가족 정보 출력 메서드 정의
void Family::show() {
	cout << name << " 가족은 다음과 같이 " << size << "명 입니다.\n"; // 가족 이름과 구성원 수 출력
	for (int i = 0; i < size; i++) {
		cout << p[i].getName() << " "; // 각 구성원의 이름 출력
	}
	cout << endl;
}

// 메인 함수
int main() {
	Family* simpson = new Family("Simpson", 3); // "Simpson" 가족 동적메모리 생성

	simpson->setName(0, "Simpson"); // 첫 번째 구성원 이름 설정
	simpson->setName(1, "Simpson2"); // 두 번째 구성원 이름 설정
	simpson->setName(2, "Simpson3"); // 세 번째 구성원 이름 설정

	simpson->show(); // 가족 정보 출력

	delete simpson; // 가족 객체 메모리 반환
	return 0; // 프로그램 종료
}


// 11번 //////////////////////////////////////////

#include <iostream> 
#include <string> 
using namespace std; 


class container {
    int size; // 재료의 크기를 나타내는 멤버 변수
public:
    // 재료를 채우는 메서드
    void fill() {
        size = 10; // 재료를 10 채운다.
    }
    // 용기에서 소비하는 메서드
    void consume() {
        size--; // 용기에서 재료의 크기를 하나 감소시킨다.
    }
    // 용기에서 재료의 크기를 반환하는 메서드
    int getSize() {
        return size; // 용기의 크기를 반환한다.
    }
};

// 커피 자판기 클래스
class CoffeeMachine {
    container tong[3]; // 커피, 물, 설탕을 담는 용기 배열

public:
    // 에스프레소 선택 메서드
    void selectEspresso();
    // 아메리카노 선택 메서드
    void selectAmericano();
    // 설탕커피 선택 메서드
    void selectSugercoffee();
    // 용기 채우는 메서드
    void fill();
    // 현재 상태 출력 메서드
    void show();
    // 자판기 작동 메서드
    void run();
};

// 에스프레소 선택 메서드 구현
void CoffeeMachine::selectEspresso() {
    tong[0].consume(); // 커피 용기에서 소비한다.
    tong[1].consume(); // 물 용기에서 소비한다.
}

// 아메리카노 선택 메서드 구현
void CoffeeMachine::selectAmericano() {
    tong[0].consume(); // 커피 용기에서 소비한다.
    tong[1].consume(); // 물 용기에서 소비한다.
    tong[1].consume(); // 물 용기에서 소비한다.
}

// 설탕커피 선택 메서드 구현
void CoffeeMachine::selectSugercoffee() {
    tong[0].consume(); // 커피 용기에서 소비한다.
    tong[1].consume(); // 물 용기에서 소비한다.
    tong[1].consume(); // 물 용기에서 소비한다.
    tong[2].consume(); // 설탕 용기에서 소비한다.
}

// 용기 채우는 메서드 구현
void CoffeeMachine::fill() {
    for (int i = 0; i < 3; i++) {
        tong[i].fill(); // 각 용기를 채운다.
    }
    show(); // 현재 상태를 출력한다.
}

// 현재 상태 출력 메서드 구현
void CoffeeMachine::show() {
    cout << "커피: " << tong[0].getSize() << " 물: " << tong[1].getSize() << " 설탕: " << tong[2].getSize() << endl; // 커피, 물, 설탕 용기의 크기를 출력한다.
}

// 자판기 작동 메서드 구현
void CoffeeMachine::run() {
    cout << "***** 커피자판기를 작동합니다. ******\n";

    while (true) {
        int menu;
        cout << "\n메뉴를 눌러주세요 (1: 에스프레소, 2: 아메리카노, 3: 설탕커피, 4: 잔량보기, 5: 채우기) >> ";
        cin >> menu; // 메뉴를 입력받는다.
        if (menu == 1) {
            if (tong[0].getSize() == 0 && tong[1].getSize() == 0)
                cout << "재료가 없습니다."; // 커피와 물이 모두 소진된 경우 메시지를 출력한다.
            else
                selectEspresso(); // 에스프레소를 선택한다.
        }
        if (menu == 2) {
            if (tong[0].getSize() == 0 && tong[1].getSize() <= 1)
                cout << "재료가 없습니다."; // 커피 또는 물이 모두 소진된 경우 메시지를 출력한다.
            else
                selectAmericano(); // 아메리카노를 선택한다.
        }
        if (menu == 3) {
            if (tong[0].getSize() == 0 && tong[1].getSize() <= 1 && tong[2].getSize() == 0)
                cout << "재료가 없습니다."; // 커피, 물, 설탕이 모두 소진된 경우 메시지를 출력한다.
            else
                selectSugercoffee(); // 설탕커피를 선택한다.
        }
        if (menu == 4) {
            show(); // 현재 상태를 출력한다.
        }
        if (menu == 5) {
            fill(); // 용기를 채운다.
        }
    }
}

// 메인 함수
int main() {
    CoffeeMachine r; // CoffeeMachine 객체 생성
    r.run(); // 자판기 작동 메서드 호출
    return 0; // 프로그램 종료
}
