package me.devjoe.frc.team6758.vision.gson;

public class VisRep extends AbstractPacket{
	public Double[][] points;
    public VisObj[] objects;

    public class VisObj
    {
        public int width;
        public int height;
        public int x;
        public int y;
    }
}
