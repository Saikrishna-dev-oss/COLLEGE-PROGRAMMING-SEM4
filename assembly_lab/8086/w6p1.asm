;Transfer a block of data from one location to another. 
ASSUME DS:DATA, ES:EXTRA, CS:CODE

DATA SEGMENT
    STR1 DB 'geethanjali'          ; Source string (11 characters)
DATA ENDS

EXTRA SEGMENT
    STR2 DB 0BH DUP(0)             ; Destination buffer initialized with 11 zeros
EXTRA ENDS

CODE SEGMENT
    MOV AX, DATA                  ; Initialize Data Segment
    MOV DS, AX

    MOV AX, EXTRA                 ; Initialize Extra Segment
    MOV ES, AX

    MOV SI, OFFSET STR1           ; Source index points to STR1
    MOV DI, OFFSET STR2           ; Destination index points to STR2
    MOV CL, 0BH                   ; Number of bytes to transfer (11 characters)

    CLD                           ; Clear Direction Flag (process forward)
    REP MOVSB                     ; Repeat move byte from DS:SI to ES:DI

    HLT                           ; Halt program execution
CODE ENDS


END
