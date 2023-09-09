#ifndef CANBERRADISTANCE_H
#define CANBERRADISTANCE_H
#include "Distance.h"
#include <vector>
// class of Canberra Distance that inheirt distance class
class CanberraDistance:public Distance{
    public:
    //constructor for canberra distance that get 2 vectors
    CanberraDistance(vector<double> v1,vector<double> v2);
    //constructor for canberra distance
    CanberraDistance();
    //set the first vector 
    void setVec1(vector<double> v);
    //set the second vector
    void setVec2(vector<double> v);
    //return the canberra distance between the two vectors
    double getDistance();



};
#endif