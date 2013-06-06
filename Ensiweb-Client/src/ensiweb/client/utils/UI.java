/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.utils;

import ensiweb.client.entity.Stock;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;
import javafx.collections.ObservableList;

/**
 *
 * @author thibaut
 */
public class UI {

    static public Map<Integer, Integer> refactorDate(String stackBy, ObservableList<Stock> data) throws ParseException {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        Integer value = 0;
        for (Stock stock : data) {
            int tmp = DateUtils.getDatebyStack(stackBy, stock.getDate());
            if (d.containsKey(tmp)) {
                value = d.get(tmp) + stock.getStock();
                d.remove(tmp);
                d.put(tmp, value);
            } else {
                d.put(tmp, stock.getStock());
            }
        }

        return d;
    }
}
