package m19.app.users;

import m19.app.exception.NoSuchUserException;
import m19.app.exception.UserIsActiveException;
import m19.core.LibraryManager;
import m19.core.exception.NoUserFoundException;
import m19.core.exception.UserIsNotSuspendedException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.2.5. Settle a fine.
 */
public class DoPayFine extends Command<LibraryManager> {

    private Input<Integer> _userId;

    /**
     * @param receiver
     */
    public DoPayFine(LibraryManager receiver) {
        super(Label.PAY_FINE, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        try {
            _form.parse();
            _receiver.payUserFine(_userId.value());

        } catch (NoUserFoundException nufe) {
            throw new NoSuchUserException(_userId.value());
            
        } catch (UserIsNotSuspendedException uinse) {
            throw new UserIsActiveException(_userId.value());
        }
    }

}
