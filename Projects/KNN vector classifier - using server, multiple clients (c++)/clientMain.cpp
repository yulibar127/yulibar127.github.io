#include "Client.h"
#include <iostream>
#include <string.h>
using namespace std;

//function that get a string and return if it's a number or not
bool is_number(const string& s)
{
    string::const_iterator it = s.begin();
    while (it != s.end() && std::isdigit(*it)) ++it;
    return !s.empty() && it == s.end();
}

//function that get a string and return if it's legal ip
bool isLegalIp(string ip)
{
    char delim[] = ".";
    int counter=0;



   char char_ip[ip.length()+1];
   strcpy(char_ip,ip.c_str());

  // tokenize str in accordance with delim
  char *token = strtok(char_ip,delim);

  // loop until strtok() returns NULL
  while (token)  {

  
 
   if(!is_number(token))
   {
    return false;
   }
   else if(stoi(token)>255||stoi(token)<0)
   {
    return false;
   }
   
    // take subsequent tokens
    token = strtok(NULL,delim);
    counter++;
  }
  if(counter==4)
  {
    return true;
    }
    return false;

}

int main(int argc,char** argv)
{
    if(argc!=3)
    {
        cout<<"invalid input"<<endl;
        return 0;
    }
    
    if(!is_number(argv[2]))
    {
       cout<<"invalid input"<<endl;
        return 0;
    }
    string ip=argv[1];
    int port=stoi(argv[2]);
   
    if(isLegalIp(ip)==0||port<0||port>65535)
    {
      cout<<"invalid input"<<endl;
        return 0;
    }
     char char_ip[ip.length()+1];
    strcpy(char_ip,ip.c_str());
    try
    {
         
    Client client(char_ip,port);
    client.clientSend();
    }
    catch(const std::exception& e)
    {
        cout<<"error while creating the socket\n";
    }
   

    return 0;
}