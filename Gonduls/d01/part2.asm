# Pierluigi's 2021 day1 part 2: read and parse integers from file,
# check if number is greater than third last number (storing in int[3] array): result++

.data

.eqv FILE_MAX_SIZE 100000		# used to set buffer size for reading, greatly exaggerated cause it works
.eqv NUMBERS_AMOUNT 3
.eqv NUMBERS_AMOUNT_BYTE 12	    # 3 * 4 bytes

NUM_ARRAY: .space NUMBERS_AMOUNT_BYTE 			
BUFFER: .space FILE_MAX_SIZE
FILE_NAME: .asciiz "input.txt"
PART2_SUCCESS: .asciiz "Here's your result for the second part:\n"

.text
##### READ FILE TO BUFFER #####
OPEN_FILE:
	li $v0, 13			        # 13 --> open_file
	la $a0, FILE_NAME		    # $a0 = address of null-terminated string containing filename
	li $a1, 0 			        # $a1 = flags, 0 for read-only
	li $a2, 0		            # $a2 = mode, mode is ignored
	syscall				        # file descriptor returned in $v0
	move $s7, $v0      		    # save the file descriptor in $s7

READ_FILE:
	li $v0, 14			        # 14 --> read_file
	move $a0, $s7			    # $a0 = file descriptor
	la $a1, BUFFER 			    # $a1 = address of input buffer
	li $a2, FILE_MAX_SIZE		# $a2 = maximum number of characters to read
	syscall				        # $v0 contains number of characters read (0 if end-of-file, negative if error).


########## PARSE INPUT ##########
# $t0 the ADDRESS of BUFF
# $t1 the ADDRESS of VECTOR
# $t2-$t4 actual temporary registers
# $t5 is the '\n'
# $t6 is the multiplier
# $t7 is the thing read and manipulated
# $s1 is the current integer
# $s2 is the index of three number array (0, 4, 8 values only)
# $s3 is the result

PARSE_START: 
	la $t0, BUFFER
	la $t1, NUM_ARRAY
	li $t5, '\n'
	li $t6, 1
	li $t9, 2147483647          # initialized with INT_MAX, never again used
	move $s1, $zero
    sw $t9, ($t1)
    sw $t9, 4($t1)
    sw $t9, 8($t1)
	move $s2, $zero
	move $s3, $zero

PARSE_CICLE:
	lb $t7, ($t0)
	beq $t5, $t7, FOUND_BACKSLASH_N	# every number is followed by a '\n'
	beq $zero, $t7, END_PARSE_CICLE	# '\x00' ends the string to parse
	subi $t7, $t7, 48		    # value of '0' in ASCII
    li $t3, 10
	mult $s1, $t3			    # multiply by 10 the prev partial number
	mflo $s1
	add $s1, $s1, $t7		    # add together the partial number and the digit we just got	
	addi $t0, $t0, 1		    # switch to next char
	j PARSE_CICLE

FOUND_BACKSLASH_N:				# finally we have found the full number
	addiu $t0, $t0, 1 		    # switch to next char
	
    addu $t3, $s2, $t1          # calculate address
    lw $t2, ($t3)               # get third last number stored in array
	
    
    bge $t2, $s1, NUMBER_NOT_INCREASED
    addi $s3, $s3, 1            # if current > third last: result ++
    


NUMBER_NOT_INCREASED:
    addu $t3, $s2, $t1          # calculate address
    sw $s1, ($t3)               # store int in correct position in array
    move $s1, $zero 		    # reset the value in $s1 so it can read a new number
	addi $s2, $s2, 4		    # switch to next index in the array modulo 3

    li $t4, 8                   
    bge $t4, $s2, INDEX_OK      # if index >= 8 (4*2): jump
    move $s2, $zero             # else: index = 0

INDEX_OK:
    j PARSE_CICLE	

END_PARSE_CICLE:
	li $v0, 4			        # 4 --> print_string
	la $a0, PART2_SUCCESS	    # $a0 = address of null-terminated string to print    
	syscall
	li $v0, 1			        # 1 --> print_int
	move $a0, $s3               # print result
	syscall	                    
    li $v0, 10      		    # End program
	syscall
