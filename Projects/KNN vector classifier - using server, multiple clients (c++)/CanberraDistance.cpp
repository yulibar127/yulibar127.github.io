#include "Distance.h"
#include "CanberraDistance.h"
#include <cmath>

   CanberraDistance::CanberraDistance(vector<double> v1,vector<double> v2)
   {
   vec1=v1;
   vec2=v2;
   }
    CanberraDistance::CanberraDistance()
   {

   }
   void CanberraDistance:: setVec1(vector<double> v)
   {
    vec1=v;
   }

   void CanberraDistance:: setVec2(vector<double> v)
   {
    vec2=v;
   }
    double CanberraDistance:: getDistance()
    {
    double dis = 0;
	for (int i = 0; i < vec1.size(); i++)
	{
        if((abs(vec1[i]) + abs(vec2[i]))==0)
        {
        dis=dis+0;
        }
        else{
		dis = dis + (abs(vec1[i] - vec2[i]) / (abs(vec1[i]) + abs(vec2[i])));
        }
	}
	return dis;
    

};