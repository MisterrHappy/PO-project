package m19.app.users;

import m19.app.exception.NoSuchUserException;
import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.core.exception.NoUserFoundException;
import m19.core.User;
import m19.core.Notification;
import java.util.List;

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
            User user = _receiver.getUser(_userId.value());
            List<Notification> notifications = _receiver.showUserNotifications(user);

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
