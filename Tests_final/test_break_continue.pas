program TestBreakContinue;
var
  tx: integer;
  processed: integer;
begin
  processed := 0;
  writeln('Starting transaction processing');
  for tx := 1 to 10 do
    begin
      if tx = 3 then
        begin
          writeln('Order ', tx, ' is cancelled, skipping');
          continue;
        end;
      if tx = 6 then
        begin
          writeln('Order ', tx, ' is flagged fraudulent, stopping');
          break;
        end;
      writeln('Processing order ', tx);
      processed := processed + 1;
    end;
  writeln('Processed orders: ', processed);
end.
