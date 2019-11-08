package m19.app.works;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.app.exception.NoSuchWorkException;
import m19.app.works.Message;

/**
 * 4.3.1. Display work.
 */
public class DoDisplayWork extends Command<LibraryManager> {

    private Input<Integer> _iD;

    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(Label.SHOW_WORK, receiver);
        _iD = _form.addIntegerInput(Message.requestWorkId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {

        try {
        _form.parse();

        _display.addLine(_receiver.getWork(_iD.value()));
        _display.display();
        } catch (IndexOutOfBoundsException ioobe) {
            throw new NoSuchWorkException(_iD.value());
        }
    }
    
}
