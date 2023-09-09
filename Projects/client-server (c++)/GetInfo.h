#ifndef GET_INFO_H_
#define GET_INFO_H_
#include "Command.h"
#include <iostream>
#include "SocketIo.h"
/*
getInfo class.
inherit from Command Abstract class
has constructor and execute function.
*/
class GetInfo:public Command{
private:
int k;
string distanceMetric;
public:
//execute the command
void execute();
//constructor
GetInfo(int sock);

void setK(int newK);
void setDistanceMetric(string dm);
string getDescription();
string getDistanceMetric();
int getK();

};
#endif
