
#ifndef PROJECT4_UPLOADINFO_H
#define PROJECT4_UPLOADINFO_H
#include "Command.h"
#include "Data_item.h"
#include "SocketIo.h"

namespace std {
    class UploadInfo: public Command {

    private:
        vector <Data_item> trainVec;
        vector <vector <double>> testVec;
    public:
        explicit UploadInfo(int sock);
        void execute();
        string getDescription();
        vector<Data_item> getTrainVec();
        vector<vector<double>> getTestVec();
    };
}

#endif //PROJECT4_UPLOADINFO_H
