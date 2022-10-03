package com.dcsplab.vdui.components;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.ArrayList;
import java.util.Collection;

public class VDComboBox<T> extends ComboBox<T> {
  
  private static final long serialVersionUID = -6284002022822326126L;
  
  private final ListDataProvider<T> dataProvider;
  
  public VDComboBox() {
    super();
    
    dataProvider = DataProvider.ofCollection(new ArrayList<T>());
    setDataProvider(dataProvider);
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
}
