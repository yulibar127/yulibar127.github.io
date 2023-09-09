#ifndef SrandartIo_H_
#define StandartIo_H_
#include "DefualtIo.h"
#include <string>
using namespace std;
/*
class of StandartIo. inherit from the abstract class DefualtIo
has read and write function
*/
class StandartIo:public DefualtIo{
public:
/*
the function read dada from the user and return the data
*/
string read();
/*
the function get a string from the user and print it
*/
void write(string st);
void write(int x);

};
#endif




