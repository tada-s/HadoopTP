#include <bits/stdc++.h>

using namespace std;

int random(){
    return rand();
}

int main()
{
    freopen("output.txt", "w", stdout);
    //user id,url,timestamp

    // Generate Username
    vector<string> username;
    for(int i = 0; i < 10; i++){
        char str[64];
        sprintf(str, "user%02d", i);
        username.push_back(str);
    }

    vector<string> urls;
    for(int i = 0; i < 10; i++){
        char str[64];
        int r = random()%5;
        switch(r){
            case 0:
                sprintf(str, "https://groups.google.com/forum/%d", random()%10);
                break;
            case 1:
                sprintf(str, "https://web.whatsapp.com/%d", random()%10);
                break;
            case 2:
                sprintf(str, "https://www.tutorialspoint.com/map_reduce/%d", random()%10);
                break;
            case 3:
                sprintf(str, "https://www.ibm.com/developerworks/%d", random()%10);
                break;
            case 4:
                sprintf(str, "https://facebook.com/%d", random()%10);
                break;
        }
        urls.push_back(str);
    }

    int rowNumber = 1000000;

    for(int i = 0; i < rowNumber; i++){
        char str[256];

        char strTimeStamp[64];
        int hora = random()%24;
        int minuto = random()%60;
        int segundo = random()%60;
        sprintf(strTimeStamp, "%04d/%02d/%02d %02d:%02d:%02d", 2017, 8, 29, hora, minuto, segundo);

        string usuario = username[random()%username.size()];
        string url = urls[random()%urls.size()];
        printf("%s,%s,%s\n", usuario.c_str(), url.c_str(), strTimeStamp);
        //printf("%")
    }
    //cout << "Hello world!" << endl;
    fclose(stdout);
    return 0;
}
