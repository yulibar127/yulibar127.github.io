#include "Distance.h"
#include "ChebyshevDIstance.h"
#include <cmath>
#include <iostream>
using namespace std;


    ChebyshevDistance::ChebyshevDistance(vector<double> v1,vector<double> v2)

    {
        vec1=v1;
        vec2=v2;

    }
    ChebyshevDistance::ChebyshevDistance()
    {

    }
    void ChebyshevDistance:: setVec1(vector<double> v)
   {
    vec1=v;
   
   }
   void ChebyshevDistance:: setVec2(vector<double> v)
   {
    vec2=v;
   
   }
    double ChebyshevDistance:: getDistance()
    {
        
       
    double maxDis = 0;
	for (int i = 0; i < vec1.size(); i++)
	{
		if (maxDis < abs(vec1[i] - vec2[i]))
		{
			maxDis = abs(vec1[i] - vec2[i]);
		}
	}
	return maxDis;
    }
 

