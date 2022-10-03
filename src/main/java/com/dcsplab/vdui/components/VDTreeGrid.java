package com.dcsplab.vdui.components;

import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.hierarchy.TreeData;

import java.util.List;

public class VDTreeGrid<T> extends TreeGrid<T> {

  private static final long serialVersionUID = -8856326851862793693L;

  public VDTreeGrid() {
    super();

    addThemeVariants(GridVariant.LUMO_COMPACT);
    addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
    addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
  }

  public void refreshItem(T item) {
    if (item != null) {
      getDataCommunicator().refresh(item);
    }
  }

  public void refreshTree(T item) {
    if (item == null) {
      return;
    }
    TreeData<T> treeData = this.getTreeData();
    List<T> children = treeData.getChildren(item);
    for (T child : children) {
      getDataProvider().refreshItem(child);

      refreshTree(child);
    }
    getDataProvider().refreshItem(item, true);
  }

  public void refresh() {
    getDataProvider().refreshAll();
  }
}
