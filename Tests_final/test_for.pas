program TestFor;
var
  i, total: integer;
begin
  total := 0;
  writeln('Testing for loop...');
  for i := 1 to 5 do
  begin
    total := total + i;
    writeln(i);
  end;
  writeln('Total sum:');
  writeln(total);
end.
