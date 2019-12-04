package com.ankita.todoList.DataModel;

import javafx.collections.FXCollections;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TodoData {         //  it is used to stored data in disk, since we are making a singleton whenver any part of the application access the data it has to call TodoData.getInstance().getItem()

    private List<String> list;
    private static TodoData instance = new TodoData();      //create instance of class so singleton effecttively
    private static String filename = "TodoListItems.txt";

    private  List<TodoItem>  todoItems;     // want to store an items
     private DateTimeFormatter  formatter;        //also need a date time formatter

   public static TodoData getInstance() {     // need a public method to return th instance
        return instance;
   }

   private TodoData(){              // we can use a private constructor for date format
       formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
   }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

   // public void setTodoItems(List<TodoItem> todoItems) {  //not using this code anymore
  //      this.todoItems = todoItems;
   // }

    public void loadTodoItems() throws IOException
    {
        todoItems = FXCollections.observableArrayList();        //FxCollections enables to hold arraylist in this format
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try
        {
            while((input=br.readLine()) != null){
                String[] itempieces = input.split("\t");
                String details = itempieces[0];
                String shortDescription = itempieces[1];
                String dateString = itempieces[2];
                LocalDate date = LocalDate.parse(dateString,formatter);     // covert date string and concert the format
                TodoItem todoItem = new TodoItem(details,shortDescription,date);
                todoItems.add(todoItem);                    // it will arraylist of an items
            }
        } finally {
            if(br != null){
                br.close();
            }
        }
    }

    public void storeTodoItems() throws IOException{        //Writing the data
       Path path = Paths.get(filename);
       BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<TodoItem> iter = todoItems.iterator();         // iterator works each item
            while(iter.hasNext()){                                  // retreive one item at a time
                TodoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s",item.getDetails(),item.getShortDescription(),item.getDeadLine().format(formatter)));  // writing data to the file
                bw.newLine();
            }


        }finally{
            if(bw !=null){
                bw.close();
            }
        }


    }
}
