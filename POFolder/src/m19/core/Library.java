package m19.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import m19.core.exception.MissingFileAssociationException;
import m19.core.exception.BadEntrySpecificationException;

import m19.core.Date;
import m19.core.User;
import m19.core.work.Work;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201901101348L;

  private Date _date;
  private int _userNextID;
  private int _workNextID;
  private Set<User> _users = new HashSet<>();
  private Set<Work> _works = new HashSet<>();

  protected int getCurrentDate() {
    return _date.getCurrentDate();
  }
  
  protected void advanceDays(int nDays) {
    _date.advanceDay(nDays);
  }

  private List<User> getOrderedUsers() {
    List<User> orderedUsers = new ArrayList<>(_users);
    Collections.sort(orderedUsers, new UserComparator());
    return orderedUsers;
  }

  protected String getUser(int iD) {
    for (User temp: _users) {
      if (temp.hashCode() == iD)
        return temp.getDescription();
    }
    return null;
  }

  protected String getAllUsers() {
    List<User> orderedUsers = getOrderedUsers();
    String res = "";
    for (User temp: orderedUsers) 
      res += temp.getDescription() + "\n";
    return res;
  }

  protected int registerUser(String name, String email) {
    User user = new User(_userNextID, name, email);
    if (_users.add(user))
      return _userNextID++;
    return -1;
  }

  /**
   * Read the text input file at the beginning of the program and populates the
   * instances of the various possible types (books, DVDs, users).
   * 
   * @param filename
   *          name of the file to load
   * @throws BadEntrySpecificationException
   * @throws IOException
   */
  void importFile(String filename) throws BadEntrySpecificationException, IOException {
    // FIXME implement method
  }

}
