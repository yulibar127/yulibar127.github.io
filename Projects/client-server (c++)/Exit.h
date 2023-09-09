//
// Created by yuli on 1/23/23.
//

#ifndef PROJECT4_EXIT_H
#define PROJECT4_EXIT_H
#include "Command.h"
#include "SocketIo.h"
class Exit: public Command{
private:
    int socket;
public:
    void execute();
    Exit(int sock);
    string getDescription();
};


#endif //PROJECT4_EXIT_H
