# Pierluigi's 2021 day1 part 1: read and parse integers from file,
# check if number is greater than previous number: result++

.data

.eqv FILE_MAX_SIZE 100000		# used to set buffer size for reading, greatly exaggerated cause why not

BUFFER: .space FILE_MAX_SIZE
FILE_NAME: .asciiz "input.txt"
WELCOME_STRING: .asciiz "Welcome to me copying (again) Riccardo's first MIPS program ever attempting to solve day 1 of Advent Of Code 2020!\n"
PART1_SUCCESS: .asciiz "Here's your result for the first part:\n"

.text
WELCOME:
	li $v0, 4			        # 4 --> print_string
	la $a0, WELCOME_STRING		# $a0 = address of null-terminated string to print    
	syscall

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
# $t5 is the '\n'
# $t6 is the multiplier
# $t7 is the thing read and manipulated
# $s1 is the current integer
# $s2 is the previous integer
# $s3 is the result
PARSE_START: 
	la $t0, BUFFER
	li $t5, '\n'
	li $t6, 1
	move $s1, $zero
	li $s2, 2147483647          # initialized with INT_MAX
	move $s3, $zero

PARSE_CICLE:
	lb $t7, ($t0)
	beq $t5, $t7, FOUND_BACKSLASH_N	# every number is followed by a '\n'
	beq $zero, $t7, END_PARSE_CICLE	# '\x00' ends the string to parse
	subi $t7, $t7, '0'		    # distance to '0': conversion int(digit)
    li $t1, 10
	mult $s1, $t1			    # multiply by 10 the prev partial number
	mflo $s1
	add $s1, $s1, $t7		    # add together the partial number and the digit we just got	
	addi $t0, $t0, 1		    # switch to next char
	j PARSE_CICLE

FOUND_BACKSLASH_N:				# finally we have found the full number
#	li $v0, 1			        # 1 --> print_int
#	move $a0, $s1
#	syscall				        # print the item just added
#   li $v0, 11                  # 11 --> print_byte
#	li $a0, '\n'
#	syscall				        # print \n
	addiu $t0, $t0, 1 		    # switch to next char
	
    bge $s2, $s1, NUMBER_NOT_INCREASED
    addi $s3, $s3, 1            # if current > previous: result ++


NUMBER_NOT_INCREASED:
    move $s2, $s1               # store previous number
    move $s1, $zero 		    # reset the value in $s1 so it can read a new number
	j PARSE_CICLE	

END_PARSE_CICLE:
	li $v0, 4			        # 4 --> print_string
	la $a0, PART1_SUCCESS	    # $a0 = address of null-terminated string to print    
	syscall
	li $v0, 1			        # 1 --> print_int
	move $a0, $s3               # print result
	syscall	
    li $v0, 10      		    # End program
	syscall
