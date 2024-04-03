// 1번

#include <iostream>
using namespace std;
class Color {
	int red, green, blue;
public:
	Color() { red = green = blue = 0; }
	Color(int r, int g, int b) { red = r, green = g, blue = b; }
	void setColor(int r, int g, int b) { red = r; green = g, blue = b; }
	void show() { cout << red << ' ' << green << ' ' << blue << endl; }
};

int main() {

	Color screenColor(255, 0, 0);
	Color* p;
	p = &screenColor;
	p->show();
	Color colors[3];
	p = colors;

	p->setColor(255, 0, 0);
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

	int evr = 0;

	int* p = new int[5];
	cout << "정수 5개 입력";
	for(int i=0; i<5; i++){
		cin >> p[i]; 
		evr += p[i];
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
	getline(cin, s, '\n');

	int num = 0;
	for (int i = 0; i < s.length(); i++) {
		if (s[i] == 'a')
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
		size = n; p = new int [n];
	}
	void read();
	void write();
	int big();
	~Sample();
};
void Sample::read() {
	for (int i = 0; i <10; i++) {
		cin >> p[i];
	}
}
void Sample::write() {
	for (int i = 0; i < 10; i++) {
		cout << p[i] << " ";
	}
}
int Sample::big() {
	int m = -500;
	for (int i = 0; i < 10; i++) {
	m = std::max(p[i], m);
	}
	return m;
}

Sample::~Sample() {
	delete[] p;
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
		getline(cin, s, '\n');
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

	while (true) {
		cout << ">>";
		getline(cin, s, '\n');
		if (s == "eixt")
			break;
		reverse(s.begin(), s.end());
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
	this-> radius = radius;
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

	string name, tel;
	Person* p;
	
	p = new Person[3];


	cout << "이름과 전화번호를 입력해주세요";

	for (int i = 0; i < 3; i++) {
		cout << "사람" << i + 1 << ">>";
		cin >> name >> tel; p[i].set(name, tel);
	}
	for (int i = 0; i < 3; i++) {
		cout << "모든 사람의 이름은" << p[i].getName();}
	cout << endl;
	cout << "전화번호를 검색합니다. 이름을 입력하세요>>";
	cin >> name;
	for (int i = 0; i < 3; i++) {
		if (name == p[i].getName()) {
			cout << "전화번호는" << p[i].getTel() << endl;
		}
		else
			cout << "없는 사람입니다.";
	}
}

//Person.cpp

#include <iostream>
#include <string>
#include "person.h"

using namespace std;

void Person::set(string name, string tel) {
	this->name = name; this->tel = tel;
}
Person::Person() {

}

//person.h

#pragma once
#ifndef PERSON_H
#define PERSON_H
#include <iostream>
#include <string>

using namespace std;

class Person {

	string name;
	string tel;
public:
	Person();

	string getName() {
		return name;
	}
	string getTel() {
		return tel;
	}
	void set(string name, string tel);

};


#endif

// 11번 //////////////////////////////////////////

#include <iostream>
#include <string>

using namespace std;

class container {

	int size;
public:
	void fill() {
		size = 10; 
	}
	void consume() {
		size--;
	}
	int getSize() {
		return size;
	}
};

class CoffeeMachine {

	container tong[3]; // tong[0] - 커피, tong[1] - 물 , tong[2] - 설탕

public:
	void selectEspresso();
	void selectAmericano();
	void selectSugercoffee();
	void fill();
	void show();
	void run();
};

void CoffeeMachine::selectEspresso() {
	tong[0].consume(); 
	tong[1].consume();
}
void CoffeeMachine::selectAmericano() {
	tong[0].consume();
	tong[1].consume();
	tong[1].consume();
}
void CoffeeMachine::selectSugercoffee() {
	tong[0].consume();
	tong[1].consume();
	tong[1].consume(); 
	tong[2].consume();
}

void CoffeeMachine::fill() {
	for (int i = 0; i < 3; i++) {
		tong[i].fill();
	}
	show();
}

void CoffeeMachine::show() {
	cout << "커피 : " << tong[0].getSize() << "물 : " << tong[1].getSize() << "커피 : " << tong[2].getSize();
}

void CoffeeMachine::run() {
	cout << "***** 커피자판기를 작동합니다. ******\n";

	while (true) {
		int menu;
		cout << "\n메뉴를 눌러주세요(1:에스프레소, 2:아메리카노, 3:설탕커피, 4:잔량보기, 5:채우기)>>";
		cin >> menu;
		if (menu == 1) {
			if (tong[0].getSize() == 0 && tong[0].getSize() == 0)
				cout << "재료가 없습니다.";
			else
				cout << "에스프레소 드세요";
		}
		if (menu == 2) {
			if (tong[0].getSize() == 0 && tong[1].getSize() <= 1)
				cout << "재료가 없습니다.";
			else
				cout << "아메리카노 드세요";
		}
		if (menu == 3) {
			if (tong[0].getSize() == 0 && tong[1].getSize() <= 1 && tong[2].getSize() == 0)
				cout << "재료가 없습니다.";
			else
				cout << "설탕커피 드세요";
		}
		if (menu == 4) {
			show();
		}
		if (menu == 5) {
			fill();
		}
	}
}

	int main() {
		CoffeeMachine r;
		r.run();
	}

