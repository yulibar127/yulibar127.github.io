#ifndef SOCKETIO_H_
#define SOCKETIO_H_
#include "DefualtIo.h"
#include <string>
#include <iostream>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <cstring>

using namespace std;
/*
class of StandartIo. inherit from the abstract class DefualtIo
has read and write function
*/
class SocketIo:public DefualtIo{
private:
int socketClient;
char buffer[4096];
           
public:
SocketIo(int sock);
/*
the function read dada from the user and return the data
*/
string read();
/*
the function get a string from the user and send it
*/
void write(string st);
int getSocket();

};
#endif
