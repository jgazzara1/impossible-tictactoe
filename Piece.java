class Piece
{
	public int player; //if piece is x set state to 0, if o, set state to 1
	public loc location;

	public Piece()
	{
		player = -1;
		location = new loc();
	}
	
	public Piece(int x, int y, int s)
	{
		player = s;
		location = new loc(x,y);
	}
	
	public void setLoc(loc place)
	{
		location = place;
	}

	public loc getLoc()
	{
		return location;
	}
	
	public void setState(int player1)
	{	
		player = player1;
	}
	
	public int getState()
	{
		return player;
	}
	
	public int getx()
	{
		return location.getx1();
	}
	
	public int gety()
	{
		return location.gety1();
	}
	
	public boolean checkOpen()
	{
		if(player == -1)
			return true;
		else
			return false;

	}

}
