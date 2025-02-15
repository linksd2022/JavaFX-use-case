package org.example.demo.view.table;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.demo.entity.PersonEntity;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class BasicTableView implements FxmlView<BasicTableViewModel>, Initializable {

    @InjectViewModel
    private BasicTableViewModel viewModel;

    @FXML
    private javafx.scene.control.TableView<PersonEntity> tableView;

    @FXML
    private TableColumn<PersonEntity, String> nameColumn;

    @FXML
    private TableColumn<PersonEntity, Long> ageColumn;

    @FXML
    private TableColumn<PersonEntity, Long> operationColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        operationColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        operationColumn.setCellFactory(column -> {
            return new TableCell<>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                        setGraphic(null);
                    } else {

                        Button editButton = new Button("编辑", FontIcon.of(Feather.EDIT));
                        editButton.setOnAction(event -> {
                            System.out.println("编辑" + item + getTableRow().getItem().getName());
                        });

                        Button deleteButton = new Button("删除", FontIcon.of(Feather.TRASH));
                        deleteButton.setOnAction(event -> {
                            System.out.println("删除" + item + getTableRow().getItem().getName());
                        });

                        HBox box = new HBox(editButton, deleteButton);
                        box.setAlignment(Pos.CENTER);
                        box.setSpacing(5.0);
                        setGraphic(box);
                    }
                }
            };
        });
        ObservableList<PersonEntity> data = FXCollections.observableArrayList(
                new PersonEntity(1L, "曹操", 30L),
                new PersonEntity(2L, "刘备", 30L),
                new PersonEntity(3L, "司马懿", 30L),
                new PersonEntity(4L, "曹操", 30L),
                new PersonEntity(5L, "刘备", 30L),
                new PersonEntity(6L, "司马懿", 30L),
                new PersonEntity(7L, "曹操", 30L),
                new PersonEntity(8L, "刘备", 30L),
                new PersonEntity(9L, "司马懿", 30L),
                new PersonEntity(10L, "曹操", 30L),
                new PersonEntity(11L, "刘备", 30L),
                new PersonEntity(12L, "司马懿", 30L)
        );
        tableView.setItems(data);
    }

}
