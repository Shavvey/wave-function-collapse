enum Direction {
    LEFT(-1,0),
    DOWN(0,1),
    UP(0,-1),
    RIGHT(1,0);
    final int x;
    final int y;
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}