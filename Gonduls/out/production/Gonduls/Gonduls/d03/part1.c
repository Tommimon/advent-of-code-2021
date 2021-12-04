#include <stdio.h>
#include <stdlib.h>

#define MAX_LEN 12
#define INPUT "input.txt"


int main(){
    int totalNumbers, ones[MAX_LEN];
    FILE* input = fopen(INPUT, "r");
    char string[MAX_LEN + 1];
    int i;
    int gamma = 0, epsilon = 0;

    for ( i = 0; i < MAX_LEN; i++)
        ones[i] = 0;
    
    // counts all '1' in each position and keeps track of iterations done (so to know total number count)
    while( fscanf(input, "%s", string) != EOF){
        //printf("%s\n", string);
        for(i = 0; i< MAX_LEN; i++)
            ones[i] += string[i] - '0';
            //ones[i] += string[i] == '0' ? 0 : 1;
        
        totalNumbers ++;
    }

    fclose(input);
    totalNumbers /= 2; // needed to see if totalNumbers counted more or less '1' than half total numbers 

    for(i = 0; i< MAX_LEN; i++){
        // easy way to convert from binary to decimal
        gamma *= 2;
        epsilon *= 2;

        gamma += ones[i] > totalNumbers ? 1 : 0;
        epsilon += ones[i] < totalNumbers ? 1 : 0;
    }

    printf("Result part 1 = %d\n", gamma*epsilon);

    return 0;
}