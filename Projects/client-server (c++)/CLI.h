
#ifndef PROJECT4_CLI_H
#define PROJECT4_CLI_H

#include "Command.h"
#include "UploadInfo.h"
#include "RunAlgo.h"
#include "SocketIo.h"
#include "GetInfo.h"
#include "ReturnClassifies.h"
#include "ClassifiesToFile.h"
#include "Exit.h"

namespace std {

class CLI {
private:
    Command** cmdArray;
    void initArray();
    int socket;
    DefualtIo *dio;
    string menu;


public:
    void start();

    explicit CLI(int sock);



    Command** getCmdArray();

    void menuNumber (char digit);
};

}


#endif //PROJECT4_CLI_H
