package cse110.models;

/**
 * Responsible for giving specified dimensions to view 
 * elements in the Application
 *
 */
public class ViewDimensions {
	private int width, height, margin;
	
	public ViewDimensions(int width, int height, int margin) {
		setWidth(width);
		setHeight(height);
		setMargin(margin);
	}

	// Getter functions
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMargin() {
		return margin;
	}
	
	// Setter functions
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setMargin(int margin) {
		this.margin = margin;
	}
	
}
