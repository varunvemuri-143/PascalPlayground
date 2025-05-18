program TestInterfaces;
type
  IReader = class
    public:
      procedure Read();
      begin
        writeln('IReader: default read');
      end;
  end;
  IWriter = class
    public:
      procedure Write();
      begin
        writeln('IWriter: default write');
      end;
  end;
  FileManager = class(IReader, IWriter)
    public:
      constructor Init();
      procedure Read();
      begin
        writeln('FileManager: reading data from file');
      end;
      procedure Write();
      begin
        writeln('FileManager: writing data to file');
      end;
  end;

var
  fm: FileManager;

begin
  fm := FileManager();
  fm.Init();
  fm.Read();
  fm.Write();
end.
