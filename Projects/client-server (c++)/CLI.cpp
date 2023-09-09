
#include "CLI.h"
namespace std {

    CLI::CLI(int sock):

            cmdArray(new Command*[9]),
            dio(new SocketIo(sock)),
            socket(sock)
            {}

void CLI::start() {

    initArray();
    menu = "Welcome to the KNN Classifier Server. Please choose an option:\n"
                   "1. " + cmdArray[1]->getDescription() + '\n' + "2. " + cmdArray[2]->getDescription() + '\n' +
                   "3. " + cmdArray[3]->getDescription() + '\n' + "4. " + cmdArray[4]->getDescription() + '\n' +
                   "5. " + cmdArray[5]->getDescription() + '\n' + "8. " + cmdArray[8]->getDescription();
    dio->write(menu);

}
void CLI::initArray() {

    cmdArray[1] = new UploadInfo(socket);
    cmdArray[2] = new GetInfo(socket);
    cmdArray[3] = new RunAlgo(socket);
    cmdArray[4] = new ReturnClassifies(socket);
    cmdArray[5] = new ClassifiesToFile(socket);
    cmdArray[8] = new Exit(socket);

}

Command** CLI::getCmdArray() {
    return cmdArray;
}

void CLI::menuNumber(char digit) {
    if (digit == '1') {
        cmdArray[1]->execute();
    }
    else if (digit == '2') {
        cmdArray[2]->execute();
    }
    else if (digit == '3') {
        UploadInfo* info1 = dynamic_cast<UploadInfo*>(cmdArray[1]);
        if (info1->getTrainVec().empty()) {
            dio->write("please upload data");
        }
        else {
            GetInfo *info2 = dynamic_cast<GetInfo *>(cmdArray[2]);
            RunAlgo *info3 = dynamic_cast<RunAlgo *>(cmdArray[3]);
            info3->setClassifiedVec(info1->getTrainVec());
            info3->setUnclassified(info1->getTestVec());
            info3->setK(info2->getK());
            info3->setDistanceMetric(info2->getDistanceMetric());
            cmdArray[3]->execute();
        }


    }
    else if (digit == '4') {
        UploadInfo* info1 = dynamic_cast<UploadInfo*>(cmdArray[1]);
        RunAlgo *info3 = dynamic_cast<RunAlgo *>(cmdArray[3]);
        ReturnClassifies *info4 = dynamic_cast<ReturnClassifies *>(cmdArray[4]);
        if (info1->getTrainVec().empty()) {
            dio->write("please upload data");
        }

        else if (info3->getMap().empty()) {
            dio->write("please classify data");
        }
        else {
            info4->setClassifies(info3->getMap());
            cmdArray[4]->execute();
        }
    }
    else if (digit == '5') {
        UploadInfo* info1 = dynamic_cast<UploadInfo*>(cmdArray[1]);
        RunAlgo *info3 = dynamic_cast<RunAlgo *>(cmdArray[3]);
        ClassifiesToFile *info5 = dynamic_cast<ClassifiesToFile *>(cmdArray[5]);
        if (info1->getTrainVec().empty()) {
            dio->write("please upload data");
        }

        else if (info3->getMap().empty()) {
            dio->write("please classify data");
        }
        else {
            info5->setClassifies(info3->getMap());
            cmdArray[5]->execute();
        }
    }
    else if (digit == '8') {
        cmdArray[8]->execute();
        return;
    }
    else {
        dio->write(menu);
        return;
    }
    dio->read();
    dio->write(menu);
}
}
