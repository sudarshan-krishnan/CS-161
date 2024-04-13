package project;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Sudarshan Krishnan, kriss03
 * @author
 * @author
 * 
 * Integrated by Sudarshan Krishnan, Amit Jadhav and Sanjana Gondariya
 * Documented by Sudarshan Krishnan
 * 
 * This is the Group submission. 
 */

public class Controller {

	@FXML
	private CheckBox filledChk;

	@FXML
	private Slider redSlider;
	@FXML
	private Slider greenSlider;
	@FXML
	private Slider blueSlider;

	@FXML
	private Rectangle circleMode;
	@FXML
	private Rectangle lineMode;
	@FXML
	private Rectangle rectMode;
	@FXML
	private Rectangle textMode;

	@FXML
	private Line menuLine;
	@FXML
	private Rectangle menuRectangle;
	@FXML
	private Circle menuCircle;
	@FXML
	private Text menuText;
	
	@FXML 
	private Pane pane;

	private Color selectedColor;
	private Node selectedNode;
	private String insertMode;
	
	//These are the only two fields I added.
	private Node current;
	
	//I flipped the pencil image upside down because the cursor hotspot was in the wrong place.
	//This gave it an awkward feel and inaccuracy. Flipping the image was the easiest solution.
	private ImageCursor pencil = new ImageCursor(new Image("file:drawcursor.png"));

	@FXML
	void initialize() {
		
		assert blueSlider != null : "fx:id=\"blueSlider\" was not injected: check your FXML file 'main.fxml'.";
        assert circleMode != null : "fx:id=\"circleMode\" was not injected: check your FXML file 'main.fxml'.";
        assert filledChk != null : "fx:id=\"filledChk\" was not injected: check your FXML file 'main.fxml'.";
        assert greenSlider != null : "fx:id=\"greenSlider\" was not injected: check your FXML file 'main.fxml'.";
        assert lineMode != null : "fx:id=\"lineMode\" was not injected: check your FXML file 'main.fxml'.";
        assert menuCircle != null : "fx:id=\"menuCircle\" was not injected: check your FXML file 'main.fxml'.";
        assert menuLine != null : "fx:id=\"menuLine\" was not injected: check your FXML file 'main.fxml'.";
        assert menuRectangle != null : "fx:id=\"menuRectangle\" was not injected: check your FXML file 'main.fxml'.";
        assert menuText != null : "fx:id=\"menuText\" was not injected: check your FXML file 'main.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'main.fxml'.";
        assert rectMode != null : "fx:id=\"rectMode\" was not injected: check your FXML file 'main.fxml'.";
        assert redSlider != null : "fx:id=\"redSlider\" was not injected: check your FXML file 'main.fxml'.";
        assert textMode != null : "fx:id=\"textMode\" was not injected: check your FXML file 'main.fxml'.";
        
        //Set the cursor to the pencil in the drawing pane on startup.
        pane.setCursor(pencil);
        
        //This sets the starting values and initializes the color sliders.
        redSlider.setValue(150);
		blueSlider.setValue(100);
		greenSlider.setValue(50);
		redSlider.valueProperty().addListener(event -> colorChange());
		blueSlider.valueProperty().addListener(event -> colorChange());
		greenSlider.valueProperty().addListener(event -> colorChange());
		colorChange();
		
		filledChk.setOnAction( click -> {
			colorChange();
		});
		
	}

	//Set insertMode and select the preview shape
	
	//For all of these modes. I called the select method to make the barrier appear
	//around them on the shape menu.
	@FXML
	void circleMode(MouseEvent event) {
			select(circleMode);
			insertMode = "circle";
	}

	@FXML
	void lineMode(MouseEvent event) {
			select(lineMode);
			insertMode = "line";
	}

	@FXML
	void rectMode(MouseEvent event) {
			select(rectMode);
			insertMode = "rectangle";
	}
		

	@FXML
	void textMode(MouseEvent event) {
			select(textMode);
			insertMode = "text";
	}

	//Detects all key events
	@FXML
	void globalKeyEvents(KeyEvent e) {
      
		//If the escape key is pressed the deselect method is called.
		if(e.getCode() == KeyCode.ESCAPE){
			insertMode = "";
			deselect();
		}
		
		//if the backspace or delete key is pressed the last letter in the text object's string is removed.
		//If the last letter is removed and the key is pressed again the text object is removed.
		//If a non-text shape is selected, the backspace or delete key removes the shape from the pane.
		if (e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.DELETE){
			if (selectedNode instanceof Text){
				String s = ((Text) selectedNode).getText();
				if (s.length() > 0){
					s = s.substring(0, s.length() - 1);
					((Text) selectedNode).setText(s);

				} else {
					pane.getChildren().remove(selectedNode);
					deselect();
				}

			} else {
				pane.getChildren().remove(selectedNode);
				deselect();
			}
			

		} else {
			if (selectedNode instanceof Text){
				String s = ((Text) selectedNode).getText() + e.getText();
				((Text) selectedNode).setText(s);
			}

		}
		
	}
	

