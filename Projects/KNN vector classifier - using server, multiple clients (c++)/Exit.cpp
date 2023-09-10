//
// Created by yuli on 1/23/23.
//

#include "Exit.h"
Exit::Exit(int sock) {
    dio = new SocketIo(sock);
    description = "exit";
    socket = sock;
}

void Exit::execute() {
    close(socket);
}
string Exit::getDescription() {
    return description;
}