package com.ankita.todoList;

import com.ankita.todoList.DataModel.TodoData;
import com.ankita.todoList.DataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;               //1) step
    @FXML
    private ListView<TodoItem> todoListView;                  //2) to bind with mainWindow (4)change ListView to ListView<TodoItem>
    @FXML
    private TextArea itemDetailsTextArea;           //3) to print shortDescription in text area
    @FXML
    private Label deadLineLabel;

    public void initialize(){                   //2)step - to initialize the method in the controller
      /*  TodoItem item1 = new TodoItem("Meeting","i have a meeting with clients", LocalDate.of(2019, Month.APRIL,23));
        TodoItem item2 = new TodoItem("Pickup","i have to pick oishika from school", LocalDate.of(2019, Month.MARCH,2));
        TodoItem item3 = new TodoItem("Project Complete","i have to complete the project", LocalDate.of(2019, Month.APRIL,5));
        TodoItem item4 = new TodoItem("Cutting","i have to do my haircut", LocalDate.of(2019, Month.AUGUST,25));
        TodoItem item5 = new TodoItem("Shopping","i have do the grocery shopping", LocalDate.of(2019, Month.MAY,1));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        TodoData.getInstance().setTodoItems(todoItems);      */       //after creating the stop() in main(),code this line

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {     // to initialize method
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {      //overriden method changed,application will listen for an event
                if(t1 != null){
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getShortDescription());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d,yyyy");      // changing the format of a date
                    deadLineLabel.setText(df.format(item.getDeadLine()));                   // printing the deadline with the format
                }
            }
        });

        todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());      //todoListView is a fx:id of ListView in mainWindow,since we call by instance so after comment upper part Change code after setAll() to..
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);    // it will highlight the particulat details when you clicked in the left pane
        todoListView.getSelectionModel().selectFirst();         // to  set selected the very first item
    }


}
