program TestEncapsulation;
type
  Wallet = class
    private:
      balance: integer;
      secretCode: string;
    ;
    public:
      constructor Init();
      procedure Deposit();
      begin
        balance := 100;
        writeln('Deposited 100');
      end;
      procedure ShowBalance();
      begin
        writeln('Balance is: ', balance);
      end;
      procedure RevealCode();
      begin
        writeln('Secret code: ', secretCode);
      end;
  end;

var
  w: Wallet;

begin
  w := Wallet();
  w.Init();
  w.Deposit();
  w.ShowBalance();
  w.RevealCode();
  w.balance := 0;   
end.
