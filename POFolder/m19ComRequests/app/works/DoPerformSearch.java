package m19.app.works;

import m19.core.LibraryManager;
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
        
    }
    
}
