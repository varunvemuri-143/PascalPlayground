program TestWidgets;
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
  widget := AlphaWidget.Create();
  widget.Activate();
end. 