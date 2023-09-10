#ifndef RETURNCLASSIFIES_H_
#define RETURNCLASSIFIES_H_
#include <string>

#include <map>
#include "Command.h"
#include "SocketIo.h"
using namespace std;

class ReturnClassifies:public Command{
private:
map<int,string> classifies;
public:
//execute the command
void execute();
//constructor
ReturnClassifies(int sock);
string getDescription();
void setClassifies(map<int, string> clsfy);


};
#endif