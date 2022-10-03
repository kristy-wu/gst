package com.dcsplab.vdui.components;

import com.dcsplab.vdui.UIConstants;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class VDMultiSelectListBox<T> extends MultiSelectListBox<T> implements HasStyle {
  
  private static final long serialVersionUID = 3825169125855464597L;
  
  final ListDataProvider<T> dataProvider;
  
  public VDMultiSelectListBox() {
    this(new ArrayList<T>());
  }
  
  public VDMultiSelectListBox(Collection<T> items) {
    if (items == null) {
      items = new ArrayList<T>();
    }
    dataProvider = DataProvider.ofCollection(items);
    super.setDataProvider(dataProvider);
    
    getStyle().set("border", "1px solid " + UIConstants.DEFAULT_ICON_ENABLED_COLOR);
  }
  
  public void sortItems(Comparator<T> comp) {
    Collection<T> currentItems = dataProvider.getItems();
    ArrayList<T> listItems = new ArrayList<T>(currentItems);
    listItems.sort(comp);
    
    currentItems.clear();
    currentItems.addAll(listItems);
    dataProvider.refreshAll();
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
  
  public void addItems(Collection<T> items) {
    if (items == null) {
      return;
    }
    
    Collection<T> currentItems = dataProvider.getItems();
    for (T item : items) {
      if (currentItems.contains(item)) {
        continue;
      }
      currentItems.add(item);
    }
    
    refresh();
  }
  
  public Collection<T> getList() {
    return dataProvider.getItems();
  }
  
  public void remove(T item) {
    if (item == null) {
      return;
    }
    Collection<T> currentItems = dataProvider.getItems();
    currentItems.remove(item);
    dataProvider.refreshAll();
  }
  
  public void removeAt(int index) {
    Collection<T> currentItems = dataProvider.getItems();
    if (index < 0 || currentItems.size() <= index) {
      return;
    }
    
    ArrayList<T> listItems = new ArrayList<T>(currentItems);
    listItems.remove(index);
    
    currentItems.clear();
    currentItems.addAll(listItems);
    dataProvider.refreshAll();
  }
  
  private void baseremove(T item) {
    if (item == null) {
      return;
    }
    Collection<T> currentItems = dataProvider.getItems();
    currentItems.remove(item);
  }
  
  public void removeList(Collection<T> items) {
    if (items.size() < 1) {
      return;
    }
    
    Iterator<T> iter = items.iterator();
    while (iter.hasNext()) {
      T obj = iter.next();
      baseremove(obj);
    }
    
    dataProvider.refreshAll();
  }
  
  public void addAt(int index, T item) {
    Collection<T> currentItems = dataProvider.getItems();
    if (index < 0 || currentItems.size() < index - 1) {
      return;
    }
    ArrayList<T> listItems = new ArrayList<T>(currentItems);
    listItems.add(index, item);
    
    currentItems.clear();
    currentItems.addAll(listItems);
    dataProvider.refreshAll();
  }
  
  public T getItemAt(int index) {
    Collection<T> currentItems = dataProvider.getItems();
    ArrayList<T> listItems = new ArrayList<T>(currentItems);
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
}
