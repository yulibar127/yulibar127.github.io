#include <iostream>
#include <sys/socket.h>
#include <cstdio>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <cstring>
#include <cctype>
#include "vectorFunctions.h"
#include "CLI.h"
#include <thread>
#ifndef ASSIGNMENT3_SERVER_H
#define ASSIGNMENT3_SERVER_H



namespace std {
    class Server {
    private:
        const int server_port;
        int sock;
        void sendInvalidInput(int client_socket);
        static void handleThread(int client_socket);



    public:
        explicit Server(int port);

        void receiveInfo();

    };
}


#endif //ASSIGNMENT3_SERVER_H
