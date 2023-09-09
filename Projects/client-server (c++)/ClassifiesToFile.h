#ifndef CLASSIFIESTOFILE_H_
#define CLASSIFIESTOFILE_H_
#include <string>

#include <map>
#include "Command.h"
#include "SocketIo.h"
using namespace std;

class ClassifiesToFile:public Command{
private:
    map<int,string> classifies;
public:
//execute the command
    void execute();
//constructor
    ClassifiesToFile(int sock);
    string getDescription();
    void setClassifies(map<int, string> clsfy);


};
#endif