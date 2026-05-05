
;TAKE N VALUES FROM USER AND CALCULATE MIN AND MAX VALUES
ASSUME CS:CODE, DS:DATA

DATA SEGMENT
    ARR DB 12H, 13H, 05H, 35H, 46H, 76H   ; Array of values
    COU DB 06H                            ; Count of elements in array
    MAX DB ?                              ; Variable to store maximum value
    MIN DB ?                              ; Variable to store minimum value
DATA ENDS

CODE SEGMENT
    MOV AX, DATA                          ; Initialize Data Segment
    MOV DS, AX

    MOV SI, OFFSET ARR                    ; Point SI to start of array
    MOV CL, COU                           ; Load count into CL register

    MOV AL, [SI]                          ; Load first element
    MOV MIN, AL                           ; Initialize MIN with first element
    MOV MAX, AL                           ; Initialize MAX with first element

BACK: INC SI                              ; Move to next element
    MOV AL, [SI]                          ; Load current element

    CMP MIN, AL                           ; Compare MIN with current element
    JL CHMAX                              ; If MIN < AL, skip updating MIN
    MOV MIN, AL                           ; Else update MIN

CHMAX: CMP MAX, AL                        ; Compare MAX with current element
    JG GO                                 ; If MAX > AL, skip updating MAX
    MOV MAX, AL                           ; Else update MAX

GO: DEC CL                                ; Decrement counter
    JNZ BACK                              ; Repeat until CL = 0

    HLT                                   ; Halt program execution
CODE ENDS

END
