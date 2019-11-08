package m19.app.users;

import m19.app.exception.UserRegistrationFailedException;
import m19.core.LibraryManager;
import m19.core.exception.BadEntrySpecificationException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
/**
 * 4.2.1. Register new user.
 */
public class DoRegisterUser extends Command<LibraryManager> {

    private Input<String> _name;
    private Input<String> _email;

    /**
     * @param receiver
     */
    public DoRegisterUser(LibraryManager receiver) {
        super(Label.REGISTER_USER, receiver);
        _name = _form.addStringInput(Message.requestUserName());
        _email = _form.addStringInput(Message.requestUserEMail());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        try {
            _form.parse();
            int iD = _receiver.registerUser(_name.value(), _email.value());
            _display.addLine(Message.userRegistrationSuccessful(iD));
            _display.display();

        } catch (BadEntrySpecificationException bese) {
            throw new UserRegistrationFailedException(_name.value(), _email.value());
        }
    }
    
}
