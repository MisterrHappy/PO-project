package m19.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

import m19.core.exception.BadEntrySpecificationException;

import m19.core.Library;
import m19.core.work.DVD;
import m19.core.work.Book;
import m19.core.Category;

public class Parser {

    private Library _library;

    Parser(Library lib) {
        _library = lib;
    }

    void parseFile(String filename) throws IOException, BadEntrySpecificationException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null)
                parseLine(line);
        }
    }

    private void parseLine(String line) throws BadEntrySpecificationException {
        String[] components = line.split(":");

        switch(components[0]) {
        case "DVD":
            parseDVD(components, line);
            break;

        case "BOOK":
            parseBook(components, line);
            break;
        case "USER":
            parseUser(components, line);
            break;
            
        default:
            throw new BadEntrySpecificationException("Invalid type " + components[0] +
                                                    " in line " + line);
        }
    }

    private void parseDVD(String[] components, String line) throws BadEntrySpecificationException {
        if (components.length != 7)
            throw new BadEntrySpecificationException("Wrong number of fields (6) in " + line);
        DVD dvd = new DVD(_library.getWorkNextID(), Integer.parseInt(components[3]), components[1], Integer.parseInt(components[6]), Category.valueOf(components[4]), components[2], components[5]);
        _library.addWork(dvd);
    }

    private void parseBook(String[] components, String line) throws BadEntrySpecificationException {
        if (components.length != 7)
            throw new BadEntrySpecificationException("Wrong number of fields (6) in " + line);

        Book book = new Book(_library.getWorkNextID(), Integer.parseInt(components[3]), components[1],
                            Integer.parseInt(components[6]), Category.valueOf(components[4]), components[2], components[5]);
        
        _library.addWork(book);
    }

    private void parseUser(String[] components, String line) throws BadEntrySpecificationException {
        if (components.length != 3)
            throw new BadEntrySpecificationException("Wrong number of fields (2) in " + line);
        _library.registerUser(components[1], components[2]);
    }
    
}