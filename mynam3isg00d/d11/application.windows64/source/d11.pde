int[][] squids = {{1,1,7,2,7,2,8,8,7,4},{6,7,5,1,4,5,4,2,8,1},{2,6,1,2,3,4,3,5,3,3},{1,8,8,4,8,7,7,5,1,1},{7,5,7,4,3,4,6,2,4,7},{2,1,1,7,4,1,3,7,4,5},{7,7,6,6,7,3,6,5,1,7},{4,3,3,1,7,8,3,4,4,4},{4,8,4,1,2,1,5,8,2,8},{6,8,5,7,7,6,6,2,7,3}};
int flash = 0; int showflash;
int g = 0; int showg;
boolean part1 = false;
boolean part2 = false;
void setup() {
  size(2000, 2000);
  frameRate(20);
}

void draw() {
  background(#0f0f23);
  println(squids[0][2]);
  //Draw it.
  for(int i=0; i<squids.length; i++) {
    for(int j=0; j<squids[0].length; j++) {
      fill(255, 255, 255, calcVal(squids[j][i]) * 255); noStroke();
      circle((i+0.5) * width/squids.length, (j+0.5) * height/squids.length, calcVal(squids[j][i]) * 120);
    }
  }
  
  //Update it.
  for (int i = 0; i < squids.length; i++) {
    for (int j = 0; j < squids[0].length; j++) {
      flash += update(i, j);
    }
  }
  
  boolean found = true;
  for (int i = 0; i < squids.length; i++) {
    for (int j = 0; j < squids[0].length; j++) {
      if (squids[i][j] == -1) {
        squids[i][j] = 0;
      } else {
        found = false;
      }
    }
  }
  
  if(found) part2 = true;
  if(g == 100) part1 = true;
  g++;
  
  int tAlp1, tAlp2;
  tAlp1 = tAlp2 = 100;
  textSize(300); textAlign(CENTER);
  if(!part1) {
    showflash = flash;
    tAlp1 = 50;
  }
  fill(#9999cc, tAlp1);
  text("Part 1: " + showflash, width/2, height/3);
  
  textSize(300); textAlign(CENTER);
  if(!part2) {
    showg = g+1;
    tAlp2 = 50;
  }
  fill(#ffff66, tAlp2);
  text("Part 2: " + showg, width/2, 2*height/3);
}

public float calcVal(float i) {
  if (i == 0) return 1.0;
  return (i / 10);
}

public int update(int i, int j) {
  int flash;
  if(squids[i][j] == -1) {
    //The square is locked, do not update;
    return 0;
  }
  if(squids[i][j] < 9) {
    squids[i][j]++;
    return 0;
  } else {
    flash = 1;
    squids[i][j] = -1;
    for(int k1 = Math.max(0, i-1); k1 < Math.min(i+2, squids.length); k1++) {
      for(int k2 = Math.max(0, j-1); k2 < Math.min(j+2, squids[0].length); k2++) {
        flash += update(k1, k2);
      }
    }
    return flash;
  }
}
