#ifndef KNN_ALGORITHM_H
#define KNN_ALGORITHM_H
#include <vector>
#include "Data_item.h"
#include "Distance.h"
using namespace std;
//class of knn algorithm 
class Knn_algorithm{
private:
int k;
vector<double> inputVector;
vector<Data_item> r_vec;
Distance* distance;

public:
//constructor of the class 
Knn_algorithm(int _k,vector<Data_item> vec,vector<double> _inputVector,Distance* d );
//set the distance for every Data item with the input vector 
void setVectorDistances();
//sort vector of data item
vector<Data_item> sortVec();
//return the classify of the input vector
string findClassify();


};

#endif
