program TestConstProp;
var
  v: integer;
begin
  v := 2*(10+11);
  v := v + 2*3;
  writeln('Final v = ', v);
end.
