from swampy.TurtleWorld import *


def set_color(t: Turtle):
    if t.get_y() > 106:
        t.set_pen_color('#F44336')
    elif t.get_y() > 53:
        t.set_pen_color('#FF9800')
    elif t.get_y() > 0:
        t.set_pen_color('#FFEB3B')
    elif t.get_y() > -53:
        t.set_pen_color('#4CAF50')
    elif t.get_y() > -106:
        t.set_pen_color('#2196F3')
    else:
        t.set_pen_color('#673AB7')


def koch(t: Turtle, order, size):
    if order == 0:
        set_color(t)
        t.fd(size)
    else:
        koch(t, order - 1, size / 3)
        t.rt(85)
        koch(t, order - 1, size / 3)
        t.lt(170)
        koch(t, order - 1, size / 3)
        t.rt(85)
        koch(t, order - 1, size / 3)


if __name__ == '__main__':
    world = TurtleWorld()
    bob = Turtle()
    bob.delay = 0

    bob.x = 0
    bob.y = 160
    bob.redraw()
    bob.rt(36)
    for i in range(6):
        koch(bob, 5, 1000)
        bob.rt(60)

    bob.y = -10
    bob.heading = 90
    bob.redraw()

    world.mainloop()
