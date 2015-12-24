#include<iostream>
#include<string>
#include<stdio.h>
#include<bitset>

using namespace std;

string toBinary(int number) {
    return bitset<32>(number).to_string();
}

int main()  {
    cout<<"\n Hello there!";
    printf("\n Life's rather interesting!");
    string s = "sda";
    cout<<s;

    for(int i = 0; i < 20; i++) {
        cout<<" B: "<<toBinary(i)<<"\n";
    }
    return -1;
}
