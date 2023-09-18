# KNN Vector Classifier

## General description of the KNN algorithm

the k-nearest neighbors algorithm, is a supervised learning classifier which uses proximity to make classifications
or predictions about the grouping of an individual data point.
## Description 
The aim of the project is to determine the classification
of the vector the server gets from the client.
It is done by choosing the most common classification
of the K nearest neighbors of the vector, calculated by a certain distance metric.</br> </br>
<b>The project is using threads ,sockets and design templates.</b> </br>

## Compilation and run
After you clone the files to a folder that is convenient for you,
open a terminal with a path to that folder, and write "make" in order to compile
the program. </br>
Then, write in the terminal "./server.out", then the port number
the server should be listening to. <span style="color: orange;">for example: ./server.out 12345.</span> </br>
Open another terminal, and write "./client.out", then the ip address of the server,
and then the port number the server is listening to. <span style="color: orange;">
for example: ./client.out 127.0.0.1 12345.</span>


## How to use
After you run the program, the server's menu will
be displayed in the client's terminal: </br>
1. <b>by pressing 1</b>, The server will wait for you
to send a path to a train file and a path to a test file. the test file should contain
   unclassified vectors which we want to classify with the help of the train file.</br>
2. <b>by pressing 2</b>, The server will send the client the current k (number of neighbors)
and distance metric. <div style="background-color: yellow; color: black;">The optional distance metrics are: </br>
<b>EUC</b> for euclidean </br>
<b>MAN</b> for manhattan </br>
<b>CHB</b> for chebyshev </br>
<b>CAN</b> for canbarra </br>
<b>MIN</b> for minkovsky </br> </div>
Then, you can insert new values. <span style="color: orange;">
   for example: "9 EUC"</span>.
you can also press enter and the values will stay the same.</br>
3. <b>by pressing 3</b>, The server will run the KNN algorithm and will classify the data
according to the current algorithm settings.</br>
4. <b>by pressing 4</b>, The server will display the results of the classification
that was made to the test file. if you'll press enter the server's menu
will be displayed again.</br>
5. <b>After pressing 5</b>, you'll need to enter a path to a file where you
want the results to be saved (if the file exists the results will be saved there,
if not, the server will create a new file).</br>
6. <b>by pressing 8</b>, the server will close the connection between the server and the client.</br>

