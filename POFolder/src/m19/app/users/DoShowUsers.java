package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import m19.core.User;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
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
    Map<Integer, User> users = _receiver.getAllUsers();

    if (users.isEmpty())
      _display.addLine("");
    else {

        List<User> orderedUsers = new ArrayList<>(users.values());
        Collections.sort(orderedUsers, new Comparator<User>() {
        @Override
        public int compare(User a, User b) {
          return a.getName().compareTo(b.getName()) == 0 ? a.hashCode() - b.hashCode() : a.getName().compareTo(b.getName());
        }	
        });

        for (User temp: orderedUsers)
          _display.addLine(temp.getDescription());
    }
    _display.display();
  }
  
}
