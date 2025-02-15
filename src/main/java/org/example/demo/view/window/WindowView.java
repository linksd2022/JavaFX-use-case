package org.example.demo.view.window;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.example.demo.view.login.LoginView;
import org.example.demo.view.login.LoginViewModel;
import org.example.demo.view.main.MainView;
import org.example.demo.view.main.MainViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowView implements FxmlView<WindowViewModel>, Initializable {
    @FXML
    private StackPane rootPane;

    @InjectViewModel
    private WindowViewModel viewModel;

    public static WindowView windowView;

    private StackPane loading;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WindowView.windowView = this;

        // 打开登录页面
        showLoginView();
    }

    public void showLoginView() {
        rootPane.getChildren().clear();
        ViewTuple<LoginView, LoginViewModel> viewTuple = FluentViewLoader.fxmlView(LoginView.class).load();
        viewTuple.getViewModel().setWindowView(this);
        rootPane.getChildren().add(viewTuple.getView());
    }

    public void showMainView() {
        rootPane.getChildren().clear();
        ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();
        viewTuple.getViewModel().setWindowView(this);
        rootPane.getChildren().add(viewTuple.getView());
    }

    public void addLoading() {
        StackPane stackPane = new StackPane();
        stackPane.setBackground(Background.fill(Color.GRAY));
        stackPane.setOpacity(0.8);
        ImageView imageView = new ImageView("/org/example/demo/image/loading.png");
        imageView.setFitWidth(24);
        imageView.setFitHeight(24);
        stackPane.getChildren().add(imageView);

        rootPane.getChildren().add(stackPane);
        this.loading = stackPane;
    }

    public void removeLoading() {
        rootPane.getChildren().remove(loading);
        this.loading = null;
    }

}
