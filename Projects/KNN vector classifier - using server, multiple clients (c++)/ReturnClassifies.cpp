#include "ReturnClassifies.h"

ReturnClassifies::ReturnClassifies(int sock)
{
description="display results";
dio=new SocketIo(sock);


}
void ReturnClassifies:: execute()
{
string output = "";
for(int i=0;i<classifies.size();i++)
{

    string num=to_string(i+1);
    int size = classifies[i].size();
    if (classifies[i].at(size - 1) == ' ' || classifies[i].at(size - 1) == '\r') {
        classifies[i].pop_back();
    }
    output = output + num + "    " + classifies[i] + "\n";

}
output = output + "Done";
dio->write(output);
}
string ReturnClassifies::getDescription() {
    return description;
}
void ReturnClassifies::setClassifies(map<int, string> clsfy) {
    classifies = clsfy;
}
