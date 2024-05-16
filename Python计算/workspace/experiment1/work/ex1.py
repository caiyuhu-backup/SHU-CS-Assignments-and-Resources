"""This module contains code from
Think Python by Allen B. Downey
http://thinkpython.com

Copyright 2012 Allen B. Downey
License: GNU GPLv3 http://www.gnu.org/licenses/gpl.html

"""

import math

from swampy.TurtleWorld import Turtle

try:
    # see if Swampy is installed as a package
    from swampy.TurtleWorld import *
except ImportError:
    # otherwise see if the modules are on the PYTHONPATH
    from TurtleWorld import *


def square(t, length):
    """Draws a square with sides of the given length.

    Returns the Turtle to the starting position and location.
    """
    for i in range(4):
        fd(t, length)
        lt(t)


def polyline(t, n, length, angle):
    """Draws n line segments.

    t: Turtle object
    n: number of line segments
    length: length of each segment
    angle: degrees between segments
    """
    for i in range(n):
        fd(t, length)
        lt(t, angle)


def polygon(t, n, length):
    """Draws a polygon with n sides.

    t: Turtle
    n: number of sides
    length: length of each side.
    """
    angle = 360.0 / n
    polyline(t, n, length, angle)


def arc(t, r, angle):
    """Draws an arc with the given radius and angle.

    t: Turtle
    r: radius
    angle: angle subtended by the arc, in degrees
    """
    arc_length = 2 * math.pi * r * abs(angle) / 360
    n = int(arc_length / 4) + 1
    step_length = arc_length / n
    step_angle = float(angle) / n

    # making a slight left turn before starting reduces
    # the error caused by the linear approximation of the arc
    lt(t, step_angle / 2)
    polyline(t, n, step_length, step_angle)
    rt(t, step_angle / 2)


def circle(t, r):
    """Draws a circle with the given radius.

    t: Turtle
    r: radius
    """
    arc(t, r, 360)


def semicircle(t, r):
    """"
    画半圆
    """
    arc(t, r, 180)


# the following condition checks whether we are
# running as a script, in which case run the test code,
# or being imported, in which case don't.

# 初始化设置
def init():
    world = TurtleWorld()
    shawn = Turtle()
    shawn.set_pen_color('#FF9900')
    shawn.delay = 1e-8
    return world, shawn


# 流汗黄豆
def emoji():
    # 脸
    radius1 = 60
    shawn.pu()
    shawn.fd(radius1)
    shawn.lt()
    shawn.pd()
    circle(shawn, radius1)

    # 嘴
    shawn.set_pen_color('#CC6633')
    radius2 = 50
    arc1 = 15
    shawn.pu()
    shawn.lt()
    shawn.fd(radius1 - radius2)
    shawn.lt()
    shawn.pd()
    arc(shawn, radius2, -180)
    shawn.rt()
    shawn.fd(radius2 * 2)
    shawn.rt()
    shawn.pu()
    arc(shawn, radius2, -arc1)
    shawn.rt(-arc1 + 90)
    shawn.pd()
    shawn.fd(2 * radius2 * math.cos(arc1 / 180 * math.pi))
    shawn.pu()
    shawn.rt(-arc1 + 90)
    arc(shawn, radius2, -arc1)
    shawn.rt()
    shawn.fd(30)

    # 眼
    k = radius2 / 5 * 2
    radius3 = 15
    shawn.lt()
    shawn.fd(18)
    shawn.rt()
    shawn.fd(radius3)
    shawn.lt()
    shawn.pd()
    semicircle(shawn, radius3)
    shawn.lt(120)
    arc(shawn, 30, -60)
    shawn.pu()
    arc(shawn, 30, -300)
    shawn.rt(120)

    shawn.lt()
    shawn.fd(radius3 * 2 + k * 3 - 20)
    shawn.lt()
    shawn.pd()
    semicircle(shawn, radius3)
    shawn.lt(120)
    arc(shawn, 30, -60)
    shawn.pu()
    arc(shawn, 30, -300)
    shawn.rt(120)

    # 汗
    shawn.set_pen_color('#0099FF')
    shawn.lt()
    shawn.fd(30)
    shawn.lt()
    shawn.fd(40)

    arc2 = 15
    radius4 = 40
    shawn.lt(180 - arc2)
    shawn.pd()
    shawn.fd(radius4)
    shawn.lt(arc2)
    semicircle(shawn, radius4 * math.sin(math.pi * arc2 / 180))
    shawn.lt(arc2)
    shawn.fd(radius4)

    # 爬
    shawn.pu()
    shawn.fd(2000)


if __name__ == '__main__':
    world, shawn = init()
    world.setup_interactive()
    emoji()
    wait_for_user()
