package m19.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

import m19.core.exception.BadEntrySpecificationException;
import m19.core.exception.BadUserEntryException;
import m19.core.Date;
import m19.core.User;
import m19.core.work.Work;
import m19.core.Parser;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;

    private Date _date = new Date();
    private int _userNextID;
    private int _workNextID;
    private List<Work> _works = new ArrayList<>();
    private Map<Integer, User> _users = new HashMap<>();

    protected int getWorkNextID() {
        return _workNextID;
    }

    protected int getCurrentDate() {
        return _date.getCurrentDate();
    }
    
    protected void advanceDays(int nDays) {
        _date.advanceDay(nDays);
    }

    private List<User> getOrderedUsers() {
        List<User> orderedUsers = new ArrayList<>(_users.values());
        Collections.sort(orderedUsers, new Comparator<User>() {
        @Override
        public int compare(User a, User b) {
            if (a.getName().compareTo(b.getName()) == 0)
                return a.hashCode() - b.hashCode();
            return a.getName().compareTo(b.getName());
        }
        });
        return Collections.unmodifiableList(orderedUsers);
    }

    protected String getUser(int iD) {
        return (_users.get(iD)).getDescription();
    }

    protected String getAllUsers() {
        List<User> orderedUsers = getOrderedUsers();
        String res = "";
        for (User temp: orderedUsers) 
        res += temp.getDescription() + "\n";
        return res;
    }

    protected int registerUser(String name, String email) throws BadEntrySpecificationException {
        if (name.isEmpty() || email.isEmpty())
            throw new BadEntrySpecificationException("User name or email are empty strings: " + name + " " + email);

        User user = new User(_userNextID, name, email);
        _users.put(_userNextID, user);
        return _userNextID++;
    }

    protected void addWork(Work work) {
        _works.add(_workNextID++, work);
    }

    protected String getWork(int iD) throws IndexOutOfBoundsException {
        return (_works.get(iD)).getDescription();
    }

    protected String getAllWorks() {
        String res = "";
        for (Work temp: _works)
        res += temp.getDescription() + "\n";
        return res;
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
        Parser parser = new Parser(this);
        parser.parseFile(filename);
    }
    
}
