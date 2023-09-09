#include <iostream>
#include "StandartIo.h"
using namespace std;


string StandartIo::read()
{
    string data;
    getline(cin,data);
    return data;
}
void StandartIo::write(string st)
{
    cout<<st<<endl;
}
void StandartIo::write(int x)
{
    cout<<x<<endl;
}