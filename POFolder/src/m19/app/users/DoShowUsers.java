package m19.app.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import m19.core.LibraryManager;
import m19.core.User;
import pt.tecnico.po.ui.Command;
/**
 * 4.2.4. Show all users.
 */
public class DoShowUsers extends Command<LibraryManager> {

  /**
   * @param receiver
   */
  public DoShowUsers(LibraryManager receiver) {
    super(Label.SHOW_USERS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    Collection<User> users = _receiver.getAllUsers();

    if (users.isEmpty())
      _display.addLine("");
    else {

        List<User> orderedUsers = new ArrayList<>(users);
        Collections.sort(orderedUsers, User.getComparatorByName());

        for (User user: orderedUsers)
          _display.addLine(user.getDescription());
    }
    _display.display();
  }
  
}
