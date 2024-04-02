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
		revers(s.begin(), s.end());
		cout << s << endl;
	}
}


