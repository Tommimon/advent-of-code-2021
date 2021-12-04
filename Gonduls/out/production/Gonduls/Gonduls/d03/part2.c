#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_LEN 12
#define INPUT "input.txt"

// need linked list to store numbers (in strings)
typedef struct el{
    char digits[MAX_LEN + 1];
    struct el * next;
}number;
typedef number *pnumber;

int mostCommonBit(pnumber numbers, int index);
int leastCommonBit(pnumber numbers, int index);
pnumber newNumbers(pnumber old, int bitVal, int index, bool freeAll); // creates new list with relevant numbers, if freeAll frees old list

int main(){
    FILE* input = fopen(INPUT, "r");
    int i;
    char string[MAX_LEN + 1];
    int oxygen, Co2;
    
    pnumber head, cursor;
    pnumber validO2, validCo2;
    head = malloc(sizeof(number));
    cursor = head;

    // need to initialize like this to properly use cursor afterwards
    fscanf(input, "%s", head->digits);

    while(true){
        if(fscanf(input, "%s", string) != EOF){
            cursor->next = malloc(sizeof(number));
            cursor = cursor->next;
            strcpy(cursor->digits, string);

        } else{
            cursor->next = NULL;
            break;
        }


    }
    fclose(input);

    // initialized like this to "save" head, in loop lists are going to be freed everytime
    validO2 = newNumbers(head, mostCommonBit(head, 0), 0, false);
    validCo2 = newNumbers(head, leastCommonBit(head, 0), 0, false);

    // initialize binary to decimal conversion
    oxygen = validO2->digits[0] - '0';
    Co2 = 1 - oxygen;

    for(i = 1; i< MAX_LEN; i++){
        validO2 = newNumbers(validO2, mostCommonBit(validO2, i), i, true);
        validCo2 = newNumbers(validCo2, leastCommonBit(validCo2, i), i, true);

        // the digit I need is for sure in position 'i' in the corresponding list: I just discarded all other numbers creating the new list
        oxygen = oxygen * 2 + validO2->digits[i] - '0';
        Co2 = Co2 * 2 + validCo2->digits[i] - '0';
    }


    printf("Result part 2 = %d\n", oxygen* Co2);
    return 0;
}

int mostCommonBit(pnumber numbers, int index){
    int counter = 0, ones = 0;
    pnumber cursor = numbers;
    while(cursor != NULL){
        ones += cursor->digits[index] - '0';    // counts '1's
        counter ++;                             // counts total list lenght
        cursor = cursor->next;
    }
    // get most common bit:
    if(ones * 2 >= counter)
        return 1;
    return 0;
}

int leastCommonBit(pnumber numbers, int index){
    return (1 - mostCommonBit(numbers, index));
}

pnumber newNumbers(pnumber old, int bitVal, int index, bool freeAll){
    pnumber new, cursor, temp;

    // if there is a single element in list:
    if(old->next == NULL)
        return old;
    
    new = malloc(sizeof(number));
    new->next = NULL;
    cursor = new;
    
    
    while(old != NULL){

        if(old->digits[index] - '0' == bitVal){

            // needed to eliminate problems with initialization
            if(cursor->next != NULL)
                cursor = cursor->next;

            strcpy(cursor->digits, old->digits);
            cursor->next = malloc(sizeof(number));
        }    
        
        if(freeAll){
            temp = old;
            old = old->next;
            free(temp);
        } else
            old = old->next;
    }
    free(cursor->next); // created an extra number, not needed
    cursor->next = NULL;
    return new;
}