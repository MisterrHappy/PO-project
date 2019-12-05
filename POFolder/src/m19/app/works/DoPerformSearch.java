package m19.app.works;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import m19.core.LibraryManager;
import m19.core.Work;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
/**
 * 4.3.3. Perform search according to miscellaneous criteria.
 */
public class DoPerformSearch extends Command<LibraryManager> {

    private Input<String> _term;

    /**
     * @param m
     */
    public DoPerformSearch(LibraryManager m) {
        super(Label.PERFORM_SEARCH, m);
        _term = _form.addStringInput(Message.requestSearchTerm());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() {
        _form.parse();
        List<Work> works = new ArrayList<>(_receiver.performSearch(_term.value()));
        if (works.isEmpty())
            _display.addLine("");
        else {
            Collections.sort(works, Work.getComparatorById());
            for (Work work: works)
                _display.addLine(work.getDescription());
        }
        _display.display();
    }
    
}
