program TestWhile;
var
  fuel: integer;
begin
  fuel := 5;
  writeln('Fuel monitor start: ', fuel);
  while fuel > 0 do
    begin
      writeln('Fuel level: ', fuel);
      fuel := fuel - 1;
    end;
  writeln('Fuel empty');
end.
