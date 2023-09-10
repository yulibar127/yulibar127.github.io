
#include "UploadInfo.h"
#include "Command.h"


    UploadInfo::UploadInfo(int sock){
        description = "upload an unclassified csv data file";
        dio = new SocketIo(sock);
}

string UploadInfo::getDescription() {
    return description;
}

void UploadInfo::execute() {
    dio->write("Please upload your local train CSV file.");
    string input1 = dio->read();
    if (input1 == "invalid input") {
        dio->write("invalid input");
        return;
    }
    char data_addr1[input1.length() + 1];
    strcpy(data_addr1, input1.c_str());

    Data_item item;
    vector<string> inputVector;
    vector<double> inputVectorInDouble;
    string pushStr = "";
    int i = 0;

    while (data_addr1[i] != '\0') {

        while (isdigit(data_addr1[i]) || data_addr1[i] == '.' || data_addr1[i] == '-') {
            pushStr += data_addr1[i];
            i++;

            if (data_addr1[i] == ' ') {
                inputVector.push_back(pushStr);
                pushStr = "";
                i++;
            }


        }
        if (!isValidDoubleVector(inputVector)) {
            dio->write("invalid input");
        } else {
            fromStringToDouble(inputVector, inputVectorInDouble);
            item.setVector(inputVectorInDouble);
        }


        while (data_addr1[i] != '\n') {
            pushStr += data_addr1[i];
            i++;
        }

        item.setClassify(pushStr);
        pushStr = "";
        i++;
        trainVec.push_back(item);
        while (!inputVector.empty()) { //emptying the input vector
            inputVector.pop_back();
        }
        while (!inputVectorInDouble.empty()) { //emptying the input vector in double
            inputVectorInDouble.pop_back();
        }
    }

    dio->write("Upload complete.\nPlease upload your local test CSV file.");

    string input2 = dio->read();
    if (input2 == "invalid input") {
        dio->write("invalid input");
        return;
    }
    char data_addr2[input2.length() + 1];
    for (int i = 0; i < strlen(data_addr2); i++) {
        data_addr2[i] = '\0';
    }
    strcpy(data_addr2, input2.c_str());


    int j = 0;
    while (data_addr2[j] != '\0') {
        if (data_addr2[j] == '\r') {
            j++;
        }
        while (isdigit(data_addr2[j]) || data_addr2[j] == '.' || data_addr2[j] == '-') {
            pushStr += data_addr2[j];
            j++;
        }
            if (data_addr2[j] == ' ') {
                inputVector.push_back(pushStr);
                pushStr = "";
                j++;
            }
            if (data_addr2[j] == '\n') {
                inputVector.push_back(pushStr);
                pushStr = "";
                j++;
                if (!isValidDoubleVector(inputVector)) {
                    dio->write("invalid input");
                } else {
                    fromStringToDouble(inputVector, inputVectorInDouble);
                    testVec.push_back(inputVectorInDouble);
                }
                while (!inputVector.empty()) { //emptying the input vector
                    inputVector.pop_back();
                }
                while (!inputVectorInDouble.empty()) { //emptying the input vector in double
                    inputVectorInDouble.pop_back();
                }
            }
            else if (data_addr2[j] == '\0') {

                pushStr = "";
                if (!isValidDoubleVector(inputVector)) {
                    dio->write("invalid input");
                } else {
                    fromStringToDouble(inputVector, inputVectorInDouble);
                    testVec.push_back(inputVectorInDouble);
                }
            }

        }

    dio->write("Upload complete.");



    }

vector<Data_item> UploadInfo::getTrainVec() {
    return trainVec;
}
vector<vector<double>> UploadInfo::getTestVec() {
    return testVec;
}
