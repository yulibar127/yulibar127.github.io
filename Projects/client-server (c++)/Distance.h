#ifndef DISTANCE_H_
#define DISTANCE_H_
#include <vector>
using namespace std;
//abstract class of distance, has 2 vectors
class Distance{
    protected:
    vector<double> vec1;
    vector<double> vec2;

    public:
    //abstract function that return the distance
    virtual double getDistance()=0;
    //set first vector
    virtual void setVec1(vector<double> v)=0;
    //set second vector
    virtual void setVec2(vector<double> v)=0;
    
    
   
};
#endif