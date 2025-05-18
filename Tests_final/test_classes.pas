program TestClasses;
type
  AlphaWidget = class
  public:
    constructor Init();
    procedure Activate();
      begin
        writeln('AlphaWidget activated!');
      end;
  end;

var
  widget: AlphaWidget;

begin
  widget := AlphaWidget();
  widget.Init();
  widget.Activate();
end.
