package m19.app.main;

import m19.core.LibraryManager;

import pt.tecnico.po.ui.Command;
import m19.app.main.Message;
/**
 * 4.1.2. Display the current date.
 */
public class DoDisplayDate extends Command<LibraryManager> implements Message{

  /**
   * @param receiver
   */
  public DoDisplayDate(LibraryManager receiver) {
    super(Label.DISPLAY_DATE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _display.addLine(Message.currentDate(_receiver.getCurrentDate()));
    _display.display();
  }
}
