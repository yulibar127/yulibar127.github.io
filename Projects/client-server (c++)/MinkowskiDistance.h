#ifndef MINKOWSKIDISTANCE_H
#define MINKOWSKIDISTANCE_H
#include "Distance.h"
#include <vector>
//class of minkowski distance that inherit distance abstract class
class MinkowskiDistance:public Distance{
    private:
    int p;
    public:
    //constructor of minkowski distance that get two vectors and p
    MinkowskiDistance(vector<double> v1,vector<double> v2,int _p=2);
    //constructor of minkowski distance that get p
    MinkowskiDistance(int _p);
    //set the first vector
     void setVec1(vector<double> v);
     //set the second vector
     void setVec2(vector<double> v);
     //return the distance between the twi vectors
     double getDistance();
};
#endif 