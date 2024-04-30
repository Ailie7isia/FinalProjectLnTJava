package application;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {
	
    List<Button> fitur = new ArrayList<>();
    DataControl dataControl;
	VBox root = new VBox();
	TableView table = new TableView();
	ScrollPane scroll = new ScrollPane();

    
    Label title1 = new Label("PT CHIPI CHAPA");
    Button saveButton = new Button("Save");


	@Override
	public void start(Stage primaryStage) {
	    
			dataControl = new DataControl();

			primaryStage.setTitle("PT CHIPI CHAPA");
			root.setAlignment(Pos.CENTER); 
	        root.setSpacing(20); 
	        root.setPadding(new Insets(20));
	        
	        title1.setStyle("-fx-font-weight: bold;");
	        title1.setFont(new Font(24));
	        
        
	        fitur.add(new Button("Add New Data"));
	        fitur.add(new Button("View Data"));
	        fitur.add(new Button("Update Data"));
	        fitur.add(new Button("Delete Data"));
		
	        for (Button button : fitur) {
	            button.setPrefWidth(100);
	            button.setPrefHeight(30);
	        }
	        
	        root.getChildren().addAll(title1);
	        root.getChildren().addAll(fitur.toArray(new Button[0]));

	        fitur.get(0).setOnAction(e -> addDataPage(root));
	        fitur.get(1).setOnAction(e -> viewDataPage(root));
	        fitur.get(2).setOnAction(e -> updateDataPage(root));
	        fitur.get(3).setOnAction(e -> delDataPage(root));

			
			
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	private void delDataPage(VBox root) {
	    root.getChildren().clear();
	    
	    Label title5 = new Label("Delete Data");
    	title5.setStyle("-fx-font-weight: bold;");
    	title5.setFont(new Font(24));
    	    
    	TableView<DataStorage> table = new TableView<>();
    	TableColumn<DataStorage, Integer> first = new TableColumn<>("No.");
    	first.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(cellData.getValue()) + 1));
    	    
    	TableColumn<DataStorage, String> second = new TableColumn<>("Kode");
    	second.setCellValueFactory(new PropertyValueFactory<>("code")); 

    	TableColumn<DataStorage, String> third = new TableColumn<>("Nama");
    	third.setCellValueFactory(new PropertyValueFactory<>("name")); 

    	TableColumn<DataStorage, Integer> fourth = new TableColumn<>("Harga");
    	fourth.setCellValueFactory(new PropertyValueFactory<>("price")); 

    	TableColumn<DataStorage, Integer> fifth = new TableColumn<>("Stock");
    	 fifth.setCellValueFactory(new PropertyValueFactory<>("stock")); 
    	    
    	 table.getColumns().addAll(first, second, third, fourth, fifth);
    	 table.getItems().addAll(dataControl.getDataList());
    	    
    	 Button backButton = new Button("Back");
    	 backButton.setOnAction(event -> {
    	     root.getChildren().clear();
    	     root.getChildren().addAll(title1); 
    	     root.getChildren().addAll(fitur);  
    	});
    	 
    	 Button delButton = new Button("Delete");
    	 delButton.setOnAction(event -> {
	    	 
	    	  DataStorage selectedData = table.getSelectionModel().getSelectedItem();
	    	    if (selectedData != null) {
	    	    	
	    	        Alert alert = new Alert(AlertType.CONFIRMATION);
	    	        alert.setTitle("Confirm Deletion");
	    	        alert.setHeaderText("Confirm delete");
	    	        alert.setContentText("Are you sure you want to delete the selected menu row?");

	    	        Optional<ButtonType> result = alert.showAndWait();
	    	        
	                if (result.isPresent() && result.get() == ButtonType.OK) {

	                	dataControl.getDataList().remove(selectedData);
	                    System.out.println("Menu has been deleted");
	                }
	            } else {
	                System.out.println("Please select a row to delete.");
	            }
	        });
	        
	        backButton.setOnAction(event -> {
	            root.getChildren().clear();
	            root.getChildren().addAll(title1); 
	            root.getChildren().addAll(fitur);  
	            
                table.refresh();
	        });
	    	    
    	 VBox pageContent = new VBox(title5, table, delButton, backButton);
    	 pageContent.setSpacing(20);
    	 pageContent.setAlignment(Pos.CENTER);
    	 
    	 root.getChildren().addAll(pageContent);

	}
	    
	private void updateDataPage(VBox root) {
	    root.getChildren().clear();
	    	    
	    	Label title4 = new Label("Update Data");
	    	title4.setStyle("-fx-font-weight: bold;");
	    	title4.setFont(new Font(24));
	    	    
	    	TableView<DataStorage> table = new TableView<>();
	    	TableColumn<DataStorage, Integer> first = new TableColumn<>("No.");
	    	first.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(cellData.getValue()) + 1));
	    	    
	    	TableColumn<DataStorage, String> second = new TableColumn<>("Kode");
	    	second.setCellValueFactory(new PropertyValueFactory<>("code")); 

	    	TableColumn<DataStorage, String> third = new TableColumn<>("Nama");
	    	third.setCellValueFactory(new PropertyValueFactory<>("name")); 

	    	TableColumn<DataStorage, Integer> fourth = new TableColumn<>("Harga");
	    	fourth.setCellValueFactory(new PropertyValueFactory<>("price")); 

	    	TableColumn<DataStorage, Integer> fifth = new TableColumn<>("Stock");
	    	 fifth.setCellValueFactory(new PropertyValueFactory<>("stock")); 
	    	    
	    	 table.getColumns().addAll(first, second, third, fourth, fifth);
	    	 table.getItems().addAll(dataControl.getDataList());
	    	    
	    	 Button backButton = new Button("Back");
	    	 backButton.setOnAction(event -> {
	    	     root.getChildren().clear();
	    	     root.getChildren().addAll(title1); 
	    	     root.getChildren().addAll(fitur);  
	    	});
	    	    
	      TextField newPrice = new TextField();
	      newPrice.setPromptText("New Price");
	      TextField newStock = new TextField();
	      newStock.setPromptText("New Stock");
	    	    
	      Button updateButton = new Button("Update");
	      updateButton.setOnAction(event -> {
	    	 
	    	  DataStorage selectedData = table.getSelectionModel().getSelectedItem();
	    	    if (selectedData != null) {
	    	    	
	    	        Alert alert = new Alert(AlertType.CONFIRMATION);
	    	        alert.setTitle("Confirm Update");
	    	        alert.setHeaderText("Confirm Update");
	    	        alert.setContentText("Are you sure you want to update the data?");

	    	        Optional<ButtonType> result = alert.showAndWait();
	    	        if (result.isPresent() && result.get() == ButtonType.OK) {
	    	            String Nprice = newPrice.getText();
	    	            String Nstock = newStock.getText();
	    	            try {
	    	                int price = Nprice.isEmpty() ? selectedData.getPrice() : Integer.parseInt(Nprice);
	    	                int stock = Nstock.isEmpty() ? selectedData.getStock() : Integer.parseInt(Nstock);
	    	                
	    	                selectedData.setPrice(price);
	    	                selectedData.setStock(stock);

	    	                System.out.println("Data updated successfully.");

	    	                table.refresh();
	    	                newPrice.clear();
	    	                newStock.clear();
	    	                
	    	            } catch (NumberFormatException e) {
	    	                System.out.println("Enter only numbers for price and stocks!");
	    	            }
	    	        }
	    	    } else {
	    	        System.out.println("Please select a row to update.");
	    	    }
	    	});
	    	    
	    	    VBox updateBox = new VBox();
	    	    updateBox.getChildren().addAll(newPrice, newStock, updateButton);
	    	    updateBox.setSpacing(10);
	    	    
	    	    VBox pageContent = new VBox(title4, table, updateBox, backButton);
	    	    pageContent.setSpacing(20);
	    	    pageContent.setAlignment(Pos.CENTER);
	    	    
	    	    root.getChildren().addAll(pageContent);

	    }
	
	private void viewDataPage(VBox root) {
	    root.getChildren().clear();
	    table.setEditable(true);
	    
	    Label title3 = new Label("View Data");
	    title3.setStyle("-fx-font-weight: bold;");
        title3.setFont(new Font(24));
	    
    	TableView<DataStorage> table = new TableView<>();
    	TableColumn<DataStorage, Integer> first = new TableColumn<>("No.");
    	first.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(cellData.getValue()) + 1));
    	    
    	TableColumn<DataStorage, String> second = new TableColumn<>("Kode");
    	second.setCellValueFactory(new PropertyValueFactory<>("code")); 

    	TableColumn<DataStorage, String> third = new TableColumn<>("Nama");
    	third.setCellValueFactory(new PropertyValueFactory<>("name")); 

    	TableColumn<DataStorage, Integer> fourth = new TableColumn<>("Harga");
    	fourth.setCellValueFactory(new PropertyValueFactory<>("price")); 

    	TableColumn<DataStorage, Integer> fifth = new TableColumn<>("Stock");
    	 fifth.setCellValueFactory(new PropertyValueFactory<>("stock")); 
	    
	    table.getColumns().addAll(first, second, third, fourth, fifth);
	    
	    Button backButton = new Button("Back");
	    backButton.setOnAction(event -> {
	        root.getChildren().clear();
	        root.getChildren().addAll(title1); 
	        root.getChildren().addAll(fitur);  
	    });
	    
	    table.getItems().clear();
	     
    	scroll.setContent(table);
    	scroll.setPrefSize(200, 200);

	    table.getItems().addAll(dataControl.getDataList());
	    root.getChildren().addAll(title3, scroll, backButton);
	    
	}
	
	private void addDataPage(VBox root) {
	    root.getChildren().clear();
	    Random rd = new Random();
	    int num1 = rd.nextInt(10), num2 = rd.nextInt(10), num3 = rd.nextInt(10);
	    
	    Label title2 = new Label("Add New Data");
	    title2.setStyle("-fx-font-weight: bold;");
	    title2.setFont(new Font(24));
	    
	    VBox.setMargin(title2, new Insets(0, 0, 20, 0));
	    VBox titleContainer = new VBox(title2);
	    titleContainer.setAlignment(Pos.CENTER);
	    
	    Button backButton = new Button("Back");
	    backButton.setOnAction(event -> {
	        root.getChildren().clear();
	        root.getChildren().addAll(title1); 
	        root.getChildren().addAll(fitur);  
	    });
   
	    Label code = new Label("Kode Menu");
	    Label textCode = new Label("PD-"+ num1 + num2 + num3);

	    Label name = new Label("Nama Menu");
	    TextField textName = new TextField();
	    textName.setPromptText("Insert nama menu");
	    
	    Label price = new Label("Harga Menu");
	    TextField textPrice = new TextField();
	    textPrice.setPromptText("Insert harga menu");
	    
	    Label stock = new Label("Stok Menu");
	    TextField textStock = new TextField();
	    textStock.setPromptText("Insert jumlah stock menu");
	    
	    FlowPane flow1 = new FlowPane();
	    flow1.setHgap(15);
	    flow1.getChildren().addAll(name, textName);
	    
	    FlowPane flow2 = new FlowPane();
	    flow2.setHgap(13);
	    flow2.getChildren().addAll(price, textPrice);
	    
	    FlowPane flow3 = new FlowPane();
	    flow3.setHgap(20);
	    flow3.getChildren().addAll(stock, textStock);
	    
	    FlowPane flow5 = new FlowPane();
	    flow5.setHgap(20);
	    flow5.getChildren().addAll(code, textCode);
	    
        saveButton.setOnAction(event -> 
        	handleSaveBtn(
        	textCode.getText(),
        	textName.getText(), 
        	textPrice.getText(), 
        	textStock.getText()));
	    
	    FlowPane flow4 = new FlowPane();
	    flow4.setHgap(10);
	    flow4.setAlignment(Pos.CENTER);
	    flow4.getChildren().addAll(backButton, saveButton);
        
	    root.getChildren().addAll(title2, flow5, flow1, flow2, flow3, flow4);
	   
	}
	
	private void handleSaveBtn(String code, String name, String priceStr, String stockStr) {
		 if (name.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
		        System.out.println("All fields must be filled!");
		        return;
		 }
		 
		 try {
	            int price = Integer.parseInt(priceStr);
	            int stock = Integer.parseInt(stockStr);
	            dataControl.addData(code, name, price, stock);
	            System.out.println("Data saved successfully.");
	            back();
	        } catch (NumberFormatException e) {
	            System.out.println("Enter only numbers for price and stocks!");
	        }

	    }
	 
	private void back() {
		 	root.getChildren().clear();
		 	root.getChildren().addAll(title1); 
	        root.getChildren().addAll(fitur); 
		}
	 
	public static void main(String[] args) {
		launch(args);
	}
}
