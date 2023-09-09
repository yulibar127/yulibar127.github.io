#ifndef RUNALGO_H_
#define RUNALGO_H_
#include "Command.h"
#include "Data_item.h"
#include "Distance.h"
#include "MinkowskiDistance.h"
#include "CanberraDistance.h"
#include "ChebyshevDIstance.h"
#include "Knn_algorithm.h"  
//#include "StandartIo.h"
#include <vector>
#include <map>
#include "SocketIo.h"
class RunAlgo:public Command{
private:
vector<Data_item> classifiedVec;
vector<vector<double>>unclassifiedVec;
map<int,string> classifies;
int k;
Distance *distanceMetric;
public:
RunAlgo(int sock);
void execute();
map<int,string> getMap();
string getDescription();
void setClassifiedVec(vector<Data_item> cVec);
void setUnclassified(vector<vector<double>> ucVec);
void setK(int n);
void setDistanceMetric(string distance);



};
#endif