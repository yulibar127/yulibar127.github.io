#include "SocketIo.h"

SocketIo::SocketIo(int sock)
{
    socketClient=sock;

    for (char & i : buffer) { //initializing the array to null terminator
        i = '\0';
    }
}
string SocketIo::read()
{

    for (char & i : buffer) { //initializing the array to null terminator
        i = '\0';
    }
    int expected_data_len = sizeof(buffer);
    int read_bytes = recv(socketClient, buffer, expected_data_len, 0);
    if(read_bytes>=expected_data_len)
    {

        string s="";
        while(read_bytes>=expected_data_len)
        {

            s+=buffer;
            for (char & i : buffer) { //initializing the array to null terminator
                i = '\0';
            }
            read_bytes = recv(socketClient, buffer, expected_data_len, 0);

        }
        s+=buffer;
        return s;


    }
    if (read_bytes == 0) {
        return "";
    }
    else if(read_bytes<0)
    {
        close(socketClient);
    }
    else{
        string s=buffer;
        return s;

    }
}
void SocketIo::write(string st)
{
    int data_len = st.length();
    char data[data_len+1];
    for (int i = 0; i < data_len+1; ++i) {
        data[i] = '\0';
    }
    strcpy(data, st.c_str());

    int sent_bytes = send(socketClient, data, data_len, 0);

    if (sent_bytes < 0) {
        close(socketClient);
    }

}
