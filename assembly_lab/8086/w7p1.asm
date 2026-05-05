; PROGRAM NAME: LINEAR SEARCH USING 8086

ASSUME CS:CODE, DS:DATA

DATA SEGMENT
    LIST DB 02H, 03H, 05H, 08H        ; Array of elements
    E    DB ?                         ; Variable to store element entered by user
    COUNT DB 04H                      ; Number of elements in LIST
    M    DB 10, "ENTER ELEMENT $"     ; Prompt message
    MSG  DB 10, "ELEMENT FOUND $"     ; Success message
    MSG1 DB 10, "ELEMENT NOT FOUND $" ; Failure message
DATA ENDS

CODE SEGMENT
    MOV AX, DATA                      ; Initialize Data Segment
    MOV DS, AX

    LEA DX, M                         ; Display prompt message
    MOV AH, 09H
    INT 21H

    MOV AH, 01H                       ; Take single character input
    INT 21H
    SUB AL, 30H                       ; Convert ASCII digit to numeric value
    MOV E, AL                         ; Store input in E

    MOV SI, OFFSET LIST               ; Point SI to start of LIST
    MOV CL, COUNT                     ; Load count into CL
    MOV AL, E                         ; Load search element into AL

FIRST: CMP AL, [SI]                   ; Compare search element with LIST[SI]
       JE NEXT                        ; If equal, jump to NEXT (found)
       INC SI                         ; Move to next element
       LOOP FIRST                     ; Repeat until CL = 0

       LEA DX, MSG1                   ; If not found, display failure message
       MOV AH, 09H
       INT 21H
       MOV AH, 4CH                    ; Terminate program
       INT 21H

NEXT:  LEA DX, MSG                    ; Display success message
       MOV AH, 09H
       INT 21H
       MOV AH, 4CH                    ; Terminate program
       INT 21H
CODE ENDS

END
