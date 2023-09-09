#include <iostream>
#include "Server.h"

using namespace std;

bool is_number(const string& s)
{
    string::const_iterator it = s.begin();
    while (it != s.end() && std::isdigit(*it)) ++it;
    return !s.empty() && it == s.end();
}

int main(int argc, char* argv[]) {
    if (argc != 2) {
        cout << "too many values in commandline" << endl;
        return 0;
    }
    vector<string> cmd;
    for(int i = 0; i < argc; i++)
    {
        cmd.push_back(argv[i]);
    }
    //starting the input checking
    if (cmd[0] != "./server.out") {
        cout << "invalid string has been inserted to the command line" << endl;
        return 0;
    }
    string portInput = cmd[1];
    int port = 0;
    if (is_number(portInput)) {
        port = stoi(cmd[1]);
        if (port < 0 || port > 65535) {
            cout << "invalid port number has been inserted to the command line" << endl;
            return 0;
        }
    }
    else {
        cout << "port is invalid" << endl;
        return 0;
    }


    Server server(port);
    server.receiveInfo();



    return 0;
}

