#include "Client.h"
#include <cstring>
#include <vector>
#include <iostream>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <fstream>
#include <ctype.h>

using namespace std;

Client::Client(char* _ip,int _port)
{
    ip=_ip;
    port=_port;
    clientSock=socket(AF_INET,SOCK_STREAM,0);
    if(clientSock<0)
    {
        throw("The socket was not created\n");
    }
    if(clientConnect()==0)
    {
        throw("Couldn't connect to the server\n");
    }

}
int Client::clientConnect()
{
    struct sockaddr_in sin;
    memset(&sin, 0, sizeof(sin));
    sin.sin_family = AF_INET;
    sin.sin_addr.s_addr = inet_addr(ip);
    sin.sin_port = htons(port);
    if (connect(clientSock, (struct sockaddr *) &sin, sizeof(sin)) < 0) {
        //error
        return 0;
    }
    return 1;
}
void Client::clientSend() {
    thread thread1;

    while (true) {

        //in the beginning, receving the menu from the server
        char rcvBuffer[4096];
        for (char &i: rcvBuffer) { //initializing the array to null terminator
            i = '\0';
        }
        int expected_data_len = sizeof(rcvBuffer);
        int read_bytes = recv(clientSock, rcvBuffer, expected_data_len, 0);
        if (read_bytes == 0) {
            // connection is closed
            cout << "The server closed the connection\n";
            break;
        } else if (read_bytes < 0) {
            // error
            cout << "error in reciving\n";
            break;
        } else {
            cout << rcvBuffer << endl;
        }
        string data_string;

        getline(cin, data_string);

        if (data_string == "-1") {
            close(clientSock);
            break;
        }


        char sndBuffer[1];
        if (data_string == "1") {
            sndBuffer[0] = '1';
            int sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }
            if (!rcvInfo()) {
                break;
            }
            //reading the first csv file to a buffer and send it
            string data1;

            getline(cin, data1);

            string line1, word1;
            int j = 0;
            fstream file1(data1, ios::in);


            //char fileBuffer1[122880];
            string strBuffer1="";

            if (file1.is_open()) {
                while (getline(file1, line1)) {

                    stringstream ss(line1);

                    while (getline(ss, word1, ',')) {

                        int wordLength = word1.length();
                        for (int i = 0; i < wordLength; i++) {
                            //fileBuffer1[j] = word1.at(i);
                            strBuffer1+=word1.at(i);
                            j++;
                        }
                        //fileBuffer1[j] = ' ';
                        strBuffer1+=" ";
                        j++;

                    }
                    //fileBuffer1[j] = '\n';
                    strBuffer1+="\n";
                    // j++;

                }
                strBuffer1+="\0";
                //fileBuffer1[j] = '\0';
            }
            else {
                string invalid = "invalid input";
                char sndInvalid[invalid.length()+1];
                strcpy(sndInvalid, invalid.c_str());
                sent_bytes = send(clientSock, sndInvalid, strlen(sndInvalid), 0);
                if (sent_bytes < 0) {
                    // error
                }
                if (!rcvInfo()) {
                    break;
                }
                sndBuffer[0] = '9';
                sent_bytes = send(clientSock, sndBuffer, 1, 0);
                if (sent_bytes < 0) {
                    // error
                }
                continue;

            }
            int data_len = strBuffer1.length();
            char fileBuffer1[data_len+1];
            for (int i = 0; i < data_len+1; ++i) {
                fileBuffer1[i] = '\0';
            }
            strcpy(fileBuffer1, strBuffer1.c_str());


            sent_bytes = send(clientSock, fileBuffer1, strlen(fileBuffer1), 0);
            if (sent_bytes < 0) {
                // error
            }


            //receiving the info if the upload complete.
            if (!rcvInfo()) {
                break;
            }


            //reading the second csv file to a buffer and send it
            string data2;
            getline(cin, data2);

            string line2, word2;
            int wordsCounter = 0;
            int k = 0;
            fstream file2(data2, ios::in);
            bool spaceFlag = false;
            string strBuffer2="";
            //char fileBuffer2[122880];

            if (file2.is_open()) {

                while (getline(file2, line1)) {

                    stringstream ss(line1);


                    while (getline(ss, word1, ',')) {
                        wordsCounter++;

                        int wordLength = word1.length();

                        char data[wordLength + 1];
                        strcpy(data, word1.c_str());
                        //fileBuffer2[k] = data[0];


                        for (int i = 0; i < wordLength; i++) {
                            if (data[i] != ' ' && data[i] != '\r') {
                                //fileBuffer2[k] = data[i];
                                strBuffer2+=data[i];

                                k++;
                            }
                            else {
                                //fileBuffer2[k] = '\n';
                                strBuffer2+="\n";
                                k++;
                                spaceFlag = true;
                            }

                        }
                        if (!spaceFlag) {
                            //fileBuffer2[k] = ' ';
                            strBuffer2+=" ";
                            k++;
                        }

                    }
                    spaceFlag = false;


                }

                //fileBuffer2[k] = '\0';
                strBuffer2+="\0";
            }
            else {
                string invalid = "invalid input";
                char sndInvalid[invalid.length()+1];
                strcpy(sndInvalid, invalid.c_str());
                sent_bytes = send(clientSock, sndInvalid, strlen(sndInvalid), 0);
                if (sent_bytes < 0) {
                    // error
                }
                if (!rcvInfo()) {
                    break;
                }
                sndBuffer[0] = '9';
                sent_bytes = send(clientSock, sndBuffer, 1, 0);
                if (sent_bytes < 0) {
                    // error
                }
                continue;

            }
            data_len = strBuffer2.length();
            char fileBuffer2[data_len+1];
            for (int i = 0; i < data_len+1; ++i) {
                fileBuffer2[i] = '\0';
            }
            strcpy(fileBuffer2, strBuffer2.c_str());

            sent_bytes = send(clientSock, fileBuffer2, strlen(fileBuffer2), 0);
            if (sent_bytes < 0) {
                // error
            }

            //receiving the info if the upload complete.
            if (!rcvInfo()) {
                break;
            }

            sndBuffer[0] = '9';
            sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }

        } else if (data_string == "2") {
            sndBuffer[0] = '2';
            int sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }
            //receiving the updated values of the classifier
            if (!rcvInfo()) {
                break;
            }


            //getting the k and the distance metric from the user
            string data;
            getline(cin, data);
            if(data.length()!=0)
            {

                char data_addr[data.length() + 1];
                strcpy(data_addr, data.c_str());

                int data_len = strlen(data_addr);

                sent_bytes = send(clientSock, data_addr, data_len, 0);
                if (sent_bytes < 0) {
                    // error
                }

                //receiving the info if the input is invalid
                char rcvBuffer[4096];
                for (char &i: rcvBuffer) { //initializing the array to null terminator
                    i = '\0';
                }
                int expected_data_len = sizeof(rcvBuffer);
                int read_bytes = recv(clientSock, rcvBuffer, expected_data_len, 0);
                string s = rcvBuffer;

                if (read_bytes == 0) {


                    continue;
                } else if (read_bytes < 0) {
                    // error
                    cout << "error in reciving\n";
                    break;
                } else if (s != " ") {
                    cout << rcvBuffer << endl;
                }

            }
            else{
                sndBuffer[0] = '\n';
                sent_bytes = send(clientSock, sndBuffer, 1, 0);
                if (sent_bytes < 0) {
                    // error
                }
                char rcvBuffer[4096];
                for (char & i : rcvBuffer) { //initializing the array to null terminator
                    i = '\0';
                }

                int expected_data_len = sizeof(rcvBuffer);
                int read_bytes = recv(clientSock, rcvBuffer, expected_data_len, 0);
                if (read_bytes == 0) {
                    // connection is closed
                    cout<<"The server closed the connection\n";
                    break;
                }
                else if (read_bytes < 0) {
                    // error
                    cout<<"error in reciving\n";
                    break;
                }


            }
            sndBuffer[0] = '\n';
            sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }


        } else if (data_string == "3") {
            sndBuffer[0] = '3';
            int sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }
            //receiving the info if the classifying is complete
            if (!rcvInfo()) {
                break;
            }
            sndBuffer[0] = '9';
            sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }
        }

        else if (data_string == "4") {
            sndBuffer[0] = '4';

            int sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }


            //receiving the classifying list
            if (!rcvInfo()) {
                break;
            }
            sndBuffer[0] = '9';
            sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }
        }

        else if (data_string == "5") {
            sndBuffer[0] = '5';
            int sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }
            char rcvBuffer[4096];
            for (char & i : rcvBuffer) { //initializing the array to null terminator
                i = '\0';
            }
            int expected_data_len = sizeof(rcvBuffer);
            int read_bytes = recv(clientSock, rcvBuffer, expected_data_len, 0);
            string s = rcvBuffer;
            if (read_bytes == 0) {
                // connection is closed
                cout<<"The server closed the connection\n";
                break;
            }
            else if (read_bytes < 0) {
                // error
                cout<<"error in reciving\n";
                break;
            }
            else if (s == "please upload data" || s == "please classify the data") {
                cout << rcvBuffer << endl;
            }
            else {

                string path;
                getline(cin, path);

                thread1 = (thread(Client::handleRcvToFileThread, rcvBuffer, path));
                thread1.detach();

            }

            sndBuffer[0] = '9';
            sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }

        }
        else if (data_string == "8") {
            sndBuffer[0] = '8';
            int sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }
        }
        else {
            sndBuffer[0] = '9';
            int sent_bytes = send(clientSock, sndBuffer, 1, 0);
            if (sent_bytes < 0) {
                // error
            }
            cout << "invalid input" << endl;

        }
    }


}

bool Client::rcvInfo() {
    char rcvBuffer[4096];
    for (char & i : rcvBuffer) { //initializing the array to null terminator
        i = '\0';
    }

    int expected_data_len = sizeof(rcvBuffer);
    int read_bytes = recv(clientSock, rcvBuffer, expected_data_len, 0);
    if (read_bytes == 0) {
        // connection is closed
        cout<<"The server closed the connection\n";
        return false;
    }
    else if (read_bytes < 0) {
        // error
        cout<<"error in reciving\n";
        return false;
    }
    else
    {
        cout << rcvBuffer << endl;
        return true;
    }
}
void Client::handleRcvToFileThread(char rcvBuffer[], string path) {
    string s = rcvBuffer;

    ofstream outfile(path);
    outfile << s;
    outfile.close();


}