	@FXML
	//Mouse press create the shape
	void createShape(MouseEvent event) {
		
		//EventHandler to move the shape when selected.
		EventHandler<MouseEvent> moveShape = action -> {
			moveShape(action);
		};
		
		//EventHandlers to make the cursor become a hand when hovering over a shape.
		EventHandler<MouseEvent> cursorChange = action -> {
			pane.setCursor(Cursor.HAND);
		};
		
		EventHandler<MouseEvent> cursorReset = action -> {
			pane.setCursor(pencil);
		};
		
		//Try/catch to catch the exceptions caused when Nothing is Selected and the mouse drag event occurs.
		try {
			
			//If the selectedNode is the rectMode create a rectangle shape.
			if(selectedNode.equals(rectMode)) {
				Rectangle box = new Rectangle();
				
				//Event Handler to Select the shape.
				EventHandler<MouseEvent> selectShape = action ->{
					select(box);
					box.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveShape);
				};
				
				//setting the starting X-Y to the mouse's X-Y position.
				box.setX(event.getX());
				box.setY(event.getY());
				
				//set color to selectedColor and determine is the shape should be filled or not. 
				box.setStroke(selectedColor);
				box.setFill((filledChk.isSelected())? selectedColor: Color.WHITE);
				
				//adding the rectangle to the pane
				pane.getChildren().add(box);
				
				//add the event handlers to the rectangle and set current equal to box.
				box.addEventHandler(MouseEvent.MOUSE_PRESSED, selectShape);
				box.addEventHandler(MouseEvent.MOUSE_ENTERED, cursorChange);
				box.addEventHandler(MouseEvent.MOUSE_EXITED, cursorReset);
				current = box;
			}
			
			//If circleMode is selected, create circle shapes.
			if(selectedNode.equals(circleMode)) {
				Ellipse ball = new Ellipse();
				
				//EventHandler to select the shape.
				EventHandler<MouseEvent> selectShape = action ->{
					select(ball);
					ball.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveShape);
				};
		
				//Setting center X-Y to mouse's X-Y position.
				ball.setCenterX(event.getX());
				ball.setCenterY(event.getY());
				
				//set color to selectedColor and determine is the shape should be filled or not. 
				ball.setStroke(selectedColor);
				ball.setFill((filledChk.isSelected())? selectedColor: Color.WHITE);
				
				//Add the circle to the pane.
				pane.getChildren().add(ball);
				
				//add event handlers to the circle and set current equal to ball.
				ball.addEventHandler(MouseEvent.MOUSE_PRESSED, selectShape);
				ball.addEventHandler(MouseEvent.MOUSE_ENTERED, cursorChange);
				ball.addEventHandler(MouseEvent.MOUSE_EXITED, cursorReset);
				current = ball;
			}
			
			if (selectedNode.equals(textMode)) {
				Text txt = new Text();
				
				//EventHandler to select the shape.
				EventHandler<MouseEvent> selectShape = action ->{
					select(txt);
					txt.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveShape);
				};
				
				//set the starting X-Y to the mouses X-Y position and set the initial Text. 
				txt.setX(event.getX());
				txt.setY(event.getY());
				txt.setText("Text");
				
				//add the text shape to the pane.
				pane.getChildren().add(txt);
				
				//set color to selectedColor and determine is the shape should be filled or not. 
				txt.setStroke(selectedColor);
				txt.setFill((filledChk.isSelected())? selectedColor: Color.WHITE);
				
