; PROGRAM NAME: STRING REVERSAL USING 8086

ASSUME CS:CODE, DS:DATA, ES:DATA

DATA SEGMENT
    STR1  DB "mpmclab$"          ; Original string (terminated with '$')
    COUNT EQU ($ - STR1)         ; Length of the string
    STR2  DB 8 DUP(0)            ; Destination buffer for reversed string
DATA ENDS

CODE SEGMENT
    MOV AX, DATA                 ; Initialize Data Segment
    MOV DS, AX
    MOV ES, AX                   ; ES also points to DATA segment

    MOV CX, COUNT - 1            ; Number of characters to reverse (excluding '$')
    LEA SI, STR1                 ; Load address of source string
    LEA DI, STR2                 ; Load address of destination buffer
    ADD SI, COUNT - 2            ; Point SI to last character (before '$')

L1: STD                          ; Set Direction Flag (process backward)
    LODSB                        ; Load byte from DS:SI into AL, decrement SI
    CLD                          ; Clear Direction Flag (process forward again)
    STOSB                        ; Store AL into ES:DI, increment DI
    LOOP L1                      ; Repeat until CX = 0

    MOV AL, 24H                  ; Append '$' at the end of reversed string
    STOSB

    MOV AH, 09H                  ; DOS function to display string
    LEA DX, STR2                 ; Load address of reversed string
    INT 21H

    INT 3H                       ; Terminate program (breakpoint interrupt)
CODE ENDS

END
