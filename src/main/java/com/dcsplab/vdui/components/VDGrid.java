package com.dcsplab.vdui.components;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.ArrayList;
import java.util.Collection;

public class VDGrid<T> extends Grid<T> {
  private static final long serialVersionUID = 6286550015569494771L;
  private final ListDataProvider<T> dataProvider;

  public VDGrid() {
    super();
    addThemeVariants(GridVariant.LUMO_COMPACT);
    addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

    /* 2022-09-14 */
    addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

    setSizeFull();

    dataProvider = DataProvider.ofCollection(new ArrayList<>());
    setDataProvider(dataProvider);
  }

  public void addItems(Collection<T> items) {
    if (items == null) {
      return;
    }

    Collection<T> currentItems = dataProvider.getItems();
    currentItems.addAll(items);

    refresh();
  }

  public Collection<T> getItems() {
    return dataProvider.getItems();
  }

  public void setItems(Collection<T> items) {
    if (items == null) {
      return;
    }

    Collection<T> currentItems = dataProvider.getItems();
    currentItems.clear();
    currentItems.addAll(items);
    dataProvider.refreshAll();
  }

  public ArrayList<T> subset(int startIndex, int endIndex) {

    Collection<T> currentItems = dataProvider.getItems();
    ArrayList<T> list = new ArrayList<>(currentItems);

    ArrayList<T> sublist = new ArrayList<>();
    for (int i = startIndex; i <= endIndex; i++) {
      sublist.add(list.get(i));
    }
    return sublist;
  }

  public void clear() {
    Collection<T> currentItems = dataProvider.getItems();
    currentItems.clear();
    dataProvider.refreshAll();
  }

  public void remove(T item) {
    if (item == null) {
      return;
    }
    Collection<T> currentItems = dataProvider.getItems();
    currentItems.remove(item);
    dataProvider.refreshAll();
  }

  public void removeItems(Collection<T> items) {
    if (items == null) {
      return;
    }

    Collection<T> currentItems = dataProvider.getItems();
    currentItems.removeAll(items);
    dataProvider.refreshAll();
  }

  public void removeAt(int index) {
    Collection<T> currentItems = dataProvider.getItems();
    if (index < 0 || currentItems.size() <= index) {
      return;
    }

    ArrayList<T> listItems = new ArrayList<>(currentItems);
    listItems.remove(index);

    currentItems.clear();
    currentItems.addAll(listItems);
    dataProvider.refreshAll();
  }

  public void addAt(int index, T item) {
    Collection<T> currentItems = dataProvider.getItems();
    if (index < 0 || currentItems.size() < index - 1) {
      return;
    }
    ArrayList<T> listItems = new ArrayList<>(currentItems);
    listItems.add(index, item);

    currentItems.clear();
    currentItems.addAll(listItems);
    dataProvider.refreshAll();
  }

  public T getItemAt(int index) {
    Collection<T> currentItems = dataProvider.getItems();
    ArrayList<T> listItems = new ArrayList<>(currentItems);
    return listItems.get(index);
  }

  public void refreshItem(T item) {
    if (item == null) {
      return;
    }
    dataProvider.refreshItem(item);
  }

  public void refresh() {
    dataProvider.refreshAll();
  }

  public void moveRow(int row, int toIndex) {
    Collection<T> currentItems = dataProvider.getItems();
    if (row < 0 || row >= currentItems.size()) {
      return;
    }
    if (toIndex < 0 || toIndex >= currentItems.size()) {
      return;
    }
    if (row == toIndex) {
      return;
    }

    ArrayList<T> listItems = new ArrayList<>(currentItems);
    T item = listItems.remove(row);
    listItems.add(toIndex, item);

    currentItems.clear();
    currentItems.addAll(listItems);
    dataProvider.refreshAll();
  }
}
