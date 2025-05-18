program TestParams;
var
  x: integer;

procedure SetX(val: integer);
begin
  writeln('Setting x to ', val);
  x := val;
end;

procedure TestScope(val: integer);
begin
  writeln('Inside TestScope, val=', val);
  val := val + 10;
  writeln('Inside TestScope after increment, val=', val);
end;

function Add(a: integer; b: integer): integer;
begin
  Add := a + b;
end;

begin
  x := 5;
  writeln('Initial x=', x);
  SetX(30);
  writeln('After SetX, x=', x);
  TestScope(100);
  writeln('After TestScope, x=', x);
  writeln('Add(7,8)=', Add(7,8));
end.
