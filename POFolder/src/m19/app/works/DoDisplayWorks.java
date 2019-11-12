package m19.app.works;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;

import m19.core.Work;
import java.util.Map;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
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
        Map<Integer, Work> works = _receiver.getAllWorks();

        if (works.isEmpty())
            _display.addLine("");
        else {
        
            List<Work> orderedWorks = new ArrayList<>(works.values());
            Collections.sort(orderedWorks, new Comparator<Work>() {
            @Override
            public int compare(Work a, Work b) {
                return a.hashCode() - b.hashCode();
            }
            });

            for (Work temp: orderedWorks)
            _display.addLine(temp.getDescription());
        }
        _display.display();
    }
    
}
