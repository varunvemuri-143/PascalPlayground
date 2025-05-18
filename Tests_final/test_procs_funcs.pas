program TestOrderProcessor;
var
  total: integer;

procedure AddItem(price: integer);
begin
  total := total + price;
  writeln('Added item: $', price, '; total = $', total);
end;

function CalculateFinalAmount(rate: integer): integer;
begin
  CalculateFinalAmount := total + (total * rate div 100);
end;

begin
  total := 0;
  AddItem(100);
  AddItem(200);
  writeln('Total due with 8% tax: $', CalculateFinalAmount(8));
end.
