package m19.app.main;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.app.main.Message;

/**
 * 4.1.3. Advance the current date.
 */
public class DoAdvanceDate extends Command<LibraryManager> implements Message{

  private Input<Integer> _daysToAdvance;

  /**
   * @param receiver
   */
  public DoAdvanceDate(LibraryManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    _daysToAdvance = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();

    _receiver.advanceDays(_daysToAdvance.value());
  }
}
