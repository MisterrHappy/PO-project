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
import m19.core.exception.EmptyUserNameOrEmail;
import m19.core.exception.NoUserFoundException;
import m19.core.Date;
import m19.core.User;
import m19.core.Work;
import m19.core.Parser;

/**
 * Class that represents the library as a whole. It contains all works users and the date.
 * Also controls works and users IDs in the respectives registrations.
 * Authors: André Marinho and João Domingos
 */
public class Library implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;

    /**
	* The current date is represented by Date class in this variable (directly initialized).
	*/
    private Date _date = new Date();

    /**
	* Variable used and updated in users registrations to set up their ID. Initialized by default int value.
	*/
    private int _userNextID;

    /**
	* Variable used and updated in works additions to set up their ID. Initialized by default int value.
	*/
    private int _workNextID;

    /**
	* A list of all assigned works (directly initialized).
	*/
    private List<Work> _works = new ArrayList<>();

    /**
	* A map of all registed users (directly initialized).
	*/
    private Map<Integer, User> _users = new HashMap<>();

    
    /** 
     * Used in class Parser to register works with the correct ID.
     * 
     * @return The ID of the next work to be added.
     */
    protected int getWorkNextID() {
        return _workNextID;
    }

    
    /** 
     * Shows the value of the current date.
     * 
     * @return The value (int) of the current date.
     */
    protected int getCurrentDate() {
        return _date.getCurrentDate();
    }
    
    
    /** 
     * Advances the current date.
     * 
     * @param nDays The number os days (int) to advance the date. It must be a positive non-zero value.
     */
    protected void advanceDays(int nDays) {
        _date.advanceDay(nDays);
    }

    
    /** 
     * Shows the user description with the given ID.
     * 
     * @param iD User ID which description is requested.
     * 
     * @return User description.
     * 
     * @throws NoUserFoundException Is thrown when the user to find with the given ID does not exist (is null).
     */
    protected String getUser(int iD) throws NoUserFoundException {
        User user = _users.get(iD);
        if ( user == null)
            throw new NoUserFoundException(iD);
        return user.getDescription();
    }

    
    /** 
     * Orders the users Collection maintained in this class alphabetically. When users have an equal name, 
     * they are ordered by ascending IDs.
     * 
     * @return A List with all library users ordered.
     */
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

    
    /** 
     * Gives all users descriptions in the library users collection.
     * 
     * @return All users descriptions.
     */
    protected String getAllUsers() {
        List<User> orderedUsers = getOrderedUsers();
        String res = "";
        for (User temp: orderedUsers) 
        res += temp.getDescription() + "\n";
        return res;
    }

    /**
     * Registers an user in the respective collection. Used in importFile method and when an user is assigned from the terminal.
     * 
     * @param name String with the user name to register.
     * 
     * @param email String with the user email to register.
     * 
     * @return The ID of the assigned user if successful.
     * 
     * @throws EmptyUserNameOrEmail Is thrown if user name or email are empty strings.
     */
    protected int registerUser(String name, String email) throws EmptyUserNameOrEmail {
        if (name.isEmpty() || email.isEmpty() )
            throw new EmptyUserNameOrEmail("User name " + name + " or email " + email + " are empty strings.");

        User user = new User(_userNextID, name, email);
        _users.put(_userNextID, user);
        return _userNextID++;
    }

    
    /** 
     * Adds a work in the respective collection.
     * 
     * @param work Work to be added.
     */
    protected void addWork(Work work) {
        _works.add(_workNextID++, work);
    }

    
    /** 
     * Gives the work description with the given ID.
     * 
     * @param iD ID of the work to be searched.
     * 
     * @return The wanted work description.
     * 
     * @throws IndexOutOfBoundsException Is thrown if the work to be searched does not exist. Therefore, works collection is ordered by IDs.
     */
    protected String getWork(int iD) throws IndexOutOfBoundsException {
        return (_works.get(iD)).getDescription();
    }

    /**
     * Gives all works descriptions in the library works collection.
     * 
     * @return All works descriptions.
     */
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
     * @param filename Name of the file to load.
     * 
     * @throws BadEntrySpecificationException
     * 
     * @throws IOException
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Parser parser = new Parser(this);
        parser.parseFile(filename);
    }
    
}
