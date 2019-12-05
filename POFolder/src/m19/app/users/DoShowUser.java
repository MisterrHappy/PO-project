package m19.app.users;

import m19.app.exception.NoSuchUserException;
import m19.core.LibraryManager;
import m19.core.User;
import m19.core.exception.NoUserFoundException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
/**
 * 4.2.2. Show specific user.
 */
public class DoShowUser extends Command<LibraryManager> {

    private Input<Integer> _iD;

    /**
     * @param receiver
     */
    public DoShowUser(LibraryManager receiver) {
        super(Label.SHOW_USER, receiver);
        _iD = _form.addIntegerInput(Message.requestUserId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        try {
        _form.parse();
        User user = _receiver.getUser(_iD.value());
        _display.addLine(user.getDescription());
        _display.display();
        
        } catch ( NoUserFoundException nufe ) {
            throw new NoSuchUserException(_iD.value());
        }
    }
    
}
