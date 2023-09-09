#include "vectorFunctions.h"
#include <iostream>
#include <istream>
#include <sstream>
#include <string>
#include <vector>
#include <ctype.h>
#include <bits/stdc++.h>
#include <math.h>

void inputToVector(vector<string>& v) {
    string input, sepInput;

    getline(cin, input); // getting the input of the vector
    stringstream ss(input);

    while (getline(ss, sepInput, ' '))
    {
        v.push_back(sepInput);
    }
return;
}

bool isValidDoubleVector (vector<string>& v) {
    if (v.empty()) {
        cout << "error - empty input" << endl;
        return false;
    }
    int signFlag = 0;
    for (auto i = v.begin(); i != v.end(); i++) {
        if ((*i).at(0) == '-') { // checking if the number is negative or positive, if negative - the sign flag is on
            signFlag = 1;
        }
        if ((*i).size() > 300) // checking if the number is over 300 digits. if it is, returns error message
        {

            cout << "error - entered number is over 300 digits";
            return false;
        }
        if (signFlag == 0 && (*i).at(0) ==
                             '.') { // checking if the first char of the string is ".". if it is, it adds 0 before the period.
            *i = "0" + (*i);
        }
        if ((*i).size() >= 2) {
            if (signFlag == 1 && (*i).at(1) ==
                                 '.') { // checking if the first char of the string is ".". if it is, it adds 0 before the period.
                // this case is if the number is negative
                *i = (*i).insert(1, "0");
            }
        }
        if ((*i).size() == 2) {
            if (signFlag == 0 && ((*i).at(1)) ==
                                 '.') // checking if the number ends in '.'. if it is, it adds 0 after it (in case the number is positive)
            {

                *i = (*i) + "0";
            }
        }
        if((*i).at((*i).size() - 1) == '.') {
            *i = (*i) + "0";
        }

        bool dotFlag = false;
        for (int j = 0; j < (*i).size(); j++) {
            if (j == 0 && signFlag == 1 && (*i).size() > 1) { // skipping the minus sign if the number is negative
                continue;
            }

            if ((*i).at(j) == '.' && !dotFlag) {
                dotFlag = true;
                continue;
            }
            if ((*i).at(j) == '.' && dotFlag) {
                cout << "number has more than one dot" << endl;
                return false;
            }


            if (j == 1 && (signFlag == 0) && (((*i).at(j)) ==
                                              '.')) // if we are standing in the second character and it's ".", it's valid and we can continue the loop
                continue;
            if ((*i).at(j) == 'E' && (*i).at(j+1) == '-' && isdigit((*i).at(j+2))) {
                int digit = (int)((*i).at(j+2));
                double num = pow(10, (-1) * digit);
                string numToString = to_string(num);
                string withoutZeroAndDot = numToString.substr(2);
                (*i).replace(j, 3, withoutZeroAndDot);

            }


            if (!(isdigit(
                    (*i).at(j)))) { // checking if all the characters (beside potential dot) are ints. if not returns with error output
                cout << "invalid value has been inserted" << endl;
                return false;
            }
        }


    }
    return true;

}
void fromStringToDouble(const vector<string> &v1, vector<double> &v2) {

    for (auto i = v1.begin(); i != v1.end(); i++) {
        double val1 = stod((*i));
        v2.push_back(val1);
    }
    return;
}

void printVector(const vector<double>& v) {
    for (auto v1 = v.begin(); v1 != v.end(); v1++) // a loop that printing the first vector
    {
        if (*v1 == (int)(*v1)) // check if the number is an integer. if not - prints 8 digits after the point
        {

            cout << *v1 << " ";
        }
        else
        {
            fixed(cout);
            cout.precision(8);
            cout << *v1 << " ";
        }
    }
    cout << "\n";
}