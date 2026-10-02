#include<iostream>
#include<unordered_map>
using namespace std;
int main(){
    vector<vector<int>> matrix;
    matrix=[[1,2,3,4],[5,6,7,8],[9,10,11,12]];

    for(int i=0;i<4;i++){
        cout<<matrix[i][0];

    }
    for(int j = 0;j<3;j++){
        cout<<matrix[3][j];
    }
    for(int i = 3;i>=0;i--){
        cout<<matrix[i][2];
    }
    for(int j = 0;j<3;j++){
        cout<<matrix[3][j];
    }
    
    
}