				//add event handlers to txt and set current equal to txt.
				txt.addEventHandler(MouseEvent.MOUSE_PRESSED, selectShape);
				txt.addEventHandler(MouseEvent.MOUSE_ENTERED, cursorChange);
				txt.addEventHandler(MouseEvent.MOUSE_EXITED, cursorReset);
				current = txt;

			}
			
			if(selectedNode.equals(lineMode)) {
				
				Line line= new Line();
				
				//Set the starting X-Y to mouse's X-Y position.
				//set the Ending X-Y to Mouse's X-Y position so it doesn't assume default value.
				line.setStartX(event.getX());
				line.setEndX(event.getX());
				line.setStartY(event.getY());
				line.setEndY(event.getY());
				
				EventHandler<MouseEvent> selectShape = action ->{
					select(line);
					
					line.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveShape);
					
				};
				
				
				//set color to selectedColor. 
				line.setStroke(selectedColor);
				
				//Add the line to the pane.
				pane.getChildren().add(line);
				
				//add event handlers to the line and set current equal to line.
				line.addEventHandler(MouseEvent.MOUSE_PRESSED, selectShape);
				line.addEventHandler(MouseEvent.MOUSE_ENTERED, cursorChange);
				line.addEventHandler(MouseEvent.MOUSE_EXITED, cursorReset);
				current = line;
				
			}
			
			
		} catch(Exception e) {
			
		}
			
		
		
	}

	@FXML
	//Mouse drag to size the shape that was just created
	void resizeShape(MouseEvent event) {
		
		//Try/Catch to handle any cast exceptions (Which shouldn't happen, but you know, better safe than sorry.)
		try {
			
			//If selectedNode is rectMode, resize the rectangle by changing its height and width.
			if(selectedNode.equals(rectMode)) {
				((Rectangle) current).setWidth(Math.abs(event.getX() - ((Rectangle) current).getX()));
				((Rectangle) current).setHeight(Math.abs(event.getY() - ((Rectangle) current).getY()));
			}
			
			//If selectNode is circleMode, resize the circle by changing its radius. 
			if(selectedNode.equals(circleMode)) {
				((Ellipse) current).setRadiusX(Math.abs(event.getX() - ((Ellipse) current).getCenterX()));
				((Ellipse) current).setRadiusY(Math.abs(event.getY() - ((Ellipse) current).getCenterY()));
			}
			
			if (selectedNode.equals(textMode)){
				double fontSize = Math.abs((event.getX() - ((Text) current).getX()) + (event.getY() - ((Text) current).getY()));
				((Text) current).setFont(new Font(fontSize));

			}
			
			if (selectedNode.equals(lineMode)){
				((Line)current).setEndX(event.getX());	
				((Line)current).setEndY(event.getY());
			}
			
		} catch(Exception e) {
			
		}
	}
	
	@FXML
	//Mouse drag to move the shape
	void moveShape(MouseEvent event) {
		
		//If selectedNode is an instance of a rectangle, move the rectangle to 
		//the mouse's X-Y position.
		if(selectedNode instanceof Rectangle) {
			((Rectangle) selectedNode).setX(event.getX());
			((Rectangle) selectedNode).setY(event.getY());
		}
		
		//If selectedNode is an instance of a Circle, move the circle's center to
		//the mouse's X-Y position.
		if(selectedNode instanceof Ellipse) {
			((Ellipse) selectedNode).setCenterX(event.getX());
			((Ellipse) selectedNode).setCenterY(event.getY());
		}
		
		//If the selectedNod is an instance of a text shape, move the text shape to the 
		//mouse's X-Y position.
		if (selectedNode instanceof Text){
			((Text) selectedNode).setX(event.getX());
			((Text) selectedNode).setY(event.getY());

		}
		
		//I could not get the line to move as per the assignment. whichever end is closest to the mouse should be the
		//End to move, but I can't get any of the approaches I take to swork.
		if (selectedNode instanceof Line){
			double[] start = {((Line) selectedNode).getLayoutX() -((Line) selectedNode).getStartX() , ((Line) selectedNode).getLayoutY() - ((Line) selectedNode).getStartY()};
			((Line) selectedNode).setTranslateX(((Line) selectedNode).getTranslateX() + event.getX() + start[0] );
			((Line) selectedNode).setTranslateY(((Line) selectedNode).getTranslateY() + event.getY()+start[1]);

			
		}
	
	}
	
	
	//Adjust tool ribbon shape colors and set color for creating shapes
	void colorChange() {
		
		//define selectedColor with rgb values from the color sliders.
		selectedColor = Color.rgb((int)redSlider.getValue(), (int)greenSlider.getValue(), (int)blueSlider.getValue());
		
		//Set the menu shapes to the selectedColor.
		menuText.setFill(selectedColor);
		menuRectangle.setStroke(selectedColor);
		menuRectangle.setFill((filledChk.isSelected())? selectedColor: Color.WHITE);
		menuCircle.setStroke(selectedColor);
		menuCircle.setFill((filledChk.isSelected())? selectedColor: Color.WHITE);
		menuLine.setStroke(selectedColor);
		
	}

	void select(Node n) {
		deselect();
		selectedNode = n;
		addBorder(n);
	}

	void addBorder(Node n) {
		try {
			n.getStyleClass().add("selected");
		} catch (Exception e) {
			//Ignore when no node is selected
		}
	}

	void deselect() {
		removeBorder(selectedNode);
		selectedNode = null;
	}

	void removeBorder(Node n) {
		try {
			n.getStyleClass().remove("selected");
		} catch (NullPointerException e) {
			//Ignore when no node is selected
		}
	}
}
