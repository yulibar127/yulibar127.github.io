
#include "Server.h"


namespace std {
    Server::Server(int port) :
            server_port(port),
            sock(socket(AF_INET, SOCK_STREAM, 0)) {}

    void Server::receiveInfo() {

        if (sock < 0) {
            perror("error creating socket");
        }
        struct sockaddr_in sin{};
        memset(&sin, 0, sizeof(sin));
        sin.sin_family = AF_INET;
        sin.sin_addr.s_addr = INADDR_ANY;
        sin.sin_port = htons(server_port);
        if (bind(sock, (struct sockaddr *) &sin, sizeof(sin)) < 0) {
            perror("error binding socket");
        }
        if (listen(sock, 5) < 0) {
            perror("error listening to a socket");
        }


        vector<thread> threads;

        while(true) {
            struct sockaddr_in client_sin{};
            unsigned int addr_len = sizeof(client_sin);
            int client_sock = accept(sock, (struct sockaddr *) &client_sin, &addr_len);
            if (client_sock < 0) {
                perror("error accepting client");
            }

            threads.push_back(thread(Server::handleThread, client_sock));

            threads.back().detach();

        }




    }

    void Server::sendInvalidInput(int client_socket) {
        char data_addr[] = "invalid input";
        int data_len = strlen(data_addr);
        int sent_bytes = send(client_socket, data_addr, data_len, 0);
        if (sent_bytes < 0) {
            perror("error sending to client");
        }
    }

    void Server::handleThread(int client_socket) {
        CLI cli(client_socket);
        cli.start();

        while (true) {

            char buffer[16384];
            for (char &i: buffer) { //initializing the array to null terminator
                i = '\0';
            }
            int expected_data_len = sizeof(buffer);
            int read_bytes = recv(client_socket, buffer, expected_data_len, 0);

            if (read_bytes == 0) {
                close(client_socket);
                break;
            } else if (read_bytes < 0) {
                close(client_socket);
                break;
            } else {

                char digit = buffer[0];

                cli.menuNumber(digit);


            }

        }

    }
}
