#include<iostream>
#include<string>
#include<algorithm>
#include<unordered_map>
using namespace std;
int main(){
    string s="listen";
    string sh="silent";
    
    sort(s.begin(),s.end());
    sort(sh.begin(),sh.end());
    if(s==sh){
        cout<<"yes";
    }
    else{
        cout<<"no";
    }
}