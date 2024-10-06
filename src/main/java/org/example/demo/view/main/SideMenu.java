package org.example.demo.view.main;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.HashMap;

// 左侧菜单
public class SideMenu extends StackPane {

    private MainView mainView;

    public SideMenu() {

        HashMap<TreeItem<String>, Ikon> treeItemIconMap = new HashMap<>();

        // 创建根节点
        TreeItem<String> rootItem = new TreeItem<>();

        // 创建子节点
        TreeItem<String> table = new TreeItem<>("表格");
        treeItemIconMap.put(table, Feather.COLUMNS);
        TreeItem<String> basicTable = new TreeItem<>("基础表格");
        TreeItem<String> editableTable = new TreeItem<>("可编辑表格");
        TreeItem<String> groupingHeadTable = new TreeItem<>("表头分组表格");
        TreeItem<String> centerTable = new TreeItem<>("居中表格");
        table.getChildren().addAll(basicTable, editableTable, groupingHeadTable, centerTable);
        table.setExpanded(true);

        TreeItem<String> image = new TreeItem<>("图片");
        treeItemIconMap.put(image, Feather.IMAGE);

        TreeItem<String> fxPagination = new TreeItem<>("分页");
        treeItemIconMap.put(fxPagination, Feather.FAST_FORWARD);

        TreeItem<String> child3 = new TreeItem<>("资源读取");
        treeItemIconMap.put(child3, Feather.AT_SIGN);
        TreeItem<String> subChild31 = new TreeItem<>("字体读取");
        TreeItem<String> subChild32 = new TreeItem<>("文件图标读取");
        child3.getChildren().addAll(subChild31, subChild32);
        child3.setExpanded(true);

        TreeItem<String> child4 = new TreeItem<>("反馈组件");
        treeItemIconMap.put(child4, Feather.MESSAGE_CIRCLE);
        TreeItem<String> subChild41 = new TreeItem<>("加载");
        TreeItem<String> subChild42 = new TreeItem<>("消息提示");
        TreeItem<String> subChild43 = new TreeItem<>("消息弹出框");
        child4.getChildren().addAll(subChild41, subChild42, subChild43);
        child4.setExpanded(true);

        TreeItem<String> child5 = new TreeItem<>("表单");
        treeItemIconMap.put(child5, Feather.FILE_TEXT);
        TreeItem<String> subChild51 = new TreeItem<>("表单校验");
        child5.getChildren().addAll(subChild51);
        child5.setExpanded(true);

        TreeItem<String> child6 = new TreeItem<>("JavaFX UI组件Bug");
        treeItemIconMap.put(child6, Feather.TOOL);
        TreeItem<String> subChild61 = new TreeItem<>("表格组件Bug");
        TreeItem<String> subChild62 = new TreeItem<>("输入组件Bug");
        child6.getChildren().addAll(subChild61, subChild62);
        child6.setExpanded(true);

        TreeItem<String> global = new TreeItem<>("全局配置");
        treeItemIconMap.put(global, Feather.APERTURE);
        TreeItem<String> theme = new TreeItem<>("主题");
        TreeItem<String> font = new TreeItem<>("字体");
        global.getChildren().addAll(theme, font);
        global.setExpanded(true);

        TreeItem<String> child8 = new TreeItem<>("基础组件");
        treeItemIconMap.put(child8, Feather.AWARD);
        TreeItem<String> subChild81 = new TreeItem<>("图标");
        child8.getChildren().addAll(subChild81);
        child8.setExpanded(true);

        // 根节点添加子节点
        rootItem.getChildren().addAll(table, image, fxPagination, child3, child4, child5, child6, global, child8);
        rootItem.setExpanded(true);

        // 创建TreeView，设置根节点
        TreeView<String> treeView = new TreeView<>(rootItem);
        // 隐藏根节点
        treeView.setShowRoot(false);
        // 设置TreeCell
        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> stringTreeView) {
                TreeCell<String> treeCell = new TreeCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        // 隐藏折叠图标
                        setDisclosureNode(null);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label label = new Label(item);
                            label.getStyleClass().add("main-view-left-tree-cell-label");
                            if (treeItemIconMap.containsKey(this.getTreeItem())) {
                                label.setGraphic(FontIcon.of(treeItemIconMap.get(this.getTreeItem()))); // 设置图标
                            }
                            label.setGraphicTextGap(10);
                            setGraphic(label);
                        }
                    }
                };
                treeCell.getStyleClass().add("main-view-left-tree-cell");
                return treeCell;
            }
        });

        // 监听用户选择模块变化，更新窗口右侧页面
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isLeaf()) {
                if (mainView != null) {
                    mainView.updateTabPane(newValue.getValue());
                }
            }
        });

        // StackPane添加TreeView
        getChildren().addAll(treeView);
    }

    public void init(MainView mainView) {
        this.mainView = mainView;
    }

}