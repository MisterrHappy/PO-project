package m19.app.requests;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {

    private Input<Integer> _userID;
    private Input<Integer> _workID;

    /**
     * @param receiver
     */
    public DoRequestWork(LibraryManager receiver) {
        super(Label.REQUEST_WORK, receiver);
        _userID = _form.addIntegerInput(Message.requestUserId());
        _workID = _form.addIntegerInput(Message.requestWorkId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
    
    }

}
