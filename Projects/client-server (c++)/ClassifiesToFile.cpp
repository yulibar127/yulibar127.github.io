
#include "ClassifiesToFile.h"


ClassifiesToFile::ClassifiesToFile(int sock)
{
    description="download results";
    dio=new SocketIo(sock);


}
void ClassifiesToFile:: execute()
{
    string output = "";
    for(int i=0;i<classifies.size();i++)
    {
        string num=to_string(i+1);
        output = output + num + "    " + classifies[i] + "\n";

    }
    dio->write(output);
}
string ClassifiesToFile::getDescription() {
    return description;
}
void ClassifiesToFile::setClassifies(map<int, string> clsfy) {
    classifies = clsfy;
}
