package m19.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import m19.core.exception.BadEntrySpecificationException;
import m19.core.exception.FailedToOpenFileException;
import m19.core.exception.ImportFileException;
import m19.core.exception.MissingFileAssociationException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import m19.core.Library;

/**
 * The façade class.
 */
public class LibraryManager {

  private Library _library = new Library();  // FIXME initialize this attribute
  private String _fileNameAssociation = null;
  
  public int getCurrentDate() {
    return _library.getCurrentDate();
  }
  
  public void advanceDays(int nDays) {
    _library.advanceDays(nDays);
  }

  public String getUser(int iD) {
    return _library.getUser(iD);
  }

  public String getAllUsers() {
    return _library.getAllUsers();
  }

  public int registerUser(String name, String email) {
    return _library.registerUser(name, email);
  }

  public String getWork(int iD) {
    return _library.getWork(iD);
  }

  public String getAllWorks() {
    return _library.getAllWorks();
  }

  /**
   * Serialize the persistent state of this application.
   * 
   * @throws MissingFileAssociationException if the name of the file to store the persistent
   *         state has not been set yet.
   * @throws IOException if some error happen during the serialization of the persistent state

   */
  public void save() throws MissingFileAssociationException, IOException {
    if (_fileNameAssociation == null)
      throw new MissingFileAssociationException();

    FileOutputStream file = new FileOutputStream(_fileNameAssociation);
    ObjectOutputStream librarySave = new ObjectOutputStream(file);
    librarySave.writeObject(_library);
    librarySave.close();
    file.close();
  }
  
  /**
   * Serialize the persistent state of this application into the specified file.
   * 
   * @param filename the name of the target file
   *
   * @throws MissingFileAssociationException if the name of the file to store the persistent
   *         is not a valid one.
   * @throws IOException if some error happen during the serialization of the persistent state
   */
  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    if (filename == null)
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
   * @throws FileNotFoundException if the file does not exist
   * @throws ClassNotFoundException 
   */
  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
    File fileToLoad = new File(filename);
    if (!fileToLoad.isFile())
      throw new FileNotFoundException();

    ObjectInputStream libraryLoad = null;
    FileInputStream file = new FileInputStream(filename);
    libraryLoad = new ObjectInputStream(file);
    Library library = (Library) libraryLoad.readObject();
    _library = library;
    libraryLoad.close();
    file.close();
  }

  /**
   * Set the state of this application from a textual representation stored into a file.
   * 
   * @param datafile the filename of the file with the textual representation of the state of this application.
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
