package m19.app.requests;

import m19.app.exception.NoSuchUserException;
import m19.app.exception.NoSuchWorkException;
import m19.app.exception.RuleFailedException;
import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.core.User;
import m19.core.Work;
import m19.core.exception.NoUserFoundException;
import m19.core.exception.NoWorkFoundException;

/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {

    private Input<Integer> _userId;
    private Input<Integer> _workId;

    /**
     * @param receiver
     */
    public DoRequestWork(LibraryManager receiver) {
        super(Label.REQUEST_WORK, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
        _workId = _form.addIntegerInput(Message.requestWorkId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        try {
            _form.parse();
            User user = _receiver.getUser(_userId.value());
            Work work = _receiver.getWork(_workId.value());
            _receiver.request

        } catch (NoUserFoundException nufe) {
            throw new NoSuchUserException(_userId.value());

        } catch (NoWorkFoundException nwfe) {
            throw new NoSuchWorkException(_workId.value());

        } catch ()
            throw new RuleFailedException(_userId.value(), _workId.value(), ruleIndex)
    
    }

}
