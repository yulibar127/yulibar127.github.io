#include "Distance.h"
#include "MinkowskiDistance.h"

#include <cmath>

    MinkowskiDistance::MinkowskiDistance(vector<double> v1,vector<double> v2,int _p)

    {
        vec1=v1;
        vec2=v2;
        p=_p;

    }
    MinkowskiDistance::MinkowskiDistance(int _p)
    {
        p=_p;
    }

    void MinkowskiDistance::setVec1(vector<double> v)
   {
    vec1=v;
   }
   void MinkowskiDistance::setVec2(vector<double> v)
   {
    vec2=v;
   }
    double MinkowskiDistance::getDistance()
    {
    double dis = 0;
	for (int i = 0; i < vec1.size(); i++)
	{
		dis = dis + pow(abs(vec1[i] - vec2[i]), p);
	}
	return pow(dis, pow(p, -1));
    }

