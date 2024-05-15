package fim.cermasi1;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

public class ComboCurrencyModel<E> implements ComboBoxModel {

    private List<Currency> items;
    private Currency selectedItem;

    public ComboCurrencyModel(List<Currency> items) {
        this.items = items;
        if (!items.isEmpty()) {
            selectedItem = items.get(0);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Currency) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public E getElementAt(int index) {
        return (E) this.items.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        // Implement this if you need to handle listeners
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        // Implement this if you need to handle listeners
    }
}
