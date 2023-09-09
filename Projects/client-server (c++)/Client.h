#ifndef CLIENT_H
#define CLIENT_H
#include "vectorFunctions.h"
#include <thread>
using namespace std;
//class for client
class Client
{
private:
    char* ip;
    int port;
    int clientSock;
    //function to connect the socket
    int clientConnect();
    bool rcvInfo();
    static void handleRcvToFileThread(char rcvBuffer[], string path);

public:
    //constructor for client
    Client(char* _ip,int _port);
    //sending and reciving data 
    void clientSend();




};


#endif
