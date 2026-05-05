;Calculte the sum by taking inputs from user
ASSUME CS:CODE, DS:DATA

DATA SEGMENT
    COU  DB 09H                  ; Number of values to be entered (count = 9)
    STR1 DB "Enter the next number$" ; Prompt message
    SUM  DB ?                    ; Variable to store the final sum
DATA ENDS

CODE SEGMENT
    MOV AX, DATA                 ; Initialize Data Segment
    MOV DS, AX

    MOV CL, COU                  ; Load count into CL register
    MOV BL, 0000H                  ; Initialize BL to 0 (sum accumulator)

GO: MOV DX, OFFSET STR1          ; Display prompt message
    MOV AH, 09H
    INT 21H

    MOV AH, 01H                  ; Take single character input from user
    INT 21H
    SUB AL, 30H                  ; Convert ASCII digit to numeric value
    ADD BL, AL                   ; Add the value to BL (sum accumulator)

    DEC CL                       ; Decrement counter
    JNZ GO                       ; Repeat until CL = 0

    MOV SUM, BL                  ; Store final sum in SUM variable
    HLT                          ; Halt program execution
CODE ENDS

END






