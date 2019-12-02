package m19.app.works;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;

import m19.core.Work;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 4.3.2. Display all works.
 */
public class DoDisplayWorks extends Command<LibraryManager> {

    /**
     * @param receiver
     */
    public DoDisplayWorks(LibraryManager receiver) {
        super(Label.SHOW_WORKS, receiver);
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() {
        Collection<Work> works = _receiver.getAllWorks();

        if (works.isEmpty())
            _display.addLine("");
        else {
        
            List<Work> orderedWorks = new ArrayList<>(works);
            Collections.sort(orderedWorks, Work.getComparatorById());

            for (Work temp: orderedWorks)
                _display.addLine(temp.getDescription());
        }
        _display.display();
    }
    
}
