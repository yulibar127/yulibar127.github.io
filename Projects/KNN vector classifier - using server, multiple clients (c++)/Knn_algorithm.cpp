#include "Data_item.h"
//#include "Distance.h"
//#include "ChebyshevDistance.h"
#include "Knn_algorithm.h"


#include "MinkowskiDistance.h"

#include <vector>
#include <algorithm>
#include <string>
#include <map>
using namespace std;


Knn_algorithm::Knn_algorithm(int _k,vector<Data_item> vec,vector<double> _inputVector,Distance* d )
{   if(k<=vec.size())
    {k=_k;}
    else{
    
    
        k=vec.size();
    }
    inputVector=_inputVector;
    r_vec=vec;

    distance=d;
}
void Knn_algorithm:: setVectorDistances()
{
    
    (*distance).setVec1(inputVector);
    
  
    
    for(int i=0;i<r_vec.size();i++)
    {
    (*distance).setVec2(r_vec[i].getVec());
    
    
    double dou=(*distance).getDistance();
   
    r_vec[i].setDistance(dou);
    
    }
  
}

vector<Data_item> Knn_algorithm::sortVec()
{
    sort(r_vec.begin(),r_vec.end(),[](Data_item r1,Data_item r2){return r1.getDistance()<r2.getDistance();});
    
    return r_vec;

}



string Knn_algorithm::findClassify()
{
    map<string,int> k_classified;
    

    setVectorDistances();
    
    sortVec();
  

    vector<string> keys;
    
    for(int i=0;i<k;i++)
    {
       if(k_classified.find(r_vec[i].getClassify())!=k_classified.end())
       {
       
        k_classified.at(r_vec[i].getClassify())=k_classified.at(r_vec[i].getClassify())+1;
       }
       else{
        
        k_classified.insert({r_vec[i].getClassify(),1});
    
        keys.push_back(r_vec[i].getClassify());
        
       }

    }
     

  
  
    int maxCount=0;
    string maxClassified;
    for(int i=0;i<k_classified.size();i++)
    {
        if(k_classified.at(keys[i])>maxCount)
        {
            maxCount=k_classified.at(keys[i]);
            maxClassified=keys[i];
        }

    }
    
   
    return maxClassified;
    
}



