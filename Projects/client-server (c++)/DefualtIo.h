#ifndef DEFUALTIO_H_
#define DEFUALTIO_H_
#include <string>
using namespace std;
/**
 * abstract class for DefualtIo 
 * has read and write virtual functions.
*/
class DefualtIo{

public:
//the function read dada from the user and return the data
virtual string read()=0;
//the function get a string from the user and write him
virtual void write(string st)=0;



};
#endif