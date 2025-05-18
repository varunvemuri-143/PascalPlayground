program TestFileHandler;
type
  FileHandler = class
  public:
    constructor Init();
    destructor Destroy();
    procedure Read();
      begin
        writeln('Reading data from file...');
      end;
    procedure Write();
      begin
        writeln('Writing data to file...');
      end;
  end;

var
  fh: FileHandler;

begin
  writeln('--- FileHandler Demo ---');
  fh := FileHandler();
  fh.Init();            
  fh.Read();            
  fh.Write();           
  fh.Destroy();         
  writeln('After cleanup:');
  fh.Read();            
  writeln('--- Demo Complete ---');
end.
