program TestInheritance;
type
  SystemBase = class
    public:
      procedure Initialize();
      begin
        writeln('SystemBase: hardware and OS initialized');
      end;
  end;

  Workstation = class(SystemBase)
    public:
      procedure StartupSequence();
      begin
        writeln('Workstation: loading user profile and applications');
      end;
  end;

var
  ws: Workstation;

begin
  ws := Workstation();
  ws.Initialize();
  ws.StartupSequence();
end.
