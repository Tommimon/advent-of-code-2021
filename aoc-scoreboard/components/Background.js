import React, { Component } from "react";

function getRandomSize(snow) {
  let r = snow.pow(snow.random(0, 1), 3);
  return snow.constrain(r * 32, 2, 32);
}

class Snowflake {
  constructor(snow, sx, sy, img) {
    this.snow = snow;
    let x = sx || random(width);
    let y = sy || random(-100, -10);
    this.img = img;
    this.pos = snow.createVector(x, y);
    this.vel = snow.createVector(0, 0);
    this.acc = snow.createVector();
    this.angle = snow.random(snow.TWO_PI);
    this.dir = snow.random(1) > 0.5 ? 1 : -1;
    this.xOff = 0;
    this.r = getRandomSize(snow);
  }

  applyForce(force) {
    // Parallax Effect hack
    let f = force.copy();
    f.mult(this.r);
    this.acc.add(f);
  }

  randomize() {
    let x = this.snow.random(this.snow.width);
    let y = this.snow.random(-100, -10);
    this.pos = this.snow.createVector(x, y);
    this.vel = this.snow.createVector(0, 0);
    this.acc = this.snow.createVector();
    this.r = getRandomSize(this.snow);
  }

  update() {
    this.xOff = this.snow.sin(this.angle * 2) * 2 * this.r;

    this.vel.add(this.acc);
    this.vel.limit(this.r * 0.2);

    if (this.vel.mag() < 1) {
      this.vel.normalize();
    }

    this.pos.add(this.vel);
    this.acc.mult(0);

    if (this.pos.y > this.snow.height + this.r) {
      this.randomize();
    }

    // Wrapping Left and Right
    if (this.pos.x < -this.r) {
      this.pos.x = this.snow.width + this.r;
    }
    if (this.pos.x > this.snow.width + this.r) {
      this.pos.x = -this.r;
    }

    this.angle += (this.dir * this.vel.mag()) / 200;
  }

  render() {
    this.snow.push();
    this.snow.translate(this.pos.x + this.xOff, this.pos.y);
    this.snow.rotate(this.angle);
    this.snow.imageMode(this.snow.CENTER);
    this.snow.image(this.img, 0, 0, this.r, this.r);
    this.snow.pop();
  }
}

class SketchStars extends Component {
  constructor() {
    super();
    this.renderRef = React.createRef();
    this.state = {
      x: 100,
      y: 100,
      loop: true,
    };
  }

  componentDidMount() {
    const p5 = require("p5");
    this.sketch = new p5((snow) => {
      // Code based on code by Daniel Shiffman
      // http://codingtra.in
      // http://patreon.com/codingtrain

      // Snowfall
      // Edited Video: https://youtu.be/cl-mHFCGzYk

      snow.snowlist = [];
      snow.gravity;

      snow.zOff = 0;

      snow.spritesheet;
      snow.textures = [];

      snow.preload = () => {
        snow.spritesheet = snow.loadImage("flakes32.png");
      };

      snow.setup = () => {
        snow.cnv = snow
          .createCanvas(document.body.clientWidth, snow.windowHeight)
          .parent(this.renderRef.current);

        snow.cnv.position(0, 0);
        snow.cnv.style("z-index", "-1");
        snow.cnv.style("position", "fixed");

        snow.frameRate(30);

        snow.createCanvas(snow.windowWidth, snow.windowHeight);
        snow.gravity = snow.createVector(0, 0.3);
        for (snow.x = 0; snow.x < snow.spritesheet.width; snow.x += 32) {
          for (snow.y = 0; snow.y < snow.spritesheet.height; snow.y += 32) {
            snow.img = snow.spritesheet.get(snow.x, snow.y, 32, 32);
            snow.image(snow.img, snow.x, snow.y);
            snow.textures.push(snow.img);
          }
        }

        for (snow.i = 0; snow.i < 400; snow.i++) {
          snow.x = snow.random(snow.width);
          snow.y = snow.random(snow.height);
          snow.design = snow.random(snow.textures);
          snow.snowlist.push(new Snowflake(snow, snow.x, snow.y, snow.design));
        }
      };

      snow.windowResized = () => {
        snow.resizeCanvas(document.body.clientWidth, window.innerHeight);
      };

      snow.draw = () => {
        snow.background(0);

        snow.zOff += 0.1;

        for (snow.flake of snow.snowlist) {
          snow.xOff = snow.flake.pos.x / snow.width;
          snow.yOff = snow.flake.pos.y / snow.height;
          snow.wAngle =
            snow.noise(snow.xOff, snow.yOff, snow.zOff) * snow.TWO_PI;
          snow.wind = p5.Vector.fromAngle(snow.wAngle);
          snow.wind.mult(0.1);

          snow.flake.applyForce(snow.gravity);
          snow.flake.applyForce(snow.wind);
          snow.flake.update();
          snow.flake.render();
        }
      };
    });
  }
  render() {
    return (
      <div className="nope">
        <div ref={this.renderRef}></div>
      </div>
    );
  }
}

export default SketchStars;
