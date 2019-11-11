package m19.app.requests;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Command<LibraryManager> {

    private Input<Integer> _userID;
    private Input<Integer> _workID;

    /**
     * @param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(Label.RETURN_WORK, receiver);
        _userID = _form.addIntegerInput(Message.requestUserId());
        _workID = _form.addIntegerInput(Message.requestWorkId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        
    }

}
