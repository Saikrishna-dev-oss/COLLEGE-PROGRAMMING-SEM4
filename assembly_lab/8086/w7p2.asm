; PROGRAM NAME: BUBBLE SORT (Ascending Order) USING 8086

ASSUME CS:CODE, DS:DATA

DATA SEGMENT
    V DB 9, 6, 7, 8        ; Array of 4 elements
DATA ENDS

CODE SEGMENT
START: 
    MOV AX, DATA           ; Initialize Data Segment
    MOV DS, AX

    MOV CH, 4              ; Number of passes (array length)
    MOV AL, 0              ; Clear AL

ITER: 
    MOV BX, 0              ; Reset index for each pass

NEXT: 
    MOV AL, V[BX]          ; Load current element
    CMP AL, V[BX+1]        ; Compare with next element
    JNL SWAP               ; If AL >= next element, swap
    JL CON                 ; Else continue

SWAP: 
    MOV DL, V[BX]          ; Store current element in DL
    MOV AL, V[BX+1]        ; Load next element into AL
    MOV V[BX+1], DL        ; Place smaller element ahead
    MOV V[BX], AL          ; Place larger element behind

CON: 
    INC BX                 ; Move to next index
    CMP BX, 3              ; Compare with last index (n-1)
    JNZ NEXT               ; If not end, repeat

    DEC CH                 ; Decrement pass counter
    CMP CH, 0              ; Check if passes are done
    JNZ ITER               ; If not, repeat passes

    ; Display sorted array
    MOV CL, 04H            ; Number of elements
    MOV BL, 00H            ; Reset index

DISP: 
    MOV DL, V[BX]          ; Load element
    ADD DL, 30H            ; Convert to ASCII
    INC BX                 ; Move to next element
    MOV AH, 02H            ; DOS function: display character
    INT 21H
    LOOP DISP              ; Repeat until all elements displayed

    MOV AH, 4CH            ; Terminate program
    INT 21H
CODE ENDS

END START
