#ifndef COMMAND_H_
#define COMMAND_H_
#include <string>
#include "DefualtIo.h"
#include "vectorFunctions.h"

using namespace std;
/*
abstract class Command
has execute function and description, dio as members
*/
class Command{
protected:
string description;
DefualtIo *dio;
public:
//execute the command 
virtual void execute()=0;
virtual string getDescription()=0;

};
#endif