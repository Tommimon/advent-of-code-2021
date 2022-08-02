#include <stdio.h>

/**
 * @brief We don't really need the dice to go from 1 to 1000, 0 to 10 is just fine,
 * seen as the board is modulo 10, with 0 counting as 10 points.
 * I calculate the amount of steps as a mean:1st number + 2nd number + 3rd number = 2nd number * 3.
 * At the end the dice is updated.
 * 
 * @return 1
 */
int main(){
    int player1 = 6, player2 = 2;
    int turn = 0;
    int points1 = 0, points2 = 0;
    int dice = 1;

    while(points1 < 1000 && points2 < 1000){
        int moves = (dice + 1) * 3 % 10;
        if(turn % 2){
            player2 = (player2 + moves) % 10;
            if (!player2)
                player2 = 10;
            points2 += player2;

        } else {
            player1 = (player1 + moves) % 10;
            if (!player1)
                player1 = 10;
            points1 += player1;
        }
        dice = dice + 3 % 10;
        turn ++;
    }

    if(turn % 2)
        printf("Result part 1 = %d\n", turn * 3 * points2);
    else
        printf("Result part 1 = %d\n", turn * 3 * points1);
    return 1;
}