; comment 1
$var x 1100100b
$var y 0
;$array ar 23 64 28 79 66 47 55 12 71 63 86 92 25 41 37 26 96 15 48 73 84

$start
begin:
  NOP
  mov y x
  mov x 101
  mov [x] y
  mov [[101]] 99h

;  MOV i addr(ar)

;  MOV a b ; comment 2
;  MOV asdad xcvxcv ; comment 3
JMP incr
;mov [[27]] 55
;end: INC x
;EXP a+= a *bcde
end:
HALT
incr:
mov @ 1
next:
add @ @
jio end
jmp next
