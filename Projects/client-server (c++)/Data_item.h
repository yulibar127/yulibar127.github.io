#ifndef DATE_ITEM_H
#define DATE_ITEM_H
#include <vector>
#include<iostream>
using namespace std;
//class for data item, every item has a vector of data,classify and distance
class Data_item
{
    private:
    vector<double> vec_data;
    string classify;
    double distance;


    public:
    //set the distance of this data item to new distance 
    void setDistance(double dis);
    //return distance of data item 
    double getDistance();
    //add number to the data vector
    void addToVec(double number);
    //return the size of data vector
    int getSizeVec();
    //return the data vector
    vector<double> getVec();
    //set the classify of the vector
    void setClassify(string c);
    //return the classify of the vector
    string getClassify();
    void setVector(vector<double> v);



};


#endif