#include "Data_item.h"
#include<vector>
#include <iostream>
using namespace std;

 
   
    //set distance function 
    void Data_item :: setDistance(double dis)
    {
        distance=dis;
    }
    double Data_item :: getDistance()
    {
        return distance;
    }
    void Data_item :: addToVec(double number)
    {
        vec_data.push_back(number);
    }
    int Data_item :: getSizeVec()
    {
        return vec_data.size();
    }
    vector<double> Data_item :: getVec()

    {
        return vec_data;
    }
    void Data_item ::setClassify(string c)
    {
        classify=c;
    }
    string Data_item:: getClassify()
    {
        return classify;
    }
void Data_item::setVector(vector <double> v) {
    vec_data = v;
}



