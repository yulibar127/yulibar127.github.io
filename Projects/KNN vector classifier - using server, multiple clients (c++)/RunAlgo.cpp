#include "RunAlgo.h"
RunAlgo::RunAlgo(int sock) {

    description = "classify data";
dio = new SocketIo(sock);


}
void RunAlgo:: execute()
{


 classifies[0].clear();
//classifies[0].append(knn.findClassify());
for(int i=0;i<unclassifiedVec.size();i++)
{
    classifies[i].clear();
    Knn_algorithm knn(k,classifiedVec,unclassifiedVec[i],distanceMetric);
   
    classifies[i].append(knn.findClassify());
    

}

dio->write("classifying data complete");
}
map<int,string> RunAlgo::getMap()
{
    return classifies;
}
string RunAlgo::getDescription() {
    return description;
}

void RunAlgo::setClassifiedVec(vector<Data_item> cVec) {
    classifiedVec = cVec;
}
void RunAlgo::setUnclassified(vector<vector<double>> ucVec) {
    unclassifiedVec = ucVec;
}

void RunAlgo::setK(int n) {
    k = n;
}
void RunAlgo::setDistanceMetric(string distance) {
    if(distance=="AUC")
    {
        //MinkowskiDistance min(2);
        distanceMetric=new MinkowskiDistance(2);

    }
    else if(distance=="MAN")
    {
        //MinkowskiDistance min(1);
        distanceMetric=new MinkowskiDistance(1);

    }
    else if(distance=="CAN")
    {
        // CanberraDistance can;
        distanceMetric=new CanberraDistance();

    }
    else if(distance=="CHB")
    {
        //ChebyshevDistance chb;
        distanceMetric=new ChebyshevDistance();

    }
    else
    {
        //MinkowskiDistance min(2);
        distanceMetric=new MinkowskiDistance(2);

    }
}

