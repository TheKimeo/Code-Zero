package Controller;

public class Camera {
	public double width;
	public double height;
	
	public double x;
	public double y;
	
	public Camera(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public void setPosition(double x, double y) {
		this.x = x - width / 2.0;
		this.y = y - height / 2.0;
	}
}
