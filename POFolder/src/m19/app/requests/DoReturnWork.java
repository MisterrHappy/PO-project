package m19.app.requests;

import m19.app.exception.NoSuchUserException;
import m19.app.exception.NoSuchWorkException;
import m19.app.exception.WorkNotBorrowedByUserException;
import m19.core.LibraryManager;
import m19.core.exception.NoSuchWorkRequestedByUserException;
import m19.core.exception.NoUserFoundException;
import m19.core.exception.NoWorkFoundException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Form;
/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Command<LibraryManager> {

    private Input<Integer> _userId;
    private Input<Integer> _workId;
    private Input<Boolean> _requestFinePaymentChoice;
    private Form _formChoice = new Form();

    /**
     * @param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(Label.RETURN_WORK, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
        _workId = _form.addIntegerInput(Message.requestWorkId());
        _requestFinePaymentChoice = _formChoice.addBooleanInput(Message.requestFinePaymentChoice());
    }
    
    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        try {
            _form.parse();
            int fine = _receiver.returnWork(_userId.value(), _workId.value());

            if (fine != 0) {
                _display.addLine(Message.showFine(_userId.value(), fine));
                _display.display();
                _formChoice.parse();
                _receiver.userPaymentChoice(_userId.value(), _requestFinePaymentChoice.value());
            }

        } catch (NoUserFoundException nufe) {
            throw new NoSuchUserException(_userId.value());

        } catch (NoWorkFoundException nwfe) {
            throw new NoSuchWorkException(_workId.value());

        } catch (NoSuchWorkRequestedByUserException nswrbue) {
            throw new WorkNotBorrowedByUserException(_workId.value(), _userId.value());
        }
    }

}
