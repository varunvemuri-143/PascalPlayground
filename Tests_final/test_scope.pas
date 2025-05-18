program TestScope;
var
  x, y: integer;

procedure ProcTest(x: integer);
begin
  writeln('[ProcTest] parameter x = ', x);
  writeln('[ProcTest] computed value = ', x + 10);
  x := x + 10;
  writeln('[ProcTest] modified parameter x = ', x);
end;

function FuncTest(y: integer): integer;
begin
  writeln('[FuncTest] parameter y = ', y);
  FuncTest := y * 2; 
  writeln('[FuncTest] returning value = ', FuncTest);
end;


begin
  x := 5;
  y := 7;
  writeln('[Main] before ProcTest, x = ', x);
  ProcTest(20);
  writeln('[Main] after ProcTest, x = ', x);
  
  writeln('[Main] before FuncTest, y = ', y);
  y := FuncTest(15);
  writeln('[Main] after FuncTest, y = ', y);
end.
