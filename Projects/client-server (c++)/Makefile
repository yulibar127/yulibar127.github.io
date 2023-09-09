OBJS	= mainServer.o CanberraDistance.o ChebyshevDistance.o MinkowskiDistance.o CLI.o UploadInfo.o GetInfo.o RunAlgo.o ReturnClassifies.o ClassifiesToFile.o Exit.o Data_item.o Knn_algorithm.o Server.o vectorFunctions.o clientMain.o Client.o SocketIo.o
OUT	= server.out,client.out

OBJS0	= mainServer.o CanberraDistance.o ChebyshevDistance.o MinkowskiDistance.o CLI.o UploadInfo.o GetInfo.o RunAlgo.o ReturnClassifies.o ClassifiesToFile.o Exit.o Data_item.o Knn_algorithm.o Server.o vectorFunctions.o SocketIo.o
SOURCE0	= mainServer.cpp CanberraDistance.cpp ChebyshevDistance.cpp MinkowskiDistance.cpp CLI.cpp UploadInfo.cpp GetInfo.cpp RunAlgo.cpp ReturnClassifies.cpp ClassifiesToFile.cpp Exit.cpp Data_item.cpp Knn_algorithm.cpp Server.cpp vectorFunctions.cpp SocketIo.cpp
HEADER0	= CanberraDistance.h ChebyshevDistance.h MinkowskiDistance.h CLI.h UploadInfo.h GetInfo.h RunAlgo.h ReturnClassifies.h ClassifiesToFile.h Exit.h Data_item.h Knn_algorithm.h Server.h vectorFunctions.h SocketIo.h Command.h DefaultIo.h
OUT0	= server.out

OBJS1	= clientMain.o Client.o
SOURCE1	= clientMain.cpp Client.cpp
HEADER1	= Client.h
OUT1	= client.out

CC	 = g++ -pthread
FLAGS	 = -g -c -Wall
LFLAGS	 = 

all: server.out client.out

server.out: $(OBJS0) $(LFLAGS)
	$(CC) -g $(OBJS0) -o $(OUT0)

client.out: $(OBJS1) $(LFLAGS)
	$(CC) -g $(OBJS1) -o $(OUT1)

mainServer.o: mainServer.cpp
	$(CC) $(FLAGS) mainServer.cpp

CanberraDistance.o: CanberraDistance.cpp
	$(CC) $(FLAGS) CanberraDistance.cpp

ChebyshevDistance.o: ChebyshevDistance.cpp
	$(CC) $(FLAGS) ChebyshevDistance.cpp

MinkowskiDistance.o: MinkowskiDistance.cpp
	$(CC) $(FLAGS) MinkowskiDistance.cpp

Distance.o: Distance.cpp
	$(CC) $(FLAGS) Distance.cpp

File.o: File.cpp
	$(CC) $(FLAGS) File.cpp

Data_item.o: Data_item.cpp
	$(CC) $(FLAGS) Data_item.cpp

Knn_algorithm.o: Knn_algorithm.cpp
	$(CC) $(FLAGS) Knn_algorithm.cpp

Server.o: Server.cpp
	$(CC) $(FLAGS) Server.cpp

vectorFunctions.o: vectorFunctions.cpp
	$(CC) $(FLAGS) vectorFunctions.cpp

clientMain.o: clientMain.cpp
	$(CC) $(FLAGS) clientMain.cpp

Client.o: Client.cpp
	$(CC) $(FLAGS) Client.cpp

CLI.o: CLI.cpp
	$(CC) $(FLAGS) CLI.cpp

SocketIo.o: SocketIo.cpp
	$(CC) $(FLAGS) SocketIo.cpp

UploadInfo.o: UploadInfo.cpp
	$(CC) $(FLAGS) UploadInfo.cpp

GetInfo.o: GetInfo.cpp
	$(CC) $(FLAGS) GetInfo.cpp

RunAlgo.o: RunAlgo.cpp
	$(CC) $(FLAGS) RunAlgo.cpp

ReturnClassifies.o: ReturnClassifies.cpp
	$(CC) $(FLAGS) ReturnClassifies.cpp

ClassifiesToFile.o: ClassifiesToFile.cpp
	$(CC) $(FLAGS) ClassifiesToFile.cpp

Exit.o: Exit.cpp
	$(CC) $(FLAGS) Exit.cpp

clean:
	rm -f $(OBJS) $(OUT)
