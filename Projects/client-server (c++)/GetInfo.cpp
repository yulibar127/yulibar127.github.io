#include "GetInfo.h"

using namespace std;
void GetInfo::execute(){
 //StandartIo stdio;
 //dio=&stdio;
 string kString = to_string(k);
 string s = "The current KNN parameters are: K = " + kString + ", distance metric = " + distanceMetric;
dio->write(s);
string input1 = dio->read();

char data_addr1[input1.length() + 1];
strcpy(data_addr1, input1.c_str());
string pshstr1="";
string pshstr2="";
int i=0;
string error=" ";
if(data_addr1[0] != '\n')
{
while(data_addr1[i]!=' '&&isdigit(data_addr1[i]))
{
    pshstr1+=data_addr1[i];
    i++;
}
if(data_addr1[i]==' ')
{
    
  string::const_iterator it = pshstr1.begin();
    while (it != pshstr1.end() && std::isdigit(*it)) ++it;
    if(!pshstr1.empty() && it == pshstr1.end())
    {
        

       
    }
    else
    {
       
       if(error==" ")
       {
        error="";
       }
        error+="invalid value for K\n";
    }
    i++;

    for(int j=i;j<strlen(data_addr1);j++)
    {
        pshstr2+=data_addr1[j];

    }

    if(pshstr2=="AUC"||pshstr2=="MAN"||pshstr2=="MIN"||pshstr2=="CAN"||pshstr2=="CHB")
    {
        

    }
    else
    {

        if(error==" ")
       {
        error="";
       }
        error+="invalid value for metric\n";
    }
}
else{
    if(error==" ")
    {
        error="";
    }
   error+="invalid value for K\n";
   while(i!=strlen(data_addr1)&&data_addr1[i]!=' ')
   {
    i++;
   }
   if(i==strlen(data_addr1))
   {
    
        if(error==" ")
       {
        error="";
       }
    error+="invalid value for metric\n";
   }
   else
   {
   

    for(int j=i+1;j<strlen(data_addr1);j++)
        {
            pshstr2+=data_addr1[j];
        }
    if(pshstr2=="AUC"||pshstr2=="MAN"||pshstr2=="MIN"||pshstr2=="CAN"||pshstr2=="CHB")
    {
        

    }
    else
    {
       
            if(error==" ")
       {
        error="";
       }
        error+="invalid value for metric\n";
    }
   }
   
}
    if (error == " ") {
        setK(stoi(pshstr1));
        setDistanceMetric(pshstr2);

    }
    else {
        error.pop_back();
    }
    dio->write(error);
}
else{
    dio->write(" ");
    return;
}

}
GetInfo::GetInfo(int sock)
{

    description="algorithm settings";
    //StandartIo stdio;
    dio=new SocketIo(sock);
    k=5;
    distanceMetric="AUC";
    
}
void GetInfo::setK(int newK)
{
    k=newK;
}
void GetInfo::setDistanceMetric(string dm)
{
    if(dm=="AUC"||dm=="MAN"||dm=="MIN"||dm=="CAN"||dm=="CHB")
    {
        distanceMetric=dm;
    }

   
}

string GetInfo::getDescription() {
    return description;
}

string GetInfo::getDistanceMetric() {
    return distanceMetric;
}

int GetInfo::getK() {
    return k;
}
