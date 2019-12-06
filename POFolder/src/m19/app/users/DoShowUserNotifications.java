package m19.app.users;

import java.util.List;

import m19.app.exception.NoSuchUserException;
import m19.core.LibraryManager;
import m19.core.Notification;
import m19.core.exception.NoUserFoundException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.2.3. Show notifications of a specific user.
 */
public class DoShowUserNotifications extends Command<LibraryManager> {

    private Input<Integer> _userId;

    /**
     * @param receiver
     */
    public DoShowUserNotifications(LibraryManager receiver) {
        super(Label.SHOW_USER_NOTIFICATIONS, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        try {
            _form.parse();
            List<Notification> notifications = _receiver.showUserNotifications(_userId.value());

            if (notifications.isEmpty())
                _display.addLine("");
            
            else {
                for (Notification n: notifications) 
                    _display.addLine(n.getMessage());
            }
            _display.display();

        } catch (NoUserFoundException nufe) {
            throw new NoSuchUserException(_userId.value());
        }
    }

}
