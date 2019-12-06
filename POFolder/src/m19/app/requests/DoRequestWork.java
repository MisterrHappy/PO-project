package m19.app.requests;

import m19.app.exception.NoSuchUserException;
import m19.app.exception.NoSuchWorkException;
import m19.app.exception.RuleFailedException;
import m19.core.LibraryManager;
import m19.core.exception.NoUserFoundException;
import m19.core.exception.NoWorkFoundException;
import m19.core.exception.RuleBrokenException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Form;
import pt.tecnico.po.ui.Input;

/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {

    private Input<Integer> _userId;
    private Input<Integer> _workId;
    private Input<Boolean> _notificationPreference;

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
            int deadline = _receiver.requestWork(_userId.value(), _workId.value());
            _display.addLine(Message.workReturnDay(_workId.value(), deadline));
            _display.display();

        } catch (NoUserFoundException nufe) {
            throw new NoSuchUserException(_userId.value());

        } catch (NoWorkFoundException nwfe) {
            throw new NoSuchWorkException(_workId.value());

        } catch (RuleBrokenException rbe) {
            int ruleIndex = rbe.getRuleIndex();
            if (ruleIndex != 3)
                throw new RuleFailedException(_userId.value(), _workId.value(), ruleIndex);
            
            Form formChoice = new Form();
            _notificationPreference = formChoice.addBooleanInput(Message.requestReturnNotificationPreference());
            formChoice.parse();
            _receiver.addObserver(_notificationPreference.value(), _userId.value(), _workId.value());

        }
    
    }

}
