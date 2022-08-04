// reused part2-DoNotRun.c threadInfo structure because it worked
#include <stdio.h>
#include <stdbool.h>

typedef struct{
    long unsigned int multiplier;
    bool player1; // true means player 1, false means player 2 (is playing)
    short p1, p2; // two players position on the board
    short p1Score, p2Score;
} threadInfo;

long unsigned int p1Wins, p2Wins;

void turn(void * arg);

int main(){
    threadInfo info;
    info.multiplier = 1;
    info.p1 = 6;
    info.p2 = 2;
    info.player1 = true;
    info.p1Score = 0;
    info.p2Score = 0;
    p1Wins = 0;
    p2Wins = 0;
    turn(&info);
    printf("Score player 1: %ld\tScore player 2: %ld\n", p1Wins, p2Wins);
}

void turn(void * arg){
    threadInfo info = *(threadInfo *) arg;
    short playingPlayer, playingPlayerScore;
    short newMultiplier;

    for(int i = 3; i<10; i++){
        playingPlayer = info.player1 ? info.p1 : info.p2;
        playingPlayerScore = info.player1 ? info.p1Score : info.p2Score;

        playingPlayer = (playingPlayer + i) % 10;
        if (!playingPlayer)
            playingPlayer = 10;
        playingPlayerScore += playingPlayer;
        
        switch (i){
            case 3: case 9:
                newMultiplier = 1;
                break;
            case 4: case 8:
                newMultiplier = 3;
                break;
            case 5: case 7:
                newMultiplier = 6;
                break;
            case 6:
                newMultiplier = 7;
                break;       
        }
        // create new variable to pass onto new turn
        threadInfo newInfo;

        // update positions
        newInfo.p1 = info.player1 ? playingPlayer : info.p1;
        newInfo.p2 = info.player1 ? info.p2 : playingPlayer;

        // update score
        newInfo.p1Score = info.player1 ? playingPlayerScore : info.p1Score;
        newInfo.p2Score = info.player1 ? info.p2Score : playingPlayerScore;

        // udate multiplier and turn
        newInfo.multiplier = newMultiplier * info.multiplier;
        newInfo.player1 = !info.player1;

        if(newInfo.p1Score > 20){
            p1Wins += newInfo.multiplier;

        } else if(newInfo.p2Score > 20){
            p2Wins += newInfo.multiplier;

        } else {
            turn(&newInfo);
        }
    }
}