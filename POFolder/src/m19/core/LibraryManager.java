package m19.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

import m19.core.exception.BadEntrySpecificationException;
import m19.core.exception.EmptyUserNameOrEmailException;
import m19.core.exception.ImportFileException;
import m19.core.exception.MissingFileAssociationException;
import m19.core.exception.NoUserFoundException;
import m19.core.exception.NoWorkFoundException;
import m19.core.exception.RuleBrokenException;
import m19.core.exception.UserIsNotSuspendedException;

/**
 * The façade class that holds a library and a file which can be initially loaded.
 * Authors: André Marinho and João Domingos
 */
public class LibraryManager {

    private Library _library = new Library(); 
    private String _fileNameAssociation;

    public List<Work> performSearch(String term) {
        return _library.performSearch(term);
    }
    
    public void payUserFine(User user) throws UserIsNotSuspendedException {
        _library.payUserFine(user);
    }

    public int requestWork(User user, Work work) throws RuleBrokenException{
        return _library.requestWork(user, work);
    }

    public int getCurrentDate() {
        return _library.getCurrentDate();
    }
    
    public void advanceDays(int nDays) {
        _library.advanceDays(nDays);
    }

    public User getUser(int iD) throws NoUserFoundException {
        return _library.getUser(iD);
    }

    public Collection<User> getAllUsers() {
        return _library.getAllUsers();
    }

    public int registerUser(String name, String email) throws EmptyUserNameOrEmailException {
        return _library.registerUser(name, email);
    }

    public Work getWork(int iD) throws NoWorkFoundException {
        return _library.getWork(iD);
    }

    public Collection<Work> getAllWorks() {
        return _library.getAllWorks();
    }


    /**
     * Serialize the persistent state of this application.
     * 
     * @throws MissingFileAssociationException if the name of the file to store the persistent
     *         state has not been set yet.
     * 
     * @throws IOException if some error happen during the serialization of the persistent state

    */
    public void save() throws MissingFileAssociationException, IOException {
        if (_fileNameAssociation == null)
            throw new MissingFileAssociationException();

        try (ObjectOutputStream librarySave = new ObjectOutputStream(new FileOutputStream(_fileNameAssociation))) {
    
            librarySave.writeObject(_library);

        }
    }
    
    /**
     * Serialize the persistent state of this application into the specified file.
     * 
     * @param filename the name of the target file
     *
     * @throws MissingFileAssociationException if the name of the file to store the persistent
     *         is not a valid one.
     * 
     * @throws IOException if some error happen during the serialization of the persistent state
     */
    public void saveAs(String filename) throws MissingFileAssociationException, IOException {
        if (filename.isEmpty())
            throw new MissingFileAssociationException();

        _fileNameAssociation = filename;
        save();
    }

    /**
     * Recover the previously serialized persitent state of this application.
     * 
     * @param filename the name of the file containing the perssitente state to recover
     *
     * @throws IOException if there is a reading error while processing the file
     * 
     * @throws FileNotFoundException if the file does not exist
     * 
     * @throws ClassNotFoundException if the class from readObject is not a library.
     */
    public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream libraryLoad = new ObjectInputStream(new FileInputStream(filename));) {
            Library library = (Library) libraryLoad.readObject();
            _library = library;

            _fileNameAssociation = filename;
        }
    }

    /**
     * Set the state of this application from a textual representation stored into a file.
     * 
     * @param datafile the filename of the file with the textual representation of the state of this application.
     * 
     * @throws ImportFileException if it happens some error during the parsing of the textual representation.
     */
    public void importFile(String datafile) throws ImportFileException {
        try {
            _library.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }
    
}
