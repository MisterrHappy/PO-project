package m19.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m19.core.exception.BadEntrySpecificationException;
import m19.core.exception.EmptyUserNameOrEmailException;
import m19.core.exception.NoSuchWorkRequestedByUserException;
import m19.core.exception.NoUserFoundException;
import m19.core.exception.NoWorkFoundException;
import m19.core.exception.RuleBrokenException;
import m19.core.exception.UserIsNotSuspendedException;

/**
 * Class that represents the library as a whole. It contains all works users and the date.
 * Also controls works and users IDs in the respectives registrations.
 * Authors: André Marinho and João Domingos
 */
public class Library implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;

    /**
     * The ammount of money (int) which penalizes users with late requests per day.
     */
    private static final int FINE_PER_DAY = 5;

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
	* A map of all assigned works (directly initialized).
	*/
    private Map<Integer, Work> _works = new HashMap<>();

    /**
	* A map of all registed users (directly initialized).
	*/
    private Map<Integer, User> _users = new HashMap<>();

    /**
     * A Set with all request done by users (directly initialized).
     */
    private Set<Request> _requests = new HashSet<>();

    /**
     * A List with all library rules (directly initialized).
     */
    private List<Rule> _rules = new ArrayList<>();

    /**
     * Constructor: adds the rules to the respective library list.
     */
    Library() {
        _rules.add(new Rule(1) {
            private static final long serialVersionUID = -5482980888028590048L;
            @Override
            protected void checkRule(User user, Work work) throws RuleBrokenException {
                if (user.checkUserRequest(work.hashCode()) != null)
                    throw new RuleBrokenException(getId());    
            }
        });
        _rules.add(new Rule(2) {
            private static final long serialVersionUID = -3483165919640302712L;
            @Override
            protected void checkRule(User user, Work work) throws RuleBrokenException {
                if (!user.checkStatus())
                    throw new RuleBrokenException(getId());
            }
        });
        _rules.add(new Rule(3) {
            private static final long serialVersionUID = -752046526405581894L;
            @Override
            protected void checkRule(User user, Work work) throws RuleBrokenException {
                if (work.getNumberOfCopiesAvailable() == 0)
                    throw new RuleBrokenException(getId());
            }
        });
        _rules.add(new Rule(4) {
            private static final long serialVersionUID = -381909310264088567L;
            @Override
            protected void checkRule(User user, Work work) throws RuleBrokenException {
                if (user.getUserRequestsNumber() >= user.getBehavior().getMaxRequests())
                    throw new RuleBrokenException(getId());
            }
        });
        _rules.add(new Rule(5) {
            private static final long serialVersionUID = 1882428612380599667L;
            @Override
            protected void checkRule(User user, Work work) throws RuleBrokenException {
                if (work.getCategory().toString().equals("Referência"))
                    throw new RuleBrokenException(getId());
            }
        });
        _rules.add(new Rule(6) {
            private static final long serialVersionUID = 5496472887415212091L;
            @Override
            protected void checkRule(User user, Work work) throws RuleBrokenException {
                if (!user.getBehavior().checkWorkPrice(work))
                    throw new RuleBrokenException(getId());
            }
        });
    }

    /** 
     * Used in class Parser to register works with the correct ID.
     * 
     * @return The ID of the next work to be added.
     */
    int getWorkNextID() {
        return _workNextID;
    }

    /**
     * Used to give all the works with the term given in the argument.
     * The term is converted in lower cases to perform the search.
     * 
     * @param term The string from where works are searched.
     * 
     * @return The list with all works found.
     */
    List<Work> performSearch(String term) {
        List<Work> worksSearched = new ArrayList<>();
        term = term.toLowerCase();
        for (Work work: _works.values()) {
            if (work.getWorkByTerm(term) != null)
                worksSearched.add(work);
        }
        return Collections.unmodifiableList(worksSearched);
    }

    /**
     * Used when the client wants to pay a user fine (pays all the fine).
     * Updates the user after the payment.
     * 
     * @param userId Id of the user whoses fine is to be payed.
     * 
     * @throws NoUserFoundException Is thrown when the user to find with the given ID does not exist (is null).
     * 
     * @throws UserIsNotSuspendedException Is thrown when the user found is active.
     */
    void payUserFine(int userId) throws NoUserFoundException, UserIsNotSuspendedException {
        User user = getUser(userId);
        user.payFine();
        user.updateUser(getCurrentDate());
    }

    /**
     * Used to regist a request asked by the user of a certain work.
     * Calculates de request deadline using the current date and checks all library rules.
     * 
     * @param userId Id of the user whose wants to request the work.
     * 
     * @param workId Id of the work to be requested.
     * 
     * @return The deadline of the work requested.
     * 
     * @throws NoUserFoundException Is thrown when the user to find with the given ID does not exist (is null).
     * 
     * @throws NoWorkFoundException Is thrown if the work to be searched does not exist.
     * 
     * @throws RuleBrokenException Is thrown when a library rule is broken by the user who wants to request a work.
     */
    int requestWork(int userId, int workId) throws NoUserFoundException, NoWorkFoundException, RuleBrokenException {
        User user = getUser(userId);
        Work work = getWork(workId);
        for (Rule rule: _rules)
            rule.checkRule(user, work);
        int currentDate = getCurrentDate();
        int deadline = user.getBehavior().getRequestTerm(work.getNumberOfCopies()) + currentDate;
        Request request = new Request(user, work, deadline);
        _requests.add(request);
        user.addRequest(request);
        work.addRequest(request);
        return deadline;
    }

    /**
     * Used when an user wants to observe a work which he could not make its requisition.
     * 
     * @param notificationPreference User choice about being notified.
     * 
     * @param userId Id of the user whose wants to request the work.
     * 
     * @param workId Id of the work which was trying to be requested.
     */
    void addObserver(boolean notificationPreference, int userId, int workId) {
        if (notificationPreference) 
            _works.get(workId).getSubjectDelivery().addObserver(_users.get(userId));
    }

    /**
     * Used to return a request registed in the library.
     * Calculates the fine to be payed and updates user behavior and his deliveries streaks.
     * 
     * @param userId Id of the user whose wants to return the work.
     * 
     * @param workId Id of the work to be returned.
     * 
     * @return The fine to be payed.
     * 
     * @throws NoUserFoundException Is thrown when the user to find with the given ID does not exist (is null).
     * 
     * @throws NoWorkFoundException Is thrown if the work to be searched does not exist.
     * 
     * @throws NoSuchWorkRequestedByUserException Is thrown when the user given did not request the work in the argument.
     */
    int returnWork(int userId, int workId) throws NoUserFoundException, NoWorkFoundException, NoSuchWorkRequestedByUserException {
        User user = getUser(userId);
        Work work = getWork(workId);
        Request request = user.checkUserRequest(work.hashCode());
        if (request == null)
            throw new NoSuchWorkRequestedByUserException(work.hashCode());
            
        int fine = (getCurrentDate() - request.getDeadline()) * FINE_PER_DAY;
        _requests.remove(request);
        user.removeRequest(request);
        work.removeRequest(request);

        if (fine > 0) {
            user.setLateStreak(user.getLateStreak() + 1);
            user.setOnTimeStreak(0);
        }
        
        else {
            user.setOnTimeStreak(user.getOnTimeStreak() + 1);
            user.setLateStreak(0);
            fine = 0;
        }
        user.getBehavior().updateBehavior(user);
        user.fineUser(fine);
        return user.getFine();
    }

    /**
     * Used to treat the decision of paying (or not) the user fine.
     * 
     * @param userId Id of the user whose fine will be (or not) payed.
     * 
     * @param choice Client choice about paying the user fine
     */
    void userPaymentChoice(int userId, boolean choice) {
        if (!choice)
            return;

        try {
            payUserFine(userId);

        } catch (NoUserFoundException | UserIsNotSuspendedException e) { 
            System.err.println("User was active or did not exist"); 
        } // this never happens here, it is only to code reuse and to not have empty catch
    }

    /**
     * Used to show notifications of the user whose id is given in the argument.
     * 
     * @param userId Id of the user whose notifications were asked for.
     * 
     * @return A list with all user notifications.
     * 
     * @throws NoUserFoundException Is thrown when the user to find with the given ID does not exist (is null).
     */
    List<Notification> showUserNotifications(int userId) throws NoUserFoundException {
        User user = getUser(userId);
        return user.getUserNotifications();
    }
    
    /** 
     * Shows the value of the current date.
     * 
     * @return The value (int) of the current date.
     */
    int getCurrentDate() {
        return _date.getCurrentDate();
    }
    
    
    /** 
     * Advances the current date.
     * 
     * @param nDays The number os days (int) to advance the date. It must be a positive non-zero value.
     */
    void advanceDays(int nDays) {
        _date.advanceDay(nDays);
        for (User u: _users.values())
            u.updateUser(getCurrentDate());
    }

    
    /** 
     * Gives the user with the given ID.
     * 
     * @param iD User ID.
     * 
     * @return Wanted user.
     * 
     * @throws NoUserFoundException Is thrown when the user to find with the given ID does not exist (is null).
     */
    User getUser(int iD) throws NoUserFoundException {
        User user = _users.get(iD);
        if ( user == null)
            throw new NoUserFoundException(iD);
        return user;
    }
    
    /** 
     * Gives all users in the library users collection.
     * 
     * @return A collection with all users assigned.
     */
    Collection<User> getAllUsers() {
        return Collections.unmodifiableCollection(_users.values());
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
     * @throws EmptyUserNameOrEmailException Is thrown if user name or email are empty strings.
     */
    int registerUser(String name, String email) throws EmptyUserNameOrEmailException {
        User user = new User(_userNextID, name, email);
        _users.put(_userNextID, user);
        return _userNextID++;
    }
    
    /** 
     * Adds a work in the respective collection.
     * 
     * @param work Work to be added.
     */
    void addWork(Work work) {
        _works.put(_workNextID++, work);
    }

    
    /** 
     * Gives the work with the given ID.
     * 
     * @param iD ID of the work to be searched.
     * 
     * @return The wanted work.
     * 
     * @throws NoWorkFoundException Is thrown if the work to be searched does not exist.
     */
    Work getWork(int iD) throws NoWorkFoundException {
        Work work = _works.get(iD);
        if (work == null)
            throw new NoWorkFoundException(iD);
        return work;
    }

    /**
     * Gives the works collection.
     * 
     * @return A collection with all works assigned.
     */
    Collection<Work> getAllWorks() {
        return Collections.unmodifiableCollection(_works.values());
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
