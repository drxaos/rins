; array bubble sort

$var c 1
$var i 0
$var swp 0
$array ar 23 64 28 79 66 47 55 12 71 63 86 92 25 41 37 26 96 15 48 73

begin: $start
  cmp c 0
  je end
  mov i ar.offset-1
  mov c 0
loop:
  inc i
  cmp i ar.offset+ar.size-1
  je begin
  mov @ i
  inc @
  cmp [i] [@]
  jl loop
  mov swp [@]
  mov [@] [i]
  mov [i] swp
  inc c
  jmp loop
end:
  halt
