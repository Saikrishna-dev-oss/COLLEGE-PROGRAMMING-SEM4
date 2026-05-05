data segment
cou db 10h
arnum db 10h dup(10)
data ends
code segment
assume cs:code,ds:data
mov ax,data
mov ds,ax
mov cl,cou
mov si,offset arnum
mov al,00h
back:mov[si],al
dec cl
jz go
inc al
inc si
jmp back
go:hlt
code ends
end
