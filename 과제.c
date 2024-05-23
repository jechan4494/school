#include <iostream>
#include <string>
using namespace std;

class Product {
	int type; int productID; int price; // type : 제품 종류 productID : 제품아이디 , price : 가격
	string description; string maker;  // description : 제품설명 , maker : 제조사
	int productType; // Book : 1, CompackDisk : 2, ConversationBook :3
public:
	Product(int type, int productID, string description, string maker, int price); // 생성자

	void showProduct(); // 제품 정보 출력
	int getProductID() { return productID; } // 제품 아이디 반환 
	string getDescriton() { return description; } // 제품 설명 반환
	string getMaker() { return maker; } // 제조사 반환
	int getPrice() { return price; } // 가격 반환
	int getType() { return type; } // 제품 종류 반환 
};

//생성자 정의
Product::Product(int type, int productID, string description, string maker, int price) {
	this->type = type; this->productID = productID;
	this->description = description; this->maker = maker;
	this->price = price;
}
// 제품 정보 출력, 제품 아이디 반환, 제품 설명 반환, 제조사 반환, 가격 반환, 제품 종류 반환
void Product::showProduct() {
	cout << "---상품ID : " << productID << endl;
	cout << "상품설명 : " << description << endl;
	cout << "생산자:" << maker << endl; cout << "가격:" << price << endl;
}

// 북 class선언 및 Product class 상속
class Book : public Product {

	int ISBN; // 고유번호 
	string title; // 책 제목
	string author; // 책저자
public:
	Book(int type, int productID, string description, string maker, int price, int ISBN, string title, string author);
	void showBook(); // 책 정보 출력
	void setTitle(string title) { this->title = title; } // 책 이름 반환
	string getAuthor() { return author; } // 저자 반환
	int getISBN() { return ISBN; } // 고유번호 반환
};
// 생성자 정의
Book::Book(int type, int productID, string description, string maker, int price, int ISBN, string title, string author)
	:Product(type, productID, description, maker, price) {
	this->ISBN = ISBN; this->title = title; this -> author = author;
}
void Book::showBook() {
	showProduct();
	cout << "ISBN : " << ISBN << endl; cout << "책제목 : " << title << endl;
	cout << "저자  : " << author << endl;
}
// Book class 상속
class ConversationBook : public Book {
	string language; // 언어 
public:
	ConversationBook(int type, int productID, string description, string maker, int price, int ISBN, string title, string author, string language);
	void showConversationBook(); // 언어책 출력
	string getLanguage() { return language; } // 언어 반환
};
//생성자 정의
ConversationBook::ConversationBook(int type, int productID, string description, string maker, int price, int ISBN, string title, string author, string language):Book(int type, int productID, string description, string maker, int price, int ISBN, string title, string author) {
	this->language = language;
}
void ConversationBook::showConversationBook() {
	showBook();
	cout << "언어 :" << language << endl;
}
// 앨범
class CompactDisc : public Product {
	string albumTitle;
	string artist;
public:
	CompactDisc(int type, int productID, string description, string maker, int price, string albumTitle, string artist);
	void showCompactDisc();
	string getAlbumTitle() { return albumTitle; }
	string getArtist() { return artist; }
};
#endif

CompactDisc::CompactDisc(int type, int productID, string description, string maker, int price, string albumTitle, string artist) {
	this->albumTitle = albumTitle;
	this->artist = artist;
}
void CompactDisc::showCompactDisc() {
	showProduct();
	cout << "앨범제목 : " << albumTitle << endl;
	cout << "가수 : " << artist << endl;
}

#ifndef PRODUCTMANAGER_H
#define PRODUCTMANAGER_H
class ProductManager {
	int nextId;
	int numberOfProducts;
	Product* p[100];
public:
	ProductManager();
	~ProductManager();
	void operate();
	void addProduct(int type);
};
#endif

ProductManager::ProductManager() {
	nextId = 0;
	numberOfProducts = 0;
	for (int i = 0; i < 100; i++) {
		p[i] = NULL;
	}
}
