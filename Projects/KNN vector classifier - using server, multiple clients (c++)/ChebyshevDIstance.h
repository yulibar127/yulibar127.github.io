#ifndef CHEBYSHEVDISTANCE_H
#define CHEBYSHEVDISTANCE_H
#include <vector>
#include "Distance.h"

//class of Chebyshev Distance that inheirt distance class
class ChebyshevDistance:public Distance{
    
    public:
    //constructor for Chebyshev Distance that get two vectos
    ChebyshevDistance(vector<double> v1,vector<double> v2);
    //defualt constructor for Chebyshev Distance
    ChebyshevDistance();
    //set the first vector
     void setVec1(vector<double> v);
      //set the second vector
     void setVec2(vector<double> v);
     //return the distance between the two vectors
     double getDistance();
    
     };
     #endif