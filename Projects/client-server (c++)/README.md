# project4

## Description 
The aim of the project is to work with threads,sockets and design templates </br>
The server has 6 diffrent commands: </br>
1.The client send the server the content of two files-the train and the test file and the server upload them</br>
2.The server sent the client the current k and distance values, The client can set this values</br>
3.The server run knn algorithm about this files </br>
4.The server return the classifications after knn algorithm</br>
5.The client saves the data in file</br>
8.close the connection between the server and the client</br>

## compilation and run
The compiler: g++
version: 11

## Instruction
Open the Terminal and write: git clone https://github.com/shanisapago/project4
then write make </br>
then in one terminal, write ./server.out <port> </br>
and in another terminal, write ./client.out <ip> <port> </br>



## Implementation details
## client
### Client.h
Header file for Client.cpp </br>

### Client.cpp
Class for client </br>
The client has ip,port and client socket as members </br>
The client trying to connect the server with the ip and port he get, if the connection is not succeed or the socket creation is not succeed, it will print "error while creating the socket\n" and exit the program </br>
The client get input from the user and sending him to the server </br>
Then, the client receive data from the server and print him </br>
This happen until the user input -1 and then the client close the connection with the server </br>
In the ClientObject.cpp we have the constructor, private function to connect to the socket and public function that sending data from the user to the server and receiving data from the server </br>

### clientMain.cpp
This is the main of the client code, there is a check if the ip and the port is legal ,create client object and calling the function that sending data to the server and receiving data from him </br>
If the port or the ip is not legal, it will print "invalid input" and end the program</br>

Is_number </br>
This function get a string and checking if the string is a number, it iterating each char and check if it a digit using isdigit function </br>

isLegalIp </br>
this function get a string and checking if it is a legal ip, the function using strtok with delim of dot to check each string between 2 dots
if it is a number(using Is_number function) and if the number is less then 256 and more than -1 the function check if there was exactly 4 strings between two dots </br>
reminder: legal ip is a ip in the format a.b.c.d such that a,b,c,d are numbers that between 0 to 255 </br>

## server
### Server.h
Header file for Server.cpp </br>

### Server.cpp
Class for server </br>
The server get from the user a string that needs to be in the next format: vector distance k </br>
such that the vector are double numbers, the distance is one of the distances function(AUC,MAN,MIN,CAN,CHB) and the k is numbers of neighbors to classify </br>
if the server get a diffrent string format, it will send to the client invalid input </br>
The server classify the vector and send the classification to the client </br>
if a connection with a client is close,the server accept another client </br>

### mainServer.cpp
This is the main of the server code, there is a check if the port is legal, create server object and calling the function that recive and send data to the client </br>
## classes
### DefualtIo.cpp
abstract class for the Io</br>
has read and write function</br>

### SocketIo.cpp
class that derived from DefualtIo class</br>
write function send data to socket and read function recv data from the socket</br>

### Command.h
abstract class of command</br>
has a virtual function named execute</br>
### UploadInfo.cpp
class for command 1</br>
the command has execute function that get from the client content of train file and content of test file</br>

### GetInfo.cpp
class for command 2</br>
the command has execute function that call write function(function of DefualtIo class) with the k and distance metric</br>
In addition, the command may set the k and the distance metric if needed</br>

### RunAlgo.cpp
class for command 3</br>
the command has execute function that run knn algorithm about the files </br>

### ReturnClassification.cpp
class for command 4</br>
the command has execute function that write the classification for each row in the file</br>

### ClassifiesToFile.cpp
class for command 5</br>
the command has execute function that write the classification for each row in the file to a txt file </br>
the user need to input a path for the txt file</br>

### Exit.cpp
class for command 8</br>
the command has execute function that close the socket with a client</br>

### Cli.cpp
class for the menu</br>
the cli init the commands in the start function</br>
the cli has a menuNumber function that get a char from the client and execute the right command</br>
### class Distance
abstract class. </br>
the getDistance is a abstract function and in addition there are function to set the vectors of the distance. </br>
 
### class MinkowskiDistance,CanberraDistance and ChecyshevDistance
classes that inherit from Distance class </br>
each of them excute it depending of the distance. </br>

### class Data_item
class for every line of the file. </br>
this class saves a vector of data,classify and distance for every data item from the file. </br>

### class File
class for file. this class reading the data from the file and save it to vector of Data_item </br>

### class Knn_algorithm
this class is for the knn algorithm. </br>
First, it will set all distances of the Data item of the vector, with the user vector and the user distance </br>
later, it will sort the vector of the data_item by their distances </br>
Finally, it will save for each classify the number of times it was saved and return the classify that return the most from the first k data item distances </br>
